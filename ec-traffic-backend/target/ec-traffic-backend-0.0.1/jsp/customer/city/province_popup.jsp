<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/base_header.jsp" />

<body class="J_scroll_fixed">
	<form method="get" action="${ctx}/city/provincePopup/">
		<div id="search">
			<table id="searchColums">
				        <tr>
				            <td align="left" valign="top">
				            <!-- 如果是组合查询 -->
				               </td>
				            <td><button type="submit" class="btn btn_submit J_ajax_submit_btn">提交</button></td>
                        </tr>
                     </table>
	</div>
	<div class="table_list">
				<table>
					<thead>
						<tr>
							<th></th>
							<th align="center">序号</th>
				              
				              <th>是否有效，1为有效，0未无效</th>
				              <th>省</th>
				              <th>拼音首字符</th>
				              <th>省简写</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
							<td class="input_pk"><input type="radio" value="${dto.id}" name="pk_id"><input type="hidden" value="${dto.name}"></td>
					   <td>${sn.count}</td>
					   <td>${sn.count}</td>
	                          <td>${dto.status}</td>
					   <td>${sn.count}</td>
	                          <td>${dto.name}</td>
					   <td>${sn.count}</td>
	                          <td>${dto.keyTitle}</td>
					   <td>${sn.count}</td>
	                          <td>${dto.simpleName}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="p5">
					<jsp:include page="../../common/pager.jsp" />
				</div>
			</div>
	</form>
</body>
</html>