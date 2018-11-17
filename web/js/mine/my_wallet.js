$(function(){
	$.ajax({
		url: domain_name_url + "/wallet",
		type: "GET",
		dataType: "jsonp", //指定服务器返回的数据类型
		data: {
			method: 'getWalletMoney',
			userId: userId,
			url_type:"wallet"
		},
		success: function(data) {
			var walletRes = data.result.rs[0];
			$('.wallet_zo span i').html((walletRes.money/100).toFixed(2));
			$('#current_balance i').html((walletRes.balance/100).toFixed(2));
			$('#have_withdrawal i').html((walletRes.withdraw/100).toFixed(2));
		}
	})
})