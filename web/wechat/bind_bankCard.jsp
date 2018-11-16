<%--
  Created by IntelliJ IDEA.
  User: sdmin
  Date: 2018/11/14
  Time: 11:26
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
    <link rel="stylesheet" href="../css/mine/bind_bankCard.css" />
    <link rel="stylesheet" href="../assets/layer_mobile/need/layer.css" />
    <script type="text/javascript" src="../assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../assets/layer_mobile/layer.js"></script>
    <script type="text/javascript" src="../assets/js/font-size.js"></script>
    <script type="text/javascript" src="../assets/js/global_variable.js"></script>
    <title>我的钱包</title>
</head>
<body>
<div class="main">
    <header>
        <div class="title_top">
            <a href="" class="title_top_first">
                <img src="../image/mine/return.png" class="hea_img" />
            </a>
            <span class="title_top_center">绑定银行卡</span>
        </div>
    </header>
    <div class="message">
        <ul>
            <li>
						<span>
							<label>*选&nbsp;择&nbsp;卡&nbsp;类:</label>
							<i class="fill">
								<select>
									<option value=""></option>
									<option value="中国工商银行">中国工商银行</option>
									<option value="中国农业银行">中国农业银行</option>
									<option value="中国建设银行">中国建设银行</option>
									<option value="中国银行">中国银行</option>
									<option value="中国邮政储蓄银行">中国邮政储蓄银行</option>
									<option value="招商银行">招商银行</option>
									<option value="交通银行">交通银行</option>
								</select>
							</i>
						</span>
                <!-- <span class="card_choose">
                    <img src="../../image/mine/enter.png" />
                </span> -->
            </li>
            <li>
						<span>
							<label>*开户人姓名:</label>
							<input type="text" value=""  class="fill" />
						</span>
            </li>
            <li>
						<span>
							<label>*银&nbsp;行&nbsp;卡&nbsp;号:</label>
							<input type="text" value=""  class="fill" />
						</span>
            </li>
            <li>
						<span>
							<label>*开户手机号:</label>
							<input type="text" value=""  class="fill" />
						</span>
            </li>
            <li>
						<span>
							<label>*所&nbsp;在&nbsp;省&nbsp;份:</label>
							<i class="fill"><select id="s_province" name="s_province"></select></i>
						</span>
                <!-- <span class="card_choose">
                    <img src="../../image/mine/enter.png" />
                </span> -->
            </li>
            <li>
						<span>
							<label>*城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市:</label>
							<i class="fill"><select id="s_city" name="s_city" ></select></i>
						</span>
                <!-- <span class="card_choose">
                    <img src="../../image/mine/enter.png" />
                </span> -->
            </li>
            <li>
						<span>
							<label>*区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;域:</label>
							<i class="fill"><select id="s_county" name="s_county"></select></i>
						</span>
                <!-- <span class="card_choose">
                    <img src="../../image/mine/enter.png" />
                </span> -->
            </li>
            <script type="text/javascript" src="../../assets/js/area1.js"></script>
            <script type="text/javascript">_init_area();</script>
            <li>
						<span>
							<label>*地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址:</label>
							<textarea class="fill" style="margin-top:.05rem;"></textarea>
						</span>
            </li>
        </ul>
    </div>
    <div class="main_btn">
        <button type="button" id="sure_btn" disabled="">确定</button>
        <p>*请认真核对填写信息,以免给您提现造成不便</p>
    </div>
</div>
</body>
<script type="text/javascript" src="../js/mine/bind_bankCard.js"></script>
</html>
