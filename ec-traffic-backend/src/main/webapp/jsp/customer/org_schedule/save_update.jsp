<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap J_check_wrap">
		<div class="h_a">
			<c:if test="${!empty dto}">编辑</c:if>
			<c:if test="${empty dto}">新增</c:if>
			-任务调度配置
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/orgSchedule/saveUpdate">
			<input type="hidden" id="id" name="id" value="${dto.id}" />
			<div class="table_full">
				<table>
					<tr>
						<td>客户名称</td>
						<td><input id="_orgCode" class="input length_5" readonly="readonly" type="text" value="${dto.orgName}" /> <input id="orgCode" name="orgCode" type="hidden"
							value="${dto.orgCode}" /> <span class="selectAlert" onclick="popup_select('orgCode','客户名称','/orgInfoPopup/',null,null,false);">[<span>选择</span>]
						</span></td>
					</tr>
					<tr>
						<td>调度名称</td>
						<td><input id="_scheduleId" class="input length_5" readonly="readonly" type="text" value="${dto.scheduleName}" /> <input id="scheduleId" name="scheduleId"
							type="hidden" value="${dto.scheduleId}" /> <span class="selectAlert"
							onclick="popup_select('scheduleId','调度名称','/scheduleInfoPopup/',null,null,false);">[<span>选择</span>]
						</span></td>
					</tr>
					<tr>
						<td>状态</td>
						<td><select id="status" class="length_2" name="status">
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