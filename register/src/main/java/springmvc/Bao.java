package springmvc;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by chenxidu on 7/8/16.
 */
public class Bao
{
    @Autowired
    RegInfo regin;

    public String getReginfo()
    {
        return regin.toString();
    }

}
