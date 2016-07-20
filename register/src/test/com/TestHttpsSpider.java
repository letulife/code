import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by chenxidu on 7/20/16.
 */
public class TestHttpsSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        List<String> list  = page.getHtml().links().regex(("https://www\\.taobao\\.com/\\w+/\\w+/.*\\.*")).all();

        if(list.size() >0){
            System.out.println(list.toString());

            //将二级页面放入到目标链接中
            page.addTargetRequests(list);
        }

        //再去这个二级页面上找出物品id链接
        String url = page.getUrl().toString();


        System.out.println(page.getHtml().toString());
        List<String> itemList = page.getHtml().xpath(("//textarea[@class='J_dynamic_data']/tidyText()")).all();
        if(itemList.size()>0){
            int itemSize = itemList.size();
            for (int i = 0;i < itemSize;++i){
                page.putField("iteminfo",itemList.get(i));
                page.setSkip(false);
            }

        }


        int a = 0;

        //再去页面中查找相应的标签,也就是在页面中去找到每个商品的详细信息
        //List<String> itemUrlList = page.getHtml().xpath().toString();
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String args[]){
        Spider.create(new TestHttpsSpider())
                .addUrl("https://www.taobao.com")
                .addPipeline( new FilePipeline("/Users/chenxidu/work"))
                .thread(500)
                .run();
    }
}
