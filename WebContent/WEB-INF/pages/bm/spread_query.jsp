<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看数据表</title>
</head>
<body>

<div class="pageContent">
	<form method="post" action="demo/common/ajaxDone.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>数据表名：</label>
				<input name="sn" type="text" size="30" value="${spreadSheets.tableName}" readonly="readonly"/>
			</p>
			<p>
				<label>实体类名：</label>
				<input name="name" class="required" type="text" size="30" value="${spreadSheets.className}"  readonly="readonly"/>
			</p>
			<p>
				
			</p>
			
	
		
	
			<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th>别称</th>
				<th>属性名</th>
				<th>字段名</th>
				<th>长度</th>
				<th>创建时间</th>
				<th>修改时间</th>
			
			</tr>
		</thead>
		<tbody>
		<c:forEach var="fields" items="${fields}">
			<tr target="sid_user" rel="${fields.id}">
				<td>${fields.name}</td>
				<td>${fields.fieldName}</td>
				<td>${fields.fieldtype}</td>
				<td>${fields.fieldLength}</td>
				<td><fmt:formatDate value="${fields.createDate}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
				<td><fmt:formatDate value="${fields.modifyDate}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
			
		
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>

</body>
</html>