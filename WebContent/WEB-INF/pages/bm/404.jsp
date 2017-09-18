<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>错误提示页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/statu.css"> 
    <script type="text/javascript" src="js/jquery-1.7.1.js"></script>
	 <script type="text/javascript">
	    $(function(){
	        $('a.close,#sure,#cancel').click(function(){
	            $('#messageBg').fadeOut('slow');
	        });

	        var a=document.body.clientWidth;  //取得iframe框架的实际宽度
	        var b = document.body.clientHeight-12; 
	        document.getElementById("section").style.width=a+"px"; 
	        document.getElementById("box").style.height=b+"px";
	    });
	
	</script>

  </head>
  
  <body>   
      <div id="section">   
  		  <div id="messageBg"> 
	  		<div id="box"  style="margin-top:12px;"></div>
	   		  <div class="main">
	       	    <div id="title"><span>提示信息</span></div>
	              <div id="content">
	                <div id="contentTop"><img src="images/statu/Stop.png">
	                   <p>
	                     	访问的资源不存在 
				       </p>
	                </div>
	                <div id="button"> <input type="button" value="返回" onclick="history.go(-1);" id="sure"/></div>
	              </div>
	   		  </div>
        </div>
        </div>
  </body>
</html>