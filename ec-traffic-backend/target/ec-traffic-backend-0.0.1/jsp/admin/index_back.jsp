<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--[if IE ]>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<![endif]-->
<meta charset="utf-8">
<title>车务运营平台-${version}</title>

<meta name="description" content="This is page-header (.page-header &gt; h1)">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="${ctx}/media/res/reset.css?v=${version}" rel="stylesheet">
<link href="${ctx}/media/bootstrap/bootstrap.min.css?v=${version}" rel="stylesheet">
<link href="${ctx}/media/bootstrap/bootstrap-responsive.min.css?v=${version}" rel="stylesheet">
<link href="${ctx}/media/ace/css/font-awesome.min.css?v=${version}" rel="stylesheet">
<!--[if IE ]>
    <link rel="stylesheet" href="${ctx}/media/ace/css/font-awesome-ie7.min.css?v=${version}" />
<![endif]-->


<link href="${ctx}/media/ace/css/ace.min.css?v=${version}" rel="stylesheet">
<link href="${ctx}/media/ace/css/ace-responsive.min.css?v=${version}" rel="stylesheet">
<link href="${ctx}/media/ace/css/ace-skins.min.css?v=${version}" rel="stylesheet">

<!--[if lte IE 8]>
   <link rel="stylesheet" href="${ctx}/media/ace/css/ace-ie.min.css?v=${version}" />
<![endif]-->

<link href="${ctx}/media/res/index/index.css?v=${version}" rel="stylesheet">
<link href="${ctx}/media/artDialog/skins/default.css?v=${version}" type="text/css" rel="stylesheet" />

<script type="text/javascript">
	//全局变量
	var GV = {
		HOST : "${ctx}"
	};
</script>

</head>

