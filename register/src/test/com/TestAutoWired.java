import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import springmvc.Bao;

/**
 * Created by chenxidu on 7/8/16.
 */
public class TestAutoWired
{
    public static  void main(String[] args)
    {
        System.out.println(TestIoc.class.getResource("/").getFile().toString());
        ApplicationContext ctx = new FileSystemXmlApplicationContext("dispatcherServlet-servlet.xml");
        Bao bao = (Bao) ctx.getBean("bao");
        if(bao != null)
        {
            System.out.println(bao.getReginfo());
        }

    }

}
