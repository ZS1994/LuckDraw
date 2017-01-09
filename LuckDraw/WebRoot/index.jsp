<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.zs.util.Token"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	session.setAttribute("token",Token.getToken());
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>抽奖</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=path %>/jquery-easyui/themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/jquery-easyui/demo/demo.css">
	<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/jquery-easyui/jquery.easyui.min.js"></script>
	
	<script type="text/javascript">
	$(function(){
		/*
		show_hint();
		setTimeout("$('#hint_modal').window('closed');",3000);//延时3秒
		*/ 
	});
	</script>
  </head>
  
  <body  style="background-image:url(1.png);background-size: 100% 100%;">
		 
	 
	 
	<div style="border:0px solid blue;">   
	<div style="text-align: center;font-size: 70px;font-weight: bold;font-family: 隶书;">
		   深圳韵达网络大会抽奖活动
	</div>
	<form action="draw" method="post">
		<CENTER style="margin-top: 30px;">
			<input type="hidden" name="token" value="${token }"/>
	 		<input type="submit" value="抽奖" onclick="return show_hint([])" 
		style="width: 200px;height: 100px;font-size: 36px;border-radius: 20px;background-color:rgb(246, 178, 88);"/>
		</CENTER>		 
	</form>
	</div>
		  
  	
  	<div style="text-align: center;"> 
  		<CENTER>
  		<div style="width: 900px;height: 300px;overflow:hidden;background-color: ;border-radius:10px;">
			  <div align="center" id="listview" style="width: 750px;text-align: center;">
				<h1 style="font-size: 30px;">
			    	中奖名单(${fn:length(close)}位)
				</h1>    
				<c:forEach items="${close}" var="c">
					<div style="width: 210px;float: left;font-size: 26px;font-weight: bold;text-align: left;margin-left: 37px;">
						<div style="width: 60px;text-align: left;float: left;">${c.id }</div>
						<div style="width: 140px;text-align: left;float: left;">${c.name }</div>
					</div>
				</c:forEach>
				<br/>
					<input type="button" value="中奖详情" onclick="query_all()" style="width；220px;height:40px;border-radius: 6px;background-color:rgb(246, 178, 88);position: fixed;left:70%;bottom:200px;"/>
				<CENTER>
					<img src="logo.png" style="width: 350px;height:150px;position: fixed;left: 50%;bottom: 120px;margin-left: -175px;z-index: -1;">
				</CENTER>
			  </div>
	  	</div>
	  	</CENTER>
  	</div>
  	

	<div id='hint_modal' class='easyui-window' title='' data-options='modal:true,closed:true' style='width:0px;height:0px;padding:10px;display: none;position: absolute;top: -100px;'>	
		<img alt="" src="zp.gif" style="position: fixed;top: 50%;left: 50%;margin-left: -150px;margin-top: -150px;"/>
	</div>
	<div id='query_all' class='easyui-window' title='中奖名单' data-options="modal:true,closed:true" style='width:780px;height:auto;padding:10px;display: none;'>	
		<div style="font-size: 30px;">第一次</div>
		<c:forEach items="${close1}" var="c1">
			<div style="width: 210px;float: left;font-size: 26px;font-weight: bold;text-align: left;margin-left: 37px;">
				<div style="width: 60px;text-align: left;float: left;">${c1.id }</div>
				<div style="width: 140px;text-align: left;float: left;">${c1.name }</div>
			</div>
		</c:forEach>
		<div style="clear: both;"></div>
		
		<div style="font-size: 30px;">第二次</div>
		<c:forEach items="${close2}" var="c2">
			<div style="width: 210px;float: left;font-size: 26px;font-weight: bold;text-align: left;margin-left: 37px;">
				<div style="width: 60px;text-align: left;float: left;">${c2.id }</div>
				<div style="width: 140px;text-align: left;float: left;">${c2.name }</div>
			</div>
		</c:forEach>
		<div style="clear: both;"></div>
		<div style="font-size: 30px;">第三次</div>
		<c:forEach items="${close3}" var="c3">
			<div style="width: 210px;float: left;font-size: 26px;font-weight: bold;text-align: left;margin-left: 37px;">
				<div style="width: 60px;text-align: left;float: left;">${c3.id }</div>
				<div style="width: 140px;text-align: left;float: left;">${c3.name }</div>
			</div>
		</c:forEach>
		<div style="clear: both;"></div>
		<div style="font-size: 30px;">第四次</div>
		<c:forEach items="${close4}" var="c4">
			<div style="width: 210px;float: left;font-size: 26px;font-weight: bold;text-align: left;margin-left: 37px;">
				<div style="width: 60px;text-align: left;float: left;">${c4.id }</div>
				<div style="width: 140px;text-align: left;float: left;">${c4.name }</div>
			</div>
		</c:forEach>
		
	</div>			
	<script type="text/javascript">
	//防止用户多次点击，过场字幕提示
	function show_hint(array) {
		console.log($("#hint_modal").html());
		$("#hint_modal").window('open');
		for ( var i = 0; i < array.length; i++) {
			$("#"+array[i]).window('close');
		}
		console.log(array.length);
		$("#hint").show();
		return true;
	}
	function query_all(){
		$('#query_all').window('open');
	}
	</script>
  </body>
</html>
