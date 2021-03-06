<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/11/17
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String openid = request.getParameter("openid");
    String userId = request.getParameter("userId");
%>
<html>
<script>
    var userId = <%=userId%>
</script>
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
    <link rel="stylesheet" href="../css/mine/wx_withdraw.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <title>提现</title>
</head>
<body style="background:#fff;">
<div class="main">
    <div class="main_top">
        <header>
            <div class="title_top">
                <a href="withdraw.jsp?userId=<%=userId%>" class="title_top_first">
                    <img src="../image/mine/return.png" class="hea_img" />
                </a>
                <span class="title_top_center">微信提现</span>
                <span style="float:right;margin-right:.26rem;font-size:.28rem;color:#666" onclick="window.location='history_withdraw.jsp'">明细</span>
            </div>
        </header>
        <p class="wx_y">
            <span class="y_txt">当前余额:<span></span></span>
        </p>
    </div>
    <div class="wifg"></div>
    <div class="main_bot">
        <p class="wx_name">缘来如此</p>
        <div class="wx_sum">
            <p>
                <input type="text" placeholder="请输入您的提现金额" value="" id="sumVal" />
            </p>
            <button type="button" id="wx_btn">提现</button>
        </div>
        <p class="wit_condition">*满五元可以提现,且可提现金额必须为5的倍数</p>
        <p class="wit_service">
            <img src="../image/mine/service1.png" />
            <a href="online_service.jsp">联系客服</a>
        </p>
    </div>
</div>
<div class="warm" style="display: none;">
    <div class="warm_title">
        <h4>提现失败</h4>
        <p>您未开通会员</p>
    </div>
    <div class="warm_choose">
        <a class="warm_login">去开通</a>
        <a class="warm_cancel">取消</a>

    </div>
</div>
</body>
<script type="text/javascript" src="../js/mine/wx_withdraw.js"></script>
</html>
