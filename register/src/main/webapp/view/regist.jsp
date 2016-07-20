<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body bgcolor="#ffffff">
    <h3>您没有该账号,请注册一个吧</h3>
    <form action="regAction.do" method="post">
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
    			<td>性别：</td>
    			<td>
    				<select name="sex" size="1">
    					<option value="boy">boy</option>
    					<option value="girl">girl</option>
    				</select>
    			</td>
    		</tr>
    		
    		<tr>
    			<td>联系电话：</td>
    			<td><input type="text" name="tel"/></td>
    		</tr>
    		
    		<tr>
    			<td>联系地址：</td>
    			<td><input type="text" name="address"/></td>
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
