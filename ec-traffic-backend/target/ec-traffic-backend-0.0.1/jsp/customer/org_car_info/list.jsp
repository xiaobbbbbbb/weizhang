<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/list_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href="${ctx}/orgCarInfo/">客户车辆信息</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/orgCarInfo/">
			<div class="mb15">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<!-- 如果是组合查询 -->
							<table>
								<tr>
									<td class="field_title">所属机构：</td>
									<td class="field_input"><input id="_orgCode" class="search_popup" readonly="readonly" type="text" /> <input id="orgCode" name="orgCode"
										type="hidden" /> <span class="selectAlert" onclick="input_popup('orgCode','所属机构','/orgCarInfo/orgInfoPopup/',null,false);">[<span>选择</span>]
									</span></td>
								</tr>
							</table>
							<table>
								<tr>
									<td class="field_title">车牌号：</td>
									<td class="field_input"><input id="carNo" class="input_text" type="text" name="carNo" value="${param.carNo}" /></td>
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
							<th>机构编号</th>
							<th>车牌号</th>
							<th>车架号</th>
							<th>发动机号</th>
							<th>车辆证书编号</th>
							<th>车辆类型</th>
							<th>状态</th>
							<th>更新时间</th>
							<th>批次</th>
							<th>创建时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td>${dto.orgCode}</td>
								<td>${dto.carNo}</td>
								<td>${dto.carFrameNo}</td>
								<td>${dto.carEngineNo}</td>
								<td>${dto.certificate}</td>
								<td>${dto.carType}</td>
								<td>${dto.status}</td>
								<td align="center"><fmt:formatDate value="${dto.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${dto.batNo}</td>
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