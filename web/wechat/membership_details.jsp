<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/11/14
  Time: 11:49
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
    <link rel="stylesheet" href="../css/mine/membership_details.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <title>个人中心</title>
</head>
<body>
<div class="main">
    <div class="main_top">
        <header>
            <div class="title_top">
                <a href="my_team.html" class="title_top_first">
                    <img src="../image/mine/return.png" class="hea_img" />
                </a>
                <span class="title_top_center">会员详情</span>
            </div>
        </header>
        <div class="mem_name">
            <p class="mem_head">
                <img />
            </p>
            <p class="mem_per">

            </p>
        </div>
    </div>
    <div class="main_bot">
        <ul>
            <li style="overflow:hidden" class="bot_li">
                <div style="float:left;" class="bot_p">
                    <p>会&nbsp;&nbsp;员&nbsp;&nbsp;ID:</p>
                    <p id="member_id"><i></i></p>
                </div>
            </li>
            <li class="bot_li">
                <div class="bot_p">
                    <p>会员级别:</p>
                    <p id="member_level"><em></em></p>
                </div>
            </li>
            <li class="bot_li">
                <div class="bot_p">
                    <p>上级会员:</p>
                    <p><span id="superior_member"></span>(<span id="superior_id"></span>)</p>
                </div>
            </li>
            <li class="bot_li">
                <div class="bot_p">
                    <p>会员级别:</p>
                    <p id="super_memlevel"><em></em></p>
                </div>
            </li>
            <li class="bot_li">
                <div class="bot_p">
                    <p>注册时间:</p>
                    <p id="register_time"><em></em></p>
                </div>
            </li>
            <li class="bot_li">
                <div class="bot_p">
                    <p>真实姓名:</p>
                    <p id="real_name"><em></em></p>
                </div>
            </li>
            <li class="bot_li">
                <div class="bot_p">
                    <p>手机号码:</p>
                    <p id="member_phone"><em></em></p>
                </div>
            </li>
        </ul>
    </div>
</div>
</body>
<script type="text/javascript" src="../js/mine/membership_details.js"></script>
</html>
