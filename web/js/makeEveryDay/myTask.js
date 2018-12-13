window.jsel = JSONSelect;
var page;
var urlStatus;
page = 1;
//任务栏
var id='';

$(function(){
    ask();
    placard();
	$(".bot_ul li").click(function() {
		$(this).children("a").addClass("tabhover").parent().siblings().find("a").removeClass("tabhover");
	})
	$('#whole').click(function(){
		placard();
	})
    
})

// 头部的内容 ask(page,urlStatus)
function ask(){
    $.ajax({
        url: domain_name_url + "/task",
        type: "GET",
        dataType: "jsonp", //指定服务器返回的数据类型
        data: {
            method: 'getUserTask',
            userId: 4599,
            status:1,
            url_type:"task"
        },
        success: function(data) {
          
            //头部内容
            var headNumber = data.result.rs[1].result2;
            var headHtml ='';
            headHtml += '<li>';
            headHtml += '<p>'+headNumber.num+'个</p>';
            headHtml += '<p>今日任务</p>';
            headHtml += '<div class="mid_line"></div>';
            headHtml += '</li>';
            headHtml += '<li>';
            headHtml += '<p>'+(headNumber.money/100).toFixed(2)+'元</p>';
            headHtml += '<p>预计奖励</p>';
            headHtml += '<div class="mid_line"></div>';
            headHtml += '</li>';
            headHtml += '<li>';
            headHtml += '<p>'+(headNumber.allMoney/100).toFixed(2)+'元</p>';
            headHtml += '<p>累计收入</p>';
            headHtml += '</li>';
          $('.main_middle ul').html(headHtml);
        }
        
    })
      
}

