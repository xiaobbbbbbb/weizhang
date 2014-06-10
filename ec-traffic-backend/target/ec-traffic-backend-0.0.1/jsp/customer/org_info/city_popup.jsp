<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/base_header.jsp" />

<body class="J_scroll_fixed">
	<form class="J_ajaxForm" method="post" action="${ctx}/queryArea/cityPopup/">
		<table id="searchColums">
			<tr>
				<td align="left" valign="top">
					<!-- 如果是组合查询 -->
					<table>
						<tr>
							<td class="field_title"><span title="城市名称">城市名称</span>：</td>
							<td class="field_input"><input id="name" class="input_text" type="text" name="name" /></td>
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
						<th>所属省份</th>
						<th>城市名称</th>
						<th>拼音首字符</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
						<tr>
							<td class="input_pk" width="40px"><input type="radio" value="${dto.id}" name="pk_id"><input type="hidden" value="${dto.name}"></td>
							<td>${dto.provinceId}</td>
							<td>${dto.name}</td>
							<td>${dto.keyTitle}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="../../common/pager.jsp" />
		</div>
	</form>
</body>
</html>