<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/base.jsp"%>
<jsp:include page="../common/save_update_header.jsp" />
<link href="${ctx}/media/artDialog/skins/default.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/media/artDialog/artDialog.js?v=${version}"></script>
<script src="${ctx}/media/artDialog/iframeTools.js?v=v1.0"></script>
<body class="J_scroll_fixed">
	<div class="wrap J_check_wrap">
		<div class="h_a">修改密码</div>
			<div class="table_full">
				<table >
					<tr>
						<th width="100">原始密码</th>
						<td><input type="password" name="oldPassword"   class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>新密码</th>
						<td><input type="password" name="password"  class="input length_5" /><span class="must_red">*</span></td>
					</tr>
					<tr>
						<th>确认密码</th>
						<td><input type="password" name="passwd"  class="input length_5" /><span class="must_red">*</span></td>
					</tr>
				</table>
			</div>
				<div class="btn_wrap_pd">
					<button class="btn btn_submit mr10 J_ajax_submit_btn" type="submit">修改</button><span id="message_span"></span>
				</div>
	</div>
</body>
</html>

<script>

$(function(){
	$("button.btn_submit").click(function(){
		var oldPassword = $("input[name=oldPassword]").val();
		var password = $("input[name=password]").val();
		var passwd = $("input[name=passwd]").val();
		url="${ctx}/user/update?oldPassword="+oldPassword+"&password="+password+"&passwd="+passwd;
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