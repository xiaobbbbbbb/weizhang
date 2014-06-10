<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href="${ctx}/orgUserInfo/">客户管理员</a></li>
				<li><a href="${ctx}/orgUserInfo/saveUpdateUI">添加</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/orgUserInfo/">
			<div class="mb10">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<table>
								<tr>
									<td class="field_title">客户名称：</td>
									<td><input id="_orgCode" name="orgName" class="input length_2" readonly="readonly" type="text" value="${param.orgName }" /> <input
										id="orgCode" name="orgCode" type="hidden" value="${param.orgCode }" /> <span class="selectAlert"
										onclick="popup_select('orgCode','机构名称','/orgInfoPopup/',null,null,false);">[<span>选择</span>]
									</span></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title">用户名：</td>
									<td><input id="nickName" class="input length_2" type="text" name="nickName" value="${param.nickName}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title">登陆名：</td>
									<td><input id="userName" class="input length_2" type="text" name="userName" value="${param.userName}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title">状态：</td>
									<td><select name="status" class="length_2">
											<option value="">-请选择-</option>
											<option value="1" <c:if test="${param.status==1}">selected="selected"</c:if>>有效</option>
											<option value="0" <c:if test="${param.status==0}">selected="selected"</c:if>>无效</option>
									</select></td>
								</tr>
							</table>
						</td>
						<td width="60px" class="search_btn"><button class="ace_btn btn_submit J_ajax_search_btn" type="submit">查询</button></td>
					</tr>
				</table>
			</div>
			<div class="table_list">
				<table>
					<thead>
						<tr>
							<th align="center" width="50px">操作</th>
							<th>所属客户</th>
							<th>用户名</th>
							<th>登陆名</th>
							<th>岗位</th>
							<th>邮箱</th>
							<th>联系电话</th>
							<th>注册时间</th>
							<th>更新时间</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td align="center">
									<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
										<a href="${ctx}/orgUserInfo/saveUpdateUI?id=${dto.id}" class="green"> <i class="icon-pencil bigger-130"></i></a> <a
											href="${ctx}/orgUserInfo/delete?ids=${dto.id}" class="red J_ajax_del"><i class="icon-remove-circle bigger-130"></i></a>
									</div>
								</td>
								<td>${dto.orgName}</td>
								<td>${dto.nickName}</td>
								<td>${dto.userName}</td>
								<td>${dto.job}</td>
								<td>${dto.email}</td>
								<td>${dto.tel}</td>
								<td align="center" width="80px"><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td align="center" width="80px"><fmt:formatDate value="${dto.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td align="center" width="60px"><c:if test="${dto.status==1}">有效</c:if> <c:if test="${dto.status==0}">无效</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<jsp:include page="../../common/pager.jsp" />
		</form>
	</div>
</body>
</html>