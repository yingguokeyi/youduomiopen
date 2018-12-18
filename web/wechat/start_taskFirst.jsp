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
                <a class="title_top_first">
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
            <p class="step_title">请仔细查看任务步骤后按流程完成任务</p>
            <p class="step_cont se_cont"></p>
            <ul class="sample_picture">

            </ul>
        </div>
    </div>
    <div class="main_middle">
        <button type="button">下一步</button>
    </div>
</div>
<!-- 点击返回上一个页面出现弹框 -->
<div class="warm" style="display: none;">
    <div class="warm_title">
        <h4>提示</h4>
        <p>将离开任务页面，任务领取后请在两小时内完成，否则视为作废。</p>
    </div>
    <div class="warm_choose">
        <a class="warm_login">离开</a>
        <a class="warm_cancel">继续任务</a>
    </div>
</div>
<!-- 任务失败模态框 -->
<div id="modal_no" class="modal">
    <div class="modal-content">
        <div class="modal-title">
            <span>任务失败</span>
        </div>
        <div class="modal-body">
            未在指定时间内完成任务，请重新接取任务
        </div>
        <footer class="modal-footer">
            <button id="no_affirm" type="button">确定</button>
        </footer>
    </div>
</div>
<!-- 任务过期模态框 -->
<div id="modal_issue" class="modal">
    <div class="modal-content">
        <div class="modal-title">
            <span>任务过期</span>
        </div>
        <div class="modal-body">
            任务发布已过期，任务已取消
        </div>
        <footer class="modal-footer">
            <button id="affirm" type="button" onclick="window.location='${request.contextPath}/youduomiopen/wechat/myTask.jsp.jsp?openid=<%=openid%>&userId=<%=userId%>'">确定</button>
        </footer>
    </div>
</div>
</body>
<script>
    var openid = "<%=openid%>";
    var userId= "<%=userId%>";
</script>
<script type="text/javascript" src="../js/mine/start_taskFirst.js"></script>
</html>
