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
<script>
	$(function() {
		var did = $("#departmentid").val();
		$("option[value=" + did + "]").attr("selected", true);
		var rid = $("#roleid").val();
		$("option[value=" + rid + "]").attr("selected", true);
	});
</script>
<title>修改用户</title>
</head>
<body>

	<div class="pageContent">
		<form method="post" action="bm/admin!add.action"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<p>
					<label>用户名：</label> <input name="admin.userName" class="required"
						type="text" size="30" value="${admin.userName}" alt="请输入用户名" />
				</p>
				<p>
					<label>密码：</label> <input name="admin.password"
						class="required alphanumeric" minlength="6" maxlength="20"
						type="password" size="30" value="" alt="请输入密码" value="${admin.password}" />
				</p>
				<p>
					<label>重复密码：</label> <input name="doublepwd"
						class="required alphanumeric" minlength="6" maxlength="20"
						type="password" size="30" value="" alt="请再次输入密码" value="${admin.password}"/>
				</p>
				<p>
					<label>姓名：</label> <input name="admin.name" class="required" value="${admin.name}"
						type="text" size="30" value="" alt="请输入姓名"/>
				</p>
				<p>
					<label>电话：</label> <input name="admin.mobile" class="phone"
						type="text" size="30" alt="请输入电话"  value="${admin.mobile}"/>
				</p>
				<p>
					<label>地址：</label> <input name="admin.address" class="required"
						type="text"  alt="请输入地址" value="${admin.address}" />
				</p>
				<p>
					<label>部门：</label> 
					<input type="hidden" value="${admin.department.id}" id="departmentid">
					<select name="admin.department.id">
						<option value="">请选择</option>
						<c:forEach var="department" items="${departments}">
						<option value="${department.id}">${department.name}</option>
						</c:forEach>
					</select>
				</p>
				<p>
					<label>角色：</label> 
					<input type="hidden" value="${admin.role.id}" id="roleid"/>
					<select name="admin.role.id">
						<option value="">请选择</option>
							<c:forEach var="role" items="${roles}">
						<option value="${role.id}">${role.name}</option>
						</c:forEach>
					</select>
				</p>

			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">保存</button>
							</div>
						</div></li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="button" class="close">取消</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>

</body>

</html>