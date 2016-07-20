package springmvc;

/**
 * Created by chenxidu on 7/12/16.
 */
public interface UserDao
{
    public void save(User user);

    //--是不是每个方法都被实现了代理

    public void load();

}
