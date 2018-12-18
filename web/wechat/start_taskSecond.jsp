<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/12/13
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String openid = request.getParameter("openid");
    String userId = request.getParameter("userId");
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <meta name="format-detection" content="telephone=no" />
    <meta name="format-detection" content="email=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="msapplication-tap-highlight" content="no">
    <link rel="stylesheet" href="../assets/css/common.css" />
    <link rel="stylesheet" href="../assets/css/header_common.css" />
    <link rel="stylesheet" href="../css/mine/start_taskFirst.css" />
    <link rel="stylesheet" href="../css/mine/start_taskSecond.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <title>任务</title>
</head>
<body style="background:#fff;">
<div class="main">
    <div class="main_top">
        <header>
            <div class="title_top">
                <a href="start_taskFirst.jsp" class="title_top_first">
                    <img src="../image/mine/return.png" class="hea_img" />
                </a>
                <span class="title_top_center" style="font-size:.3rem;">任务开始</span>
            </div>
        </header>
        <div class="awards_show">
            <span class="quest_rewards">任务奖励:<i></i></span>
            <span class="time_remaining">剩余时间 <i id="drew"></i></span>
        </div>
        <div class="step">
            <p class="step_title"><button type="button">点击去做任务</button></p>
            <p class="step_cont">点击后会调起手机浏览器打开任务链接，按任务说明完成操作后，进行步骤操作。</p>
        </div>
    </div>
    <div class="main_middle" style="margin-top:3.4rem;">
        <button type="button" onclick="window.location='${request.contextPath}/youduomiopen/wechat/start_taskThree.jsp?openid=<%=openid%>&userId=<%=userId%>'">下一步</button>
    </div>
</div>
</body>
<script>
    var openid = "<%=openid%>";
    var userId= "<%=userId%>";
</script>
<script type="text/javascript" src="../js/mine/start_taskSecond.js"></script>
</html>
