<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href=" ${ctx}/orgInfo/">客户管理</a></li>
				<li><a href="${ctx}/orgInfo/saveUpdateUI">添加</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/orgInfo/">
			<div class="mb10">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<!-- 如果是组合查询 -->
							<table>
								<tr>
									<td class="field_title"><span title="机构名称">客户编码</span>：</td>
									<td class="field_input"><input id="name" class="input length_2" type="text" name="code" value="${param.code}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title"><span title="客户名称">客户名称</span>：</td>
									<td class="field_input"><input id="name" class="input length_2" type="text" name="name" value="${param.name}" /></td>
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
							<th width="50px">操作</th>
							<th>序号</th>
							<th>所属集团</th>
							<th>发展时间</th>
							<th>客户编码</th>
							<th>客户名称</th>
							<th>省份</th>
							<th>城市</th>
							<th>查询类型</th>
							<th>授权车辆数</th>
							<th>最大查询次数</th>
							<th>剩余车辆数</th>
							<th>剩余查询次数</th>
							<th>策略名称</th>
							<th>电话</th>
							<th>密钥</th>
							<th>联系人</th>
							<th>业务员名称</th>
							<th>地址</th>
							<th>创建时间</th>
							<th>到期时间</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td align="center"><div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
										<a href="${ctx}/orgInfo/saveUpdateUI?id=${dto.id}" class="green"> <i class="icon-pencil bigger-130"></i></a> <a
											href="${ctx}/orgInfo/delete?ids=${dto.id}" class="red J_ajax_del"><i class="icon-remove-circle bigger-130"></i></a>
									</div></td>
								<td>${sn.count}</td>
								<td>${dto.belongTo}</td>
								<td align="center"><fmt:formatDate value="${dto.joinTime}" pattern="yyyy-MM-dd" /></td>
								<td>${dto.code}</td>
								<td>${dto.name}</td>
								<td>${dto.provinceName}</td>
								<td>${dto.cityName}</td>
								<td>${dto.queryType}</td>
								<td>${dto.maxCarCounts}</td>
								<td>${dto.maxQueryCounts}</td>
								<td>${dto.leftCarCounts}</td>
								<td>${dto.leftQueryCounts}</td>
								<td>${dto.tackName}</td>
								<td>${dto.tel}</td>
								<td>${dto.appKey}</td>
								<td>${dto.contacts}</td>
								<td>${dto.salesman}</td>
								<td>${dto.address}</td>
								<td align="center"><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td align="center"><fmt:formatDate value="${dto.expiredTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><c:if test="${dto.status==1}">有效</c:if> <c:if test="${dto.status==0}">无效</c:if></td>
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