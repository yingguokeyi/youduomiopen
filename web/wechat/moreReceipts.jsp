<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/11/17
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userId = request.getParameter("userId");
%>
<html>
<script>

    var userId = <%=userId%>
</script>
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
    <link rel="stylesheet" href="../css/receipt/moreReceipts.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <script type="text/javascript" src="../assets/js/jsonselect.js"></script>
    <title>上传历史</title>
</head>
<body style="background-color:#fff;">
<div class="main">
    <header>
        <div class="title_top">
            <a onclick="javascript:history.back(-1);" class="title_top_first">
                <img src="../image/mine/return.png" class="hea_img" />
            </a>
            <span class="title_top_center">上传历史</span>
            <button class="main_qingkong_title_btn">清空</button>
        </div>
    </header>
    <div class="vacant">您的历史空空如也~~</div>
    <div class="withdrawal_record">
        <ul>
        </ul>
    </div>
</div>
<!-- 弹框 -->
<div class="warm" style="display: none;">
    <div class="warm_title">
        <h4>提示</h4>
        <p>将清除您所有的查询历史</p>
    </div>
    <div class="warm_choose">
        <a class="warm_login">确定</a>
        <a class="warm_cancel">取消</a>

    </div>
</div>
</body>
<script type="text/javascript" src="../js/receipt/moreReceipts.js"></script>
</html>
