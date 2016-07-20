package springmvc;

/**
 * Created by chenxidu on 7/12/16.
 */
public class TestFanType<T> extends User
{
    private String name ;
    private T object;

    public  <T> T getTarget(T t)
    {
        return t;
    }



}
