<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/11/14
  Time: 10:38
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
    <link rel="stylesheet" href="../css/mine/my_team.css" />
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
                <a href="mine.jsp?openid=<%=openid%>" class="title_top_first">
                    <img src="../image/mine/return.png" class="hea_img" />
                </a>
                <span class="title_top_center">我的团队</span>
            </div>
        </header>
    </div>
    <div class="main_bot">
        <div class="team_tile">
            <ul class="bot_ul">
                <li>
                    <a class="tabhover">
                        总人数6人
                    </a>
                </li>
                <li>
                    <a>
                        直系邀请5人
                    </a>
                </li>
                <li>
                    <a>
                        二级邀请1人
                    </a>
                </li>
            </ul>
        </div>
        <div class="tabcontent" id="orderContent">
            <ul>
                <li class="first">
                    <a href="membership_details.jsp" class="first_a">
								<span class="title_content">
								<img src='../image/mine/autoPic.jpg' />
									<span>刘一兵<i>(262745)</i></span>
								</span>
                        <span class="back_span">
									<span>普通会员</span>
									<img src="../image/mine/enter.png" />
								</span>
                    </a>
                </li>
                <li class="first">
                    <a href="javascript:;" class="first_a">
								<span class="title_content">
								<img src='../image/mine/autoPic.jpg' />
									<span>刘一兵<i>(262745)</i></span>
								</span>
                        <span class="back_span">
									<span>普通会员</span>
									<img src="../image/mine/enter.png" />
								</span>
                    </a>
                </li>
                <li class="first">
                    <a href="javascript:;" class="first_a">
								<span class="title_content">
								<img src='../image/mine/autoPic.jpg' />
									<span>刘一兵<i>(262745)</i></span>
								</span>
                        <span class="back_span">
									<span>vip</span>
									<img src="../image/mine/enter.png" />
								</span>
                    </a>
                </li>
                <li class="first">
                    <a href="javascript:;" class="first_a">
								<span class="title_content">
								<img src='../image/mine/autoPic.jpg' />
									<span>刘一兵<i>(262745)</i></span>
								</span>
                        <span class="back_span">
									<span>vip</span>
									<img src="../image/mine/enter.png" />
								</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="../js/mine/my_team.js"></script>
</html>
