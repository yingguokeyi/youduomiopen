<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2019/1/7
  Time: 15:39
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
    <link rel="stylesheet" href="../css/mine/taskdetail.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <script type="text/javascript" src="../assets/js/jsonselect.js"></script>
    <title>任务</title>
</head>
<body style="background:#fff;margin-bottom: 1.20rem;">
<div class="main">
    <div class="main_top" id="main_top" style="height: 0 auto;">
        <header>
            <div class="title_top">
                <a href="javascript:history.back(-1)" class="title_top_first">
                    <img src="../image/mine/return.png" class="hea_img" />
                </a>
                <span class="title_top_center" style="font-size:.26rem;">任务详情</span>
            </div>
        </header>

        <!-- 任务编号，创建时间，任务状态  -->
        <div class="task_info">
            <span class="taskId">任务编号：<i class="mark">001</i></span>
            <span class="taskId">创建时间：<i class="mark">2019-1-3 12：00：00</i></span>
            <!-- 审核失败 -->
            <!-- <div class="failureAudit">
                <span class="taskState">任务状态： <i class="mark_red">审核失败</i></span>
                <span class="cause">原因：</span>
                <span class="mark_cause">哈尔巨无霸发布会方便加快速度你加我卡死你不违反，巨无霸的抚养权外国语</span>
            </div> -->
            <!-- 发布中 -->
            <!-- <span class="taskId">任务状态：<i class="mark_blue">发布中</i></span> -->
            <!-- 待支付 -->
            <!-- <span class="taskId">任务状态：<i class="mark_greed">待支付</i></span> -->
            <!-- 待审核 -->
            <span class="taskId">任务状态：<i class="mark_orange">待审核</i></span>
            <div class="task_bgs"></div>
            <!-- 已领取，已完成，剩余 -->
            <div class="r_document">
                <span class="r_left">已领取：0个</span>
                <span class="r_middle">已完成：0个</span>
                <span class="r_right">剩余：20个</span>
            </div>
        </div>

        <div class="task_bg"></div>
        <!-- 基本信息 -->
        <div class="task_div">
            <div class="d_basic">基本信息</div>
            <span class="causee">任务名称：</span>
            <span class="mark_cause">哈尔巨无霸发布会方便加快速度你加我卡死你不违反，巨无霸的抚养权外国语</span>
            <!-- 任务步骤1 -->
            <span class="quest_one">任务步骤 1：<i class="a_account">打开APP进行注册</i></span>
            <!-- 图片 -->
            <div class="p_photo">
                <div class="photo_one"><img class="img_one" src='../image/mine/autoPic.jpg' /></div>
                <div class="photo_two"><img class="img_two" src='../image/mine/goods_list.jpg' /></div>
            </div>
            <!-- 审核示例图 -->
            <span class="quest_one">审核示例图：<i class="a_account"></i></span>
            <!-- 图片 -->
            <div class="p_photo">
                <div class="photo_one"><img class="img_one" src='../image/mine/autoPic.jpg' /></div>
                <div class="photo_two"><img class="img_two" src='../image/mine/goods_list.jpg' /></div>
            </div>
            <!-- 阴影 -->
            <div class="umbr"></div>
            <div class="umbrr"></div>
            <div class="umbrrr"></div>
            <div class="umbrrrr"></div>
            <!-- 提交文字 -->
            <span class="quest_os">提交文字：<i class="a_account">手机号+用户名</i></span>
            <div class="task_bg"></div>
            <!-- 任务设置 -->
            <div class="taskSet">任务设置</div>
            <div class="taskSet_list">

                <div class="list_one">
                    <span class="list_left">投放单价</span>
                    <span class="list_right">￥1.20</span>
                </div>
                <div class="list_one">
                    <span class="list_left">投放数量</span>
                    <span class="list_grey">20个</span>
                </div>
                <div class="list_one">
                    <span class="list_left">结束时间</span>
                    <span class="list_grey">数量满后自动结束</span>
                </div>
                <div class="list_one">
                    <span class="list_left">任务限时</span>
                    <span class="list_grey">1小时</span>
                </div>
                <div class="list_one">
                    <span class="list_left">审核周期</span>
                    <span class="list_grey">72小时</span>
                </div>
            </div>
            <div class="task_bg"></div>
        </div>

    </div>
</div>
<!-- 底部 -->
<footer>
    <div class="tail">待审核</div>
</footer>
</body>
<script type="text/javascript" src="../assets/js/scroll.js"></script>
<script type="text/javascript" src="../js/mine/taskdetail.js"></script>
</html>