import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * Created by chenxidu on 7/18/16.
 */
public class TestSpider implements PageProcessor {
    protected static final Logger LOGGER = LoggerFactory.getLogger(TestSpider.class);
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {

        List<String> list = page.getHtml().links().regex("(http://www\\.wineim\\.com/\\w+\\.html)").all();
        System.out.println(list.toString());
        page.addTargetRequests(list);

        Selectable con_page = page.getUrl();
        page.putField("author", con_page);

        String name = page.getHtml().xpath("//div[@class='main']/tidyText()").toString();
        if(name != null){
            System.out.println(name);
        }
        page.putField("name", name);

        if (page.getResultItems().get("name")==null){
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='index_right']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args){

        Spider.create(new TestSpider())
                .addUrl("http://www.wineim.com")
                .addPipeline(new FilePipeline("/Users/chenxidu/work"))
                //开启5个线程抓取
                .thread(50)
                //启动爬虫
                .run();
    }


}
