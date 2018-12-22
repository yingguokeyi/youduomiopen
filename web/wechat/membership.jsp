<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/11/14
  Time: 11:45
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
    <link rel="stylesheet" href="../css/mine/membership.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <title>个人中心</title>
</head>
<body>
<div class="main">
    <div class="main_top">
        <header>
            <div class="title_top">
                <a href="mine.jsp?userId=<%=userId%>" class="title_top_first">
                    <img src="../image/mine/return.png" class="hea_img" />
                </a>
                <span class="title_top_center">会员资料</span>
                <span style="float:right;margin-right:.26rem;font-size:.28rem;color:#666" onclick="window.location='${request.contextPath}/youduomiopen/wechat/modified_data.jsp'">修改</span>
            </div>
        </header>
        <div class="mem_name">
            <p class="mem_head">
                <img id="headImg"  />
            </p>
            <p class="mem_per">
                <span id="set_nickname"></span>
            </p>
        </div>
    </div>
    <div class="main_bot">
        <ul>
            <li style="overflow:hidden" class="bot_li">
                <div style="float:left;" class="bot_p">
                    <p>会&nbsp;&nbsp;员&nbsp;&nbsp;ID:</p>
                    <p><i id="iIds"></i></p>
                </div>
                <div style="float:right; width:15%;margin-right:0">
                    <button class="copy" data-clipboard-action="copy" data-clipboard-target="i">复制</button>
                </div>
            </li>
            <li class="bot_li">
                <div class="bot_p">
                    <p>会员级别:</p>
                    <p id="memberLevel"><em></em></p>
                </div>
            </li>
            <li class="bot_li">
                <div class="bot_p">
                    <p>上级会员:</p>
                    <p id="parentMember"><em>缘来如此 (528572)</em></p>
                </div>
            </li>
            <li id="iId" class="bot_li">
                <div class="bot_p">
                    <p>会员级别:</p>
                    <p><em></em></p>
                </div>
            </li>
            <li class="bot_li">
                <div class="bot_p">
                    <p>真实姓名:</p>
                    <p id="memberName"><em></em></p>
                </div>
            </li>
            <li class="bot_li">
                <div class="bot_p">
                    <p>手机号码:</p>
                    <p id="memberPhone"><em></em></p>
                </div>
            </li>
            <li class="bot_li">
                <div class="bot_p">
                    <p>所在省份:</p>
                    <p id="my_province"><em></em></p>
                </div>
            </li>
            <li class="bot_li">
                <div class="bot_p">
                    <p>城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市:</p>
                    <p id="my_city"><em></em></p>
                </div>
            </li>
            <li class="bot_li">
                <div class="bot_p">
                    <p>区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;域:</p>
                    <p id="my_area"><em></em></p>
                </div>
            </li>
            <li style="height:1.56rem">
                <div class="bot_p">
                    <p style="margin-top:.3rem;">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址:</p>
                    <p><em></em></p>
                </div>
            </li>
        </ul>
    </div>
</div>
</body>
<script type="text/javascript" src="../assets/js/clipboard.min.js"></script>
<script type="text/javascript" src="../js/mine/membership.js"></script>
</html>

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
                    $("#iIds").html(data.wxMember.result.rs[0].Invitation_code);
                    $("#memberLevel em").html(data.wxMember.result.rs[0].member_level == 1?"普通会员":"vip会员")
                    $("#parentMember em").html(data.parentMember.result.rs[0].wx_nick_name);
                    $("#iId em").html(data.parentMember.result.rs[0].member_level == 1?"普通会员":"vip会员");
                    $('#memberName').html(data.wxMember.result.rs[0].real_name);
                    $('#memberPhone').html(data.wxMember.result.rs[0].phone.substr(0, 3) + '****' +data.wxMember.result.rs[0].phone.substr(7));

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
