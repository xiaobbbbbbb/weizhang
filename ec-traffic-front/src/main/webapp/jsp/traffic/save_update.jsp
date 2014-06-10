<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/base.jsp"%>
<jsp:include page="../common/save_update_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap J_check_wrap">
		<div class="h_a">客户信息</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/traffic/saveUpdate" target="${ctx}/traffic/taskUI" >
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="table_full">
				<table>
					<tr>
						<th width="100">任务名称</th>
						<td><input type="text" name="name"  value="${dto.name}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>任务描述</th>
						<td><input type="text"  name="taskDetail" value="${dto.taskDetail}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>执行频率</th>
						<td><select name="expressionId">
							<c:forEach	items="${exs}" var ="item">
							<option value = "${ item.id}">${item.name}</option>
							</c:forEach>
						
						</select><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>状态</th>
						<td><input type="radio" checked="checked" value="1" name="status">启用 <label><input type="radio" value="0" name="status">禁止</label></td>
					</tr>
				</table>
			</div>
				<div class="btn_wrap_pd">
					<button class="btn btn_submit mr10 J_ajax_submit_btn" type="submit">提交</button>
					<a class="btn" href="javascript:history.go(-1);">返回</a>
			</div>
		</form>
	</div>
</body>
</html>