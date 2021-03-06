<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/11/14
  Time: 11:22
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
    <link rel="stylesheet" href="../assets/css/header_common.css" />
    <link rel="stylesheet" href="../css/mine/history_withdraw.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <title>提现</title>
</head>
<body>
<div class="main">
    <header>
        <div class="title_top">
            <a onclick="javascript:history.back(-1);" class="title_top_first">
                <img src="../image/mine/return.png" class="hea_img" />
            </a>
            <span class="title_top_center">历史提现</span>
        </div>
    </header>
    <div class="withdrawal_record">
        <ul>
            <li>
                <div class="record_infor">
                    <p class="infor_title">
                        <span>提现成功</span>
                        <span>-1.00</span>
                    </p>
                    <p class="record_time">2018-12-12  10:10:00</p>
                    <p class="record_plan">申请提 <i>10.00</i>元，提现成功！请注意查收</p>
                </div>
                <div class="record_img" onclick="window.location='show_details.html'">
                    <img src="../image/mine/enter.png" />
                </div>
            </li>
            <li>
                <div class="record_infor">
                    <p class="infor_title">
                        <span>提现成功</span>
                        <span>-1.00</span>
                    </p>
                    <p class="record_time">2018-12-12  10:10:00</p>
                    <p class="record_plan">申请提 <i>10.00</i>元，提现成功！请注意查收</p>
                </div>
                <div class="record_img">
                    <img src="../image/mine/enter.png" />
                </div>
            </li>
            <li>
                <div class="record_infor">
                    <p class="infor_title">
                        <span>提现成功</span>
                        <span>-1.00</span>
                    </p>
                    <p class="record_time">2018-12-12  10:10:00</p>
                    <p class="record_plan">申请提 <i>10.00</i>元，提现成功！请注意查收</p>
                </div>
                <div class="record_img">
                    <img src="../image/mine/enter.png" />
                </div>
            </li>
        </ul>
    </div>
</div>
</body>
<script type="text/javascript" src="../js/mine/history_withdraw.js"></script>
</html>
