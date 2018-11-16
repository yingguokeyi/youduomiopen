<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/11/14
  Time: 11:52
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
    <link rel="stylesheet" href="../css/mine/modified_data.css" />
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
                <a href="membership.html" class="title_top_first">
                    <img src="../image/mine/return.png" class="hea_img" />
                </a>
                <span class="title_top_center">修改基本资料</span>
            </div>
        </header>
    </div>
    <div class="main_bot">
        <ul>
            <li class="bot_li">
                <label>昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:</label>
                <input type="text" value="缘来如此" />
            </li>
            <li class="bot_li">
                <label>真实姓名:</label>
                <input type="text" value="默 *" />
            </li>
            <li class="bot_li">
                <label>所在省份:</label>
                <select id="s_province" name="s_province"></select>
                <!-- <input type="text" value="山东省" id="s_province" name="s_province" /> -->
            </li>

            <li class="bot_li">
                <label>城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市:</label>
                <!-- <input type="text" value="泰安市" id="s_city" name="s_city" /> -->
                <select id="s_city" name="s_city" ></select>
            </li>
            <li class="bot_li">
                <label>区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;域:</label>
                <!-- <input type="text" value="泰安市" id="s_county" name="s_county" /> -->
                <select id="s_county" name="s_county"></select>
            </li>
            <script type="text/javascript" src="../assets/js/area.js"></script>
            <script type="text/javascript">_init_area();</script>
            <li style="height:1.56rem;">
                <label style="vertical-align: top;line-height: 1.56rem;">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址:</label>
                <textarea style="margin-top:.2rem;height:80%;">翟镇镇刘官庄村新村路88号</textarea>
            </li>
        </ul>
        <div class="main_btn">
            <button type="button" id="mod_btn" disabled="">确定修改</button>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="../js/mine/modified_data.js"></script>
</html>
