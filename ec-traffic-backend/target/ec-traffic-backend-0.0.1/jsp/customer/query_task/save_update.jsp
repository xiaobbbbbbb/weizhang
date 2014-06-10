<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/save_update_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap J_check_wrap">
		<div class="h_a"><c:if test="${!empty dto}">编辑</c:if><c:if test="${empty dto}">新增</c:if>-批查任务</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/queryTask/saveUpdate">
	        <input type="hidden" id="id" name="id" value="${dto.id}" />
			<div class="table_full">
				<table>
		            <tr>
                        <td>机构编号</td>
                        <td><input type="text" class="input length_5" id="orgCode" name="orgCode" value="${dto.orgCode}"  />
                            </td>
                    </tr>
		            <tr>
                        <td>操作员</td>
                        <td><input type="text" class="input length_5" id="operatorId" name="operatorId" value="${dto.operatorId}"  />
                            </td>
                    </tr>
		            <tr>
                        <td>更新时间</td>
                        <td><input type="text" readonly="readonly" id="updateTime" name="updateTime" value="${dto.updateTime}" />   
</td>
                    </tr>
		            <tr>
                        <td>创建时间</td>
                        <td><input type="text" readonly="readonly" id="createTime" name="createTime" value="${dto.createTime}" />   
</td>
                    </tr>
		            <tr>
                        <td>任务状态</td>
                        <td><input type="text" class="input length_5" id="status" name="status" value="${dto.status}"  />
                            </td>
                    </tr>
		            <tr>
                        <td>任务类型</td>
                        <td><input type="text" class="input length_5" id="taskType" name="taskType" value="${dto.taskType}"  />
                            </td>
                    </tr>
		            <tr>
                        <td>任务描述</td>
                        <td><input type="text" class="input length_5" id="taskDetail" name="taskDetail" value="${dto.taskDetail}"  />
                            </td>
                    </tr>
		            <tr>
                        <td>expression_id</td>
                        <td><input type="text" class="input length_5" id="expressionId" name="expressionId" value="${dto.expressionId}"  />
                            </td>
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