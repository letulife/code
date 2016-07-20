package springmvc;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenxidu on 7/2/16.
 */

public class Index implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("chen")) {
					String o_name = "";
					String o_pass = "";
					String o_data = "";

					o_name = cookies[i].getValue().split("#")[0];// 获取用户名
					o_pass = cookies[i].getValue().split("#")[1];
					o_data = cookies[i].getValue().split("#")[2];

					// --还是要去数据库验证一下
					if (!"".equals(o_name) && !"".equals(o_pass)) {
						String statement = "userMapper.getUserByName";// 映射sql的标识字符串
						RegInfo user = DBInterface.getSession().selectOne(statement, o_name);
						if (user != null && user.getPassword().equals(o_pass)) {
							// --该玩家已经在cookie的有效时间内登录过,直接进入到角色信息界面
							request.setAttribute("chen", user);
							request.getRequestDispatcher("./view/loginSuccess.jsp").forward(request, response);
							return null;
						} else {
							// --从登录界面走验证流程吧
							request.getRequestDispatcher("login.jsp").forward(request, response);
							return null;
						}
					} else {
						// --从登录界面走验证流程吧
						request.getRequestDispatcher("login.jsp").forward(request, response);
						return null;
					}
				}
			}
		} else {
			// --从登录界面走验证流程吧
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return null;
		}

		request.getRequestDispatcher("login.jsp").forward(request, response);
		return null;
	}
}
