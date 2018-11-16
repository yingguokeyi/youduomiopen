console.log("openid",openid);
//上传图片
function receiptImg() {
    var formData = new FormData();
    var img_file = document.getElementById("doc");
    var fileObject = img_file.files[0];
    formData.append("file", fileObject);
    formData.append("openid",openid);
    $.ajax({
        url: domain_name_url+"/receipts?method=uploadImg",
        type: "POST",
        dataType: "json",
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            console.log("sendImg",data.result)
        },
        error: function (data) {
            console.log("sendImg",data.result)
        }
    });
}


$(function(){

  // 弹框 ----上传
  $(".up").click(function() {
    layer.open({
        type: 1,
        content: $('.warm').html(),
        anim: 'up',
        scrollbar: false,
        shadeClose: false,
        style: 'position:fixed;bottom:50%;left: 8%; right:8%;height: auto;border:none;border-radius:6px'
        
    });
    
})
//点击确定退出登录
$(document).on("click", ".warm_login", function(){
    layer.closeAll('page');
    });

});

//下面用于图片上传预览功能
function setImagePreview(avalue) {
    //input
    var docObj = document.getElementById("doc");
    //img
    var imgObjPreview = document.getElementById("preview");
    //div
    var divs = document.getElementById("localImag");
    var add = document.getElementById("add");
        if (docObj.files && docObj.files[0]) {
            //火狐下，直接设img属性
            imgObjPreview.style.display = 'block';
            imgObjPreview.style.width = '1.8rem';
            imgObjPreview.style.height = '1.8rem';
            //imgObjPreview.src = docObj.files[0].getAsDataURL();
            //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
            imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
            add.style.display="none";
            $("#btn").removeAttr("disabled");
			$('#btn').css({'background':'#b61c25','color':'#fff'});
        } else {
            //IE下，使用滤镜
            docObj.select();
            var imgSrc = document.selection.createRange().text;
            var localImagId = document.getElementById("localImag");
            //必须设置初始大小
            localImagId.style.width = "1.8rem";
            localImagId.style.height = "1.8rem";
            //图片异常的捕捉，防止用户修改后缀来伪造图片
            try {
                localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
                add.style.display="none";
                $("#btn").removeAttr("disabled");
				$('#btn').css({'background':'#b61c25','color':'#fff'});
            } catch(e) {
                alert("您上传的图片格式不正确，请重新选择!");
                return false;
            }
            imgObjPreview.style.display = 'none';
            document.selection.empty();
        }
    var formData = new FormData();
    var img_file = document.getElementById("doc");
    var fileObject = img_file.files[0];
    formData.append("file", fileObject);
    formData.append("openid",openid);
    $.ajax({
        url: domain_name_url+"/receipts?method=uploadImg",
        type: "POST",
        dataType: "json",
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            console.log("order",data.result);
            $('#le').val(data.result);
            // 判断是否填写小票号
            var name = document.getElementById("le").value;
            if(data.code == "0"){
                $('.push_button').css({display: 'block'});

            }else {
                $('.push_button').css({display: 'none'});
            }
        },
        error: function (data) {
            console.log("order",data.result)
        }
    });
    return true;
}


