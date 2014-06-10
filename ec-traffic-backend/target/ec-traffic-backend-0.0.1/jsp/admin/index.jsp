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

					<li class="light-blue"><a data-toggle="dropdown" href="#" class="dropdown-toggle"> <span>张三哈哈 </span> <i class="fa fa-caret-down"></i>
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
							<li><a href="javascript:openapp('${ctx}/orgInfo/','admin_orgInfo','客户信息');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">客户信息</span></a></li>
							<li><a href="javascript:openapp('${ctx}/queryArea/','admin_queryArea','违章查询区域');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">违章查询区域</span></a></li>
							<li><a href="javascript:openapp('${ctx}/orgUserInfo/','admin_orgUserInfo','客户管理员');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">客户管理员</span></a></li>
							<li><a href="javascript:openapp('${ctx}/queryTask/','admin_queryTask','批查任务');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">批查任务</span></a></li>
						</ul></li>

					<li class=""><a class="dropdown-toggle" href="#"><i class="fa fa-group"></i><span class="menu-text">车辆管理</span><b
							class="arrow fa fa-angle-down"></b></a>
						<ul class="submenu" style="display: none;">
							<li><a href="javascript:openapp('${ctx}/orgCarInfo/','admin_orgCarInfo','机构车辆信息');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">机构车辆信息</span></a></li>
							<li><a href="javascript:openapp('${ctx}/carInfo/','admin_carInfo','车辆信息');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">车辆信息</span></a></li>
							<li><a href="javascript:openapp('${ctx}/queryInfo/','admin_queryInfo','违章查询记录');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">违章查询记录</span></a></li>
							<li><a href="javascript:openapp('${ctx}/trafficInfo/','admin_trafficInfo','违章明细');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">违章明细</span></a></li>
						</ul></li>

					<li class=""><a class="dropdown-toggle" href="#"><i class="fa fa-list"></i><span class="menu-text">接口管理</span><b
							class="arrow fa fa-angle-down"></b></a>
						<ul class="submenu" style="display: none;">
							<li><a href="javascript:openapp('${ctx}/spiInfo/','admin_spiInfo','接口配置');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">接口配置</span></a></li>
							<li><a href="javascript:openapp('${ctx}/spiQuery/','admin_query_info','接口调用记录');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">接口调用记录</span></a></li>
							<li><a href="javascript:openapp('${ctx}/queryInfo/','admin_trafficInfo','接口监控');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">接口监控</span></a></li>
						</ul></li>

					<li class=""><a class="dropdown-toggle" href="#"><i class="fa fa-list"></i><span class="menu-text">查询对账</span><b
							class="arrow fa fa-angle-down"></b></a>
						<ul class="submenu" style="display: none;">
							<li><a href="javascript:openapp('${ctx}/queryAccount/spiAccount/','admin_spiAccount','来源接口对账');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">来源接口对账</span></a></li>
							<li><a href="javascript:openapp('${ctx}/queryAccount/spiRealTimeAccount/','admin_spiRealTimeAccount','实时查询对账');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">实时查询对账</span></a></li>
							<li><a href="javascript:openapp('${ctx}/queryAccount/spiBatchAccount/','admin_spiBatchAccount','批次查询对账');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">批次查询对账</span></a></li>
						</ul></li>

					<li class=""><a class="dropdown-toggle" href="#"><i class="fa fa-list"></i><span class="menu-text">基础数据管理</span><b
							class="arrow fa fa-angle-down"></b></a>
						<ul class="submenu" style="display: none;">
							<li><a href="javascript:openapp('${ctx}/orgType/','admin_orgType','客户类型管理');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">客户类型管理</span></a></li>
							<li><a href="javascript:openapp('${ctx}/scheduleInfo/','admin_scheduleInfo','任务类型管理');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">任务类型管理</span></a></li>
						</ul></li>

					<li class=""><a class="dropdown-toggle" href="#"><i class="fa fa-th"></i><span class="menu-text">系统管理</span><b
							class="arrow fa fa-angle-down"></b></a>
						<ul class="submenu" style="display: none;">
							<li><a href="javascript:openapp('${ctx}/logInfo/','admin_logInfo','系统日志');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">系统日志</span></a></li>
							<li class="open"><a class="dropdown-toggle" href="#"><i class="fa fa-angle-double-right"></i><span class="menu-text">权限管理</span><b
									class="arrow fa fa-angle-down"></b></a>
								<ul class="submenu" style="display: block;">
									<li><a href="javascript:openapp('/admin/post/recyclebin/menuid/302.html','302Admin','用户管理');"><i class="fa fa-leaf"></i><span
											class="menu-text">用户管理</span></a></li>
									<li><a href="javascript:openapp('/admin/page/recyclebin/menuid/301.html','301Admin','角色管理');"><i class="fa fa-leaf"></i><span
											class="menu-text">角色管理</span></a></li>
								</ul></li>
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
			document
					.write("_$tag______________________________________________________"
							+ "_$tag_______");
	</script>

	<script src="${ctx}/media/bootstrap/bootstrap.min.js?${version}"></script>
	<script src="${ctx}/media/ace/js/ace-elements.min.js?${version}"></script>
	<script src="${ctx}/media/ace/js/ace.min.js?${version}"></script>
	<script src="${ctx}/media/res/jquery.nicescroll.js?${version}"></script>

	<!-- 弹出层 -->
	<script src="${ctx}/media/artDialog/artDialog.js?v=${version}"></script>
	<script src="${ctx}/media/artDialog/iframeTools.js?v=${version}"></script>
	<script type="text/javascript">
		(function(config) {
			config['lock'] = true;
			config['fixed'] = true;
			config['resize'] = false;
			config['opacity'] = '0.3';
			config['okVal'] = '确定';
			config['cancelVal'] = '取消';
			config['title'] = '系统提示';
		})(art.dialog.defaults);
	</script>

	<!-- 首页交互 -->
	<script src="${ctx}/media/res/index/index.js?${version}"></script>

</body>
</html>