// tab切换---刚进到页面获取的数据    
function placard(page){
    $.ajax({
        url: domain_name_url + "/task",
        type: "GET",
        dataType: "jsonp", //指定服务器返回的数据类型
        data: {
            method: 'getUserTask',
            userId: 4599,
            status:1,
            url_type:"task"
        },
        success: function(data) {
            if(data.success==1){
                var cutRst = data.result.rs[1].result2;//tab里面的内容
                var detailsRst = data.result.rs[0].result;//获取的内容
                $('#whole em').html(cutRst.state1);
                $('#conduct em').html(cutRst.state2);
                $('#toAudit em').html(cutRst.state3);
                $('#completed em').html(cutRst.state4);
                var runId = jsel.match('.id', detailsRst);//获得id
                var phaseState = jsel.match('.state', detailsRst);//获得状态state
                var walletBonus = jsel.match('.bonus', detailsRst);//获得钱bonus
                var captionName = jsel.match('.category_name', detailsRst);//获得标题category_name
                var stopTime = jsel.match('.create_end_time', detailsRst);//结束时间
                var goodListHtml = '';
                if(detailsRst.length!=0){
                    for(var i= 0 ; i<detailsRst.length;i++){
                        if(detailsRst[i].state == 1){//进行中
                             //获取开始时间
                            var startTime = detailsRst[i].create_time;
                            id = detailsRst[i].id;
                            
                            // 开始时间的总秒数
                            var startTimetm = "20" + startTime.substring(0, 2) + "/" + startTime.substring(2, 4) + "/" + startTime.substring(4, 6) + " " + startTime.substring(6, 8) + ":" + startTime.substring(8, 10) + ":" + startTime.substring(10, 12);
                            var startDate = new Date(startTimetm).getTime();
                            
                            // 获取结束时间
                            var endTime = detailsRst[i].create_end_time;
                            // 结束时间的总秒数
                            sekillEndTime = "20" + endTime.substring(0, 2) + "/" + endTime.substring(2, 4) + "/" + endTime.substring(4, 6) + " " + endTime.substring(6, 8) + ":" + endTime.substring(8, 10) + ":" + endTime.substring(10, 12);
                            var endTDate = new Date(sekillEndTime).getTime();
                            
                            //获取当前时间
                            var currentDate = new Date();
                            currentDate = currentDate.getTime();
                            //时间段要注意两种情况一种是刚进来就已经开始倒计时，还有就是到页面还没有倒计时，就用结束的时间减去当前的时间
                            var totalSecond;
                            if (startDate < currentDate  && currentDate <= endTDate) {//已经在倒计时了
                                totalSecond = parseInt((endTDate - currentDate) / 1000);
                                setTimeout(function () {//已经在倒计时了
                                    countdown(totalSecond)
                                    },1000)
                            } 
                       
                            goodListHtml += '<li class="main_content_li mtw_k" data-id='+runId[i]+'  data-state='+phaseState[i]+'  data-bonus='+walletBonus[i]+'  data-category_name='+captionName[i]+' data-create_end_time='+stopTime[i]+'>';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/money.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="p_purse">'+(detailsRst[i].bonus/100).toFixed(2)+'</span>';
                            goodListHtml += '<span class="main_content_a_right">';
                            goodListHtml += '<span class="m_c_a_r_top">'+detailsRst[i].category_name+' <i class="just_now">刚刚 </i></span>';
                            goodListHtml += '<span class="m_c_a_r_bottom">';
                            goodListHtml += '<span class="m_c_a_r_bottomleft" id="drew"></span>';
                            goodListHtml += '</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<a href="#" class="main_content_a">';
                            goodListHtml += '<div class="particulars">进行中</div>';
                            goodListHtml += '</a>';
                            goodListHtml += '</li>';
                        }
                        if(detailsRst[i].state == 3){//审核中
                            goodListHtml += '<li class="main_content_li mtw_k" data-id='+runId[i]+'  data-state='+phaseState[i]+'  data-bonus='+walletBonus[i]+'  data-category_name='+captionName[i]+'>';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/money.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="p_purse">'+(detailsRst[i].bonus/100).toFixed(2)+'</span>';
                            goodListHtml += '<span class="main_content_a_right">';
                            goodListHtml += ' <span class="m_c_a_r_top">'+detailsRst[i].category_name+' <i class="just_now">'+warnTime+'</i></span>';
                            goodListHtml += '<span class="m_c_a_r_bottom">';
                            goodListHtml += '<span class="m_c_a_r_orange">计1-23小时，超时自动到账</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<a href="#" class="main_content_a">';
                            goodListHtml += ' <div class="aerea">审核中</div>';
                            goodListHtml += '</a>';
                            goodListHtml += '</li>';
                        }
                         if(detailsRst[i].state == 4){//审核失败
                            
                            goodListHtml += '<li class="main_content_li" id="task_apply">';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/money.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="p_purse">'+(detailsRst[i].bonus/100).toFixed(2)+'</span>';
                            goodListHtml += '<span class="main_content_a_right">';
                            goodListHtml += '<span class="m_c_a_r_top">'+detailsRst[i].category_name+'<i class="just_now">'+warnTime+'</i></span>';
                            goodListHtml += '<span class="m_c_a_r_bottom">';
                            goodListHtml += '<span class="m_c_a_r_red">审核失败</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<div class="bonus">查看</div>';
                            goodListHtml += '</li>';
                        }
                        if(detailsRst[i].state == 5){//已完成
                            
                            goodListHtml += '<li class="main_content_li mtw_k" data-id='+runId[i]+'  data-state='+phaseState[i]+'  data-bonus='+walletBonus[i]+'  data-category_name='+captionName[i]+'>';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/money.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="p_purse">'+(detailsRst[i].bonus/100).toFixed(2)+'</span>';
                            goodListHtml += '<span class="main_content_a_right">';
                            goodListHtml += '<span class="m_c_a_r_top">'+detailsRst[i].category_name+'<i class="just_now">'+warnTime+'</i></span>';
                            goodListHtml += '<span class="m_c_a_r_bottom">';
                            goodListHtml += ' <span class="m_c_a_r_green">获得奖励  '+(detailsRst[i].bonus/100).toFixed(2)+'元</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<div class="main_content_a">';
                            goodListHtml += '<div class="creoline">已完成</div>';
                            goodListHtml += '</div>';
                            goodListHtml += '</li>';
                        }
                        if(detailsRst[i].state == 6){//已过期
                            
                            goodListHtml += '<li class="main_content_li">';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/ash.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="y_purse">'+(detailsRst[i].bonus/100).toFixed(2)+' </span>';
                            goodListHtml += '<span class="main_content_a_ash">';
                            goodListHtml += '<span class="m_c_a_r_grey">'+detailsRst[i].category_name+'<i class="just_now">'+warnTime+' </i></span>';
                            goodListHtml += '<span class="m_c_a_r_ash">';
                            goodListHtml += '<span class="m_c_a_r_bottomlefts">任务已过期</span>';
                            goodListHtml += ' </span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<div class="main_content_a">';
                            goodListHtml += ' <div class="overdue">已过期</div>';
                            goodListHtml += ' </div>';
                            goodListHtml += ' </li>'; 
                        }
        
                    }
                    if(12*page>12){
                        $('#orderContent ul').append(goodListHtml);
                        $('.mtw_k').click(function(){
                            var pastMoney = $(this).data('bonus');//奖励钱
                            var pastTitle = $(this).data('category_name');//标题
                            var uri = $(this).data('id');//id
                            var pastState = $(this).data('state');//获得状态state
                            var board = $(this).data('create_end_time');//时间
                            sStorage = window.localStorage; //本地存题目
                          
    
                            sStorage.uri_goods = uri;//id
                            sStorage.equation= pastState;//获得状态state
                            sStorage.cash= (pastMoney/100).toFixed(2);//奖励钱
                            sStorage.slogan= pastTitle;//标题
                            sStorage.endingTime = board;//时间
                            var gurl = window.location.href;
        
                            localStorage.setItem('gurl', window.location.href);
                            location.href = '../mine/task_details.html?spuId=' + uri + '&url=' + gurl ;
                        })
        
                    }else{
                        $('#orderContent ul').html(goodListHtml);
                        $('.mtw_k').click(function(Countdown){
                            var pastMoney = $(this).data('bonus');//奖励钱
                            var pastTitle = $(this).data('category_name');//标题
                            var uri = $(this).data('id');//id
                            var pastState= $(this).data('state');//获得状态state
                            var board = $(this).data('create_end_time');//时间
                            sStorage = window.localStorage; //本地存题目
                         
        
                            sStorage.uri_goods = uri;//id
                            sStorage.equation= pastState;//获得状态state
                            sStorage.cash= (pastMoney/100).toFixed(2);//奖励钱
                            sStorage.slogan= pastTitle;//标题
                            sStorage.endingTime = board;//时间
                            var gurl = window.location.href;
        
                            localStorage.setItem('gurl', window.location.href);
                            location.href = '../mine/task_details.html?spuId=' + uri + '&url=' + gurl ;
                        })
                    } 

                    // 查看出现弹框
                    $(function(){
                        $('.close').click(function(){
                            $('#modal_help').hide();
                            $('#modal_apply').hide();
                        })
                        $('#task_apply').click(function(){
                            $('#modal_apply').show();
                        })
                        $('#sure').click(function(){
                            var uri = $(this).data('id');//id
                            var pastState = $(this).data('state');//获得状态state
                            var pastMoney = $(this).data('bonus');//奖励钱
                            var pastTitle = $(this).data('category_name');//标题
                            sStorage = window.localStorage; //本地存题目
        
                            sStorage.uri_goods = uri;//id
                            sStorage.equation= pastState;//获得状态state
                            sStorage.cash= (pastMoney/100).toFixed(2);//奖励钱
                            sStorage.slogan= pastTitle;//标题
                            var gurl = window.location.href;
        
                            localStorage.setItem('gurl', window.location.href);
                            location.href = '../mine/task_details.html?spuId=' + uri +'&url=' + gurl ;

                        })
                    })
                   
    
                }

            }else{
                $('#orderContent').html('<span class="information">全部中还没有信息</span>');
            }
           
          
            // ask(1,urlStatus);
            $.fn.navbarscroll = function (options) {
                //各种属性、参数
                var _defaults = {
                    className:'cur', //当前选中点击元素的class类名
                    clickScrollTime:300, //点击后滑动时间
                    duibiScreenWidth:0.4, //单位以rem为准，默认为0.4rem
                    scrollerWidth:3, //单位以px为准，默认为3,[仅用于特殊情况：外层宽度因为小数点造成的不精准情况]
                    defaultSelect:0, //初始选中第n个，默认第0个
                    fingerClick:0, //目标第0或1个选项触发,必须每一项长度一致，方可用此项
                    endClickScroll:function(thisObj){}//回调函数
                }
                var _opt = $.extend(_defaults, options);
                this.each(function () {
                    //插件实现代码
                    var _wrapper = $(this);
                    var _win = $(window);
                    var _win_width = _win.width(),_wrapper_width = _wrapper.width(),_wrapper_off_left = _wrapper.offset().left;
                    var _wrapper_off_right=_win_width-_wrapper_off_left-_wrapper_width;
                    var _obj_scroller = _wrapper.children('.team_tile');
                    var _obj_ul = _obj_scroller.children('ul');
                    var _obj_li = _obj_ul.children('li');
                    var _scroller_w = 0;
                    for (var i = 0; i < _obj_li.length; i++) {
                        _scroller_w += _obj_li[i].offsetWidth;
                    }
                    _obj_scroller.width(_scroller_w+_opt.scrollerWidth);
                    var myScroll = new IScroll('#'+_wrapper.attr('id'), {
                        eventPassthrough: true,
                        scrollX: true,
                        scrollY: false,
                        preventDefault: false
                    });
                    _init(_obj_li.eq(_opt.defaultSelect));
                  
                    
                    //解决PC端谷歌浏览器模拟的手机屏幕出现莫名的卡顿现象，滑动时禁止默认事件（2017-01-11）
                    _wrapper[0].addEventListener('touchmove',function (e){e.preventDefault();},false);
                    function _init(thiObj){
                        var $this_obj=thiObj;
                        // var duibi=_opt.duibiScreenWidth*_win_width/10,this_index=$this_obj.index(),this_off_left=$this_obj.offset().left,this_pos_left=$this_obj.position().left,this_width=$this_obj.width(),this_prev_width=$this_obj.prev('li').width(),this_next_width=$this_obj.next('li').width();
                        // var this_off_right=_win_width-this_off_left-this_width;
                        if(_scroller_w+2>_wrapper_width){
                            if(_opt.fingerClick==1){
                                if(this_index==1){
                                    myScroll.scrollTo(-this_pos_left+this_prev_width,0, _opt.clickScrollTime);
                                }else if(this_index==0){
                                    myScroll.scrollTo(-this_pos_left,0, _opt.clickScrollTime);
                                }else if(this_index==_obj_li.length-2){
                                    myScroll.scrollBy(this_off_right-_wrapper_off_right-this_width,0, _opt.clickScrollTime);
                                }else if(this_index==_obj_li.length-1){
                                    myScroll.scrollBy(this_off_right-_wrapper_off_right,0, _opt.clickScrollTime);
                                }else{
                                    if(this_off_left-_wrapper_off_left-(this_width*_opt.fingerClick)<duibi){
                                        myScroll.scrollTo(-this_pos_left+this_prev_width+(this_width*_opt.fingerClick),0, _opt.clickScrollTime);
                                    }else if(this_off_right-_wrapper_off_right-(this_width*_opt.fingerClick)<duibi){
                                        myScroll.scrollBy(this_off_right-this_next_width-_wrapper_off_right-(this_width*_opt.fingerClick),0, _opt.clickScrollTime);
                                    }
                                }
                            }else{
                                if(this_index==1){
                                    myScroll.scrollTo(-this_pos_left+this_prev_width,0, _opt.clickScrollTime);
                                }else if(this_index==_obj_li.length-1){
                                    if(this_off_right-_wrapper_off_right>1||this_off_right-_wrapper_off_right<-1){
                                        myScroll.scrollBy(this_off_right-_wrapper_off_right,0, _opt.clickScrollTime);
                                    }
                                }else{
                                    if(this_off_left-_wrapper_off_left<duibi){
                                        myScroll.scrollTo(-this_pos_left+this_prev_width,0, _opt.clickScrollTime);
                                    }else if(this_off_right-_wrapper_off_right<duibi){
                                        myScroll.scrollBy(this_off_right-this_next_width-_wrapper_off_right,0, _opt.clickScrollTime);
                                    }
                                }
                            }
                        }
                        $this_obj.addClass(_opt.className).siblings('li').removeClass(_opt.className);
                        _opt.endClickScroll.call(this,$this_obj);
                    }
                });
            };
            $('.wrapper').navbarscroll();
        }
    })

   
    
    
}
 //点击进行中
 $('#conduct').click(function(){  
    $.ajax({
        url: domain_name_url + "/task",
        type: "GET",
        dataType: "jsonp", //指定服务器返回的数据类型
        data: {
            method: 'getUserTask',
            userId: 4599,
            status:2,
            url_type:"task"
        },
        success: function(data) {
            if(data.success==1){
                var detailsRst = data.result.rs[0].result;//获取的内容
                var runId = jsel.match('.id', detailsRst);//获得id
                var phaseState = jsel.match('.state', detailsRst);//获得状态state
                var walletBonus = jsel.match('.bonus', detailsRst);//获得钱bonus
                var captionName = jsel.match('.category_name', detailsRst);//获得标题category_name
                var stopTime = jsel.match('.create_end_time', detailsRst);//结束时间
                var goodListHtml = '';
                if(detailsRst.length!=0){
                    for(var i= 0 ; i<detailsRst.length;i++){
                      if(detailsRst[i].state == 1){ //进行中
                       
                             //获取开始时间
                             var startTime = detailsRst[i].create_time;
                             id = detailsRst[i].id;
                             
                             // 开始时间的总秒数
                             var startTimetm = "20" + startTime.substring(0, 2) + "/" + startTime.substring(2, 4) + "/" + startTime.substring(4, 6) + " " + startTime.substring(6, 8) + ":" + startTime.substring(8, 10) + ":" + startTime.substring(10, 12);
                             var startDate = new Date(startTimetm).getTime();
                             
                             // 获取结束时间
                             var endTime = detailsRst[i].create_end_time;
                             // 结束时间的总秒数
                             sekillEndTime = "20" + endTime.substring(0, 2) + "/" + endTime.substring(2, 4) + "/" + endTime.substring(4, 6) + " " + endTime.substring(6, 8) + ":" + endTime.substring(8, 10) + ":" + endTime.substring(10, 12);
                             var endTDate = new Date(sekillEndTime).getTime();
                             
                             //获取当前时间
                             var currentDate = new Date();
                             currentDate = currentDate.getTime();
                             //时间段要注意两种情况一种是刚进来就已经开始倒计时，还有就是到页面还没有倒计时，就用结束的时间减去当前的时间
                             var totalSecond;
                             if (startDate < currentDate  && currentDate <= endTDate) {//已经在倒计时了
                                 totalSecond = parseInt((endTDate - currentDate) / 1000);
                                 setTimeout(function () {//已经在倒计时了
                                     countdown(totalSecond)
                                     },1000)
                             } 
                            
                            goodListHtml += '<li class="main_content_li mtw_k" data-id='+runId[i]+'  data-state='+phaseState[i]+'  data-bonus='+walletBonus[i]+'  data-category_name='+captionName[i]+' data-create_end_time='+stopTime[i]+'>';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/money.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="p_purse">'+(detailsRst[i].bonus/100).toFixed(2)+'</span>';
                            goodListHtml += '<span class="main_content_a_right">';
                            goodListHtml += '<span class="m_c_a_r_top">'+detailsRst[i].category_name+' <i class="just_now">刚刚 </i></span>';
                            goodListHtml += '<span class="m_c_a_r_bottom">';
                            goodListHtml += '<span class="m_c_a_r_bottomleft" id="drew"></span>';
                            goodListHtml += '</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<a href="#" class="main_content_a">';
                            goodListHtml += '<div class="particulars">进行中</div>';
                            goodListHtml += '</a>';
                            goodListHtml += '</li>';
                        }
                        if(detailsRst[i].state == 3){//审核中
                            goodListHtml += '<li class="main_content_li mtw_k" data-id='+runId[i]+'  data-state='+phaseState[i]+'  data-bonus='+walletBonus[i]+'  data-category_name='+captionName[i]+'>';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/money.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="p_purse">'+(detailsRst[i].bonus/100).toFixed(2)+'</span>';
                            goodListHtml += '<span class="main_content_a_right">';
                            goodListHtml += ' <span class="m_c_a_r_top">'+detailsRst[i].category_name+' <i class="just_now">'+warnTime+'</i></span>';
                            goodListHtml += '<span class="m_c_a_r_bottom">';
                            goodListHtml += '<span class="m_c_a_r_orange">计1-23小时，超时自动到账</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<a href="#" class="main_content_a">';
                            goodListHtml += ' <div class="aerea">审核中</div>';
                            goodListHtml += '</a>';
                            goodListHtml += '</li>';
                        }
                         if(detailsRst[i].state == 4){//审核失败
                            
                            goodListHtml += '<li class="main_content_li"  id="task_apply">';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/money.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="p_purse">'+(detailsRst[i].bonus/100).toFixed(2)+'</span>';
                            goodListHtml += '<span class="main_content_a_right">';
                            goodListHtml += '<span class="m_c_a_r_top">'+detailsRst[i].category_name+'<i class="just_now">'+warnTime+'</i></span>';
                            goodListHtml += '<span class="m_c_a_r_bottom">';
                            goodListHtml += '<span class="m_c_a_r_red">审核失败</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<div class="bonus">查看</div>';
                            goodListHtml += '</li>';
                        }
                        if(detailsRst[i].state == 5){//已完成
                            
                            goodListHtml += '<li class="main_content_li mtw_k" data-id='+runId[i]+'  data-state='+phaseState[i]+'  data-bonus='+walletBonus[i]+'  data-category_name='+captionName[i]+'>';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/money.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="p_purse">'+(detailsRst[i].bonus/100).toFixed(2)+'</span>';
                            goodListHtml += '<span class="main_content_a_right">';
                            goodListHtml += '<span class="m_c_a_r_top">'+detailsRst[i].category_name+'<i class="just_now">'+warnTime+'</i></span>';
                            goodListHtml += '<span class="m_c_a_r_bottom">';
                            goodListHtml += ' <span class="m_c_a_r_green">获得奖励  '+(detailsRst[i].bonus/100).toFixed(2)+'元</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<div class="main_content_a">';
                            goodListHtml += '<div class="creoline">已完成</div>';
                            goodListHtml += '</div>';
                            goodListHtml += '</li>';
                        }
                        if(detailsRst[i].state == 6){//已过期
                            
                            goodListHtml += '<li class="main_content_li">';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/ash.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="y_purse">'+(detailsRst[i].bonus/100).toFixed(2)+' </span>';
                            goodListHtml += '<span class="main_content_a_ash">';
                            goodListHtml += '<span class="m_c_a_r_grey">'+detailsRst[i].category_name+'<i class="just_now">'+warnTime+' </i></span>';
                            goodListHtml += '<span class="m_c_a_r_ash">';
                            goodListHtml += '<span class="m_c_a_r_bottomlefts">任务已过期</span>';
                            goodListHtml += ' </span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<div class="main_content_a">';
                            goodListHtml += ' <div class="overdue">已过期</div>';
                            goodListHtml += ' </div>';
                            goodListHtml += ' </li>'; 
                        }
        
                    }
                    if(12*page>12){
                        $('#orderContent ul').append(goodListHtml);
                        $('.mtw_k').click(function(){
                            var pastMoney = $(this).data('bonus');//奖励钱
                            var pastTitle = $(this).data('category_name');//标题
                            var uri = $(this).data('id');//id
                            var pastState = $(this).data('state');//获得状态state
                            var board = $(this).data('create_end_time');//时间
                            sStorage = window.localStorage; //本地存题目
                         
        
                            sStorage.uri_goods = uri;//id
                            sStorage.equation= pastState;//获得状态state
                            sStorage.cash= (pastMoney/100).toFixed(2);//奖励钱
                            sStorage.slogan= pastTitle;//标题
                            sStorage.endingTime = board;//时间
                            var gurl = window.location.href;
        
                            localStorage.setItem('gurl', window.location.href);
                            location.href = '../mine/task_details.html?spuId=' + uri + '&url=' + gurl ;
                        })
                    }else{
                        $('#orderContent ul').html(goodListHtml);
                        $('.mtw_k').click(function(){
                            var pastMoney = $(this).data('bonus');//奖励钱
                            var pastTitle = $(this).data('category_name');//标题
                            var uri = $(this).data('id');//id
                            var pastState = $(this).data('state');//获得状态state
                            var board = $(this).data('create_end_time');//时间
                            sStorage = window.localStorage; //本地存题目
                           
        
                            sStorage.uri_goods = uri;//id
                            sStorage.equation= pastState;//获得状态state
                            sStorage.cash= (pastMoney/100).toFixed(2);//奖励钱
                            sStorage.slogan= pastTitle;//标题
                            sStorage.endingTime = board;//时间
                            var gurl = window.location.href;
        
                            localStorage.setItem('gurl', window.location.href);
                            location.href = '../mine/task_details.html?spuId=' + uri + '&url=' + gurl ;
                        })
                    } 
                    $(this).addClass("tabhover").parent().siblings().find("a").removeClass("tabhover");
                    // 查看出现弹框
                    $(function(){
                        $('.close').click(function(){
                            $('#modal_help').hide();
                            $('#modal_apply').hide();
                        })
                        $('#task_apply').click(function(){
                            $('#modal_apply').show();
                        })
                        $('#sure').click(function(){
                            var uri = $(this).data('id');//id
                            var pastState = $(this).data('state');//获得状态state
                            var pastMoney = $(this).data('bonus');//奖励钱
                            var pastTitle = $(this).data('category_name');//标题
                            sStorage = window.localStorage; //本地存题目
        
                            sStorage.uri_goods = uri;//id
                            sStorage.equation= pastState;//获得状态state
                            sStorage.cash= (pastMoney/100).toFixed(2);//奖励钱
                            sStorage.slogan= pastTitle;//标题
                            var gurl = window.location.href;
        
                            localStorage.setItem('gurl', window.location.href);
                            location.href = '../mine/task_details.html?spuId=' + uri +'&url=' + gurl ;

                        })
                    })
    
                }

            }else{
                $('#orderContent').html('<span class="information">进行中还没有信息</span>');
            }
           
            // ask(1,urlStatus);
            $.fn.navbarscroll = function (options) {
                //各种属性、参数
                var _defaults = {
                    className:'cur', //当前选中点击元素的class类名
                    clickScrollTime:300, //点击后滑动时间
                    duibiScreenWidth:0.4, //单位以rem为准，默认为0.4rem
                    scrollerWidth:3, //单位以px为准，默认为3,[仅用于特殊情况：外层宽度因为小数点造成的不精准情况]
                    defaultSelect:0, //初始选中第n个，默认第0个
                    fingerClick:0, //目标第0或1个选项触发,必须每一项长度一致，方可用此项
                    endClickScroll:function(thisObj){}//回调函数
                }
                var _opt = $.extend(_defaults, options);
                this.each(function () {
                    //插件实现代码
                    var _wrapper = $(this);
                    var _win = $(window);
                    var _win_width = _win.width(),_wrapper_width = _wrapper.width(),_wrapper_off_left = _wrapper.offset().left;
                    var _wrapper_off_right=_win_width-_wrapper_off_left-_wrapper_width;
                    var _obj_scroller = _wrapper.children('.team_tile');
                    var _obj_ul = _obj_scroller.children('ul');
                    var _obj_li = _obj_ul.children('li');
                    var _scroller_w = 0;
                    for (var i = 0; i < _obj_li.length; i++) {
                        _scroller_w += _obj_li[i].offsetWidth;
                    }
                    _obj_scroller.width(_scroller_w+_opt.scrollerWidth);
                    var myScroll = new IScroll('#'+_wrapper.attr('id'), {
                        eventPassthrough: true,
                        scrollX: true,
                        scrollY: false,
                        preventDefault: false
                    });
                    _init(_obj_li.eq(_opt.defaultSelect));
                  
                    
                    //解决PC端谷歌浏览器模拟的手机屏幕出现莫名的卡顿现象，滑动时禁止默认事件（2017-01-11）
                    _wrapper[0].addEventListener('touchmove',function (e){e.preventDefault();},false);
                    function _init(thiObj){
                        var $this_obj=thiObj;
                        // var duibi=_opt.duibiScreenWidth*_win_width/10,this_index=$this_obj.index(),this_off_left=$this_obj.offset().left,this_pos_left=$this_obj.position().left,this_width=$this_obj.width(),this_prev_width=$this_obj.prev('li').width(),this_next_width=$this_obj.next('li').width();
                        // var this_off_right=_win_width-this_off_left-this_width;
                        if(_scroller_w+2>_wrapper_width){
                            if(_opt.fingerClick==1){
                                if(this_index==1){
                                    myScroll.scrollTo(-this_pos_left+this_prev_width,0, _opt.clickScrollTime);
                                }else if(this_index==0){
                                    myScroll.scrollTo(-this_pos_left,0, _opt.clickScrollTime);
                                }else if(this_index==_obj_li.length-2){
                                    myScroll.scrollBy(this_off_right-_wrapper_off_right-this_width,0, _opt.clickScrollTime);
                                }else if(this_index==_obj_li.length-1){
                                    myScroll.scrollBy(this_off_right-_wrapper_off_right,0, _opt.clickScrollTime);
                                }else{
                                    if(this_off_left-_wrapper_off_left-(this_width*_opt.fingerClick)<duibi){
                                        myScroll.scrollTo(-this_pos_left+this_prev_width+(this_width*_opt.fingerClick),0, _opt.clickScrollTime);
                                    }else if(this_off_right-_wrapper_off_right-(this_width*_opt.fingerClick)<duibi){
                                        myScroll.scrollBy(this_off_right-this_next_width-_wrapper_off_right-(this_width*_opt.fingerClick),0, _opt.clickScrollTime);
                                    }
                                }
                            }else{
                                if(this_index==1){
                                    myScroll.scrollTo(-this_pos_left+this_prev_width,0, _opt.clickScrollTime);
                                }else if(this_index==_obj_li.length-1){
                                    if(this_off_right-_wrapper_off_right>1||this_off_right-_wrapper_off_right<-1){
                                        myScroll.scrollBy(this_off_right-_wrapper_off_right,0, _opt.clickScrollTime);
                                    }
                                }else{
                                    if(this_off_left-_wrapper_off_left<duibi){
                                        myScroll.scrollTo(-this_pos_left+this_prev_width,0, _opt.clickScrollTime);
                                    }else if(this_off_right-_wrapper_off_right<duibi){
                                        myScroll.scrollBy(this_off_right-this_next_width-_wrapper_off_right,0, _opt.clickScrollTime);
                                    }
                                }
                            }
                        }
                        $this_obj.addClass(_opt.className).siblings('li').removeClass(_opt.className);
                        _opt.endClickScroll.call(this,$this_obj);
                    }
                });
            };
            $('.wrapper').navbarscroll();
        }
    })

 })

 //点击待审核
 $('#toAudit').click(function(){  
    $.ajax({
        url: domain_name_url + "/task",
        type: "GET",
        dataType: "jsonp", //指定服务器返回的数据类型
        data: {
            method: 'getUserTask',
            userId: 4599,
            status:3,
            url_type:"task"
        },
        success: function(data) {
            if(data.success==1){
                var detailsRst = data.result.rs[0].result;//获取的内容
                var runId = jsel.match('.id', detailsRst);//获得id
                var phaseState = jsel.match('.state', detailsRst);//获得状态state
                var walletBonus = jsel.match('.bonus', detailsRst);//获得钱bonus
                var captionName = jsel.match('.category_name', detailsRst);//获得标题category_name
                var stopTime = jsel.match('.create_end_time', detailsRst);//结束时间
                var goodListHtml = '';
                if(detailsRst.length!=0){
                    for(var i= 0 ; i<detailsRst.length;i++){
                      if(detailsRst[i].state == 1){ //进行中
                             //获取开始时间
                             var startTime = detailsRst[i].create_time;
                             id = detailsRst[i].id;
                             
                             // 开始时间的总秒数
                             var startTimetm = "20" + startTime.substring(0, 2) + "/" + startTime.substring(2, 4) + "/" + startTime.substring(4, 6) + " " + startTime.substring(6, 8) + ":" + startTime.substring(8, 10) + ":" + startTime.substring(10, 12);
                             var startDate = new Date(startTimetm).getTime();
                             
                             // 获取结束时间
                             var endTime = detailsRst[i].create_end_time;
                             // 结束时间的总秒数
                             sekillEndTime = "20" + endTime.substring(0, 2) + "/" + endTime.substring(2, 4) + "/" + endTime.substring(4, 6) + " " + endTime.substring(6, 8) + ":" + endTime.substring(8, 10) + ":" + endTime.substring(10, 12);
                             var endTDate = new Date(sekillEndTime).getTime();
                             
                             //获取当前时间
                             var currentDate = new Date();
                             currentDate = currentDate.getTime();
                             //时间段要注意两种情况一种是刚进来就已经开始倒计时，还有就是到页面还没有倒计时，就用结束的时间减去当前的时间
                             var totalSecond;
                             if (startDate < currentDate  && currentDate <= endTDate) {//已经在倒计时了
                                 totalSecond = parseInt((endTDate - currentDate) / 1000);
                                 setTimeout(function () {//已经在倒计时了
                                     countdown(totalSecond)
                                     },1000)
                             } 
                            
                            goodListHtml += '<li class="main_content_li mtw_k" data-id='+runId[i]+'  data-state='+phaseState[i]+'  data-bonus='+walletBonus[i]+'  data-category_name='+captionName[i]+' data-create_end_time='+stopTime[i]+'>';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/money.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="p_purse">'+(detailsRst[i].bonus/100).toFixed(2)+'</span>';
                            goodListHtml += '<span class="main_content_a_right">';
                            goodListHtml += '<span class="m_c_a_r_top">'+detailsRst[i].category_name+' <i class="just_now">刚刚 </i></span>';
                            goodListHtml += '<span class="m_c_a_r_bottom">';
                            goodListHtml += '<span class="m_c_a_r_bottomleft" id="drew"></span>';
                            goodListHtml += '</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<a href="#" class="main_content_a">';
                            goodListHtml += '<div class="particulars">进行中</div>';
                            goodListHtml += '</a>';
                            goodListHtml += '</li>';
                        }
                        if(detailsRst[i].state == 3){//审核中
                            goodListHtml += '<li class="main_content_li mtw_k" data-id='+runId[i]+'  data-state='+phaseState[i]+'  data-bonus='+walletBonus[i]+'  data-category_name='+captionName[i]+'>';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/money.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="p_purse">'+(detailsRst[i].bonus/100).toFixed(2)+'</span>';
                            goodListHtml += '<span class="main_content_a_right">';
                            goodListHtml += ' <span class="m_c_a_r_top">'+detailsRst[i].category_name+' <i class="just_now">'+warnTime+'</i></span>';
                            goodListHtml += '<span class="m_c_a_r_bottom">';
                            goodListHtml += '<span class="m_c_a_r_orange">计1-23小时，超时自动到账</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<a href="#" class="main_content_a">';
                            goodListHtml += ' <div class="aerea">审核中</div>';
                            goodListHtml += '</a>';
                            goodListHtml += '</li>';
                        }
                         if(detailsRst[i].state == 4){//审核失败
                            
                            goodListHtml += '<li class="main_content_li" id="task_apply">';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/money.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="p_purse">'+(detailsRst[i].bonus/100).toFixed(2)+'</span>';
                            goodListHtml += '<span class="main_content_a_right">';
                            goodListHtml += '<span class="m_c_a_r_top">'+detailsRst[i].category_name+'<i class="just_now">'+warnTime+'</i></span>';
                            goodListHtml += '<span class="m_c_a_r_bottom">';
                            goodListHtml += '<span class="m_c_a_r_red">审核失败</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<div class="bonus">查看</div>';
                            goodListHtml += '</li>';
                        }
                        if(detailsRst[i].state == 5){//已完成
                            
                            goodListHtml += '<li class="main_content_li mtw_k" data-id='+runId[i]+'  data-state='+phaseState[i]+'  data-bonus='+walletBonus[i]+'  data-category_name='+captionName[i]+'>';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/money.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="p_purse">'+(detailsRst[i].bonus/100).toFixed(2)+'</span>';
                            goodListHtml += '<span class="main_content_a_right">';
                            goodListHtml += '<span class="m_c_a_r_top">'+detailsRst[i].category_name+'<i class="just_now">'+warnTime+'</i></span>';
                            goodListHtml += '<span class="m_c_a_r_bottom">';
                            goodListHtml += ' <span class="m_c_a_r_green">获得奖励  '+(detailsRst[i].bonus/100).toFixed(2)+'元</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<div class="main_content_a">';
                            goodListHtml += '<div class="creoline">已完成</div>';
                            goodListHtml += '</div>';
                            goodListHtml += '</li>';
                        }
                        if(detailsRst[i].state == 6){//已过期
                            
                            goodListHtml += '<li class="main_content_li">';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/ash.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="y_purse">'+(detailsRst[i].bonus/100).toFixed(2)+' </span>';
                            goodListHtml += '<span class="main_content_a_ash">';
                            goodListHtml += '<span class="m_c_a_r_grey">'+detailsRst[i].category_name+'<i class="just_now">'+warnTime+' </i></span>';
                            goodListHtml += '<span class="m_c_a_r_ash">';
                            goodListHtml += '<span class="m_c_a_r_bottomlefts">任务已过期</span>';
                            goodListHtml += ' </span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<div class="main_content_a">';
                            goodListHtml += ' <div class="overdue">已过期</div>';
                            goodListHtml += ' </div>';
                            goodListHtml += ' </li>'; 
                        }
        
                    }
                    if(12*page>12){
                        $('#orderContent ul').append(goodListHtml);
                        $('.mtw_k').click(function(){
                            var pastMoney = $(this).data('bonus');//奖励钱
                            var pastTitle = $(this).data('category_name');//标题
                            var uri = $(this).data('id');//id
                            var pastState = $(this).data('state');//获得状态state
                            var board = $(this).data('create_end_time');//时间
                            sStorage = window.localStorage; //本地存题目
                           
        
                            sStorage.uri_goods = uri;//id
                            sStorage.equation= pastState;//获得状态state
                            sStorage.cash= (pastMoney/100).toFixed(2);//奖励钱
                            sStorage.slogan= pastTitle;//标题
                            sStorage.endingTime = board;//时间
                            var gurl = window.location.href;
        
                            localStorage.setItem('gurl', window.location.href);
                            location.href = '../mine/task_details.html?spuId=' + uri + '&url=' + gurl ;
                        })
                    }else{
                        $('#orderContent ul').html(goodListHtml);
                        $('.mtw_k').click(function(){
                            var pastMoney = $(this).data('bonus');//奖励钱
                            var pastTitle = $(this).data('category_name');//标题
                            var uri = $(this).data('id');//id
                            var pastState = $(this).data('state');//获得状态state
                            var board = $(this).data('create_end_time');//时间
                            sStorage = window.localStorage; //本地存题目
                           
        
                            sStorage.uri_goods = uri;//id
                            sStorage.equation= pastState;//获得状态state
                            sStorage.cash= (pastMoney/100).toFixed(2);//奖励钱
                            sStorage.slogan= pastTitle;//标题
                            sStorage.endingTime = board;//时间
                            var gurl = window.location.href;
        
                            localStorage.setItem('gurl', window.location.href);
                            location.href = '../mine/task_details.html?spuId=' + uri + '&url=' + gurl ;
                        })
                    } 
                    $(this).addClass("tabhover").parent().siblings().find("a").removeClass("tabhover");
                    // 查看出现弹框
                    $(function(){
                        $('.close').click(function(){
                            $('#modal_help').hide();
                            $('#modal_apply').hide();
                        })
                        $('#task_apply').click(function(){
                            $('#modal_apply').show();
                        })
                        $('#sure').click(function(){
                            var uri = $(this).data('id');//id
                            var pastState = $(this).data('state');//获得状态state
                            var pastMoney = $(this).data('bonus');//奖励钱
                            var pastTitle = $(this).data('category_name');//标题
                            sStorage = window.localStorage; //本地存题目
        
                            sStorage.uri_goods = uri;//id
                            sStorage.equation= pastState;//获得状态state
                            sStorage.cash= (pastMoney/100).toFixed(2);//奖励钱
                            sStorage.slogan= pastTitle;//标题
                            var gurl = window.location.href;
        
                            localStorage.setItem('gurl', window.location.href);
                            location.href = '../mine/task_details.html?spuId=' + uri +'&url=' + gurl ;

                        })
                    })    
    
                }

            }else{
                $('#orderContent').html('<span class="information">待审核中还没有信息</span>');
            }
            // ask(1,urlStatus);
            $.fn.navbarscroll = function (options) {
                //各种属性、参数
                var _defaults = {
                    className:'cur', //当前选中点击元素的class类名
                    clickScrollTime:300, //点击后滑动时间
                    duibiScreenWidth:0.4, //单位以rem为准，默认为0.4rem
                    scrollerWidth:3, //单位以px为准，默认为3,[仅用于特殊情况：外层宽度因为小数点造成的不精准情况]
                    defaultSelect:0, //初始选中第n个，默认第0个
                    fingerClick:0, //目标第0或1个选项触发,必须每一项长度一致，方可用此项
                    endClickScroll:function(thisObj){}//回调函数
                }
                var _opt = $.extend(_defaults, options);
                this.each(function () {
                    //插件实现代码
                    var _wrapper = $(this);
                    var _win = $(window);
                    var _win_width = _win.width(),_wrapper_width = _wrapper.width(),_wrapper_off_left = _wrapper.offset().left;
                    var _wrapper_off_right=_win_width-_wrapper_off_left-_wrapper_width;
                    var _obj_scroller = _wrapper.children('.team_tile');
                    var _obj_ul = _obj_scroller.children('ul');
                    var _obj_li = _obj_ul.children('li');
                    var _scroller_w = 0;
                    for (var i = 0; i < _obj_li.length; i++) {
                        _scroller_w += _obj_li[i].offsetWidth;
                    }
                    _obj_scroller.width(_scroller_w+_opt.scrollerWidth);
                    var myScroll = new IScroll('#'+_wrapper.attr('id'), {
                        eventPassthrough: true,
                        scrollX: true,
                        scrollY: false,
                        preventDefault: false
                    });
                    _init(_obj_li.eq(_opt.defaultSelect));
                  
                    
                    //解决PC端谷歌浏览器模拟的手机屏幕出现莫名的卡顿现象，滑动时禁止默认事件（2017-01-11）
                    _wrapper[0].addEventListener('touchmove',function (e){e.preventDefault();},false);
                    function _init(thiObj){
                        var $this_obj=thiObj;
                        // var duibi=_opt.duibiScreenWidth*_win_width/10,this_index=$this_obj.index(),this_off_left=$this_obj.offset().left,this_pos_left=$this_obj.position().left,this_width=$this_obj.width(),this_prev_width=$this_obj.prev('li').width(),this_next_width=$this_obj.next('li').width();
                        // var this_off_right=_win_width-this_off_left-this_width;
                        if(_scroller_w+2>_wrapper_width){
                            if(_opt.fingerClick==1){
                                if(this_index==1){
                                    myScroll.scrollTo(-this_pos_left+this_prev_width,0, _opt.clickScrollTime);
                                }else if(this_index==0){
                                    myScroll.scrollTo(-this_pos_left,0, _opt.clickScrollTime);
                                }else if(this_index==_obj_li.length-2){
                                    myScroll.scrollBy(this_off_right-_wrapper_off_right-this_width,0, _opt.clickScrollTime);
                                }else if(this_index==_obj_li.length-1){
                                    myScroll.scrollBy(this_off_right-_wrapper_off_right,0, _opt.clickScrollTime);
                                }else{
                                    if(this_off_left-_wrapper_off_left-(this_width*_opt.fingerClick)<duibi){
                                        myScroll.scrollTo(-this_pos_left+this_prev_width+(this_width*_opt.fingerClick),0, _opt.clickScrollTime);
                                    }else if(this_off_right-_wrapper_off_right-(this_width*_opt.fingerClick)<duibi){
                                        myScroll.scrollBy(this_off_right-this_next_width-_wrapper_off_right-(this_width*_opt.fingerClick),0, _opt.clickScrollTime);
                                    }
                                }
                            }else{
                                if(this_index==1){
                                    myScroll.scrollTo(-this_pos_left+this_prev_width,0, _opt.clickScrollTime);
                                }else if(this_index==_obj_li.length-1){
                                    if(this_off_right-_wrapper_off_right>1||this_off_right-_wrapper_off_right<-1){
                                        myScroll.scrollBy(this_off_right-_wrapper_off_right,0, _opt.clickScrollTime);
                                    }
                                }else{
                                    if(this_off_left-_wrapper_off_left<duibi){
                                        myScroll.scrollTo(-this_pos_left+this_prev_width,0, _opt.clickScrollTime);
                                    }else if(this_off_right-_wrapper_off_right<duibi){
                                        myScroll.scrollBy(this_off_right-this_next_width-_wrapper_off_right,0, _opt.clickScrollTime);
                                    }
                                }
                            }
                        }
                        $this_obj.addClass(_opt.className).siblings('li').removeClass(_opt.className);
                        _opt.endClickScroll.call(this,$this_obj);
                    }
                });
            };
            $('.wrapper').navbarscroll();
        }
    })

 })
