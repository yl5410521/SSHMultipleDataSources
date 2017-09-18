<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加数据表</title>
<script type="text/javascript">
  var index = 1;
  function add() {
    var addstep = index + 1;
    $("#addtest p:last").after("<div id='did" + index + "'>"+
			"<p>"+
			"<label>字段名：</label>"+
			"<input type='text' class='required' name='fieldNames' value='' suggestFields='orgNum,orgName' suggestUrl='demo/database/db_lookupSuggest.html' lookupGroup='orgLookup' />"+
		"</p>"+
		"<p>"+
		"<label>名称：</label>"+
		"<input type='text'  value='' name='name' class='textInput'>"+
	"</p>"+"<p>"+"</p>"+
	"<p>"+
	"<label>字段类型：</label>"+
	"<select name='contentids'>"+
	"<option value=''>"+'-----------请选择-----------'+"</option>"+
	"<c:forEach var='content' items='${contents}'>"+
	"<option value='${content.id}'>"+'${content.name}'+"</option>"+
	"</c:forEach>"+
"</select>"+
"</p>"+
"<p>"+
"<label>长度：</label>"+
"<input name='fieldlength' class='digits' type='text' alt='请输入数字'/>"+
"</p>"+"</div>");
    index += 1;
  }
  
  function adds() {
	    var addstep = index + 1;
	    $("#addtest div:last").after("<p>"+"</p>"+
						"<p>"+
		"<label>选择外键：</label>"+
		"<select name='subordinateTable'>"+
		"<option value=''>"+'-----------请选择-----------'+"</option>"+
		"<c:forEach var='sheets' items='${sheets}'>"+
		"<option value='${sheets.tableName}'>"+'${sheets.className}'+"</option>"+
		"</c:forEach>"+
	"</select>"+
	"</p>");
	    index += 1;
	  }
</script>
</head>
<body>

<div class="pageContent">
	<form method="post" action="bm/spread!add.action" class="pageForm required-validate">
		<div class="pageFormContent" layoutH="56" id="addtest">
			<p>
				<label>实体类名：</label>
				<input name="spreadSheets.className" class="required" type="text"  value="" alt="请输入实体类名称"/>
							</p>
							<p>
				<label>属性名：</label>
				<input name="spreadSheets.name" class="required" type="text"  value="" alt="请输入属性名称"/>
							</p>
				<p>
			<button type="button" onclick="add()">新增属性</button>
			<button type="button" onclick="adds();this.disabled='disabled'">添加外键</button>
			</p>
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