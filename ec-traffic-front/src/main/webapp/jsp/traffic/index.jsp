<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/base.jsp"%>
<jsp:include page="../common/list_header.jsp" />

<body class="J_scroll_fixed">
	<div class="nav">
		<ul class="cc">
			<li class="current"><a href="#">违章查询</a></li>
		</ul>
	</div>
	<div class="h_a">违章查询</div>
		<div class="table_full">
			<table>
		  		<thead id="ser_thead">
				<tr>
					<th width="100">查询省份</th>

					<td><select name="province" id="province">
							<option value="">请选择省份</option>
							<c:forEach items="${qArea}" var="dto" varStatus="sn">
								<option value="${dto.provinceId }">${dto.provinceName}</option>
							</c:forEach>
					</select> <span class="must_red">*</span></td>
				</tr>
				<tr>
					<th>查询城市</th>
					<td><select name="city" id="city" >
					</select></td>
				</tr>
				</thead>
				<tbody id="ser_tbody">
				</tbody>
			</table>
		</div>
				<div >
					<button class="btn btn_search mr10 " >查询</button>
				</div>
		<div class="table_list">
			<table id= "list_table" style="width: 100%;margin:auto">
			
			</table>
		</div>
			
		
</body>
</html>
<script>
	$(function(){
		$(".btn_search").click(function(){
			var param= "";
			var provinceId = $("#province").val()?$("#province").val():0;
			var cityId = $("#city").val()?$("#city").val():0;
			var carNo = $("#carNo").val()?$("#carNo").val():"";
			var carFrameNo = $("#carFrameNo").val()? $("#carFrameNo").val():"";
			var carEngineNo =$("#carEngineNo").val()?$("#carEngineNo").val():"";
			var certificate =$("#certificate").val()?$("#certificate").val():"";
			var carType =$("#cartype").val()?$("#cartype").val():00;
			param+="provinceId="+provinceId;
			param+="&cityId="+cityId;
			param+="&carNo="+carNo;
			param+="&carFrameNo="+carFrameNo;
			param+="&carEngineNo="+carEngineNo;
			param+="&certificate="+certificate;
			param+="&carType="+carType;
			var url = "${ctx}/traffic/search";
			$.ajax({
				url:	url,	
				type: 'get',
				data:param,
				dataType:'json',
				beforeSend:function(){
					
					var text = $(".btn_search").text();
                    //按钮文案、状态修改
                    $(".btn_search").text(text + '中...').prop('disabled', true).addClass('disabled');
				},
				success:function(voData) {
							$("#list_table").empty();
			// 				if(voData.errorCode==0){
							var html="<thead>"+
								"<td>序号</td>"+
								"<td>车牌号</td>"+
								"<td>违章详情</td>"+
								"<td>违章地点</td>"+
								"<td>违章时间</td>"+
								"<td>文书号</td>"+
								"<td>扣分</td>"+
								"<td>罚款</td></thead><tbody>";
							if(voData&&voData.value&&voData.value.traffics&&voData.value.traffics.length>0){
								$.each(voData.value.traffics,function(index, value){
									html+="<tr><td>"+(index+1)+"</td>";
									html+="<td>"+value.carNo+"</td>";
									html+="<td>"+value.trafficDetail+"</td>";
									html+="<td>"+value.trafficLocation+"</td>";
									html+="<td>"+dateTostr(value.trafficTime)+"</td>";
									html+="<td>"+value.archive+"</td>";
									html+="<td>"+value.scores+"</td>";
									html+="<td>"+value.money+"</td></tr>";
								});
							}else{
								html+="<tr><td colspan='8' align='center'>无违章信息</td></tr>"
							}
							html+="</tbody>";
							 $(".btn_search").text('查询').prop('disabled', false).removeClass('disabled');
							$("#list_table").append(html);
						}
					});
				});
		
		$("#province").change(function(){
			$("#city").empty();
			var provinceId=$("#province").val();
			var url='${ctx}/traffic/getCity?id='+provinceId;
			$.getJSON(url,function(voData) {
				var html =" ";
				if(voData&&voData.length>0){
					$.each(voData,
							function(index, value) {
						html+="<option value='"+value.cityId+"'>"+value.cityName+"</option>";
					});
					$("#city").append(html);
				}
				$("#ser_tbody").empty();
				
				var cityId = $("#city").val()?$("#city").val():0;
				//根据input_rule找到所需填的字段
				var url2='${ctx}/traffic/rule?provinceId='+provinceId+"&cityId="+cityId;
				$.getJSON(url2,function(voData) {
					var html ="";
					if(voData){
						html+="<tr><th>车辆类型</th><td><select id='cartype'><option value='2'>小型车</option><option value='1'>大型车</option></select> </td></tr>";
						html+="<tr><th>车牌号</th><td><input type='text' name='carNo' id='carNo' class='input length_5'";
						if(!voData.carNoPrefix){
							voData.carNoPrefix = "";
						}
						html+=" value='"+voData.carNoPrefix+"' /><span class='must_red'>*</span></td></tr>";
						if(voData.carFrameLen>0){
							html+="<tr><th>车架号</th><td><input type='text' name='carFrameNo' id='carFrameNo' class='input length_5' /><span class='must_red'>*长度为后"
								+voData.carFrameLen+"位</span></td></tr>";
						}if(voData.carEngineLen>0){
							html+="<tr><th>引擎号</th><td><input type='text' name='carEngineNo' id='carEngineNo' class='input length_5' /><span class='must_red'>*长度为后"
								+voData.carEngineLen+"位</span></td></tr>";
						}if(voData.carCertificateLen>0){
							html+="<tr><th>证书号</th><td><input type='text' name='certificate'  id='certificate' class='input length_5' /><span class='must_red'>*长度为后"
								+voData.carEngineLen+"位</span></td></tr>";
						}
						$("#ser_tbody").append(html);
					}
				});
			});
		});
	
		$("#city").change(function(){
			$("#ser_tbody").empty();
			var provinceId=$("#province").val();
			var cityId = $("#city").val();
			//根据input_rule找到所需填的字段
			var url2='${ctx}/traffic/rule?provinceId='+provinceId+'&cityId='+cityId;
			$.getJSON(url2,function(voData) {
				var html ="";
				if(voData){
					html+="<tr><th>车辆类型</th><td><select id='cartype'><option value='2'>小型车</option><option value='1'>大型车</option></select> </td></tr>";
					html+="<tr><th>车牌号</th><td><input type='text' name='carNo' id='carNo' class='input length_5'";
					if(!voData.carNoPrefix){
						voData.carNoPrefix = "";
					}
					html+=" value='"+voData.carNoPrefix+"' /><span class='must_red'>*</span></td></tr>";
					if(voData.carFrameLen>0){
						html+="<tr><th>车架号</th><td><input type='text' name='carFrameNo' id='carFrameNo'  class='input length_5' /><span class='must_red'>*长度为后"
							+voData.carFrameLen+"位</span></td></tr>"
					}if(voData.carEngineLen>0){
						html+="<tr><th>引擎号</th><td><input type='text' name='carEngineNo' id='carEngineNo' class='input length_5' /><span class='must_red'>*长度为后"
							+voData.carEngineLen+"位</span></td></tr>"
					}if(voData.carCertificateLen>0){
						html+="<tr><th>证书号</th><td><input type='text' name='certificate' id='certificate' class='input length_5' /><span class='must_red'>*长度为后"
							+voData.carEngineLen+"位</span></td></tr>";
					}
					$("#ser_tbody").append(html);
				}
			});
		});
		
		
	});
		
	function dateTostr(dateTime){
		var date = new Date(dateTime);
		var month = "";
		var day = "";
		var hour ="";
		if(date.getMonth()<9){
			month = "0"+(date.getMonth()+1);
		}
		if(date.getDate()<10){
			day = "0"+date.getDate();
		}else{
			day = date.getDate();
		}
		
		
		return date.getFullYear()+"-"+month+"-"+day+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
	}
	
	
</script>