<body>
	<div id="loading">
		<i class="loadingicon"></i><span>正在加载...</span>
	</div>

	<div class="navbar" class="navbar navbar-default">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a href="__ROOT__/index.php?g=admin&m=index&a=index" class="brand"> <small> <img src="${ctx}/media/res/image/logo-18.png">
						车信车务运营平台
				</small>
				</a>
				<ul class="nav ace-nav pull-right">

					<li class="blue">
						<div id="right_tools_wrapper">
							<span id="right_tools_clearcache" title="清除缓存" onclick="javascript:openapp('admin/setting/clearcache','right_tool_clearcache','清除缓存');"><i
								class="fa fa-trash-o right_tool_icon"></i></span> <span id="refresh_wrapper" title="刷新当前页"><i class="fa fa-refresh right_tool_icon"></i></span>
						</div>
					</li>

					<li class="light-blue"><a data-toggle="dropdown" href="#" class="dropdown-toggle"> <span>张三哈哈
						</span> <i class="fa fa-caret-down"></i>
					</a>
						<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer">
							<li><a href="javascript:openapp('setting/site','index_site','站点管理');"><i class="fa fa-cog"></i>站点管理</a></li>
							<li><a href="javascript:openapp('user/userinfo','index_userinfo','个人资料');"><i class="fa fa-user"></i>个人资料</a></li>
							<li class="divider"></li>
							<li><a href="Public/logout"><i class="fa fa-off"></i>退出</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="main-container container-fluid">
		<a class="menu-toggler" id="menu-toggler" href="#"> <span class="menu-text"></span>
		</a>

		<div class="sidebar" id="sidebar">
			<div class="sidebar-shortcuts" id="sidebar-shortcuts">
				<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
					<a class="btn btn-small btn-success" href="javascript:openapp('term/index','index_termlist','分类管理');" title="分类管理"> <i class="fa fa-th"></i>
					</a> <a class="btn btn-small btn-info" href="javascript:openapp('post/index','index_postlist','文章管理');" title="文章管理"> <i class="fa fa-pencil"></i>
					</a> <a class="btn btn-small btn-warning" href="__ROOT__/" title="前台首页" target="_blank"> <i class="fa fa-home"></i>
					</a> <a class="btn btn-small btn-danger" href="javascript:openapp('admin/setting/clearcache','index_clearcache','清除缓存');" title="清除缓存"> <i
						class="fa fa-trash-o"></i>
					</a>
				</div>

				<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
					<span class="btn btn-success"></span> <span class="btn btn-info"></span> <span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
				</div>
			</div>
			<div class="sidebar-collapse" id="sidebar-collapse">
				<i class="fa fa-angle-double-left"></i>
			</div>
			<div id="nav_wraper">
				<ul class="nav nav-list">
					<li class=""><a class="dropdown-toggle" href="#"><i class="fa fa-cogs"></i><span class="menu-text">客户管理</span><b
							class="arrow fa fa-angle-down"></b></a>
						<ul class="submenu" style="display: none;">
							
							<li><a href="javascript:openapp('${ctx}/orgInfo/','1admin','客户信息');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">客户信息</span></a></li>
							<li><a href="javascript:openapp('${ctx}/orgUserInfo/','2admin','客户管理员');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">客户管理员</span></a></li>
							<li><a href="javascript:openapp('/admin/setting/clearcache/menuid/298.html','298Admin','批查任务');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">批查任务</span></a></li>
						</ul></li>
						
						
					<li class=""><a class="dropdown-toggle" href="#"><i class="fa fa-group"></i><span class="menu-text">车辆管理</span><b
							class="arrow fa fa-angle-down"></b></a>
						<ul class="submenu" style="display: none;">
							<li class="open"><a class="dropdown-toggle" href="#"><i class="fa fa-angle-double-right"></i><span class="menu-text">用户组</span><b
									class="arrow fa fa-angle-down"></b></a>
								<ul class="submenu" style="display: block;">
									<li><a href="javascript:openapp('/member/indexadmin/index/menuid/287.html','287Member','本站用户');"><i class="fa fa-leaf"></i><span
											class="menu-text">本站用户</span></a></li>
									<li><a href="javascript:openapp('/api/oauthadmin/index/menuid/288.html','288Api','第三方用户');"><i class="fa fa-leaf"></i><span
											class="menu-text">第三方用户</span></a></li>
								</ul></li>
							<li><a class="dropdown-toggle" href="#"><i class="fa fa-angle-double-right"></i><span class="menu-text">管理组</span><b
									class="arrow fa fa-angle-down"></b></a>
								<ul class="submenu">
									<li><a href="javascript:openapp('/admin/rbac/index/menuid/291.html','291Admin','角色管理');"><i class="fa fa-leaf"></i><span
											class="menu-text">角色管理</span></a></li>
									<li><a href="javascript:openapp('/admin/user/index/menuid/292.html','292Admin','管理员');"><i class="fa fa-leaf"></i><span
											class="menu-text">管理员</span></a></li>
								</ul></li>
						</ul></li>
					<li class=""><a class="dropdown-toggle" href="#"><i class="fa fa-list"></i><span class="menu-text">菜单管理</span><b
							class="arrow fa fa-angle-down"></b></a>
						<ul class="submenu" style="display: none;">
							<li class="open"><a class="dropdown-toggle" href="#"><i class="fa fa-angle-double-right"></i><span class="menu-text">前台菜单</span><b
									class="arrow fa fa-angle-down"></b></a>
								<ul class="submenu" style="display: block;">
									<li><a href="javascript:openapp('/admin/nav/index/menuid/295.html','295Admin','菜单管理');"><i class="fa fa-leaf"></i><span
											class="menu-text">菜单管理</span></a></li>
									<li><a href="javascript:openapp('/admin/navcat/index/menuid/296.html','296Admin','菜单分类');"><i class="fa fa-leaf"></i><span
											class="menu-text">菜单分类</span></a></li>
								</ul></li>
							<li><a href="javascript:openapp('/admin/menu/index/menuid/297.html','297Admin','后台菜单');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">后台菜单</span></a></li>
						</ul></li>
					<li class=""><a class="dropdown-toggle" href="#"><i class="fa fa-th"></i><span class="menu-text">内容管理</span><b
							class="arrow fa fa-angle-down"></b></a>
						<ul class="submenu" style="display: none;">
							<li><a href="javascript:openapp('/api/guestbookadmin/index/menuid/554.html','554Api','所有留言');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">所有留言</span></a></li>
							<li><a href="javascript:openapp('/comment/commentadmin/index/menuid/557.html','557Comment','评论管理');"><i
									class="fa fa-angle-double-right"></i><span class="menu-text">评论管理</span></a></li>
							<li><a href="javascript:openapp('/admin/post/index/menuid/285.html','285Admin','文章管理');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">文章管理</span></a></li>
							<li><a href="javascript:openapp('/admin/term/index/menuid/245.html','245Admin','分类管理');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">分类管理</span></a></li>
							<li><a href="javascript:openapp('/admin/page/index/menuid/277.html','277Admin','页面管理');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">页面管理</span></a></li>
							<li class="open"><a class="dropdown-toggle" href="#"><i class="fa fa-angle-double-right"></i><span class="menu-text">回收站</span><b
									class="arrow fa fa-angle-down"></b></a>
								<ul class="submenu" style="display: block;">
									<li><a href="javascript:openapp('/admin/post/recyclebin/menuid/302.html','302Admin','文章回收');"><i class="fa fa-leaf"></i><span
											class="menu-text">文章回收</span></a></li>
									<li><a href="javascript:openapp('/admin/page/recyclebin/menuid/301.html','301Admin','页面回收');"><i class="fa fa-leaf"></i><span
											class="menu-text">页面回收</span></a></li>
								</ul></li>
						</ul></li>
					<li><a class="dropdown-toggle" href="#"><i class="fa fa-tags"></i><span class="menu-text">微信管理</span><b class="arrow fa fa-angle-down"></b></a>
						<ul class="submenu">
							<li><a href="javascript:openapp('/wx/indexadmin/index/menuid/495.html','495Wx','账号信息');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">账号信息</span></a></li>
							<li><a class="dropdown-toggle" href="#"><i class="fa fa-angle-double-right"></i><span class="menu-text">回复设置</span><b
									class="arrow fa fa-angle-down"></b></a>
								<ul class="submenu">
									<li><a href="javascript:openapp('/wx/answeradmin/index/menuid/308.html','308Wx','默认回复');"><i class="fa fa-leaf"></i><span
											class="menu-text">默认回复</span></a></li>
									<li><a href="javascript:openapp('/wx/answeradmin/fixed/menuid/309.html','309Wx','固定回复');"><i class="fa fa-leaf"></i><span
											class="menu-text">固定回复</span></a></li>
									<li><a href="javascript:openapp('/wx/answeradmin/robot/menuid/310.html','310Wx','智能回复');"><i class="fa fa-leaf"></i><span
											class="menu-text">智能回复</span></a></li>
								</ul></li>
							<li><a class="dropdown-toggle" href="#"><i class="fa fa-angle-double-right"></i><span class="menu-text">数据分析</span><b
									class="arrow fa fa-angle-down"></b></a>
								<ul class="submenu">
									<li><a href="javascript:openapp('/wx/collectadmin/answer/menuid/314.html','314Wx','回复数量');"><i class="fa fa-leaf"></i><span
											class="menu-text">回复数量</span></a></li>
									<li><a href="javascript:openapp('/wx/collectadmin/location/menuid/313.html','313Wx','地理位置');"><i class="fa fa-leaf"></i><span
											class="menu-text">地理位置</span></a></li>
									<li><a href="javascript:openapp('/wx/collectadmin/users/menuid/317.html','317Wx','关注趋势');"><i class="fa fa-leaf"></i><span
											class="menu-text">关注趋势</span></a></li>
									<li><a href="javascript:openapp('/wx/collectadmin/userlist/menuid/316.html','316Wx','粉丝列表');"><i class="fa fa-leaf"></i><span
											class="menu-text">粉丝列表</span></a></li>
								</ul></li>
							<li><a href="javascript:openapp('/wx/menuadmin/index/menuid/318.html','318Wx','菜单管理');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">菜单管理</span></a></li>
						</ul></li>
					<li class="open"><a class="dropdown-toggle" href="#"><i class="fa fa-cloud"></i><span class="menu-text">扩展工具</span><b
							class="arrow fa fa-angle-down"></b></a>
						<ul class="submenu" style="display: block;">
							<li><a class="dropdown-toggle" href="#"><i class="fa fa-angle-double-right"></i><span class="menu-text">备份管理</span><b
									class="arrow fa fa-angle-down"></b></a>
								<ul class="submenu">
									<li><a href="javascript:openapp('/admin/backup/restore/menuid/421.html','421Admin','数据还原');"><i class="fa fa-leaf"></i><span
											class="menu-text">数据还原</span></a></li>
									<li><a href="javascript:openapp('/admin/backup/index/menuid/496.html','496Admin','数据备份');"><i class="fa fa-leaf"></i><span
											class="menu-text">数据备份</span></a></li>
								</ul></li>
							<li><a class="dropdown-toggle" href="#"><i class="fa fa-angle-double-right"></i><span class="menu-text">幻灯片</span><b
									class="arrow fa fa-angle-down"></b></a>
								<ul class="submenu">
									<li><a href="javascript:openapp('/admin/slide/index/menuid/264.html','264Admin','幻灯片管理');"><i class="fa fa-leaf"></i><span
											class="menu-text">幻灯片管理</span></a></li>
									<li><a href="javascript:openapp('/admin/slidecat/index/menuid/268.html','268Admin','幻灯片分类');"><i class="fa fa-leaf"></i><span
											class="menu-text">幻灯片分类</span></a></li>
								</ul></li>
							<li><a href="javascript:openapp('/admin/ad/index/menuid/265.html','265Admin','网站广告');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">网站广告</span></a></li>
							<li><a href="javascript:openapp('/admin/link/index/menuid/270.html','270Admin','友情链接');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">友情链接</span></a></li>
							<li><a href="javascript:openapp('/api/oauthadmin/setting/menuid/299.html','299Api','第三方登陆');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">第三方登陆</span></a></li>
						</ul></li>
				</ul>
			</div>

		</div>

		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<a id="task-pre" class="task-changebt"><i class="fa fa-arrow-circle-o-left"></i></a>
				<div id="task-content">
					<ul class="macro-component-tab" id="task-content-inner">
						<li class="macro-component-tabitem noclose" app-id="0" app-url="${ctx}/orgInfo/" app-name="首页"><span class="macro-tabs-item-text">首页</span></li>
					</ul>
					<div style="clear: both;"></div>
				</div>
				<a id="task-next" class="task-changebt"><i class="fa fa-arrow-circle-o-right"></i></a>
			</div>

			<div id="content">
				<iframe src="${ctx}/orgInfo/" style="width: 100%; height: 100%;" frameborder="0" id="appiframe-0" class="appiframe"></iframe>
			</div>
		</div>
	</div>

	<script src="${ctx}/media/res/jquery-1.8.2.min.js?${version}"></script>

	<script type="text/javascript">
		if ("ontouchend" in document)
			document.write("<script src='${ctx}/media/res/jquery.mobile.custom.min.js'>"+ "<"+"/script>");
	</script>

	<script src="${ctx}/media/bootstrap/bootstrap.min.js?${version}"></script>
	<script src="${ctx}/media/ace/js/ace-elements.min.js?${version}"></script>
	<script src="${ctx}/media/ace/js/ace.min.js?${version}"></script>
	<script src="${ctx}/media/res/jquery.nicescroll.js?${version}"></script>
	
	<!-- 弹出层 -->
	<script src="${ctx}/media/artDialog/artDialog.js?v=${version}"></script>
    <script src="${ctx}/media/artDialog/iframeTools.js?v=${version}"></script>
	<script type="text/javascript">
	(function (config) 
	{
	    config['lock'] = true;
	    config['fixed'] = true;
	    config['resize'] = false;
	    config['opacity']='0.3';
	    config['okVal'] = '确定';
	    config['cancelVal'] = '取消';
	    config['title'] = '系统提示';
	})(art.dialog.defaults);
	</script>
	
	<!-- 首页交互 -->
	<script src="${ctx}/media/res/index/index.js?${version}"></script>

</body>
</html>