package springmvc;

/**
 * Created by chenxidu on 7/12/16.
 */
public class UserDaoInstance
{
    //--这是一个类,
    public void save(User user)
    {
        System.out.println("XXXX start dao save");
    }

    public void load()
    {
        System.out.println("XXXX every method can be used by  proxy");
    }
}
