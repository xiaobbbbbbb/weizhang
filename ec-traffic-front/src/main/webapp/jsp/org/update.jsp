<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/base.jsp"%>
<jsp:include page="../common/save_update_header.jsp" />

<body class="J_scroll_fixed">
	<div class="wrap J_check_wrap">
		<div class="h_a">机构信息</div>
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="table_full">
				<table>
					<tr>
						<th width="100">机构名称</th>
						<td><input type="text" name="name"  value="${dto.name}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>地址</th>
						<td><input type="text" name="address" value="${dto.address}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>联系人</th>
						<td><input type="text" name="contacts" value="${dto.contacts}" class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>电话</th>
						<td><input type="text" name="tel" value="${dto.tel}" class="input length_5" /></td>
					</tr>
					<tr>
						<th>业务员</th>
						<td><input type="text" name="salesman" value="${dto.salesman}" class="input length_5" /></td>
					</tr>
				</table>
			</div>
				<div class="btn_wrap_pd">
					<button class="btn btn_submit mr10 J_ajax_submit_btn" type="submit">修改</button><span id="message_span"> </span>
			</div>
	</div>
</body>
</html>
<script type="text/javascript">

$(function(){
	$("button.btn_submit").click(function(){
		var name = $("input[name=name]").val();
		var id = $("input[name=id]").val();
		var address = $("input[name=address]").val();
		var contacts = $("input[name=contacts]").val();
		var tel = $("input[name=tel]").val();
		var salesman = $("input[name=salesman]").val();
		url="${ctx}/org/update?id="+id+"&name="+name+"&address="+address+"&contacts="+contacts+"&tel="+tel+"&salesman="+salesman;
		$.getJSON(url,function(voData) {
			if(voData.success){
				var html='<span class="tips_success">'+voData.msg + '</span>';
				$("#message_span").empty().append(html);
			}else{
				var html='<span class="tips_error">'+voData.msg + '</span>';
				$("#message_span").empty().append(html);
			}
		});
	});
});
</script>