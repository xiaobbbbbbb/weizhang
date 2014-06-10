<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
<title></title>

<link href="${ctx}/media/bootstrap/bootstrap.min.css?v=${version}" rel="stylesheet">
<link href="${ctx}/media/css/font-awesome.min.css?v=${version}" rel="stylesheet">
<link href="${ctx}/media/ace/css/ace.min.css?v=${version}" rel="stylesheet">
<link href="${ctx}/media/res/index/admin.css?v=${version}" rel="stylesheet" />
<script type="text/javascript">
	//全局变量
	var GV = {
		DIMAUB : "${ctx}",
		JS_ROOT : "/media/"
	};
</script>
<link href="${ctx}/media/uploadify3.1/uploadify.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/media/artDialog/skins/default.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/media/res/jquery-1.8.2.min.js?v=${version}"></script>
<script src="${ctx}/media/ec/js/jquery.ec-business.js?v=${version}"></script>
<script src="${ctx}/media/artDialog/artDialog.js?v=${version}"></script>
<script src="${ctx}/media/artDialog/iframeTools.js?v=v1.0"></script>
<script type="text/javascript" src="${ctx}/media/uploadify3.1/jquery.uploadify-3.1.js"></script>
<body class="J_scroll_fixed">
	<div class="nav">
		<ul class="cc">
			<li class="current"><a href="${ctx}/car/">车辆管理</a></li>
		</ul>
	</div>
	<div>
		<a href="${ctx}/car/template/">下载模板</a>
	</div>
	<div class="common-form">
		<form id="form" method="post" enctype="multipart/form-data">
	        <input type="hidden" id="fileSize"/>
	        <table align="center" id="up_table" style="width: 250px;">
	            <tr>
                   <td height="30px"></td>
                </tr>
                <tr>
                   <td valign="bottom" align="left"><input type="file" id="file_upload" name="file_upload" /></td>
                </tr>
                <tr>
                   <td><div id="fileQueue"></div></td>
                </tr>
                <tr id ="success"></tr>
			</table>
		</form>
	</div>
</body>

</html>
<script type="text/javascript">
	$(function(){
// 		Wind.use('ajaxForm', 'artDialog','toJSON', function () {
// 		});
	
		$("#file_upload").uploadFile({
   		 auto          : true,
   		 fileTypeDesc  : '请选择Excel文件(*.xls)',
            fileTypeExts  : '.xls', 
		     uploader      : '${ctx}/car/uploadFile',
		     onDialogClose : dialogClose,
		     onDialogOpen  : dialogOpen,
		     onUploadSuccess : uploadSuccess
   		});
	});

	 function dialogClose(data)
     {
     	$("#fileSize").val(data.queueLength);
     }
     
     function dialogOpen(data)
     {
     	$("#file_upload").uploadify('cancel');
     }
     
     function uploadSuccess(data)
     {
     	art.dialog.close();
  	    var dataObj=eval(data);
	    	if(dataObj.msg==100){
	    		art.dialog.alert('上传失败,请检查导入模板是否正确!');
	    	}
  	    else
  	    {
  	    	var html="<tr><td>成功导入"+dataObj.obj.successNums+"条！一共有"+dataObj.obj.totalNums+"条！重复数据"+dataObj.obj.repeatNums+"条！已存在"+dataObj.obj.exitsNums+"条！</td></tr>";
  	    	$("#success").empty().append(html);
  	    }
     }

     function upload()
		{
     	var $fileSize=$("#fileSize").val();
		    if($fileSize>0)
		    {
		    	$("#file_upload").uploadify('upload');
		    	return true;
		    }
		    else
		    {
		    	art.dialog.alert('请选择要上传的文件!');
		    	return false;
		    }
		}
</script>