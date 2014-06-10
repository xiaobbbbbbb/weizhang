<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap J_check_wrap">
		<div class="h_a">
			<c:if test="${!empty dto}">编辑</c:if>
			<c:if test="${empty dto}">新增</c:if>
			-接口配置
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/spiInfo/saveUpdate">
			<input type="hidden" id="id" name="id" value="${dto.id}" />
			<div class="table_full">
				<table>
					<tr>
						<td>接口名称</td>
						<td><input type="text" class="input length_5" id="name" name="name" value="${dto.name}" /></td>
					</tr>
					<tr>
						<td>接口节点url</td>
						<td><input type="text" class="input length_5" id="nodeUrl" name="nodeUrl" value="${dto.nodeUrl}" /></td>
					</tr>
					<tr>
						<td>接口描述</td>
						<td><input type="text" class="input length_5" id="enName" name="enName" value="${dto.enName}" /></td>
					</tr>
					<tr>
						<td>查询规则url</td>
						<td><input type="text" class="input length_5" id="ruleUrl" name="ruleUrl" value="${dto.ruleUrl}" /></td>
					</tr>
					<tr>
						<td>获取违章数据url</td>
						<td><input type="text" class="input length_5" id="trafficUrl" name="trafficUrl" value="${dto.trafficUrl}" /></td>
					</tr>
					<tr>
						<td>登录名</td>
						<td><input type="text" class="input length_5" id="userName" name="userName" value="${dto.userName}" /></td>
					</tr>
					<tr>
						<td>密码</td>
						<td><input type="text" class="input length_5" id="password" name="password" value="${dto.password}" /></td>
					</tr>
					<tr>
						<td>SpiKey</td>
						<td><input type="text" class="input length_5" id="key" name="spiKey" value="${dto.spiKey}" /></td>
					</tr>
					<tr>
						<td>接口状态</td>
						<td><select id="status" class="has_select" name="status">
								<option value="1">有效</option>
								<option value="0">无效</option>
						</select></td>
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