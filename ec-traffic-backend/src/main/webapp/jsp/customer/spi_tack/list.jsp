<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href=" ${ctx}/spiTack/">SPI策略配置</a></li>
				<li><a href="${ctx}/spiTack/saveUpdateUI">添加</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/spiTack/">
			<div class="mb10">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<table>
								<tr>
									<td class="field_title">策略名称：</td>
									<td><input id="_tackId" name="_tackId" class="input length_2" readonly="readonly" type="text" value="${param._tackId}" /> <input
										id="tackId" name="tackId" type="hidden" value="${param.tackId}" /> <span class="selectAlert"
										onclick="popup_select('tackId','策略名称','/tackInfoPopup/',null,null,false);">[<span>选择</span>]
									</span></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title">接口名称：</td>
									<td><input id="_spiId" name="_spiId" class="input length_2" readonly="readonly" type="text" value="${param._spiId}" /> <input
										id="spiId" name="spiId" type="hidden" value="${param.spiId}" /> <span class="selectAlert"
										onclick="popup_select('spiId','接口名称','/spiInfoPopup/',null,null,false);">[<span>选择</span>]
									</span></td>
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
							<th>策略名称</th>
							<th>第三方配置名称</th>
							<th>创建时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td align="center"><div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
										<a href="${ctx}/spiTack/saveUpdateUI?id=${dto.id}" class="green"> <i class="icon-pencil bigger-130"></i></a> <a
											href="${ctx}/spiTack/delete?ids=${dto.id}" class="red J_ajax_del"><i class="icon-trash bigger-130"></i></a>
									</div></td>
								<td>${dto.tackName}</td>
								<td>${dto.spiName}</td>
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