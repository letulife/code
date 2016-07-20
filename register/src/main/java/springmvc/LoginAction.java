package springmvc;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class LoginAction extends SimpleFormController
{
    private String error_view;
    private String success_view;
    private String register_view;

    protected ModelAndView onSubmit(HttpServletRequest request,
                                    HttpServletResponse response,Object cmd, BindException ex) throws Exception
    {
        RegInfo person=(RegInfo)cmd;
        HashMap<String,Object> map=new HashMap<String,Object>();
        ModelAndView result=new ModelAndView();
        String page;

        int ret = check(person);
        if(ret == 0)
        {
            map.put("successMsg", person);
            page=getSuccess_view();

            //--保存在cookie
            request.setCharacterEncoding("utf-8");
            Cookie login_info=new Cookie("chen",person.getName()+"#"+ person.getPassword()+"#" +new java.util.Date().toLocaleString());
            //--设置cookie有效期为1天
            login_info.setMaxAge(60*60*24*1);
            response.addCookie(login_info);
        }
        else if(ret == -1)
        {
            map.put("errorMsg", "login failed, please register a account");
            page=getRegister();
        }
        else
        {
            map.put("errorMsg", "login failed, please make sure your username and password right.");
            page=getError_view();
        }


        result.setViewName(page);
        result.addAllObjects(map);
        return result;
    }

    private int  check(RegInfo person)
    {
        //--打印出执行路径,一般是在target目录下面
        System.out.println(getClass().getResource("/").getFile().toString());

        //--用户名和密码验证
        String name = person.getName();
        String pass = person.getPassword();

        String statement = "userMapper.getUserByName";//映射sql的标识字符串
        RegInfo user = DBInterface.getSession().selectOne(statement,name);
        if(user == null)
        {
            //--没有这个账号,需要注册
            System.out.println("can not find user by name " + name);
            return -1;
        }

        //--登录成功,不需要注册
        if(user.getPassword().equals(pass))
        {
            System.out.println("success find user by name " + name);

            person.setSex(user.getSex());
            person.setTel(user.getTel());
            person.setAddress(user.getAddress());
            return  0;
        }
        else
        {
            //--登录失败,密码错误
            System.out.println("pass error " + name);
            return -2;
        }
    }
    public LoginAction()
    {

    }

    public String getError_view()
    {
        return error_view;
    }

    public void setError_view(String error_view)
    {
        this.error_view = error_view;
        logger.info("error:"+error_view);
    }

    public String getSuccess_view()
    {
        return success_view;
    }

    public void setSuccess_view(String success_view)
    {
        this.success_view = success_view;
        logger.info("success:"+success_view);
    }

    public String getRegister() {
        return register_view;
    }

    public void setRegister_view(String register)
    {
        this.register_view = register;
    }

}
