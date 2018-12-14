$("#recharge").click(function () {
    $.ajax({
        url: domain_name_url + "/order",
        type: "GET",
        dataType: "json", //指定服务器返回的数据类型
        data: {
            method: 'createOrder',
            openid:openid,
            transactionBody:"购买会员",
            spbillCreateIp:"10.1.0.23"
        },
        success: function(data) {
            $('.mian_btn_login').attr('disabled',true);
            $('.mian_btn_login').css({'background':'#b4b4b4','color':'#fff'});
            var da_success = data.success;
            if(da_success == 1) {
                WeixinJSBridge.invoke(
                    'getBrandWCPayRequest', {
                        "appId":"wxb9787523cec7fc6d",     //公众号名称，由商户传入
                        "timeStamp":"1395712654",         //时间戳，自1970年以来的秒数
                        "nonceStr":"e61463f8efa94090b1f366cccfbbb444", //随机串
                        "package":"prepay_id=u802345jgfjsdfgsdg888",
                        "signType":"MD5",         //微信签名方式：
                        "paySign":"70EA570631E4BB79628FBCA90534C63FF7FADD89" //微信签名
                    },
                    function(res){
                        if(res.err_msg == "get_brand_wcpay_request:ok" ){
                            // 使用以上方式判断前端返回,微信团队郑重提示：
                            //res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
                        }
                    });

            }
        }
    });
});