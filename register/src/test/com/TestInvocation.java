import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by chenxidu on 7/12/16.
 */
public class TestInvocation implements InvocationHandler
{

    private Object target;


    //植入的代码
    private  void beforeMethod(Method method)
    {
        System.out.println(method.getName()+" start " + new Date());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        beforeMethod(method);

        //--再执行被代理的方法
        method.invoke(target,args);

        return null;
    }

    public Object getTarget()
    {
        return target;
    }

    public  void setTarget(Object target)
    {
        this.target = target;
    }


}
