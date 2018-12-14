<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/12/10
  Time: 10:26
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
    <link rel="stylesheet" href="../css/makeEveryDay/myTask.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <script type="text/javascript" src="../assets/js/jsonselect.js"></script>
    <title>我的任务</title>
</head>
<body>
<div class="main">
    <div class="main_top">
        <header>
            <div class="title_top">
                <a href="javascript:'';" class="title_top_first">
                    <img src="../image/mine/return.png" class="hea_img" />
                </a>
                <span class="title_top_center">我的任务</span>
            </div>
        </header>
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
                    <p>预计奖励</p>
                    <div class="mid_line"></div>
                </li>

                <li>
                    <p>350.00元</p>
                    <p>累计收入</p>
                </li> -->
            </ul>
            <div class="kong"></div>
        </div>
    </div>


    <div class="allGoods" id='list'>
        <div class="main_bot">
            <div class="wrapper wrapper01" id="retr">
                <!-- <div class="scroller" style="width: 100%;"> -->
                <div class="team_tile">
                    <ul class="bot_ul">
                        <li id="whole">
                            <a class="tabhover">
                                全部<em></em>
                            </a>
                        </li>
                        <li id="conduct">
                            <a>
                                进行中<em></em>
                            </a>
                        </li>
                        <li id="toAudit">
                            <a>
                                待审核<em></em>
                            </a>
                        </li>
                        <li id="completed">
                            <a>
                                已结束<em></em>
                            </a>
                        </li>
                    </ul>
                </div>

                <!-- </div> -->
            </div>

            <div class="allGoods_list" id="orderContent">
                <ul>
                    <!-- <li class="main_content_li">
                        <span class="main_content_a_left">
                            <img class="main_img" src="../../image/makeEveryDay/money.png">
                        </span>
                        <span class="main_content_a_right">
                            <span class="m_c_a_r_top">cb一分钟简单注册 <i class="just_now">刚刚 </i></span>
                            <span class="m_c_a_r_bottom">
                                <span class="m_c_a_r_bottomleft">任务剩余时间：01:20:10</span>
                            </span>
                        </span>
                        <a href="#" class="main_content_a">
                            <div class="particulars">进行中</div>
                        </a>
                    </li>
                    <li class="main_content_li">
                        <span class="main_content_a_left">
                            <img class="main_img" src="../../image/makeEveryDay/money.png">
                        </span>
                        <span class="main_content_a_right">
                            <span class="m_c_a_r_top">58同城速运丰城四级注册推广，只需完成推广 <i class="just_now">11月6</i></span>
                            <span class="m_c_a_r_bottom">
                                <span class="m_c_a_r_orange">计1-23小时，超时自动到账</span>
                            </span>
                        </span>
                        <a href="#" class="main_content_a">
                            <div class="aerea">审核中</div>
                        </a>
                    </li>
                    <li class="main_content_li">
                        <span class="main_content_a_left">
                            <img class="main_img" src="../../image/makeEveryDay/ash.png">
                        </span>
                        <span class="main_content_a_right">
                            <span class="m_c_a_r_top">58同城速运丰城四级注册推广，只需完成推广 <i class="just_now">11月5</i></span>
                            <span class="m_c_a_r_bottom">
                                <span class="m_c_a_r_green">获得奖励  20元</span>
                            </span>
                        </span>
                        <a href="#" class="main_content_as">
                            <div class="creoline">已完成</div>
                        </a>
                    </li>
                    <li class="main_content_li">
                        <span class="main_content_a_left">
                            <img class="main_img" src="../../image/makeEveryDay/ash.png">
                        </span>
                        <span class="main_content_a_right">
                            <span class="m_c_a_r_top">58同城速运丰城四级注册推广，只需完成推广 <i class="just_now">11月4</i></span>
                            <span class="m_c_a_r_bottom">
                                <span class="m_c_a_r_red">审核失败</span>
                            </span>
                        </span>
                        <a href="#" class="main_content_as">
                            <div class="bonus">查看</div>
                        </a>
                    </li> -->
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- 弹框 -->
<div id="modal_apply" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close">×</span>
        </div>
        <div class="modal-title">
            <span>审核失败</span>
        </div>
        <div class="modal-body">
            <ul>
                <li class="onec">需要正是注册成功的截图</li>
                <li class="two">*可核实原因并重新提交审核 </li>
            </ul>
        </div>
        <footer class="modal-footer">
            <button id="sure">查看任务</button>
        </footer>
    </div>
</div>
</body>
<script>
    var openid = "<%=openid%>";
    var userId= "<%=userId%>";
</script>
<script type="text/javascript" src="../assets/js/iscroll.js"></script>
<script type="text/javascript" src="../js/makeEveryDay/myTask.js"></script>
</html>
