<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注册成功</title>
  </head>
  
  <body bgcolor="#ffffff">
   注册成功，您提交的注册信息如下：<br/>
   用户名：<c:out value="${successMsg.name}"></c:out><br/>
   密码：<c:out value="${successMsg.password}"></c:out><br/>
   性别：<c:out value="${successMsg.sex}"></c:out><br/>
   电话：<c:out value="${successMsg.tel}"></c:out><br/>
   地址：<c:out value="${successMsg.address}"></c:out><br/>
  <hr>
  基于spring MVC实现
  </body>
</html>
