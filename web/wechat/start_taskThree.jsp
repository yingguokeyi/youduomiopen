<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/12/13
  Time: 15:05
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
    <link rel="stylesheet" href="../css/mine/start_taskFirst.css" />
    <link rel="stylesheet" href="../css/mine/start_taskThree.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <title>任务</title>
</head>
<body style="background:#fff;">
<div class="main">
    <div class="main_top">
        <header>
            <div class="title_top">
                <a href="javascript :;" onClick="javascript :history.back(-1);" class="title_top_first">
                    <img src="../image/mine/return.png" class="hea_img" />
                </a>
                <span class="title_top_center" style="font-size:.3rem;">任务开始</span>
            </div>
        </header>
        <div class="awards_show">
            <span class="quest_rewards">任务奖励:<i></i></span>
            <span class="time_remaining">剩余时间 <i id="drew"></i></span>
        </div>
        <div class="step_line"></div>
        <div class="step">
            <p class="step_title">上传任务截图</p>
        </div>
        <ul class="upload_pic">
            <li>
                <label class="upload_pictures" id="localImag1">
                    <input class="fileInput" id="doc1" type="file"  accept="image/*" name="file" style="display:none;" onchange="javascript:setImagePreview1();">
                    <img src='../image/mine/unpload_smoal.png' class="add" id="add1">
                    <p class="upolad_txt">上传图片</p>
                    <img id="preview1" src="" width="100%" height="100%" style="display: none;"/>
                </label>
            </li>
            <li>
                <label class="upload_pictures" id="localImag2">
                    <input class="fileInput" id="doc2" type="file"  accept="image/*" name="file" style="display:none;" onchange="javascript:setImagePreview2();">
                    <img src='../image/mine/unpload_smoal.png' class="add" id="add2">
                    <p class="upolad_txt">上传图片</p>
                    <img id="preview2" src="" width="100%" height="100%" style="display: none;"/>
                </label>
            </li>
            <li>
                <label class="upload_pictures" id="localImag3">
                    <input class="fileInput" id="doc3" type="file"  accept="image/*" name="file" style="display:none;" onchange="javascript:setImagePreview3();">
                    <img src='../image/mine/unpload_smoal.png' class="add" id="add3">
                    <p class="upolad_txt">上传图片</p>
                    <img id="preview3" src="" width="100%" height="100%" style="display: none;"/>
                </label>
            </li>
        </ul>
    </div>
    <div class="main_middle" style="margin-top:3.4rem;">
        <button type="button" id="sub_task" disabled="">提交任务</button>
    </div>
</div>
<div id="modal_start" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close">×</span>
        </div>
        <div class="modal-title">
            <span>确认提示</span>
        </div>
        <div class="modal-body">
            <ul>
                <li>为确保任务正常通过，请仔细核对提交信息是否符合任务要求，一旦提交审核不可修改。
                </li>
            </ul>
        </div>
        <footer class="modal-footer">
            <button id="sure" type="button">确认提交</button>
        </footer>
    </div>
</div>
</body>
<script>
    var openid = "<%=openid%>";
    var userId= "<%=userId%>";
</script>
<script type="text/javascript" src="../js/mine/start_taskThree.js"></script>
</html>
