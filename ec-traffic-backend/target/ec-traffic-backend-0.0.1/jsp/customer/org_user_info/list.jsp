<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/list_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href="${ctx}/orgUserInfo/">客户管理员</a></li>
				<li><a href="${ctx}/orgUserInfo/saveUpdateUI">添加</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/orgUserInfo/">
			<div class="search_type cc mb10">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<!-- 如果是组合查询 -->
							<table>
								<tr>
									<th class="field_title"><span title="所属客户">所属客户</span>：</th>
									<td class="field_input"><input id="_orgCode" class="search_popup" readonly="readonly" type="text" /> <input id="orgCode" name="orgCode"
										type="hidden" /> <span class="selectAlert" onclick="input_popup('orgCode','所属客户','/orgUserInfo/orgInfoPopup/',null,false);">[<span>选择</span>]
									</span></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title"><span title="用户名">用户名</span>：</td>
									<td class="field_input"><input id="nickName" class="input_text" type="text" name="nickName" value="${param.nickName}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title"><span title="登陆名">登陆名</span>：</td>
									<td class="field_input"><input id="userName" class="input_text" type="text" name="userName" value="${param.userName}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title"><span title="状态">状态</span>：</td>
									<td class="field_input"><select name="status">
											<option value="">-请选择-</option>
											<option value="1">有效</option>
											<option value="0">无效</option>
									</select></td>
								</tr>
							</table>
						</td>
						<td><button class="ace_btn btn_submit" type="submit">提交</button></td>
					</tr>
				</table>
			</div>
			<div class="table_list">
				<table>
					<thead>
						<tr>
							<th align="center">操作</th>
							<th>所属客户</th>
							<th>用户名</th>
							<th>登陆名</th>
							<th>密码</th>
							<th>岗位</th>
							<th>状态</th>
							<th>邮箱</th>
							<th>联系电话</th>
							<th>注册时间</th>
							<th>更新时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td align="center">
									<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
										<a href="${ctx}/orgUserInfo/saveUpdateUI?id=${dto.id}" class="green"> <i class="icon-pencil bigger-130"></i></a> <a
											href="${ctx}/orgUserInfo/delete?ids=${dto.id}" class="red J_ajax_del"><i class="icon-trash bigger-130"></i></a>
									</div>
								</td>
								<td>${dto.orgCode}</td>
								<td>${dto.nickName}</td>
								<td>${dto.userName}</td>
								<td>${dto.password}</td>
								<td>${dto.job}</td>
								<td><c:if test="${dto.status==1}">有效</c:if> <c:if test="${dto.status==0}">无效</c:if></td>
								<td>${dto.email}</td>
								<td>${dto.tel}</td>
								<td align="center"><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td align="center"><fmt:formatDate value="${dto.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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