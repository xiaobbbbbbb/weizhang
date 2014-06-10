<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/list_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap_pop">
		<div class="nav">
			<ul>
				<li class="current"><a href=" ${ctx}/spiInfo/">接口配置</a></li>
				<li><a href="${ctx}/spiInfo/saveUpdateUI">添加</a></li>
			</ul>
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/spiInfo/">
			<div class="mb15">
				<table id="searchColums">
					<tr>
						<td align="left" valign="top">
							<!-- 如果是组合查询 -->
							<table>
								<tr>
									<td><span>接口名称</span>：</td>
									<td><input id="name" class="input_text" type="text" name="name" value="${param.name}" /></td>
								</tr>
							</table>

							<table>
								<tr>
									<td>接口状态：</td>
									<td><select name="status">
											<option value="">-请选择-</option>
											<option value="1">有效</option>
											<option value="0">无效</option>
									</select></td>
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
							<th>接口名称</th>
							<th>接口节点url</th>
							<th>接口描述</th>
							<th>查询规则url</th>
							<th>获取违章数据url</th>
							<th>登录名</th>
							<th>密码</th>
							<th>SpiKEY</th>
							<th>创建时间</th>
							<th>更新时间</th>
							<th>接口状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td align="center"><div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
										<a href="${ctx}/spiInfo/saveUpdateUI?id=${dto.id}" class="green"> <i class="icon-pencil bigger-130"></i></a> <a
											href="${ctx}/spiInfo/delete?ids=${dto.id}" class="red J_ajax_del"><i class="icon-trash bigger-130"></i></a>
									</div></td>
								<td>${dto.name}</td>
								<td>${dto.nodeUrl}</td>
								<td>${dto.enName}</td>
								<td>${dto.ruleUrl}</td>
								<td>${dto.trafficUrl}</td>
								<td>${dto.userName}</td>
								<td>${dto.password}</td>
								<td>${dto.spiKey}</td>
								<td align="center"><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td align="center"><fmt:formatDate value="${dto.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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