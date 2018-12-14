<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/12/10
  Time: 10:11
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
    <link rel="stylesheet" href="../css/makeEveryDay/makeEveryDay.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <script type="text/javascript" src="../assets/js/jsonselect.js"></script>
    <title>天天赚---全部任务</title>
</head>
<body style="background:#f4f5f9;">
<div class="main">
    <div class="head_img">
        <img src="../image/mine/map.png" />
    </div>
    <!-- 任务，金额 -->
    <div class="main_middle" id="top_1">
        <ul>
            <!-- <li>
                <p>20个</p>
                <p>今日任务</p>
                <div class="mid_line"></div>
            </li>

            <li>
                <p>50.00元</p>
                <p>奖励总金额</p>
            </li> -->
        </ul>
        <div class="kong"></div>
    </div>
    <!-- 全部任务 -->
    <div class="allGoods" id='list'>
        <div class="wrapper wrapper01" id="retr">
            <div class="scroller" style="width: 100%; transition-timing-function: cubic-bezier(0.1, 0.57, 0.1, 1); transition-duration: 0ms; transform: translate(-83.125px, 0px) translateZ(0px);">
                <ul class="clearfix">
                    <!-- <li>全部内容</li> -->
                </ul>
            </div>
        </div>
        <div class="allGoods_list" id="orderContent">
            <ul>
                <!-- <li class="main_content_li">
                    <span class="main_content_a_left">
                        <img class="main_img" src="../../image/makeEveryDay/money.png">
                    </span>
                    <span class="main_content_a_right">
                        <span class="m_c_a_r_top">58同城速运丰城四级注册推广，只需完成推广 <i class="just_now">刚刚 </i></span>
                        <span class="m_c_a_r_bottom">
                            <span class="m_c_a_r_bottomleft">已领取    剩余时间：01:20:10</span>
                        </span>
                    </span>
                    <a href="#" class="main_content_a">
                        <div class="particulars">详情</div>
                    </a>
                </li>
                <li class="main_content_li">
                    <span class="main_content_a_left">
                        <img class="main_img" src="../../image/makeEveryDay/money.png">
                    </span>
                    <span class="main_content_a_right">
                        <span class="m_c_a_r_top">58同城速运丰城四级注册推广，只需完成推广 <i class="just_now">刚刚 </i></span>
                        <span class="m_c_a_r_bottom">
                            <span class="m_c_a_r_bottomleft">已有20人领取</span>
                        </span>
                    </span>
                    <a href="#" class="main_content_a">
                        <div class="particulars">详情</div>
                    </a>
                </li>
                <li class="main_content_li">
                    <span class="main_content_a_left">
                        <img class="main_img" src="../../image/makeEveryDay/ash.png">
                    </span>
                    <span class="main_content_a_ash">
                        <span class="m_c_a_r_grey">58同城速运丰城四级注册推广，只需完成推广 <i class="just_now">刚刚 </i></span>
                        <span class="m_c_a_r_ash">
                            <span class="m_c_a_r_bottomlefts">已完成</span>
                        </span>
                    </span>
                    <a href="#" class="main_content_as">
                        <div class="particularss">详情</div>
                    </a>
                </li>
                <li class="main_content_li">
                    <span class="main_content_a_left">
                        <img class="main_img" src="../../image/makeEveryDay/ash.png">
                    </span>
                    <span class="main_content_a_ash">
                        <span class="m_c_a_r_grey">58同城速运丰城四级注册推广，只需完成推广 <i class="just_now">刚刚 </i></span>
                        <span class="m_c_a_r_ash">
                            <span class="m_c_a_r_bottomlefts">已完成</span>
                        </span>
                    </span>
                    <a href="#" class="main_content_as">
                        <div class="particularss">详情</div>
                    </a>
                </li>
                <li class="main_content_li">
                    <span class="main_content_a_left">
                        <img class="main_img" src="../../image/makeEveryDay/ash.png">
                    </span>
                    <span class="main_content_a_ash">
                        <span class="m_c_a_r_grey">58同城速运丰城四级注册推广，只需完成推广 <i class="just_now">刚刚 </i></span>
                        <span class="m_c_a_r_ash">
                            <span class="m_c_a_r_bottomlefts">已完成</span>
                        </span>
                    </span>
                    <a href="#" class="main_content_as">
                        <div class="particularss">详情</div>
                    </a>
                </li>
                <li class="main_content_li">
                    <span class="main_content_a_left">
                        <img class="main_img" src="../../image/makeEveryDay/ash.png">
                    </span>
                    <span class="main_content_a_ash">
                        <span class="m_c_a_r_grey">58同城速运丰城四级注册推广，只需完成推广 <i class="just_now">刚刚 </i></span>
                        <span class="m_c_a_r_ash">
                            <span class="m_c_a_r_bottomlefts">已完成</span>
                        </span>
                    </span>
                    <a href="#" class="main_content_as">
                        <div class="particularss">详情</div>
                    </a>
                </li>
                <li class="main_content_li">
                    <span class="main_content_a_left">
                        <img class="main_img" src="../../image/makeEveryDay/ash.png">
                    </span>
                    <span class="main_content_a_ash">
                        <span class="m_c_a_r_grey">58同城速运丰城四级注册推广，只需完成推广 <i class="just_now">刚刚 </i></span>
                        <span class="m_c_a_r_ash">
                            <span class="m_c_a_r_bottomlefts">已完成</span>
                        </span>
                    </span>
                    <a href="#" class="main_content_as">
                        <div class="particularss">详情</div>
                    </a>
                </li>
                <li class="main_content_li">
                    <span class="main_content_a_left">
                        <img class="main_img" src="../../image/makeEveryDay/ash.png">
                    </span>
                    <span class="main_content_a_ash">
                        <span class="m_c_a_r_grey">58同城速运丰城四级注册推广，只需完成推广 <i class="just_now">刚刚 </i></span>
                        <span class="m_c_a_r_ash">
                            <span class="m_c_a_r_bottomlefts">已完成</span>
                        </span>
                    </span>
                    <a href="#" class="main_content_as">
                        <div class="particularss">详情</div>
                    </a>
                </li> -->
            </ul>
        </div>
    </div>
</div>
<!-- 我的任务记录 -->
<footer>
    <div class="record">
        <a href="myTask.jsp">我的任务记录</a>
    </div>
</footer>
<script>
    var openid = "<%=openid%>";
    var userId= "<%=userId%>";
</script>
<script type="text/javascript" src="../assets/js/iscroll.js"></script>
<script type="text/javascript" src="../js/makeEveryDay/makeEveryDay.js"></script>
</body>
</html>