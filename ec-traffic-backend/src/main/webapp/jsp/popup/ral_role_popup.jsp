<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/base.jsp"%>
<jsp:include page="../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="popup">
		<form class="J_ajaxForm" method="post" action="${ctx}/ralRolePopup/">
			<table id="searchColums">
				<tr>
					<td align="left" valign="top">
						<table>
							<tr>
								<td class="field_title">角色名称：</td>
								<td><input id="name" class="input length_2" type="text" name="name" value="${param.name}" /></td>
							</tr>
						</table>
					</td>
					<td width="60px" class="search_btn"><button class="ace_btn btn_submit J_ajax_search_btn" type="submit">查询</button></td>
				</tr>
			</table>
			<div class="table_list">
				<table>
					<thead>
						<tr>
							<th align="center" width="40px">操作</th>
							<th>角色名称</th>
							<th>角色描述</th>
							<th>创建时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td class="input_pk"><input type="checkbox" class="ace" value="${dto.roleId}" name="pk_id"> <span class="lbl"></span> <input
									type="hidden" value="${dto.name}"></td>
								<td>${dto.name}</td>
								<td>${dto.message}</td>
								<td align="center" width="80px"><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<jsp:include page="../common/pager.jsp" />
		</form>
	</div>
</body>
</html>