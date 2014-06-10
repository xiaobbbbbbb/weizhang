<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap J_check_wrap">
		<div class="h_a">
			<c:if test="${!empty dto}">编辑</c:if>
			<c:if test="${empty dto}">新增</c:if>
			-策略管理
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/tackInfo/saveUpdate">
			<input type="hidden" id="id" name="id" value="${dto.id}" />
			<div class="table_full">
				<table>
					<tr>
						<td>接口名称</td>
						<td><input type="text" class="input length_5" id="name" name="name" value="${dto.name}" /></td>
					</tr>
					<tr>
						<td>查询类型</td>
						<td><select id="queryType" class="length_2" name="queryType">
								<option value="2">非实时查询</option>
								<option value="1">实时查询</option>
						</select></td>
					</tr>
					<tr>
						<td>缓存时间(小时)</td>
						<td><input type="text" class="input length_5" id="cacheTime" name="cacheTime" value="${dto.cacheTime}" /></td>
					</tr>
				</table>
			</div>
			<div class="btn_wrap">
				<div class="btn_wrap_pd">
					<button class="ace_btn btn_submit mr10 J_ajax_submit_btn" type="submit">提交</button>
					<a class="ace_btn" href="javascript:history.go(-1);">返回</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>