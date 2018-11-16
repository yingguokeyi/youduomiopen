<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/11/14
  Time: 11:12
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
    <link rel="stylesheet" href="../css/mine/task_query.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <title>个人中心</title>
</head>
<body>
<div class="main">
    <header>
        <div class="title_top">
            <a href="mine.html" class="title_top_first">
                <img src="../image/mine/return.png" class="hea_img" />
            </a>
            <span class="title_top_center">任务查询</span>
        </div>
    </header>
    <div class="query">
        <div class="query_title">
            查询时间:
            <p class="select" id="cl_sel">
                <span class="year"></span>
                <img src="../image/mine/down.png" />
            </p>
            <span class="time">年</span>
            <p class="select" id="cli_sel">
                <span class="month"></span>
                <img src="../image/mine/down.png" />
            </p>
            <span class="time">月</span>
            <button type="button" id="query_btn">查询</button>
            <div class="select_cont" id="year_sel">
                <ul>
                    <li>2018</li>
                    <li>2017</li>
                    <li>2016</li>
                    <li>2015</li>
                    <li>2014</li>
                    <li>2013</li>
                    <li>2012</li>
                    <li>2011</li>
                    <li>2010</li>
                    <li>2009</li>
                    <li>2008</li>
                </ul>
            </div>
            <div class="select_cont" id="mont_sel">
                <ul>

                </ul>
            </div>
        </div>
    </div>
    <div class="query_list">
        <ul>
            <li>
                <div class="query_left">
                    <p class="left_per">
                        <span>CB一分钟简单注册，奖励8.00元</span>
                    </p>
                    <p class="left_pro">
                        <span class="pro_plan">已完成</span>
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
                        <span>CB一分钟简单注册，奖励8.00元</span>
                    </p>
                    <p class="left_pro">
                        <span>进行中</span>
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
                        <span>CB一分钟简单注册，奖励8.00元</span>
                    </p>
                    <p class="left_pro">
                        <span>待审核</span>
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
<script type="text/javascript" src="../js/mine/task_query.js"></script>
</html>
