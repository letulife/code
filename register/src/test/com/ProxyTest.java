import springmvc.User;
import springmvc.UserDao;
import springmvc.UserDaoImpl;
import springmvc.UserDaoInstance;

import java.lang.reflect.Proxy;

/**
 * Created by chenxidu on 7/12/16.
 */
public class ProxyTest
{
    public static  void main(String[] args)
    {
        {
            UserDao userDao   =  new UserDaoImpl();
            TestInvocation li = new TestInvocation();
            li.setTarget(userDao);

            UserDao userDaoProxy = (UserDao) Proxy.newProxyInstance(userDao.getClass().getClassLoader(),new Class[]{UserDao.class},li);
            userDaoProxy.save(new User());
            userDaoProxy.load();

            System.out.println(userDaoProxy.getClass().getName());
        }


        {
            UserDaoInstance userDao = new UserDaoInstance();
            TestInvocation li =  new TestInvocation();
            li.setTarget(userDao);

            //--第二个参数必须是接口
            UserDaoInstance userDaoProxy = (UserDaoInstance)Proxy.newProxyInstance(userDao.getClass().getClassLoader(),new Class[]{UserDaoInstance.class}
            ,li);

            userDaoProxy.save(new User());
            userDaoProxy.load();
        }

    }

}
