<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/save_update_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap J_check_wrap">
		<div class="h_a"><c:if test="${!empty dto}">编辑</c:if><c:if test="${empty dto}">新增</c:if>-客户信息</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/orgInfo/saveUpdate">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="table_full">
				<table>
					<tr>
						<th width="100">发展日期</th>
						<td><input type="text" name="joinTime" value="2014-01-12" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>客户名称</th>
						<td><input type="text" name="name" value="${dto.name}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>查询方式</th>
						<td><input type="checkbox" name="queryTypeReal" value="1" checked>实时查询<label> &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"
								name="queryTypeBatch" value="2">批次查询
						</label></td>
					</tr>
					<tr>
						<th>授权查询次数</th>
						<td><input type="text" name="maxQueryCounts" value="${dto.maxQueryCounts}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>授权车辆数</th>
						<td><input type="text" name="maxCarCounts" value="${dto.maxCarCounts}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>所属省份</th>
						<td><input type="text" name="province" value="${dto.province}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>所属城市</th>
						<td><input type="text" name="city" value="${dto.city}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>行业类型</th>
						<td><input type="text" value="${dto.province}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>所属集团</th>
						<td><input type="text" name="belongTo" value="${dto.belongTo}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>联系人</th>
						<td><input type="text" name="contacts" value="${dto.contacts}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>联系电话</th>
						<td><input type="text" name="tel" value="${dto.tel}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>地址</th>
						<td><input type="text" name="address" value="${dto.address}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>业务员</th>
						<td><input type="text" name="salesman" value="${dto.salesman}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>合同到期日期</th>
						<td><input type="text" name="expiredTime" value="2016-01-12" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>状态</th>
						<td><input type="radio" checked="checked" value="1" name="status">启用 <label><input type="radio" value="0" name="status">禁止</label></td>
					</tr>
				</table>
			</div>
			<div class="btn_wrap">
				<div class="btn_wrap_pd">
					<button class="btn btn_submit mr10 J_ajax_submit_btn" type="submit">提交</button>
					<a class="btn" href="javascript:history.go(-1);">返回</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>