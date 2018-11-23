<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/11/9
  Time: 14:38
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
    <link rel="stylesheet" href="../css/mine/mine.css" />
    <link rel="stylesheet" href="../assets/css/swiper.min.css"/>
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <script type="text/javascript" src="../assets/js/swiper.min.js"></script>
    <title>个人中心</title>
</head>
<script>
    var openid = "<%=openid%>"
        window.onload =function(){
            $.ajax({
                type: "get",
                url: "${ctx}/youduomiopen/wxUser?method=getUserInfo&openid="+openid,
                /*data: {"openid":openid},*/
                contentType:"application/json",  //缺失会出现URL编码，无法转成json对象
                cache: false,
                async : false,
                dataType: "json",
                success:function(data) {
                    if(data.message ==1){
                        $("#set_nickname").html(data.wxMember.result.rs[0].wx_nick_name);
                        $("#headImg").attr("src",data.wxMember.result.rs[0].head_image);
                        $("#iId em").html(data.wxMember.result.rs[0].id);
                        $("#pid").html(data.userWallet.result.rs[0].balance/100+"元");
                        $("#lowerId").html(data.lowerCount+"人");
                    }else{
                        alert("error");
                    }
                },
                error : function() {
                    alert("出错了");
                }
            });
        }
</script>
<body style="background:#fff;">
<div class="main">
    <div class="main_top">
        <ul class="hea_ul">
            <li class="first">
                <a id="fir_per" href="membership.jsp?openid=<%=openid%>">
                    <img id="headImg" />
                </a>
            </li>
            <li class="second">
                <p><span id="set_nickname"></span>&nbsp;&nbsp;(<i id="iId">ID<em></em></i>)<button class="copy" data-clipboard-action="copy" data-clipboard-target="em">复制</button></p>
                <p class="rank">普通会员</p>
            </li>
        </ul>
    </div>
    <div class="main_middle">
        <ul>
            <li>
                <p id="pid"></p>
                <p>当前资产</p>
                <div class="mid_line"></div>
            </li>

            <li>
                <p id="lowerId"></p>
                <p>我的团队</p>
            </li>
        </ul>
        <!-- 轮播 -->
        <%--<div class="swiper-container" id="lunboIdOut">--%>
            <%--<div class="swiper-wrapper" id="lunboIdIn">--%>
                <%--<div class="swiper-slide">--%>
                    <%--<img src="../image/mine/goods_list.jpg" alt="">--%>
                <%--</div>--%>
                <%--<div class="swiper-slide">--%>
                    <%--<img src="../image/mine/goods_list.jpg" alt="">--%>
                <%--</div>--%>
                <%--<div class="swiper-slide">--%>
                    <%--<img src="../image/mine/goods_list.jpg" alt="">--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="swiper-pagination "></div>--%>
        <%--</div>--%>
        <div id="lunboIdOut">
            <img src="../image/mine/goods_list.jpg" alt="">
        </div>
    </div>
    <div class="main_bot">
        <ul>
            <li onclick="window.location='${request.contextPath}/youduomiopen/wechat/my_team.jsp?openid=<%=openid%>&userId=<%=userId%>'">
                <p><img src="../image/mine/team.png" /></p>
                <p>我的团队</p>
            </li>
            <li onclick="window.location='${request.contextPath}/youduomiopen/wechat/my_wallet.jsp?openid=<%=openid%>&userId=<%=userId%>'">
                <p><img src="../image/mine/wallet.png" /></p>
                <p>我的钱包</p>
            </li>
            <li onclick="window.location='${request.contextPath}/youduomiopen/wechat/receipt.jsp?openid=<%=openid%>&userId=<%=userId%>'">
                <p><img src="../image/mine/query.png" /></p>
                <p>小票查询</p>
            </li>
            <li onclick="window.location='${request.contextPath}/youduomiopen/wechat/toUploadReceipts.jsp?openid=<%=openid%>&userId=<%=userId%>'">
                <p><img src="../image/mine/upload.png" /></p>
                <p>小票上传</p>
            </li>
            <li onclick="window.location='${request.contextPath}/youduomiopen/wechat/subsidies_query.jsp?openid=<%=openid%>&userId=<%=userId%>'">
                <p><img src="../image/mine/subsidy.png" /></p>
                <p>补贴查询</p>
            </li>
            <li onclick="window.location='${request.contextPath}/youduomiopen/wechat/online_service.jsp?openid=<%=openid%>&userId=<%=userId%>'">
                <p><img src="../image/mine/service.png" /></p>
                <p>客服服务</p>
            </li>
            <li onclick="window.location='../makeEveryDay/makeEveryDay.html'">
                <p><img src="../image/mine/make_every.png" /></p>
                <p>天天赚</p>
            </li>
            <li onclick="window.location='${request.contextPath}/youduomiopen/wechat/task_query.jsp?openid=<%=openid%>&userId=<%=userId%>'">
                <p><img src="../image/mine/task_query.png" /></p>
                <p>任务查询</p>
            </li>

        </ul>
    </div>
</div>
</body>

<script type="text/javascript" src="../assets/js/clipboard.min.js"></script>
<script type="text/javascript" src="../js/mine/mine.js"></script>
</html>
