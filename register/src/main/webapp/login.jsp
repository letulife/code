<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body bgcolor="#ffffff">
    <h3>用户登录(账号信息会写入cookie)</h3>
    <form action="loginAction.do" method="post">
    	<table width="200" border="0" cellspacing="0">
    		<tr>
    			<td>用户名：</td>
    			<td><input type="text" name="name"/></td>
    		</tr>
    		
    		<tr>
    			<td>密码：</td>
    			<td><input type="text" name="password"/></td>
    		</tr>
    		<tr>
    			<td colspan="2"><input type="submit" name="Submit" value="提交"/></td>
    		</tr>
    	</table>
    </form>
    <hr>
    基于Spring MVC实现
  </body>
</html>
