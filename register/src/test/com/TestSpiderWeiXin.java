import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxidu on 7/19/16.
 */
public class TestSpiderWeiXin implements PageProcessor {
    protected static final Logger LOGGER = LoggerFactory.getLogger(TestSpider.class);
    private Site site = Site.me().setCharset("UTF-8").setRetryTimes(3).setSleepTime(100);

    private  int a = 0;

    @Override
    public void process(Page page) {

        String mainUrl = page.getUrl().toString();

        //特殊符号要用\\,例如 \\?

        //直接从html 中找到url 地址的特征
        List<String> list = page.getHtml().links().regex("(http://mp\\.weixin\\.qq\\.com/s\\?src=3.*)").all();

        //从规律中匹配出更准确的链接地址 src=3&timestamp= ,要从中找出正则的使用方法
        List<String> list1 = page.getHtml().links().regex("(http://mp\\.weixin\\.qq\\.com/s\\?src=3\\&timestamp\\=.*)").all();

        //也可以从网页中提取 url 地址,只要努力,就能搞定
        List<String> list2 = page.getHtml().xpath("//div[@class='wx-news-info2']/h4/a/@href").all();

        System.out.println(list2.size());

        //把这些列表也装进
        page.addTargetRequests(list2);

        //依次查询每个页面的值
        String url = page.getUrl().toString();
        System.out.println(url);

        //打印出整个页面的内容
        System.out.println(page.getHtml().toString());

        //然后获取每个页面的具体内容
        String pageContent = page.getHtml().xpath("//body[@id='activity-detail']/tidyText()").toString();

        //只把有内容的取出来
        if(pageContent != null && pageContent.length() > 0){
            page.putField("pageContent", pageContent);
            page.setSkip(false);
        }else{
            System.out.println(url);
        }

        a = a + 1;

        System.out.println(a);


    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args){

        List<String> list = new ArrayList<String>();
        list.add("http://weixin.sogou.com/pcindex/pc/pc_3/pc_3.html");
        list.add("http://weixin.sogou.com/pcindex/pc/pc_3/1.html");
        list.add("http://weixin.sogou.com/pcindex/pc/pc_3/2.html");
        list.add("http://weixin.sogou.com/pcindex/pc/pc_3/3.html");
        list.add("http://weixin.sogou.com/pcindex/pc/pc_3/4.html");

        Spider.create(new TestSpiderWeiXin())
                .addUrl(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(4))
                .addPipeline(new FilePipeline("/Users/chenxidu/work"))
                //开启5个线程抓取
                .thread(1)
                //启动爬虫
                .run();
    }


    /*
    这里不要采用 JsonFilePipeline,会在 PrintWriter 那里产生数组越界的问题
     */

}
