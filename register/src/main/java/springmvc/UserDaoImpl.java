package springmvc;

/**
 * Created by chenxidu on 7/12/16.
 */
public class UserDaoImpl implements UserDao
{

    @Override
    public void save(User user)
    {
        System.out.println("start dao save");
    }

    @Override
    public void load()
    {
        System.out.println("every method can be used by  proxy");
    }
}
