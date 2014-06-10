$task_content_inner = null;
$mainiframe=null;
var tabwidth=128;
$loading=null;
$nav_wraper=$("#nav_wraper");
$(function () {
	$mainiframe=$("#mainiframe");
	$content=$("#content");
	$loading=$("#loading");
	var headerheight=86;
	$content.height($(window).height()-headerheight);
	$nav_wraper.height($(window).height()-headerheight);

	$(window).resize(function(){
		$content.height($(window).height()-headerheight);
		 calcTaskitemsWidth();
	});
	$("#content iframe").load(function(){
    	$loading.hide();
    });
	
    $task_content_inner = $("#task-content-inner");
   

    $(".apps_container li").live("click", function () {
        var app = '<li><span class="delete" style="display:inline">×</span><img src="" class="icon"><a href="#" class="title"></a></li>';
        var $app = $(app);
        $app.attr("data-appname", $(this).attr("data-appname"));
        $app.attr("data-appid", $(this).attr("data-appid"));
        $app.attr("data-appurl", $(this).attr("data-appurl"));
        $app.find(".icon").attr("src", $(this).attr("data-icon"));
        $app.find(".title").html($(this).attr("data-appname"));
        $app.appendTo("#appbox");
        $("#appbox  li .delete").off("click");
        $("#appbox  li .delete").click(function () {
            $(this).parent().remove();
            return false;
        });
    });

    //菜单的处理
    $("#task-content-inner li").live("click", function () {
    	openapp($(this).attr("app-url"), $(this).attr("app-id"), $(this).attr("app-name"), $(this));
    	return false;
    });
    
    $("#task-content-inner li").live("dblclick", function () {
    	closeapp($(this));
    	return false;
    });
    
    $("#task-content-inner a.macro-component-tabclose").live("click", function () {
    	closeapp($(this).parent());
        return false;
    });
    
    //下一个菜单
    $("#task-next").click(function () {
        var marginleft = $task_content_inner.css("margin-left");
        marginleft = marginleft.replace("px", "");
        var width = $("#task-content-inner li").length * tabwidth;
        var content_width = $("#task-content").width();
        var lesswidth = content_width - width;
        marginleft = marginleft - tabwidth <= lesswidth ? lesswidth : marginleft - tabwidth;

        $task_content_inner.stop();
        $task_content_inner.animate({ "margin-left": marginleft + "px" }, 300, 'swing');
    });
    
    //上一个菜单
    $("#task-pre").click(function () {
        var marginleft = $task_content_inner.css("margin-left");
        marginleft = parseInt(marginleft.replace("px", ""));
        marginleft = marginleft + tabwidth > 0 ? 0 : marginleft + tabwidth;
        $task_content_inner.stop();
        $task_content_inner.animate({ "margin-left": marginleft + "px" }, 300, 'swing');
    });
    
    //刷新当前页
    $("#refresh_wrapper").click(function(){
    	var $current_iframe=$("#content iframe:visible");
    	$loading.show();
    	$current_iframe[0].contentWindow.location.reload();
    	return false;
    });

    //调整当前菜单宽度
    calcTaskitemsWidth();
});

function calcTaskitemsWidth() {
    var width = $("#task-content-inner li").length * tabwidth;
    $("#task-content-inner").width(width);
    if (($(window).width()-268-119- 30 * 2) < width) {
        $("#task-content").width($(window).width() -268-119- 30 * 2);
        $("#task-next,#task-pre").show();
    } else {
        $("#task-next,#task-pre").hide();
        $("#task-content").width(width);
    }
}

//关闭当前菜单页
function close_current_app(){
	closeapp($("#task-content-inner .current"));
}

//关闭菜单页
function closeapp($this){
	if(!$this.is(".noclose")){
		$this.prev().click();
    	$this.remove();
    	calcTaskitemsWidth();
    	$("#task-next").click();
	}
}


var task_item_tpl ='<li class="macro-component-tabitem">'+
'<span class="macro-tabs-item-text"></span>'+
'<a class="macro-component-tabclose" href="javascript:void(0)" title="点击关闭标签"><span></span><i class="fa fa-times-circle-o fa-ec macro-component-tabclose-icon"></i></a>'+
'</li>';

var appiframe_tpl='<iframe style="width:100%;height: 100%;" frameborder="0" class="appiframe"></iframe>';

//打开菜单页
function openapp(url, appid, appname, selectObj) {
    var $app = $("#task-content-inner li[app-id='"+appid+"']");
    $("#task-content-inner .current").removeClass("current");
    var $appiframe=$(appiframe_tpl).attr("src",url).attr("id","appiframe-"+appid);
    if ($app.length == 0) {
        var task = $(task_item_tpl).attr("app-id", appid).attr("app-url",url).attr("app-name",appname).addClass("current");
        task.find(".macro-tabs-item-text").html(appname);
        $task_content_inner.append(task);
        $(".appiframe").hide();
        $loading.show();
        $appiframe.appendTo("#content");
        $appiframe.load(function(){
        	$loading.hide();
        });
        calcTaskitemsWidth();
    } else {
    	$app.addClass("current");
    	$(".appiframe").hide();
    	var $iframe=$("#appiframe-"+appid);
    	var src=$iframe.get(0).contentWindow.location.href;
    	src=src.substr(src.indexOf("://")+3);
    	if(src!=GV.HOST+url){
    		$loading.show();
    		$iframe.attr("src",url);
    		$appiframe.load(function(){
            	$loading.hide();
            });
    	}
    	$iframe.show();
    	$mainiframe.attr("src",url);
    }
    
    var itemoffset= $("#task-content-inner li[app-id='"+appid+"']").index()* tabwidth;
    var width = $("#task-content-inner li").length * tabwidth;
   
    var content_width = $("#task-content").width();
    var offset=itemoffset+tabwidth-content_width;
    
    var lesswidth = content_width - width;
    
    var marginleft = $task_content_inner.css("margin-left");
   
    marginleft =parseInt( marginleft.replace("px", "") );
    var copymarginleft=marginleft;
    if(offset>0){
    	marginleft=marginleft>-offset?-offset:marginleft;
    }else{
    	marginleft=itemoffset+marginleft>=0?marginleft:-itemoffset;
    }
    
    if(-itemoffset==marginleft){
    	marginleft = marginleft + tabwidth > 0 ? 0 : marginleft + tabwidth;
    }
    
    if(content_width-copymarginleft-tabwidth==itemoffset){
    	marginleft = marginleft - tabwidth <= lesswidth ? lesswidth : marginleft - tabwidth;
    }
    
	$task_content_inner.animate({ "margin-left": marginleft + "px" }, 300, 'swing');
}

