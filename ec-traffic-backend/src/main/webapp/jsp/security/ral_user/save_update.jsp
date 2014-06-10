<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap J_check_wrap">
		<div class="h_a">
			<c:if test="${!empty dto}">编辑</c:if>
			<c:if test="${empty dto}">新增</c:if>
			-用户管理
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/ralUser/saveUpdate">
			<input type="hidden" id="userId" name="userId" value="${dto.userId}" />
			<div class="table_full">
				<table>
					<tr>
						<td>姓名</td>
						<td><input type="text" class="input length_5" id="name" name="name" value="${dto.name}" /></td>
					</tr>
					<tr>
						<td>登录名</td>
						<td><input type="text" class="input length_5" id="loginName" name="loginName" value="${dto.loginName}" /></td>
					</tr>
					<tr>
						<td>密码</td>
						<td><input type="password" class="input length_5" id="password" name="password" value="${dto.password}" /></td>
					</tr>
					<tr>
						<td>确认密码</td>
						<td><input type="password" class="input length_5" id="reqPassword" value="${dto.password}" /></td>
					</tr>
					<tr>
						<td>电话</td>
						<td><input type="text" class="input length_5" id="phone" name="phone" value="${dto.phone}" /></td>
					</tr>
					<tr>
						<td>邮件</td>
						<td><input type="text" class="input length_5" id="email" name="email" value="${dto.email}" /></td>
					</tr>
					<tr>
						<td>用户角色</td>
						<td><input id="_roleIds" class="input length_5" readonly="readonly" type="text" value="${dto.roleNames}" /> <input id="roleIds"
							name="roleIds" type="hidden" value="${dto.roleIds}" /> <span class="selectAlert"
							onclick="popup_select('roleIds','用户角色','/ralRolePopup/',null,null,true);">[<span>选择</span>]
						</span></td>
					</tr>
					<tr>
						<td>备注</td>
						<td><input type="text" class="input length_5" id="message" name="message" value="${dto.message}" /></td>
					</tr>
					<tr>
						<td>是否有效</td>
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