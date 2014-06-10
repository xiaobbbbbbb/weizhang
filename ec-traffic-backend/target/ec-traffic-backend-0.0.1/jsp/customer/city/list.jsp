<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/list_header.jsp" />

<body class="J_scroll_fixed">
	<div class="nav">
		<ul class="cc">
			<li class="current"><a href="${ctx}/city/">城市管理</a></li>
			<li><a href="${ctx}/city/saveUpdateUI">添加</a></li>
		</ul>
	</div>
	<form class="J_ajaxForm" method="get" action="${ctx}/city/">
		<div class="search_type cc mb10">
	<table id="searchColums">
				        <tr>
				            <td align="left" valign="top">
				            <!-- 如果是组合查询 -->
				                        <table>
				                            <tr>
				                              <td class="field_title"><span title="所属省份">所属省份</span>：</td>
				                              <td class="field_input decimal">
				                                 <input id="beginProvinceId" type="text" name="beginProvinceId" value="${param.beginProvinceId}" />~<input id="endProvinceId}" type="text" name="endProvinceId}" value="${param.endProvinceId}" />
				                              </td>
				                            </tr>
				                        </table>
				                           <table>
				                              <tr>
				                                 <td class="field_title"><span title="城市名称">城市名称</span>：</td>
				                                 <td class="field_input">
				                                    <input id="name" class="input_text" type="text" name="name" value="${param.name}" />
				                                 </td>
				                              </tr>
				                           </table>
				               </td>
				           <td><button class="J_ajax_submit_btn" type="submit">提交</button></td>
                        </tr>
                     </table>
	</div>
	<div class="table_list">
				<table>
					<thead>
						<tr>
							<th align="center">操作</th>
							<th align="center">序号</th>
				              
				              <th>是否有效，1为有效，0未无效</th>
				              <th>所属省份</th>
				              <th>城市名称</th>
				              <th>拼音首字符</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ECPage.list}" var="dto" varStatus="sn">
							<tr>
								<td align="center">
									<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
										<a href="${ctx}/city/saveUpdateUI?id=${dto.id}" class="green"> <i class="icon-pencil bigger-130"></i></a>
										<a href="${ctx}/city/delete?ids=${dto.id}" class="red J_ajax_del"><i class="icon-trash bigger-130"></i></a>
									</div>
								</td>
								<td>${sn.count}</td>
	                          <td>${dto.status}</td>
	                          <td>${dto.provinceId}</td>
	                          <td>${dto.name}</td>
	                          <td>${dto.keyTitle}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="p5">
					<jsp:include page="../../common/pager.jsp" />
				</div>
			</div>
	</form>
</body>
</html>