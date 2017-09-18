<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据库类型基础数据添加</title>
</head>
<body>
 <form action="content!add.action" method="post">
 数据库字段属性:<input type="text" name="content.contentType"><br>
 实体类字段类型:<input type="text" name="content.fieldType"><br/>
 别称：<input type="text" name="content.name"><br/>
 <input type="submit" name="提交"/>
 </form>
</body>
</html>