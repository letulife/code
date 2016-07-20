<%@ page language="java" import="java.util.*" import="springmvc.RegInfo" pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder" %>
<%@ page import="java.io.PrintWriter" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录成功</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
  登录成功，您的账号信息如下：<br/>
  <%
      RegInfo user = (RegInfo) request.getAttribute("chen");
      if(user != null )
      {
          System.out.println(user.toString());

          PrintWriter put = response.getWriter();
          put.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
          put.println("<html>");
          put.println("<head>");
          put.println("<title>登录成功</title>");
          put.println("<body>");
          put.println("<h3>" + "您已经成功登录过,不需要再走登录验证流程" + "</h1>");
          put.println("<h3>" + "用户名：" + user.getName()+ "</h1>");
          put.println("<h3>" + "密码：" + user.getPassword()+ "</h1>");
          put.println("<h3>" + "性别：" + user.getSex()+ "</h1>");
          put.println("<h3>" + "电话：" + user.getTel()+ "</h1>");
          put.println("<h3>" + "地址：" + user.getAddress()+ "</h1>");
          put.println("</body>");
          put.println("</head>");

          put.println("</html>");
          put.close();
      }
      else
      {
          %>
  用户名：<c:out value="${successMsg.name}"></c:out><br/>
  密码：<c:out value="${successMsg.password}"></c:out><br/>
  性别：<c:out value="${successMsg.sex}"></c:out><br/>
  电话：<c:out value="${successMsg.tel}"></c:out><br/>
  地址：<c:out value="${successMsg.address}"></c:out><br/>
  <hr>
  <%
      }
  %>

  基于spring MVC实现
    </body>
</html>
