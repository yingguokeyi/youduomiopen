<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/12/13
  Time: 14:43
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
    <link rel="stylesheet" href="../css/mine/task_details.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <script type="text/javascript" src="../assets/js/jsonselect.js"></script>
    <title>任务</title>
</head>
<body style="background:#fff;">
<div class="main">
    <div class="main_top">
        <header>
            <div class="title_top">
                <a href="javascript:history.back(-1)" class="title_top_first">
                    <img src="../image/mine/return.png" class="hea_img" />
                </a>
                <span class="title_top_center" style="font-size:.26rem;">任务详情</span>
                <span style="float:right;margin-right:.26rem;font-size:.26rem;color:#666">
							<span id="task_help">帮助</span>
							<span onclick="window.location='report.jsp'">举报</span>
						</span>
            </div>
        </header>
        <div class="task_div">
            <div class="task_infor">
                <div class="infor_top">
                    <div class="top_img">
                        <img src="../image/mine/award.png" />
                        <span class="top_money"></span>
                    </div>
                    <div class="top_txt">
                        <p><span class="top_title"></span><span class="now"></span></p>
                        <p class="txt_p"><span class="txt_p_ti"></span>&nbsp;&nbsp;<span id="drew"></span></p>
                    </div>
                </div>
                <div class="infor_bot">
                    <p>
                        任务次数：限1次
                    </p>
                    <p>
                        截止日期: <span class="expiration_date"></span>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <div class="main_middle">
        <ul class="task_specification">
            <p class="task_title" id="task_title">
            </p>
        </ul>
        <div class="sample_picture">
            <p class="task_title">
                示例图片
            </p>
            <ul style="margin-bottom:.2rem;">

            </ul>
        </div>
        <div class="task_bg">

        </div>
        <div class="task_hunter">
            <div class="hunter_top">
                <p class="hunter_sj">赏金猎人</p>
                <p class="hunter_infor">平均通过率91% 超时自动打款</p>
            </div>
            <div class="myscroll">
                <ul>

                </ul>
            </div>
        </div>
        <div class="task_bg">

        </div>
        <div class="task_btn">
            <button type="button" id="task_apply"></button>
        </div>
    </div>
</div>
<div id="modal_help" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close">×</span>
        </div>
        <div class="modal-title">
            <span>新手帮助</span>
        </div>
        <div class="modal-body">
            <ul>
                <li>1.天天赚所有业务均与企业签约，确保任务奖励高额、真实、可靠！全场任务奖励均享受超时垫付保障！
                </li>
                <li>2.领取任务后请按要求在1小时内完成。并提交材料认证后进入审核，审核通过后获得奖励报酬，可在我钱包里进行提现。
                </li>
                <li>3.会员做任务专享提额奖励10%~30%，另外可享受粉丝任务最高50%奖励。
                </li>
            </ul>
        </div>
    </div>
</div>
<div id="modal_apply" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close">×</span>
        </div>
        <div class="modal-title">
            <span>领取说明</span>
        </div>
        <div class="modal-body">
            <ul>
                <li>1.领取任务前请仔细阅读任务说明，领取任务后请在1小时内按步骤要求提交审核信息，过期无效。
                </li>
                <li>2.所有现金任务均享受超时垫付保障，任务提交超过72小时未审核完成，系统自动发放奖励给您。
                </li>
                <li>3.会员做任务专享提额奖励10%~30%，另外可享受粉丝任务最高50%奖励。
                </li>
            </ul>
        </div>
        <footer class="modal-footer">
            <button id="sure" onclick="window.location='${request.contextPath}/youduomiopen/wechat/start_taskFirst.jsp?openid=<%=openid%>&userId=<%=userId%>'">确认开始任务</button>
        </footer>
    </div>
</div>
<!-- 任务过期模态框 -->
<div id="modal_issue" class="modal">
    <div class="modal-content">
        <div class="modal-title">
            <span>任务过期</span>
        </div>
        <div class="modal-body">
            任务发布已过期，任务已取消
        </div>
        <footer class="modal-footer">
            <button id="affirm" onclick="window.location='${request.contextPath}/youduomiopen/wechat/myTask.jsp?openid=<%=openid%>&userId=<%=userId%>'">确定</button>
        </footer>
    </div>
</div>
</body>
<script>
    var openid = "<%=openid%>";
    var userId= "<%=userId%>";
</script>
<script type="text/javascript" src="../assets/js/scroll.js"></script>
<script type="text/javascript" src="../js/mine/task_details.js"></script>
</html>
