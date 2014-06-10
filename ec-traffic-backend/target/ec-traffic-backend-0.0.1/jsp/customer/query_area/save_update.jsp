<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/save_update_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap J_check_wrap">
		<div class="h_a">
			<c:if test="${!empty dto}">编辑</c:if>
			<c:if test="${empty dto}">新增</c:if>
			-违章查询区域
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/queryArea/saveUpdate">
			<input type="hidden" id="id" name="id" value="${dto.id}" />
			<div class="table_full">
				<table>
					<tr>
						<td>机构名称</td>
						<td><input id="_orgCode" class="input length_5" readonly="readonly" type="text" value="${dto.orgName}" /> <input id="orgCode" name="orgCode"
							type="hidden" value="${dto.orgCode}" /> <span class="selectAlert"
							onclick="input_popup('orgCode','机构名称','/queryArea/orgInfoPopup/',null,false);">[<span>选择</span>]
						</span></td>
					</tr>
					<tr>
						<td>省份名称</td>
						<td><input id="_provinceId" class="input length_5" name="provinceName" readonly="readonly" type="text" value="${dto.provinceName}" /> <input id="provinceId" name="provinceId"
							type="hidden" value="${dto.provinceId}" /> <span class="selectAlert"
							onclick="input_popup('provinceId','省份','/queryArea/provincePopup/',null,false);">[<span>选择</span>]
						</span></td>
					</tr>
					<tr>
						<td>城市名称</td>
						<td><input id="_cityId" class="input length_5" name="cityName" readonly="readonly" type="text" value="${dto.cityName}" /> <input id="cityId" name="cityId"
							type="hidden" value="${dto.cityId}" /> <span class="selectAlert"
							onclick="input_popup('cityId','城市','/queryArea/cityPopup/','provinceId',false);">[<span>选择</span>]
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