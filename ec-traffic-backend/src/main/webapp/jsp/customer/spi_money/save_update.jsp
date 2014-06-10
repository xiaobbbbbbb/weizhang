<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap J_check_wrap">
		<div class="h_a">
			<c:if test="${!empty dto}">编辑</c:if>
			<c:if test="${empty dto}">新增</c:if>
			-SPI价格管理
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/spiMoney/saveUpdate">
			<input type="hidden" id="id" name="id" value="${dto.id}" />
			<div class="table_full">
				<table>
					<tr>
						<td>接口名称</td>
						<td><input id="_spiId" class="input length_5" readonly="readonly" type="text" value="${dto.spiInfoName}" /> <input id="spiId" name="spiId" type="hidden"
							value="${dto.spiId}" /> <span class="selectAlert" onclick="popup_select('spiId','接口名称','/spiInfoPopup/',null,null,false);">[<span>选择</span>]
						</span></td>
					</tr>
					<tr>
						<td>收费描述</td>
						<td><input type="text" class="input length_5" id="name" name="name" value="${dto.name}" /></td>
					</tr>
					<tr>
						<td>收费(元)</td>
						<td><input type="text" class="input length_5" id="money" name="money" value="${dto.money}" /></td>
					</tr>
					<tr>
						<td>收费类型</td>
						<td><select id="type" class="length_2" name="type">
								<option value="3">按月</option>
								<option value="2">按年</option>
								<option value="4">按日</option>
								<option value="1">单个请求</option>
						</select></td>
					</tr>
					<tr>
						<td>状态</td>
						<td><select id="status" class="length_2" name="status">
								<option value="1">有效</option>
								<option value="0">无效</option>
						</select></td>
					</tr>
				</table>
			</div>
			<div class="btn_wrap">
				<div class="btn_wrap_pd">
					<button class="ace_btn btn_submit mr10 J_ajax_submit_btn" type="submit">提交</button>
					<a class="ace_btn" href="javascript:history.go(-1);">返回</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>