//点击已结束
$('#completed').click(function(){  
    $.ajax({
        url: domain_name_url + "/task",
        type: "GET",
        dataType: "jsonp", //指定服务器返回的数据类型
        data: {
            method: 'getUserTask',
            userId: 4599,
            status:4,
            url_type:"task"
        },
        success: function(data) {
            if(data.success==1){
                var detailsRst = data.result.rs[0].result;//获取的内容
                var runId = jsel.match('.id', detailsRst);//获得id
                var phaseState = jsel.match('.state', detailsRst);//获得状态state
                var walletBonus = jsel.match('.bonus', detailsRst);//获得钱bonus
                var captionName = jsel.match('.category_name', detailsRst);//获得标题category_name
                var stopTime = jsel.match('.create_end_time', detailsRst);//结束时间
                var goodListHtml = '';
                if(detailsRst.length!=0){
                    for(var i= 0 ; i<detailsRst.length;i++){
                        if(detailsRst[i].state == 1){ //进行中

                             //获取开始时间
                             var startTime = detailsRst[i].create_time;
                             id = detailsRst[i].id;
                             
                             // 开始时间的总秒数
                             var startTimetm = "20" + startTime.substring(0, 2) + "/" + startTime.substring(2, 4) + "/" + startTime.substring(4, 6) + " " + startTime.substring(6, 8) + ":" + startTime.substring(8, 10) + ":" + startTime.substring(10, 12);
                             var startDate = new Date(startTimetm).getTime();
                             
                             // 获取结束时间
                             var endTime = detailsRst[i].create_end_time;
                             // 结束时间的总秒数
                             sekillEndTime = "20" + endTime.substring(0, 2) + "/" + endTime.substring(2, 4) + "/" + endTime.substring(4, 6) + " " + endTime.substring(6, 8) + ":" + endTime.substring(8, 10) + ":" + endTime.substring(10, 12);
                             var endTDate = new Date(sekillEndTime).getTime();
                             
                             //获取当前时间
                             var currentDate = new Date();
                             currentDate = currentDate.getTime();
                             //时间段要注意两种情况一种是刚进来就已经开始倒计时，还有就是到页面还没有倒计时，就用结束的时间减去当前的时间
                             var totalSecond;
                             if (startDate < currentDate  && currentDate <= endTDate) {//已经在倒计时了
                                 totalSecond = parseInt((endTDate - currentDate) / 1000);
                                 setTimeout(function () {//已经在倒计时了
                                     countdown(totalSecond)
                                     },1000)
                             } 
                            goodListHtml += '<li class="main_content_li mtw_k" data-id='+runId[i]+'  data-state='+phaseState[i]+'  data-bonus='+walletBonus[i]+'  data-category_name='+captionName[i]+' data-create_end_time='+stopTime[i]+'>';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/money.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="p_purse">'+(detailsRst[i].bonus/100).toFixed(2)+'</span>';
                            goodListHtml += '<span class="main_content_a_right">';
                            goodListHtml += '<span class="m_c_a_r_top">'+detailsRst[i].category_name+' <i class="just_now">刚刚 </i></span>';
                            goodListHtml += '<span class="m_c_a_r_bottom">';
                            goodListHtml += '<span class="m_c_a_r_bottomleft" id="drew"></span>';
                            goodListHtml += '</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<a href="#" class="main_content_a">';
                            goodListHtml += '<div class="particulars">进行中</div>';
                            goodListHtml += '</a>';
                            goodListHtml += '</li>';
                        }
                        if(detailsRst[i].state == 3){//审核中
                            goodListHtml += '<li class="main_content_li mtw_k" data-id='+runId[i]+'  data-state='+phaseState[i]+'  data-bonus='+walletBonus[i]+'  data-category_name='+captionName[i]+'>';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/money.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="p_purse">'+(detailsRst[i].bonus/100).toFixed(2)+'</span>';
                            goodListHtml += '<span class="main_content_a_right">';
                            goodListHtml += ' <span class="m_c_a_r_top">'+detailsRst[i].category_name+' <i class="just_now">'+warnTime+'</i></span>';
                            goodListHtml += '<span class="m_c_a_r_bottom">';
                            goodListHtml += '<span class="m_c_a_r_orange">计1-23小时，超时自动到账</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<a href="#" class="main_content_a">';
                            goodListHtml += ' <div class="aerea">审核中</div>';
                            goodListHtml += '</a>';
                            goodListHtml += '</li>';
                        }
                         if(detailsRst[i].state == 4){//审核失败
                            
                            goodListHtml += '<li class="main_content_li"  id="task_apply">';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/money.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="p_purse">'+(detailsRst[i].bonus/100).toFixed(2)+'</span>';
                            goodListHtml += '<span class="main_content_a_right">';
                            goodListHtml += '<span class="m_c_a_r_top">'+detailsRst[i].category_name+'<i class="just_now">'+warnTime+'</i></span>';
                            goodListHtml += '<span class="m_c_a_r_bottom">';
                            goodListHtml += '<span class="m_c_a_r_red">审核失败</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<div class="bonus">查看</div>';
                            goodListHtml += '</li>';
                        }
                        if(detailsRst[i].state == 5){//已完成
                            
                            goodListHtml += '<li class="main_content_li mtw_k" data-id='+runId[i]+'  data-state='+phaseState[i]+'  data-bonus='+walletBonus[i]+'  data-category_name='+captionName[i]+'>';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/money.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="p_purse">'+(detailsRst[i].bonus/100).toFixed(2)+'</span>';
                            goodListHtml += '<span class="main_content_a_right">';
                            goodListHtml += '<span class="m_c_a_r_top">'+detailsRst[i].category_name+'<i class="just_now">'+warnTime+'</i></span>';
                            goodListHtml += '<span class="m_c_a_r_bottom">';
                            goodListHtml += ' <span class="m_c_a_r_green">获得奖励  '+(detailsRst[i].bonus/100).toFixed(2)+'元</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<div class="main_content_a">';
                            goodListHtml += '<div class="creoline">已完成</div>';
                            goodListHtml += '</div>';
                            goodListHtml += '</li>';
                        }
                        if(detailsRst[i].state == 6){//已过期
                            
                            goodListHtml += '<li class="main_content_li">';
                            goodListHtml += '<span class="main_content_a_left">';
                            goodListHtml += '<img class="main_img" src="../../image/makeEveryDay/ash.png">';
                            goodListHtml += '</span>';
                            goodListHtml += '<span class="y_purse">'+(detailsRst[i].bonus/100).toFixed(2)+' </span>';
                            goodListHtml += '<span class="main_content_a_ash">';
                            goodListHtml += '<span class="m_c_a_r_grey">'+detailsRst[i].category_name+'<i class="just_now">'+warnTime+' </i></span>';
                            goodListHtml += '<span class="m_c_a_r_ash">';
                            goodListHtml += '<span class="m_c_a_r_bottomlefts">任务已过期</span>';
                            goodListHtml += ' </span>';
                            goodListHtml += '</span>';
                            goodListHtml += '<div class="main_content_a">';
                            goodListHtml += ' <div class="overdue">已过期</div>';
                            goodListHtml += ' </div>';
                            goodListHtml += ' </li>'; 
                        }
        
                    }
                    if(12*page>12){
                        $('#orderContent ul').append(goodListHtml);
                        $('.mtw_k').click(function(){
                            var pastMoney = $(this).data('bonus');//奖励钱
                            var pastTitle = $(this).data('category_name');//标题
                            var uri = $(this).data('id');//id
                            var pastState = $(this).data('state');//获得状态state
                            var board = $(this).data('create_end_time');//时间
                            sStorage = window.localStorage; //本地存题目
                           
        
                            sStorage.uri_goods = uri;//id
                            sStorage.equation= pastState;//获得状态state
                            sStorage.cash= (pastMoney/100).toFixed(2);//奖励钱
                            sStorage.slogan= pastTitle;//标题
                            sStorage.endingTime = board;//时间
                            var gurl = window.location.href;
        
                            localStorage.setItem('gurl', window.location.href);
                            location.href = '../mine/task_details.html?spuId=' + uri + '&url=' + gurl ;
                        })
                    }else{
                        $('#orderContent ul').html(goodListHtml);
                        $('.mtw_k').click(function(){
                            var pastMoney = $(this).data('bonus');//奖励钱
                            var pastTitle = $(this).data('category_name');//标题
                            var uri = $(this).data('id');//id
                            var pastState = $(this).data('state');//获得状态state
                            var board = $(this).data('create_end_time');//时间
                            sStorage = window.localStorage; //本地存题目
        
                            sStorage.uri_goods = uri;//id
                            sStorage.equation= pastState;//获得状态state
                            sStorage.cash= (pastMoney/100).toFixed(2);//奖励钱
                            sStorage.slogan= pastTitle;//标题
                            sStorage.endingTime = board;//时间
                            var gurl = window.location.href;
        
                            localStorage.setItem('gurl', window.location.href);
                            location.href = '../mine/task_details.html?spuId=' + uri + '&url=' + gurl ;
                        })
                    } 
                    $(this).addClass("tabhover").parent().siblings().find("a").removeClass("tabhover");
                    // 查看出现弹框
                    $(function(){
                        $('.close').click(function(){
                            $('#modal_help').hide();
                            $('#modal_apply').hide();
                        })
                        $('#task_apply').click(function(){
                            $('#modal_apply').show();
                        })
                        $('#sure').click(function(){
                            var uri = $(this).data('id');//id
                            var pastState = $(this).data('state');//获得状态state
                            var pastMoney = $(this).data('bonus');//奖励钱
                            var pastTitle = $(this).data('category_name');//标题
                            sStorage = window.localStorage; //本地存题目
        
                            sStorage.uri_goods = uri;//id
                            sStorage.equation= pastState;//获得状态state
                            sStorage.cash= (pastMoney/100).toFixed(2);//奖励钱
                            sStorage.slogan= pastTitle;//标题
                            var gurl = window.location.href;
        
                            localStorage.setItem('gurl', window.location.href);
                            location.href = '../mine/task_details.html?spuId=' + uri +'&url=' + gurl ;

                        })
                    })
    
                }

            }else{
                $('#orderContent').html('<span class="information">已结束中还没有信息</span>');
            }
            // ask(1,urlStatus);
            $.fn.navbarscroll = function (options) {
                //各种属性、参数
                var _defaults = {
                    className:'cur', //当前选中点击元素的class类名
                    clickScrollTime:300, //点击后滑动时间
                    duibiScreenWidth:0.4, //单位以rem为准，默认为0.4rem
                    scrollerWidth:3, //单位以px为准，默认为3,[仅用于特殊情况：外层宽度因为小数点造成的不精准情况]
                    defaultSelect:0, //初始选中第n个，默认第0个
                    fingerClick:0, //目标第0或1个选项触发,必须每一项长度一致，方可用此项
                    endClickScroll:function(thisObj){}//回调函数
                }
                var _opt = $.extend(_defaults, options);
                this.each(function () {
                    //插件实现代码
                    var _wrapper = $(this);
                    var _win = $(window);
                    var _win_width = _win.width(),_wrapper_width = _wrapper.width(),_wrapper_off_left = _wrapper.offset().left;
                    var _wrapper_off_right=_win_width-_wrapper_off_left-_wrapper_width;
                    var _obj_scroller = _wrapper.children('.team_tile');
                    var _obj_ul = _obj_scroller.children('ul');
                    var _obj_li = _obj_ul.children('li');
                    var _scroller_w = 0;
                    for (var i = 0; i < _obj_li.length; i++) {
                        _scroller_w += _obj_li[i].offsetWidth;
                    }
                    _obj_scroller.width(_scroller_w+_opt.scrollerWidth);
                    var myScroll = new IScroll('#'+_wrapper.attr('id'), {
                        eventPassthrough: true,
                        scrollX: true,
                        scrollY: false,
                        preventDefault: false
                    });
                    _init(_obj_li.eq(_opt.defaultSelect));
                  
                    
                    //解决PC端谷歌浏览器模拟的手机屏幕出现莫名的卡顿现象，滑动时禁止默认事件（2017-01-11）
                    _wrapper[0].addEventListener('touchmove',function (e){e.preventDefault();},false);
                    function _init(thiObj){
                        var $this_obj=thiObj;
                        if(_scroller_w+2>_wrapper_width){
                            if(_opt.fingerClick==1){
                                if(this_index==1){
                                    myScroll.scrollTo(-this_pos_left+this_prev_width,0, _opt.clickScrollTime);
                                }else if(this_index==0){
                                    myScroll.scrollTo(-this_pos_left,0, _opt.clickScrollTime);
                                }else if(this_index==_obj_li.length-2){
                                    myScroll.scrollBy(this_off_right-_wrapper_off_right-this_width,0, _opt.clickScrollTime);
                                }else if(this_index==_obj_li.length-1){
                                    myScroll.scrollBy(this_off_right-_wrapper_off_right,0, _opt.clickScrollTime);
                                }else{
                                    if(this_off_left-_wrapper_off_left-(this_width*_opt.fingerClick)<duibi){
                                        myScroll.scrollTo(-this_pos_left+this_prev_width+(this_width*_opt.fingerClick),0, _opt.clickScrollTime);
                                    }else if(this_off_right-_wrapper_off_right-(this_width*_opt.fingerClick)<duibi){
                                        myScroll.scrollBy(this_off_right-this_next_width-_wrapper_off_right-(this_width*_opt.fingerClick),0, _opt.clickScrollTime);
                                    }
                                }
                            }else{
                                if(this_index==1){
                                    myScroll.scrollTo(-this_pos_left+this_prev_width,0, _opt.clickScrollTime);
                                }else if(this_index==_obj_li.length-1){
                                    if(this_off_right-_wrapper_off_right>1||this_off_right-_wrapper_off_right<-1){
                                        myScroll.scrollBy(this_off_right-_wrapper_off_right,0, _opt.clickScrollTime);
                                    }
                                }else{
                                    if(this_off_left-_wrapper_off_left<duibi){
                                        myScroll.scrollTo(-this_pos_left+this_prev_width,0, _opt.clickScrollTime);
                                    }else if(this_off_right-_wrapper_off_right<duibi){
                                        myScroll.scrollBy(this_off_right-this_next_width-_wrapper_off_right,0, _opt.clickScrollTime);
                                    }
                                }
                            }
                        }
                        $this_obj.addClass(_opt.className).siblings('li').removeClass(_opt.className);
                        _opt.endClickScroll.call(this,$this_obj);
                    }
                });
            };
            $('.wrapper').navbarscroll();
        }
    })

 })


