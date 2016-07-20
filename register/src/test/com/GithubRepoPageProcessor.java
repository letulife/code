import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * Created by chenxidu on 7/19/16.
 */
public class GithubRepoPageProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {

        //\w 是匹配所有的字母和数字,以及下划线
        //<a href="/code4craft/jsoup-learning" class="mini-repo-list-item css-truncate">
        //这个链接上面的 /表示当前路径,所以也是可以匹配进来的
        //"(https://github\\.com/\\w+/\\w+\\-\\w+)" 这种里面就是取 https://github.com/ipiaoniu/piaoniu-api 这种格式的
        //"(https://github\\.com/\\w+/\\w+\\)" 这种里面就是取 https://github.com/ipiaoniu/piaoniu这种格式的,会把后面的 -api 截断
        List<String> list = page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all();
        page.addTargetRequests(list);
        System.out.println(list.toString());

        // /.* 表示后面的任何字符串匹配
        Selectable url = page.getUrl();
        String s1 = url.regex("https://github\\.com/(\\w+)/.*").toString();
        page.putField("author", s1);

        String s2 = page.getHtml().xpath("//[@class='site-header-menu']/nav/a/tidyText()").toString();
        if(s2 != null){
            System.out.println(s2);
        }
        page.putField("name",s2 );


        if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }

        String s3 = page.getHtml().xpath("//div[@id='readme']/tidyText()").toString();
        page.putField("readme", s3);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new GithubRepoPageProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://github.com/code4craft")
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }

}
