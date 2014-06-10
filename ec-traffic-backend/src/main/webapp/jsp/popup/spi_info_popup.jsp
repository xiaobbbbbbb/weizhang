<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/base.jsp"%>
<jsp:include page="../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="popup">
		<form class="J_ajaxForm" method="post" action="${ctx}/spiInfoPopup/">
			<table id="searchColums">
				<tr>
					<td align="left" valign="top">
						<table>
							<tr>
								<td class="field_title"><span title="接口名称">接口名称</span>：</td>
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
							<th width="40px"></th>
							<th>接口名称</th>
							<th>接口节点url</th>
							<th>接口描述</th>
							<th>查询规则url</th>
							<th>获取违章数据url</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td class="input_pk"><input type="radio" class="ace" value="${dto.id}" name="pk_id"> <span class="lbl"></span> <input
									type="hidden" value="${dto.name}"></td>
								<td>${dto.name}</td>
								<td>${dto.nodeUrl}</td>
								<td>${dto.enName}</td>
								<td>${dto.ruleUrl}</td>
								<td>${dto.trafficUrl}</td>
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