<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="base.jsp"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
<title>系统后台</title>
<link href="${ctx}/media/res/index/admin_style.css?v=${version}" rel="stylesheet" />
<link href="${ctx}/media/artDialog/skins/default.css?v=${version}" rel="stylesheet" />
<script type="text/javascript">
	//全局变量
	var GV = {
		DIMAUB : "${ctx}",
		JS_ROOT : "/media/"
	};
</script>
<script src="${ctx}/media/res/wind.js?v=${version}"></script>
<script src="${ctx}/media/res/jquery-1.7.2.min.js?v=${version}"></script>
<script src="${ctx}/media/res/index/common.js?v=${version}"></script>