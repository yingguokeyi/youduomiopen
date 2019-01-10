<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2019/1/7
  Time: 15:38
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
    <link rel="stylesheet" href="../css/mine/taskManager.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <script type="text/javascript" src="../assets/js/jsonselect.js"></script>
    <title>任务管理</title>
</head>
<body style="background:#f4f5f9;">
<div class="main">
    <div class="main_top">
        <header>
            <div class="title_top">
                <a href="javascript:history.back(-1)" class="title_top_first">
                    <img src="../image/mine/return.png" class="hea_img" />
                </a>
                <span class="title_top_center">任务管理</span>
                <a href="#" class="i_issue">
                    <span class="publish">发布</span>
                </a>
            </div>
        </header>
        <!-- 任务，金额 -->
        <div class="main_middle" id="top_1"></div>
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
            <div class="kong"></div>
            <div class="allGoods_list" id="orderContent">
                <ul>
                    <li class="main_content_li">
                                <span class="v_venn">
                                    <span class="main_content_a_left">
                                        <img class="main_img" src="../image/mine/award.png">
                                    </span>
                                    <span class="p_purse">20</span>
                                    <span class="main_content_a_right">
                                        <span class="m_c_a_r_top">cb一分钟简单注册</span>
                                    </span>

                                </span>
                        <span class="v_amount">
                                    <span class="s_scale"><i class="magnitude">数量</i>：<i class="just_now">￥1.20</i>/20</span>
                                    <span class="s_time">结束时间：2018-12-30</span>
                                    <a href="#" class="main_content_a">
                                        <div class="particulars">进行中</div>
                                    </a>
                                </span>
                    </li>
                    <li class="main_content_li">
                                    <span class="v_venn">
                                        <span class="main_content_a_left">
                                            <img class="main_img" src="../image/mine/award.png">
                                        </span>
                                        <span class="p_purse">20</span>
                                        <span class="main_content_a_right">
                                            <span class="m_c_a_r_top">58同城速运丰城四级注册推广，只需完成推广</span>
                                        </span>
                                    </span>
                        <span class="v_amount">
                                        <span class="s_scale"><i class="magnitude">数量</i>：<i class="just_now">￥1.20</i>/20</span>
                                        <span class="s_time">数量满后自动结束</span>
                                        <a href="#" class="main_content_a">
                                            <div class="aerea">审核中</div>
                                        </a>
                                    </span>
                    </li>
                    <li class="main_content_li">
                                <span class="v_venn">
                                    <span class="main_content_a_left">
                                        <img class="main_img" src="../image/mine/award.png">
                                    </span>
                                    <span class="p_purse">20</span>
                                    <span class="main_content_a_right">
                                        <span class="m_c_a_r_top">58同城速运丰城四级注册推广，只需完成推广</span>
                                    </span>
                                </span>
                        <span class="v_amount">
                                    <span class="s_scale"><i class="magnitude">数量</i>：<i class="just_now">￥1.20</i>/20</span>
                                    <span class="s_time">数量满后自动结束</span>
                                    <a href="#" class="main_content_a">
                                        <div class="bonus">待支付</div>
                                    </a>
                                </span>
                    </li>
                    <li class="main_content_li">
                                    <span class="v_venn">
                                        <span class="main_content_a_left">
                                            <img class="main_img" src="../image/mine/award.png">
                                        </span>
                                        <span class="p_purse">20</span>
                                        <span class="main_content_a_right">
                                            <span class="m_c_a_r_top">58同城速运丰城四级注册推广，只需完成推广</span>
                                        </span>
                                    </span>
                        <span class="v_amount">
                                        <span class="s_scale"><i class="magnitude">数量</i>：<i class="just_now">￥1.20</i>/20</span>
                                        <span class="s_time">数量满后自动结束</span>
                                        <a href="#" class="main_content_a">
                                            <div class="failure">审核失败</div>
                                        </a>
                                    </span>
                    </li>

                    <li class="main_content_li">
                                <span class="v_venn">
                                    <span class="main_content_a_left">
                                        <img class="main_img" src="../image/mine/award.png">
                                    </span>
                                    <span class="p_purse">20</span>
                                    <span class="main_content_a_right">
                                        <span class="m_c_a_r_top">58同城速运丰城四级注册推广，只需完成推广</span>
                                    </span>
                                </span>
                        <span class="v_amount">
                                    <span class="s_scale"><i class="magnitude">数量</i>：<i class="just_now">￥1.20</i>/20</span>
                                    <span class="s_time">数量满后自动结束</span>
                                    <a href="#" class="main_content_a">
                                        <div class="creoline">已结束</div>
                                    </a>
                                </span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- 无数据显示的页面 -->
    <div class="w_without">
        <!-- 图片 -->
        <div class="w_img"><img class="w_little" src="../image/mine/little.png"/></div>
        <!-- 文字 -->
        <span class="w_message">无发布任务信息</span>
        <!-- 发布任务 -->
        <a class="w_stroke" href="#">
            <div class="w_hist">发布任务</div>
        </a>
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
        <div class="modal-footer">
            <button id="sure">查看任务</button>
        </div>
    </div>
</div>
<script type="text/javascript" src="../assets/js/iscroll.js"></script>
<script>
var openid = "<%=openid%>";
var userId = "<%=userId%>";
</script>
<!-- <script type="text/javascript" src="../../js/mine/taskManager.js"></script>	 -->
</body>
</html>
