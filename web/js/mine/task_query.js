$(function(){
    var listHtml = '';
    var num = 2018;
    for(var j=0;j<100;j++){
        listHtml += '<li>'+(num+j)+'</li>'
    }
    $('#year_sel ul').html(listHtml);
    $('#cl_sel').click(function(){
        $('#year_sel').toggle();
    })
    $('#year_sel ul li').click(function(){
        $('.year').html($(this).html());
        $('#year_sel').hide();
    })
    $('#cli_sel').click(function(){
        $('#mont_sel').toggle();
    })
    $('#mont_sel ul li').click(function(){
        $('.month').html($(this).html());
        $('#mont_sel').hide();
    })

    $('#query_btn').click(function(){
        var year = $('.year').html();
        var month = $('.month').html();
        $.ajax({
            url: domain_name_url + "/task",
            type: "GET",
            dataType: "jsonp", //指定服务器返回的数据类型
            data: {
                method: 'searchTask',
                userId: userId,
                year:year,
                month:month,
                url_type:"task"
            },
            success: function(data) {
                if(data.success==1){
                    $('.query_list ul').show();
                    var queryRes = data.result.rs[0].result.result.rs;
                    if(queryRes.length!=0){
                        var queryListHtml='';
                        for(var i=0;i<queryRes.length;i++){
                            var money = queryRes[i].bonus;
                            var taskEndTime = queryRes[i].agree_time;
                            var taskTime = "20" + taskEndTime.substring(0, 2) + "-" + taskEndTime.substring(2, 4) + "-" + taskEndTime.substring(4, 6) + " " + taskEndTime.substring(6, 8) + ":" + taskEndTime.substring(8, 10) + ":" + taskEndTime.substring(10, 12);
                            queryListHtml+='<li>';
                            queryListHtml+='<div class="query_left">';
                            queryListHtml+='<p class="left_per">';
                            queryListHtml+='<span>'+queryRes[i].category_name+'</span>&nbsp;&nbsp;';
                            queryListHtml+='<span>奖励'+(money/100).toFixed(2)+'元</span>';
                            queryListHtml+='</p>';
                            queryListHtml+='<p class="left_pro">';
                            queryListHtml+='<span class="pro_plan">已完成</span>';
                            queryListHtml+='<span>'+taskTime+'</span>';
                            queryListHtml+='</p>';
                            queryListHtml+='</div>';
                            queryListHtml+='<div class="query_right">';
                            queryListHtml+='￥ <span>'+(money/100).toFixed(2)+'</span>';
                            queryListHtml+='</div>';
                            queryListHtml+='</li>';
                        }
                        $('.query_list ul').html(queryListHtml);
                    }else{
                        $('.query_no').show();
                    }
                }else{

                }
            }
        })
    })

})