<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href=" ${ctx}/tackInfo/">策略管理</a></li>
				<li><a href="${ctx}/tackInfo/saveUpdateUI">添加</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/tackInfo/">
			<div class="mb10">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<table>
								<tr>
									<td class="field_title">接口名称：</td>
									<td><input id="name" class="input length_2" type="text" name="name" value="${param.name}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title">查询类型：</td>
									<td><select name="queryType" class="length_2">
											<option value="">-请选择-</option>
											<option value="2" <c:if test="${param.queryType==2}">selected="selected"</c:if>>非实时查询</option>
											<option value="1" <c:if test="${param.queryType==1}">selected="selected"</c:if>>实时查询</option>
									</select></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title">创建时间：</td>
									<td><input id="beginCreateTime" class="input length_2 J_date" type="text" name="beginCreateTime" value="${param.beginCreateTime}" /> ~
										<input id="endCreateTime" class="input length_2 J_date" type="text" name="endCreateTime" value="${param.endCreateTime}" /></td>
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
							<th>接口名称</th>
							<th>查询类型</th>
							<th>操作员</th>
							<th>缓存时间(小时)</th>
							<th>创建时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td align="center"><div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
										<a href="${ctx}/tackInfo/saveUpdateUI?id=${dto.id}" class="green"> <i class="icon-pencil bigger-130"></i></a> <a
											href="${ctx}/tackInfo/delete?ids=${dto.id}" class="red J_ajax_del"><i class="icon-trash bigger-130"></i></a>
									</div></td>
								<td>${dto.name}</td>
								<td align="center"><c:if test="${dto.queryType==1}">实时查询</c:if> <c:if test="${dto.queryType==2}">非实时查询</c:if></td>
								<td>${dto.operatorId}</td>
								<td>${dto.cacheTime}</td>
								<td align="center" width="80px"><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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