//获取滚动条当前的位置
function getScrollTop() {
    var scrollTop = 0;
    if(document.documentElement && document.documentElement.scrollTop) {
        scrollTop = document.documentElement.scrollTop;
    } else if(document.body) {
        scrollTop = document.body.scrollTop;
    }
    return scrollTop;
}
// 获取当前可视范围的高度
function getClientHeight() {
    var clientHeight = 0;
    if(document.body.clientHeight && document.documentElement.clientHeight) {
        clientHeight = Math.min(document.body.clientHeight, document.documentElement.clientHeight);
    } else {
        clientHeight = Math.max(document.body.clientHeight, document.documentElement.clientHeight);
    }
    return clientHeight;
}
// tips：Math.min是两个值取最小的值，Math.max则相反。
// 获取文档完整的高度
function getScrollHeight() {
    return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
}
// 实现下拉刷新
window.onscroll = function(){
	if(getScrollTop() + getClientHeight() == getScrollHeight()) {
		setTimeout(function () {
            page++;
            ask(12*page+1,urlStatus);
		},0)
	}
	var navH = $("#list").offset().top;
	var scroH = $(this).scrollTop();
	if(scroH>=navH){
		$("#list #retr").addClass('active')
	
	}else if(scroH<navH){
		$("#list #retr").removeClass('active');
	}
}


