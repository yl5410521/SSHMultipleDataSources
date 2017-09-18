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
<title>添加基础类型</title>
</head>
<body>

	<div class="pageContent">
		<form method="post" action="bm/content!add.action"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<p>
					<label>数据字段属性：</label> <input name="content.contentType" class="required"
						type="text" size="30" value="" alt="数据库字段属性" />
				</p>
				<p>
					<label>实体属性类型：</label> <input name="content.fieldsType"
						class="required alphanumeric" 
						type="text"  value="" alt=" 实体类字段类型" />
				</p>
				<p>
					<label>别称：</label> <input name="content.name"
						class="required alphanumeric"  value="" alt="文本框" type="text" />
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