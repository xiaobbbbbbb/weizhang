<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/list_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href=" ${ctx}/queryArea/">违章查询区域</a></li>
				<li><a href="${ctx}/queryArea/saveUpdateUI">添加</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/queryArea/">
			<div class="mb15">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<table>
								<tr>
									<td>机构名称：</td>
									<td><input id="_orgCode" class="search_popup" readonly="readonly" type="text" /> <input id="orgCode" name="orgCode" type="hidden" /> <span
										class="selectAlert" onclick="input_popup('orgCode','机构名称','/queryArea/orgInfoPopup/',null,false);">[<span>选择</span>]
									</span></td>
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
							<th align="center" width="80px">操作</th>
							<th>机构编码</th>
							<th>省份名称</th>
							<th>城市名称</th>
							<th>修改时间</th>
							<th>创建时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td align="center"><div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
										<a href="${ctx}/queryArea/saveUpdateUI?id=${dto.id}" class="green"> <i class="icon-pencil bigger-130"></i></a> <a
											href="${ctx}/queryArea/delete?ids=${dto.id}" class="red J_ajax_del"><i class="icon-trash bigger-130"></i></a>
									</div></td>
								<td>${dto.orgCode}</td>
								<td>${dto.provinceName}</td>
								<td>${dto.cityName}</td>
								<td align="center"><fmt:formatDate value="${dto.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td align="center"><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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