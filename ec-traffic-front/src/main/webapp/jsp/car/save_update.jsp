<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/base.jsp"%>
<jsp:include page="../common/save_update_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap J_check_wrap">
		<div class="h_a">车辆信息</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/car/saveUpdate">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="table_full">
				<table>
					<tr>
						<th width="100">车牌号</th>
						<td><input type="text" name="carNo"  value="${dto.carNo}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>车架号</th>
						<td><input type="text" name="carFrameNo" value="${dto.carFrameNo}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>引擎号</th>
						<td><input type="text" name="carEngineNo" value="${dto.carEngineNo}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>证书号</th>
						<td><input type="text" name="certificate" value="${dto.certificate}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>车辆类型</th>
						<td><select name = "carType">
							<option value= "1" <c:if test="${dto.carType==1 }"> selected="true"</c:if>>大型车</option>
							<option value= "2" <c:if test="${dto.carType==2 }"> selected="true"</c:if>>小型车</option>
						</select> </td>
					</tr>
					<tr>
						<th>查询类型</th>
						<td><select name = "queryType">
							<option value= "1" <c:if test="${dto.queryType==1 }"> selected="true"</c:if>>按次</option>
							<option value= "2" <c:if test="${dto.queryType==2 }"> selected="true"</c:if>>包年</option>
						</select></td>
					</tr>
					<tr>
						<th>车辆批次</th>
						<td><input type="text" name="batNo" value="${dto.batNo}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>状态</th>
						<td><input type="radio" checked="checked" value="1" name="status">启用 <label><input type="radio" value="0" name="status">禁止</label></td>
					</tr>
				</table>
			</div>
				<div class="btn_wrap_pd">
					<button class="btn btn_submit mr10 J_ajax_submit_btn" type="submit">提交</button>
					<a class="btn" href="javascript:history.go(-1);">返回</a>
			</div>
		</form>
	</div>
</body>
</html>