<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台系统登录</title>
  <link rel="stylesheet" type="text/css" href="css/bm/login/skin.css" />
  <script src="js/bm/jquery-1.11.3.js" type="text/javascript"></script>
  <script type="text/javascript">
	$(document).ready(function() {
		   $("#pro_btn").bind('click', function(e) {
			   var username = $("#username").val();
				var password = $("#password").val();
				$.ajax({
					url : "bm/admin!login.action",
					type : "POST",
					data : {
						"admin.userName" : username,
						"admin.password" : password
					},
					dataType : "json",
					success : function(data) {
	 				if(data.status=="success"){
	 					window.location.href=data.message;
	 				}
						if(data.status=="error"){
							alert(data.message);
						}
					},
				});
			});
		});
  </script>
</head>
 <body>
<form action="bm/admin!login.action" method="post">
        <table width="100%">
            <!-- 顶部部分 -->
            <tr height="41"><td colspan="2" background="images/bm/login/login_top_bg.gif">&nbsp;</td></tr>
            <!-- 主体部分 -->
            <tr style="background:url(images/bm/login/login_bg.jpg) repeat-x;" height="532">
                <!-- 主体左部分 -->
                <td id="left_cont">
                    <table width="100%" height="100%">
                        <tr height="155"><td colspan="2">&nbsp;</td></tr>
                        <tr>
                            <td width="20%" rowspan="2">&nbsp;</td>
                            <td width="60%">
                                <table width="100%">
                                    <tr height="70"><td align="right"><img src="images/bm/login/logo.gif" title="A_One科技" alt="A_One科技" /></td></tr>
                                    <tr height="274">
                                        <td valign="top" align="right">
                                            <ul>
                                                <li>1- 快速建立开发模型...</li>
                                                <li>2- 方便快捷的管理...</li>
                                                <li>3- 强大的后台系统，管理内容易如反掌...</li>
                                                <li><img src="images/bm/login/icon_demo.gif" />&nbsp;<a href="javascript:void(0)">使用说明</a>&nbsp;&nbsp;<span> <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=378087842&site=qq&menu=yes" onfocus="this.blur()"><img border="0" src="http://wpa.qq.com/pa?p=2:378087842:41" alt="A_One为您服务" title="A_one"></a> </span></li>
                                            </ul>
                                        </td>
                                    </tr>
                                </table>
                            <td width="15%" rowspan="2">&nbsp;</td>
                            </td>
                        </tr>
                        <tr><td colspan="2">&nbsp;</td></tr>
                    </table>
                </td>
                <!-- 主体右部分 -->
                <td id="right_cont">
                    <table height="100%">
                        <tr height="30%"><td colspan="3">&nbsp;</td></tr>
                        <tr>
                            <td width="30%" rowspan="5">&nbsp;</td>
                            <td valign="top" id="form">
                                <form action="bm/admin!login.action" method="post">
                                    <table valign="top" width="50%">
                                        <tr><td colspan="2"><h4 style="letter-spacing:1px;font-size:16px;">Alien 网站管理后台</h4></td></tr>
                                        <tr><td>管理员：</td><td><input type="text" name="admin.userName" value="admin" id="username"/></td></tr>
                                        <tr><td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td><td><input type="text" name="admin.password" value="123456" id="password"/></td></tr>
                                        <!-- <tr><td>验证码：</td><td><input type="text" name="" value="" style="width:80px;"/></td></tr> -->
                                        <tr class="bt" align="center"><td>&nbsp;<input type="button" value="登录" id="pro_btn" /></td><td>&nbsp;<input type="reset" value="重填" /></td></tr>
                                    </table>
                                </form>
                            </td>
                            <td rowspan="5">&nbsp;</td>
                        </tr>
                        <tr><td colspan="3">&nbsp;</td></tr>
                    </table>
                </td>
            </tr>
            <!-- 底部部分 -->
            <tr id="login_bot"><td colspan="2"><p>Copyright © 2011-2012 Alien-A_One创作</p></td></tr>
        </table>
        </form>
    </body>
<!-- <body>
后台！
<br/>
<form action="admin!login.action" method="post">
用户名:<input type="text" name="admin.username"  value="aaaaaa"><br/>
密&nbsp;码<input type="password" name="admin.password" value="20"><br/>
<input type="submit" value="登录"> 
</form>

</body>-->
</html>