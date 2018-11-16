<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/11/14
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="../css/mine/withdraw.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <title>提现</title>
</head>
<body style="background:#fff;">
<div class="main">
    <div class="wfg"></div>
    <div class="main_top">
        <ul>
            <li class="first">
                <a href="wx_withdraw.html" class="first_a">
							<span class="title_content">
							<img src='../image/mine/wx.png' />
								<span>微信</span>
							</span>
                    <span class="back_span">
								<button type="button">提现</button>
							</span>
                </a>
            </li>
            <li class="first">
                <a href="yhk_withdraw.html" class="first_a">
							<span class="title_content">
							<img src='../image/mine/yhk.png' />
								<span>银行卡</span>
							</span>
                    <span class="back_span">
								<button type="button">提现</button>
							</span>
                </a>
            </li>
        </ul>
    </div>
    <div class="wifg"></div>
    <div class="main_bot">
        <p class="with_title">提现须知:</p>
        <ul>
            <li>
                1.满五元可以提现，且可提现金额必须为5的倍数。
            </li>
            <li>
                2.每提现一笔将扣除0元手续费。
            </li>
            <li>
                3.目前仅支持转账到您的微信红包或银行卡，请在个人资料中补齐想关信息
            </li>
        </ul>
        <p class="wit_service">
            <img src="../image/mine/service1.png" />
            <a href="javascript:void(0)">联系客服</a>
        </p>
    </div>
</div>
</body>
<script type="text/javascript" src="../js/mine/withdraw.js"></script>
</html>
