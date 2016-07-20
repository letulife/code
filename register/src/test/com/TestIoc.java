
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import springmvc.RegInfo;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;

/**
 * Created by chenxidu on 7/7/16.
 */
public class TestIoc
{
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        TestIoc test = new TestIoc();
        test.getNameTest();
        System.out.println("");
        test.getModuleTest();
        test.testBean();

    }

    public void getNameTest() throws ClassNotFoundException
    {
        System.out.println("===============begin getNameTest================");
        String name = "amiguo";
        Class cls = name.getClass();
        System.out.println("String 类名" + cls.getName());

        Button btn = new Button();
        Class btnClass = btn.getClass();
        System.out.println("Button 类名" + btn.getName());

        Class superBtnClass = btnClass.getSuperclass();
        System.out.println("Button 父类名" + superBtnClass.getName());

        Class clsTest = Class.forName("java.awt.Button");
        System.out.println("clsTest name: " + clsTest.getName());

        System.out.println("===============end getNameTest================");
    }

    public void getModuleTest() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        System.out.println("===============begin getModuleTest================");
        Class cls = Class.forName("TestIoc");
        Class pytypes[] = new Class[2];
        pytypes[0] = Class.forName("java.lang.String");
        pytypes[1] = Class.forName("java.util.Hashtable");
        Method method = cls.getMethod("testMethod", pytypes);

        Method[] m = cls.getMethods();
        if (m != null && m.length > 0)
        {
            for (int i = 0; i < m.length; ++i)
            {
                if (m[i].getName().equals("testMethod"))
                {

                }
            }
        }

        Object args[] = new Object[2];
        args[0] = "hello, my dear!";
        Hashtable<String, String> ht = new Hashtable<String, String>();
        ht.put("name", "阿蜜果");
        args[1] = ht;

        String returnStr = (String) method.invoke(new TestIoc(), args);
        System.out.println("returnStr= " + returnStr);
        System.out.println("===========end getMethodTest==========");

    }

    public String testMethod(String str, Hashtable ht) throws Exception
    {
        String returnStr = "返回值";
        System.out.println("测试testMethod()方法调用");
        System.out.println("str= " + str);
        System.out.println("名字= " + (String) ht.get("name"));
        System.out.println("结束testMethod()方法调用");
        return returnStr;

    }

    //--测试bean
    public void testBean()
    {
        System.out.println(TestIoc.class.getResource("/").getFile().toString());
        ApplicationContext context = new FileSystemXmlApplicationContext("dispatcherServlet-servlet.xml");
        if(context != null)
        {
            RegInfo animal = (RegInfo) context.getBean("regin");
            if(animal != null)
            {
                System.out.println(animal.toString());
            }

        }

    }


}