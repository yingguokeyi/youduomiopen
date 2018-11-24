<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/11/10
  Time: 11:44
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
    <link rel="stylesheet" href="../css/receipt/receipt.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <script type="text/javascript" src="../assets/js/jsonselect.js"></script>
    <title>小票查询</title>
</head>
<body style="background:#fff;">
<div class="main">
    <div class="main_bot">
        <div class="wx_sum">
            <p>
                <input type="text" id="demo1" placeholder="请输入“小票号”进行查询" value="" />
            </p>
            <button type="button" id="query">查询</button>
        </div>
    </div>
    <!-- 没有查询记录时显示的内容 -->
    <div class="without">
        <p class="wit_condition">还没有你的历史记录</p>
        <p class="wit_service">赶快点开查询，查看您的购买信息</p>
    </div>
    <!-- 查询记录时已有的历史记录 -->
    <diV class="exist">
        <div class="discountgoods_title">
            <div class="kong"></div>
            <ul>
                <li class="synthesize">
                    <%--<img class="synthesize_img" src="../image/receipt/flashback.png"/>--%>
                        <span class="synthesize_img"></span>
                        <span class="synthesizes_img"></span>
                </li>
                <li class="sales">上传历史</li>
                <li class="price">
                    <a href="moreReceipts.jsp?userId=<%=userId%>">  更多<span class="p_ret"></span></a>
                </li>
            </ul>
        </div>
        <!-- 查询的列表 -->
        <div class="withdrawal_record">
            <ul>
            </ul>
        </div>

    </diV>
</div>
<!-- 弹框 -->
<div class="warm" style="display: none;">
    <div class="warm_title">
        <h4>提示</h4>
        <p>该小票未被上传</p>
    </div>
    <div class="warm_choose">
        <a class="warm_cancel">取消</a>
        <a class="warm_login" href="toUploadReceipts.jsp">上传小票</a>
    </div>
</div>
</body>
<script type="text/javascript" src="../js/receipt/receipt.js"></script>
</html>
