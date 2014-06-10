<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/save_update_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap J_check_wrap">
		<div class="h_a">
			<c:if test="${!empty dto}">编辑</c:if>
			<c:if test="${empty dto}">新增</c:if>
			-客户系统管理员
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/orgUserInfo/saveUpdate">
			<input type="hidden" id="id" name="id" value="${dto.id}" />
			<div class="table_full">
				<table>
					<tr>
						<td>所属客户</td>
						<td><input id="_orgCode" class="input length_5" readonly="readonly" type="text" value="${dto.orgName}" /> <input id="orgCode" name="orgCode"
							type="hidden" value="${dto.orgCode}" /> <span class="selectAlert"
							onclick="input_popup('orgCode','所属客户','/orgUserInfo/orgInfoPopup/',null,false);">[<span>选择</span>]
						</span></td>
					</tr>
					<tr>
						<td>用户名</td>
						<td><input type="text" class="input length_5" id="nickName" name="nickName" value="${dto.nickName}" /></td>
					</tr>
					<tr>
						<td>登陆名</td>
						<td><input type="text" class="input length_5" id="userName" name="userName" value="${dto.userName}" /></td>
					</tr>
					<tr>
						<td>密码</td>
						<td><input type="password" class="input length_5" id="password" name="password" value="${dto.password}" /></td>
					</tr>
					<tr>
						<td>岗位</td>
						<td><input type="text" class="input length_5" id="job" name="job" value="${dto.job}" /></td>
					</tr>
					<tr>
						<td>状态</td>
						<td><select id="status" class="has_select" name="status">
								<option value="1">有效</option>
								<option value="0">无效</option>
						</select></td>
					</tr>
					<tr>
						<td>邮箱</td>
						<td><input type="text" class="input length_5" id="email" name="email" value="${dto.email}" /></td>
					</tr>
					<tr>
						<td>联系电话</td>
						<td><input type="text" class="input length_5" id="tel" name="tel" value="${dto.tel}" /></td>
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