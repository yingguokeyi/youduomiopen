<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/12/13
  Time: 15:39
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
    <link rel="stylesheet" href="../css/mine/report.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <title>天天赚</title>
</head>
<body style="background:#fff;">
<div class="main">
    <header>
        <div class="title_top">
            <a href="task_details.jsp" class="title_top_first">
                <img src="../image/mine/return.png" class="hea_img" />
            </a>
            <span class="title_top_center" style="font-size:.3rem;">举报</span>
        </div>
    </header>
    <ul class="report_reasons">
        <p class="report_title">
            举报原因:
        </p>
        <li>
            <!-- <input type="radio" name="text" value="" id="int1"> -->
            <img src="../image/mine/registernoc.png" id="test1" />
            <span>任务信息有误，无法提交完成。</span>
        </li>
        <li>
            <!-- <input type="radio" name="text" value="" id="int2"> -->
            <img src="../image/mine/registernoc.png" id="test2" />
            <span>审核太慢，已超过三天了。</span>
        </li>
        <li>
            <!-- <input type="radio" name="text" value="" id="int3"> -->
            <img src="../image/mine/registernoc.png" id="test3" />
            <span>存在虚假骗人等违法违规行为。</span>
        </li>
    </ul>
    <ul class="report_reasons">
        <p class="report_title">
            内容描述:
        </p>
        <li>
            <textarea placeholder="(描述文字最多200字)" id="report_description" onkeyup="this.value = this.value.substring(0,200)" value=""></textarea>
        </li>
    </ul>
    <div class="report_btn">
        <button type="button" disabled="">举报</button>
    </div>
</div>
</body>
<script>
    var openid = "<%=openid%>";
    var userId= "<%=userId%>";
</script>
<script type="text/javascript" src="../js/mine/report.js"></script>
</html>
