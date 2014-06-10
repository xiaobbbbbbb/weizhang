<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap J_check_wrap">
		<div class="h_a">
			<c:if test="${!empty dto}">编辑</c:if>
			<c:if test="${empty dto}">新增</c:if>
			-SPI策略配置
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/spiTack/saveUpdate">
			<input type="hidden" id="id" name="id" value="${dto.id}" />
			<div class="table_full">
				<table>
					<tr>
						<td>策略名称</td>
						<td><input id="_tackId" class="input length_5" readonly="readonly" type="text" value="${dto.tackName}" /> <input id="tackId" name="tackId"
							type="hidden" value="${dto.tackId}" /> <span class="selectAlert" onclick="popup_select('tackId','策略名称','/tackInfoPopup/',null,null,false);">[<span>选择</span>]
						</span></td>
					</tr>
					<tr>
						<td>接口名称</td>
						<td><input id="_spiId" class="input length_5" readonly="readonly" type="text" value="${dto.spiName}" /> <input id="spiId" name="spiId"
							type="hidden" value="${dto.spiId}" /> <span class="selectAlert" onclick="popup_select('spiId','接口名称','/spiInfoPopup/',null,null,false);">[<span>选择</span>]
						</span></td>
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