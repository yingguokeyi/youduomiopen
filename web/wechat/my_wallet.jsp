<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/11/14
  Time: 10:57
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
    <link rel="stylesheet" href="../css/mine/my_wallet.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <title>我的钱包</title>
</head>
<body>
<div class="main">
    <div class="main_top">
        <div class="wallet_total">
            <p class="wallet_btn">
                <button id="wit_btn" onclick="window.location='${request.contextPath}/youduomiopen/wechat/withdraw.jsp'">提现</button>
            </p>
            <p class="wallet_num">
                <span class="wallet_zo">总资产:<span><i></i>元</span></span>
            </p>
        </div>
    </div>
    <div class="main_middle">
        <ul>
            <li>
                <p id="current_balance"><i></i>元</p>
                <p>当前余额</p>
                <div class="mid_line"></div>
            </li>

            <li>
                <p id="have_withdrawal"><i></i>元</p>
                <p>已提现</p>
            </li>
        </ul>
    </div>
    <div class="main_bot">
        <ul>
            <li class="first">
                <a href="history_withdraw.jsp?userId=<%=userId%>&openid=<%=openid%>" class="first_a">
							<span class="title_content">
								<span>提现记录</span>
							</span>
                    <span class="back_span">
								<img src="../image/mine/enter.png" />
							</span>
                </a>
            </li>
            <li class="first">
                <a href="accounting_withdraw.jsp?userId=<%=userId%>&openid=<%=openid%>" class="first_a">
							<span class="title_content">
								<span>入账记录</span>
							</span>
                    <span class="back_span">
								<img src="../image/mine/enter.png" />
							</span>
                </a>
            </li>
            <li class="first">
                <a href="bind_bankCard.jsp?userId=<%=userId%>&openid=<%=openid%>" class="first_a">
							<span class="title_content">
								<span>绑定银行卡</span>
							</span>
                    <span class="back_span">
								<img src="../image/mine/enter.png" />
							</span>
                </a>
            </li>
        </ul>
    </div>
</div>
</body>
<script>
    var openid = "<%=openid%>";
    var userId= "<%=userId%>";
</script>
<script type="text/javascript" src="../js/mine/my_wallet.js"></script>
</html>
