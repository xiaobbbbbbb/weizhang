<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href=" ${ctx}/ralUser/">用户管理</a></li>
				<li><a href="${ctx}/ralUser/saveUpdateUI">添加</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/ralUser/">
			<div class="mb10">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<table>
								<tr>
									<td class="field_title">姓名：</td>
									<td><input id="name" class="input length_2" type="text" name="name" value="${param.name}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title">登录名：</td>
									<td><input id="loginName" class="input length_2" type="text" name="loginName" value="${param.loginName}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title">是否有效：</td>
									<td><select name="status" class="length_2">
											<option value="">-请选择-</option>
											<option value="1" <c:if test="${param.status==1}">selected="selected"</c:if>>有效</option>
											<option value="0" <c:if test="${param.status==0}">selected="selected"</c:if>>无效</option>
									</select></td>
								</tr>
							</table>
						</td>
						<td width="60px" class="search_btn"><button class="ace_btn btn_submit" type="submit">提交</button></td>
					</tr>
				</table>
			</div>
			<div class="table_list">
				<table>
					<thead>
						<tr>
							<th align="center" width="50px">操作</th>
							<th>姓名</th>
							<th>登录名</th>
							<th>电话</th>
							<th>邮件</th>
							<th>是否有效</th>
							<th>更新时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td align="center"><div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
										<a href="${ctx}/ralUser/saveUpdateUI?id=${dto.userId}" class="green"> <i class="icon-pencil bigger-130"></i></a> <a
											href="${ctx}/ralUser/delete?ids=${dto.userId}" class="red J_ajax_del"><i class="icon-trash bigger-130"></i></a>
									</div></td>
								<td>${dto.name}</td>
								<td>${dto.loginName}</td>
								<td>${dto.phone}</td>
								<td>${dto.email}</td>
								<td align="center" width="80px"><fmt:formatDate value="${dto.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td align="center" width="60px"><c:if test="${dto.status==1}">有效</c:if> <c:if test="${dto.status==0}">无效</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<jsp:include page="../../common/pager.jsp" />
			</div>
		</form>
	</div>
</body>
</html>