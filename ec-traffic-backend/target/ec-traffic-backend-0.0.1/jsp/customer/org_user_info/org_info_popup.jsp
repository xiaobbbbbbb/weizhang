<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/base_header.jsp" />

<body class="J_scroll_fixed">
	<form class="J_ajaxForm" method="post" action="${ctx}/queryArea/orgInfoPopup/">
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
		<div class="table_list">
			<table>
				<thead>
					<tr>
						<th></th>
						<th>所属集团</th>
						<th>发展时间</th>
						<th>机构编码</th>
						<th>机构名称</th>
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
						<th>状态</th>
						<th>到期时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
						<tr>
							<td class="input_pk" width="40px"><input type="radio" value="${dto.code}" name="pk_id"><input type="hidden" value="${dto.name}"></td>
							<td>${dto.belongTo}</td>
							<td align="center"><fmt:formatDate value="${dto.joinTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${dto.code}</td>
							<td>${dto.name}</td>
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
							<td>${dto.status}</td>
							<td>${dto.expiredTime}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="../../common/pager.jsp" />
		</div>
	</form>
</body>
</html>