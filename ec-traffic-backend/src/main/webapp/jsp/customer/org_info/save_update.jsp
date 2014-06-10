<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/header.jsp" />
<body class="J_scroll_fixed">
	<div class="wrap J_check_wrap">
		<div class="h_a">
			<c:if test="${!empty dto}">编辑</c:if>
			<c:if test="${empty dto}">新增</c:if>
			-客户管理
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/orgInfo/saveUpdate/">
			<input type="hidden" id="id" name="id" value="${dto.id}" />
			<div class="table_full">
				<table>
					<tr>
						<td>所属集团</td>
						<td><input type="text" class="input length_5" id="belongTo" name="belongTo" value="${dto.belongTo}" /></td>
					</tr>
					<tr>
						<td>发展时间</td>
						<td><input type="text" class="input length_5 J_date" id="joinTime" name="joinTime"
							value="<fmt:formatDate value="${dto.joinTime}" pattern="yyyy-MM-dd" />" /></td>
					</tr>
					<tr>
						<td>机构编码</td>
						<td><input type="text" class="input length_5" id="code" name="code" value="${dto.code}" /></td>
					</tr>
					<tr>
						<td>机构名称</td>
						<td><input type="text" class="input length_5" id="name" name="name" value="${dto.name}" /></td>
					</tr>
					<tr>
						<td>省份名称</td>
						<td><input id="_provinceId" class="input length_5" readonly="readonly" type="text" value="${dto.provinceName}" /> <input id="provinceId"
							name="provinceId" type="hidden" value="${dto.provinceId}" /> <span class="selectAlert"
							onclick="popup_select('provinceId','省份','/provincePopup/',null,'cityId',false);">[<span>选择</span>]
						</span></td>
					</tr>
					<tr>
						<td>城市名称</td>
						<td><input id="_cityId" class="input length_5" readonly="readonly" type="text" value="${dto.cityName}" /> <input id="cityId" name="cityId"
							type="hidden" value="${dto.cityId}" /> <span class="selectAlert" onclick="popup_select('cityId','城市','/cityPopup/','provinceId',null,false);">[<span>选择</span>]
						</span></td>
					</tr>
					<tr>
						<td>查询类型</td>
						<td><input value="2" class="ace" name="queryTypeBatch" <c:if test="${dto.queryType==2 || dto.queryType==3}">checked="checked"</c:if>
							type="checkbox"> <span class="lbl">批量查询</span> <input value="1"
							<c:if test="${dto.queryType==1 || dto.queryType==3}">checked="checked"</c:if> name="queryTypeReal" class="ace" type="checkbox"> <span
							class="lbl">实时查询</span></td>
					</tr>
					<tr>
						<td>授权车辆数</td>
						<td><input type="text" class="input length_5" id="maxCarCounts" name="maxCarCounts" value="${dto.maxCarCounts}" /></td>
					</tr>
					<tr>
						<td>最大查询次数</td>
						<td><input type="text" class="input length_5" id="maxQueryCounts" name="maxQueryCounts" value="${dto.maxQueryCounts}" /></td>
					</tr>
					<tr>
						<td>策略名称</td>
						<td><input id="_tackId" class="input length_5" readonly="readonly" type="text" value="${dto.tackName}" /> <input id="tackId" name="tackId"
							type="hidden" value="${dto.tackId}" /> <span class="selectAlert"
							onclick="popup_select('tackId','策略名称','/spiTack/tackInfoPopup/',null,null,false);">[<span>选择</span>]
						</span></td>
					</tr>
					<tr>
						<td>电话</td>
						<td><input type="text" class="input length_5" id="tel" name="tel" value="${dto.tel}" /></td>
					</tr>
					<tr>
						<td>密钥</td>
						<td><input type="text" class="input length_5" id="appKey" name="appKey" value="${dto.appKey}" /></td>
					</tr>
					<tr>
						<td>联系人</td>
						<td><input type="text" class="input length_5" id="contacts" name="contacts" value="${dto.contacts}" /></td>
					</tr>
					<tr>
						<td>业务员名称</td>
						<td><input type="text" class="input length_5" id="salesman" name="salesman" value="${dto.salesman}" /></td>
					</tr>
					<tr>
						<td>地址</td>
						<td><input type="text" class="input length_5" id="address" name="address" value="${dto.address}" /></td>
					</tr>
					<tr>
						<td>到期时间</td>
						<td><input type="text" class="input length_5 J_date" id="expiredTime" name="expiredTime"
							value="<fmt:formatDate value="${dto.expiredTime}" pattern="yyyy-MM-dd" />" /></td>
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