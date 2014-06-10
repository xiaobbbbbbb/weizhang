<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href=" ${ctx}/spiMoney/">SPI价格管理</a></li>
				<li><a href="${ctx}/spiMoney/saveUpdateUI">添加</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/spiMoney/">
			<div class="mb10">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<table>
								<tr>
									<td class="field_title">接口名称：</td>
									<td><input id="_spiId" name="spiName" class="input length_2" readonly="readonly" type="text" value="${param.spiName}" /> <input id="spiId" name="spiId"
										type="hidden" value="${dto.spiId}" /> <span class="selectAlert"
										onclick="('spiId','接口名称','/spiTack/spiInfoPopup/',null,null,false);">[<span>选择</span>]
									</span></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title">收费描述：</td>
									<td><input id="name" class="input length_2" type="text" name="name" value="${param.name}" /></td>
								</tr>
							</table>

							<table>
								<tr>
									<td class="field_title">收费类型：</td>
									<td><select name="type" class="length_2">
											<option value="">-请选择-</option>
											<option value="1" <c:if test="${param.type==1}">selected="selected"</c:if>>单个请求</option>
											<option value="2" <c:if test="${param.type==2}">selected="selected"</c:if>>按年</option>
											<option value="3" <c:if test="${param.type==3}">selected="selected"</c:if>>按月</option>
											<option value="4" <c:if test="${param.type==4}">selected="selected"</c:if>>按日</option>
									</select></td>
								</tr>
							</table>

							<table>
								<tr>
									<td class="field_title">状态：</td>
									<td><select name="status" class="length_2">
											<option value="">-请选择-</option>
											<option value="1" <c:if test="${param.status==1}">selected="selected"</c:if>>有效</option>
											<option value="0" <c:if test="${param.status==0}">selected="selected"</c:if>>无效</option>
									</select></td>
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
							<th>收费描述</th>
							<th>收费(元)</th>
							<th>收费类型</th>
							<th>创建时间</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td align="center"><div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
										<a href="${ctx}/spiMoney/saveUpdateUI?id=${dto.id}" class="green"> <i class="icon-pencil bigger-130"></i></a> <a
											href="${ctx}/spiMoney/delete?ids=${dto.id}" class="red J_ajax_del"><i class="icon-trash bigger-130"></i></a>
									</div></td>
								<td>${dto.spiInfoName}</td>
								<td>${dto.name}</td>
								<td>${dto.money}</td>
								<td><c:if test="${dto.type==1}">单个请求</c:if> <c:if test="${dto.type==2}">按年</c:if> <c:if test="${dto.type==3}">按月</c:if> <c:if
										test="${dto.type==4}">按日</c:if></td>
								<td align="center" width="80px"><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td align="center" width="60px"><c:if test="${dto.status==1}">有效</c:if> <c:if test="${dto.status==0}">无效</c:if></td>
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