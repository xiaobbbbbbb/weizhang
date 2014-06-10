<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="base.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		var url = url || window.location.href || '';
		if (url) {
			url = (url.match(/^([^#]+)/) || [])[1];
			var index = url.indexOf('?');
			if (index > 0) { //如果分页存在固定参数，如省份下的城市，必须存在省份ID
				var paramStr = url.substring(index + 1, url.length).split('&');
				for ( var i = 0; j = paramStr[i]; i++) {
					var jindex = j.indexOf('='), 
					    name = j.substring(0, jindex), 
					    val = j.substring(jindex + 1, j.length), 
					    $objName = $("input[name='"+ name + "']");
					if ($objName.length == 0) {//表示页面其他位置默认没有该参数
						var $input = $("<input>", {
							"type" : "hidden",
							"name" : name,
							"value" : val
						});
						$("#hidden_param").append($input);//追加隐藏值
					}
				}
			}
		}
	});

	//到指定的分页页面
	function topage(page) {
		$('#page').val(page);
		var $form = $('form.J_ajaxForm');
		form_submit($form);
	}
</script>
<input type="hidden" id="page" name="page" value="${param.page}" />
<span id="hidden_param"></span>
<c:if test="${ECPage.totalPage>0}">
	<div class="pagination pagination-left">
		<ul>
			<li class="disabled"><a href="javascript:void(0);">共${ECPage.totalPage}页/${ECPage.totalRows}条记录</a></li>
			<c:choose>
				<c:when test="${ECPage.currentPage==1}">
					<li class="disabled"><a href="javascript:void(0);">首页</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:topage('1');">首页</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${ECPage.currentPage>1}">
					<li><a href="javascript:topage('${ECPage.currentPage-1}');">上一页</a></li>
				</c:when>
				<c:otherwise>
					<li class="disabled"><a href="javascript:void(0);">上一页</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach begin="${ECPage.startPage}" end="${ECPage.endPage}" var="wp">
				<c:choose>
					<c:when test="${ECPage.currentPage==wp}">
						<li class="disabled"><a href="javascript:void(0);">${wp}</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:topage('${wp}');">${wp}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${ECPage.currentPage<ECPage.totalPage}">
					<li><a href="javascript:topage('${ECPage.currentPage+1}');">下一页</a></li>
				</c:when>
				<c:otherwise>
					<li class="disabled"><a href="javascript:void(0);">下一页</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${ECPage.currentPage<ECPage.totalPage}">
					<li><a href="javascript:topage('${ECPage.totalPage}');">末页</a></li>
				</c:when>
				<c:otherwise>
					<li class="disabled"><a href="javascript:void(0);">末页</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</c:if>


