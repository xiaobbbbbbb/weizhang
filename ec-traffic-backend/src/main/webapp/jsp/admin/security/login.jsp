<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
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

<link href="${ctx}/media/bootstrap/bootstrap.min.css?v=${version}" rel="stylesheet">
<link href="${ctx}/media/bootstrap/bootstrap-responsive.min.css?v=${version}" rel="stylesheet">
<link href="${ctx}/media/ace/css/font-awesome.min.css?v=${version}" rel="stylesheet">
<!--[if IE 7]>
    <link rel="stylesheet" href="${ctx}/media/ace/css/font-awesome-ie7.min.css?v=${version}" />
<![endif]-->


<link href="${ctx}/media/ace/css/ace.min.css?v=${version}" rel="stylesheet">
<link href="${ctx}/media/ace/css/ace-responsive.min.css?v=${version}" rel="stylesheet">
<link href="${ctx}/media/ace/css/ace-skins.min.css?v=${version}" rel="stylesheet">

<!--[if lte IE 8]>
   <link rel="stylesheet" href="${ctx}/media/ace/css/ace-ie.min.css?v=${version}" />
<![endif]-->

</head>

<body class="login-layout">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>
								<span class="red">车信</span> <span class="white">车务运营平台</span>
							</h1>
						</div>
						<div class="space-6"></div>
						<div class="position-relative">
							<div id="login-box" class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<form action="${ctx}/login/doLogin" method="post">
											<fieldset>
												<label class="block clearfix"> <span class="block input-icon input-icon-right"><i class="icon-user"></i><input type="text"
														class="form-control" name="userName" placeholder="Username" /> </span>
												</label> <label class="block clearfix"> <span class="block input-icon input-icon-right"><i class="icon-lock"></i><input
														type="password" name="password" class="form-control" placeholder="Password" /> </span>
												</label>
												<div class="clearfix">
													<button type="submit" class="width-35 btn btn-sm btn-primary">登陆</button>
													<span>${msg}</span>
												</div>
											</fieldset>
										</form>
									</div>
									<!-- /widget-main -->
								</div>
								<!-- /widget-body -->
							</div>
							<!-- /login-box -->
						</div>
						<!-- /position-relative -->
					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
	</div>
</body>
</html>
