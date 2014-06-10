<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="base.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	//到指定的分页页面
	function topage(page) {
		$("#page").val(page);
		$('form.J_ajaxForm').submit();
	}
</script>
<input type="hidden" id="page" name="page" value="${param.page}" />
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


