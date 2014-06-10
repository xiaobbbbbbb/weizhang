<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/list_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href=" ${ctx}/orgInfo/">客户管理</a></li>
				<li><a href="${ctx}/orgInfo/saveUpdateUI">添加</a></li>
			</ul>
		</div>
		<form method="get" action="${ctx}/orgInfo/">
			<div class="mb15">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<!-- 如果是组合查询 -->
							<table>
								<tr>
									<td class="field_title"><span title="机构名称">机构编码</span>：</td>
									<td class="field_input"><input id="name" class="length_3" type="text" name="code" value="${param.code}" /></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title"><span title="机构名称">机构名称</span>：</td>
									<td class="field_input"><input id="name" class="length_3" type="text" name="name" value="${param.name}" /></td>
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
							<th align="center">序号</th>
							<th>所属集团</th>
							<th>发展时间</th>
							<th>机构编码</th>
							<th>机构名称</th>
							<!-- <th>省份</th>
							<th>城市</th> -->
							<th>查询类型</th>
							<th>授权车辆数</th>
							<th>最大查询次数</th>
							<th>剩余车辆数</th>
							<th>剩余查询次数</th>
							<th>电话</th>
							<th>密钥</th>
							<th>联系人</th>
							<th>业务员名称</th>
							<th>更新时间</th>
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
											href="${ctx}/orgInfo/delete?ids=${dto.id}" class="red J_ajax_del"><i class="icon-trash bigger-130"></i></a>
									</div></td>
								<td>${sn.count}</td>
								<td>${dto.belongTo}</td>
								<td align="center"><fmt:formatDate value="${dto.joinTime}" pattern="yyyy-MM-dd" /></td>
								<td>${dto.code}</td>
								<td>${dto.name}</td>
								<%-- <td>${dto.provinceName}</td>
								<td>${dto.cityName}</td> --%>
								<td>${dto.queryType}</td>
								<td>${dto.maxCarCounts}</td>
								<td>${dto.maxQueryCounts}</td>
								<td>${dto.leftCarCounts}</td>
								<td>${dto.leftQueryCounts}</td>
								<td>${dto.tel}</td>
								<td>${dto.appKey}</td>
								<td>${dto.contacts}</td>
								<td>${dto.salesman}</td>
								<td align="center"><fmt:formatDate value="${dto.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${dto.address}</td>
								<td>${dto.createTime}</td>
								<td>${dto.expiredTime}</td>
								<td><c:if test="${dto.status==1}">有效</c:if> <c:if test="${dto.status==0}">无效</c:if></td>
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