//  倒计时方法---已经开始
function countdown (totalSecond){
    var that=this;
    clearInterval(that.interval);
    that.interval = setInterval(function () {
	    // 总秒数
	    var second = totalSecond;
	    // 天数位
	    var day = Math.floor(second / 3600 / 24);
	    var dayStr = day.toString();
	    if (dayStr.length == 1) dayStr = '0' + dayStr;
	    // 小时位
	    var hr = Math.floor((second - day * 3600 * 24) / 3600);
	    var hrStr = hr.toString();
	    if (hrStr.length == 1) hrStr = '0' + hrStr;
	    // 分钟位
	    var min = Math.floor((second - day * 3600 * 24 - hr * 3600) / 60);
	    var minStr = min.toString();
	    if (minStr.length == 1) minStr = '0' + minStr;
	    // 秒位
	    var sec = second - day * 3600 * 24 - hr * 3600 - min * 60;
	    var secStr = sec.toString();
        if (secStr.length == 1) secStr = '0' + secStr;
        //将倒计时赋值到div中
        document.getElementById("drew").innerHTML = '已领取    ' +  ' 剩余时间'+'：'+hrStr+':'+minStr+':'+secStr;  
        totalSecond--; 
	    if (totalSecond == 0) {
            setTimeout(function tt(){
                document.getElementById("drew").innerHTML = '已领取    ' +  ' 剩余时间'+'：'+'00'+':'+'00'+':'+'00';
                clearInterval(that.interval); 
            },1000)
            $.ajax({
                url: domain_name_url + "/task",
                type: "GET",
                dataType: "jsonp", //指定服务器返回的数据类型
                data: {
                    method: 'delTask',
                    userId: 4599,
                    task_id:id,
                    url_type:"task"
                },
                success: function(data) {
                     $('#orderContent ul').html('');
                }
            })
          
	    }
    }.bind(that) ,1000);

}