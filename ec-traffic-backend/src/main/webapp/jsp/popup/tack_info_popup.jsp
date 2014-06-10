<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/base.jsp"%>
<jsp:include page="../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="popup">
		<form class="J_ajaxForm" method="post" action="${ctx}/tackInfoPopup/">
			<table id="searchColums">
				<tr>
					<td align="left" valign="top">
						<table>
							<tr>
								<td class="field_title">策略名称：</td>
								<td class="field_input"><input id="name" class="input length_2" type="text" name="name" value="${param.name}" /></td>
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
							<th>策略名称</th>
							<th>查询类型</th>
							<th>缓存时间(小时)</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td class="input_pk"><input type="radio" class="ace" value="${dto.id}" name="pk_id"> <span class="lbl"></span> <input
									type="hidden" value="${dto.name}"></td>
								<td>${dto.name}</td>
								<td>${dto.queryType}</td>
								<td>${dto.cacheTime}</td>
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