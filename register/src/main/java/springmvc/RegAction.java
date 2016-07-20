package springmvc;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import java.util.HashMap;

public class RegAction extends SimpleFormController {

	private String error_view;
	private String success_view;
	private String errorMsg;

	protected ModelAndView onSubmit(Object cmd, BindException ex) throws Exception {
		RegInfo regInfo = (RegInfo) cmd;
		System.out.println(regInfo.getName());
		if (isEntity(regInfo)) {
			// --先写进数据库里面
			RegInfo oneUser = new RegInfo();
			oneUser.setId(regInfo.getId());
			oneUser.setName(regInfo.getName());
			oneUser.setPassword(regInfo.getPassword());
			oneUser.setSex(regInfo.getSex());
			oneUser.setTel(regInfo.getTel());
			oneUser.setAddress(regInfo.getAddress());
			DBInterface.getSession().insert("userMapper.insertUser", oneUser);
			DBInterface.getSession().commit();

			// --再返回页面
			HashMap hm = new HashMap();
			hm.put("successMsg", regInfo);
			return new ModelAndView(this.getSuccess_view(), hm);
		} else {
			errorMsg = "注册失败";
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("errorMsg", errorMsg);
			return new ModelAndView(this.getError_view(), map);
		}
	}

	private boolean isEntity(RegInfo regInfo) {
		if (regInfo.getName() != null && !regInfo.getName().trim().equals("") && regInfo.getPassword() != null
				&& !regInfo.getPassword().trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public String getError_view() {
		return error_view;
	}

	public void setError_view(String error_view) {
		this.error_view = error_view;
	}

	public String getSuccess_view() {
		return success_view;
	}

	public void setSuccess_view(String success_view) {
		this.success_view = success_view;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
