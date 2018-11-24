<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/11/15
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String openid = request.getParameter("openid");
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
    <link rel="stylesheet" href="../css/receipt/history.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <title>历史记录</title>
</head>
<body>
<div class="main">
    <header>
        <div class="title_top">
            <a href="" class="title_top_first">
                <img src="../image/mine/return.png" class="hea_img" />
            </a>
            <span class="title_top_center">历史记录</span>
        </div>
    </header>
    <div class="query_list">
        <ul>
            <li onclick="window.location='unpload_details.html'">
                <div class="query_left">
                    <p class="left_per">
                        <span>【收货方】</span>
                        <span>小票号:<i>53147965</i></span>
                    </p>
                    <p class="left_pro">
                        <span>已结算</span>
                        <span></span>
                        <span>2018-12-12  10:10:00</span>
                    </p>
                </div>
                <div class="query_right">
                    ￥ <span>1.00</span>
                </div>
            </li>
            <li>
                <div class="query_left">
                    <p class="left_per">
                        <span>【收货方】</span>
                        <span>小票号:<i>53147965</i></span>
                    </p>
                    <p class="left_pro">
                        <span>已结算</span>
                        <span></span>
                        <span>2018-12-12  10:10:00</span>
                    </p>
                </div>
                <div class="query_right">
                    ￥ <span>1.00</span>
                </div>
            </li>
        </ul>
    </div>
</div>
</body>
<script type="text/javascript" src="../js/receipt/history.js"></script>
</html>
