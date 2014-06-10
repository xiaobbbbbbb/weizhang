<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/save_update_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap J_check_wrap">
		<div class="h_a"><c:if test="${!empty dto}">编辑</c:if><c:if test="${empty dto}">新增</c:if>-城市管理</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/city/saveUpdate">
	        <input type="hidden" id="id" name="id" value="${dto.id}" />
			<div class="table_full">
				<table>
		            <tr>
                        <td>是否有效，1为有效，0未无效</td>
                        <td><input type="text" class="input length_5" id="status" name="status" value="${dto.status}"  />
                            </td>
                    </tr>
		            <tr>
                        <td>所属省份</td>
                        <td>							   <input id="_provinceId" class="input length_5" readonly="readonly" type="text" value="" />
							   <input id="provinceId" name="provinceId" type="hidden" value="${dto.provinceId}" />
						       <span class="selectAlert" onclick="input_popup('provinceId','所属省份','/city/provincePopup/',null,false);">[<span>选择</span>]</span>
                           </td>
                    </tr>
		            <tr>
                        <td>城市名称</td>
                        <td><input type="text" class="input length_5" id="name" name="name" value="${dto.name}"  />
                            </td>
                    </tr>
		            <tr>
                        <td>拼音首字符</td>
                        <td><input type="text" class="input length_5" id="keyTitle" name="keyTitle" value="${dto.keyTitle}"  />
                            </td>
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