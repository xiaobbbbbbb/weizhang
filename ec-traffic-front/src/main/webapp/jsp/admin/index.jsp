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
<title>车务服务平台-${version}</title>

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
				<a href="" class="brand"> <small> <img src="${ctx}/media/res/image/logo-18.png">
						车信车务服务系统
				</small>
				</a>
				<ul class="nav ace-nav pull-right">

					<li class="blue">
						<div><span>${org.name }</span></div>
					</li>

					<li class="light-blue"><a data-toggle="dropdown" href="#" class="dropdown-toggle"> <span>${user.nickName }
						</span> <i class="fa fa-caret-down"></i>
					</a>
						<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer">
							<li><a href="javascript:openapp('${ctx}/org/updateUI','index_userinfo','机构信息');"><i class="fa fa-user"></i>机构修改</a></li>
							<li><a href="javascript:openapp('${ctx}/user/updatePwdUI','index_userinfo','修改密码');"><i class="fa fa-user"></i>密码修改</a></li>
							<li class="divider"></li>
							<li><a href="${ctx }/admin/exit"><i class="fa fa-off"></i>退出</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="main-container container-fluid">
		<a class="menu-toggler" id="menu-toggler" href="#"> <span class="menu-text"></span>
		</a>

		<div class="sidebar" id="sidebar">
			<div class="sidebar-collapse" id="sidebar-collapse">
				<i class="fa fa-angle-double-left"></i>
			</div>
			<div id="nav_wraper">
				<ul class="nav nav-list">
					<li class=""><a class="dropdown-toggle" href="#"><i class="fa fa-cogs"></i><span class="menu-text">车辆管理</span><b
							class="arrow fa fa-angle-down"></b></a>
						<ul class="submenu" style="display: none;">
							<li><a href="javascript:openapp('${ctx}/car/','100Car','车辆信息');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">车辆信息</span></a></li>
							<li><a href="javascript:openapp('${ctx}/car/upload','101Car','批量导入');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">批量导入</span></a></li>
						</ul></li>
					
					<li class=""><a class="dropdown-toggle" href="#"><i class="fa fa-list"></i><span class="menu-text">违章查询</span><b
							class="arrow fa fa-angle-down"></b></a>
						<ul class="submenu" style="display: none;">
							<li><a href="javascript:openapp('${ctx}/traffic/','300Traffic','违章查询');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">违章实时查询</span></a></li>
							<li><a href="javascript:openapp('${ctx}/traffic/taskUI','301Traffic','定时批查');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">违章批查设置</span></a></li>
							<li><a href="javascript:openapp('${ctx}/traffic/result','302Traffic','批查结果');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">违章批查结果</span></a></li>
							<li><a href="javascript:openapp('${ctx}/traffic/realTime','303Traffic','时查结果');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">违章实时结果</span></a></li>
						</ul></li>
					<li class=""><a class="dropdown-toggle" href="#"><i class="fa fa-th"></i><span class="menu-text">查询对账</span><b
							class="arrow fa fa-angle-down"></b></a>
						<ul class="submenu" style="display: none;">
							<li><a href="javascript:openapp('${ctx}/queryAccount/batch','401Finance','定时批查');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">违章批查对账</span></a></li>
							<li><a href="javascript:openapp('${ctx}/queryAccount/realTime','402Traffic','实时查询');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">违章时查对账</span></a></li>
							
						</ul></li>
						
					<li class=""><a class="dropdown-toggle" href="#"><i class="fa fa-group"></i><span class="menu-text">系统设置</span><b
							class="arrow fa fa-angle-down"></b></a>
						<ul class="submenu" style="display: none;">
							<li><a href="javascript:openapp('${ctx}/org/updateUI','200org','机构信息');"><i class="fa fa-angle-double-right"></i><span
									class="menu-text">机构资料修改</span></a></li>
						</ul></li>
					
				</ul>
			</div>

		</div>

		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<a id="task-pre" class="task-changebt"><i class="fa fa-arrow-circle-o-left"></i></a>
				<div id="task-content">
					<ul class="macro-component-tab" id="task-content-inner">
						<li class="macro-component-tabitem noclose" app-id="0" app-url="${ctx}/car/" app-name="首页"><span class="macro-tabs-item-text">首页</span></li>
					</ul>
					<div style="clear: both;"></div>
				</div>
				<a id="task-next" class="task-changebt"><i class="fa fa-arrow-circle-o-right"></i></a>
			</div>

			<div id="content">
				<iframe src="${ctx}/car/" style="width: 100%; height: 100%;" frameborder="0" id="appiframe-0" class="appiframe"></iframe>
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