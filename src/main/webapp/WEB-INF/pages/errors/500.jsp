<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html style="height:100%;margin:0">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>500-服务器内部错误</title>
		<meta name="viewport" content="width=device-width,initial-scale=1.0">		
		<link rel="shortcut icon" href="/favicon.ico">
		<link rel="apple-touch-icon" href="/apple-touch-icon.png">		
		<style>
			.error-wrapper {
				height:500px;
				width: 500px;
				margin:auto;
				position:relative;
				top:50%;
				margin-top:-300px;
				color: #a0a0a0;
				background:url("<fpx:static />/images/500.png") no-repeat ;
			}
			#ce-page-main .error-wrapper {
				top:0;
				margin-top:20px;
			}
			.error-title {
			    font-size: 26px;
			    font-weight: 600;
			}
			.error-detail {
				font-size:14px;
				position: absolute;
				left: 180px;
				top:280px;
			}
			.time-home-color {
				color:#f60;
			}
			.error-wrapper .btn {
				background-color:#54b9cd;
				border-color:#54b9cd;
			}
			.error-wrapper a {
				margin-left: 6em;
			}
		</style>	
	</head>
	<body style="height:100%;margin:0">
			<div class="error-wrapper">	
				<div class="error-detail">
				    <p class="error-title">出错了</p>
	                <p class="error-info">抱歉，无法访问您的请求！<span class="time-home-color">5</span>秒后将回到首页……</p>
	                <a class="btn btn-success" href="/page/home">
	                  	返回首页
	                </a>
				</div>
				
			</div>	
	</body>
	<script>
	 (function() {
        var num=document.querySelector('.time-home-color'),
            time=5,
            timeout,
            hash=location.hash;             
        timeout=setTimeout(function() {
            time--;                
            if(time ===0){
                return;
            }               
            //URL地址相同才持续等待自动跳转,第一次进入页面时同样等待
            if(time===4 || hash ===location.hash){
                num.innerHTML=time;               
                timeout=setTimeout(arguments.callee,1000);                
            }
            hash=location.hash;                
        },1000);
       
    }());
	</script>
</html>