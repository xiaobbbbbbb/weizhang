<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/base.jsp"%>
<jsp:include page="../../common/header.jsp" />
<link href="${ctx}/media/ztree3.5/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/media/ztree3.5/jquery.ztree.core-3.5.min.js" type="text/javascript"></script>
<script src="${ctx}/media/ztree3.5/jquery.ztree.excheck-3.5.min.js" type="text/javascript"></script>

<script type="text/javascript">
	$(function($) {
		$('button.J_ajax_submit_btn').on('click', function(e) {
			loadIds();
		});
	});

	function zTreeOnClick(event, treeId, treeNode, clickFlag) {
		//空实现，目的是在别的页面可以共用这棵树
	}
	function zTreeOnDblClick(event, treeId, treeNode) {
		//空实现，目的是在别的页面可以共用这棵树
	};

	//异步成功后
	function onAsyncSuccess(event, treeId, treeNode, msg) {
		if (treeId == "treeDate") {
			expandAll();
		}
	}

	//加载选中的资源
	function loadIds() {
		var treeObj = $.fn.zTree.getZTreeObj("treeDate"), nodes = treeObj
				.getCheckedNodes(true), array = new Array(); //用于保存 选中的那一条数据的ID
		for ( var i = 0; i < nodes.length; i++) {
			array.push(nodes[i].id); //将选中的值 添加到 array中 
		}
		$("#ids").val(array);
		return array;
	}

	$(function() {
		var $showUrl = $("#showUrl").val();
		var $roleId = $("#roleId").val();
		var setting = {
			check : {
				enable : true
			},
			async : {
				enable : true,
				type : "get",
				url : "${ctx}/ralRole/tree?showUrl=" + $showUrl + "&roleId="
						+ $roleId + "&time=" + Math.random(),
				autoParam : [ "id" ],
				dataFilter : filter
			},
			callback : {
				onClick : zTreeOnClick,
				onDblClick : zTreeOnDblClick,
				onAsyncSuccess : onAsyncSuccess
			}
		};
		$.fn.zTree.init($("#treeDate"), setting);
	});

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for ( var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}

	//判断是否是父节点
	function getIsParent() {
		var treeObj = $.fn.zTree.getZTreeObj("treeDate");
		var isParent = false;
		var sNodes = treeObj.getSelectedNodes();
		if (sNodes.length > 0) {
			isParent = sNodes[0].isParent;
		}
		return isParent;
	}

	//默认打开所有
	function expandAll() {
		var zTree = $.fn.zTree.getZTreeObj("treeDate");
		expandNodes(zTree.getNodes());
	}

	function expandNodes(nodes) {
		if (!nodes)
			return;
		var zTree = $.fn.zTree.getZTreeObj("treeDate");
		for ( var i = 0, l = nodes.length; i < l; i++) {
			zTree.expandNode(nodes[i], true, false, false);
			if (nodes[i].isParent && nodes[i].zAsync) {
				expandNodes(nodes[i].children);
			}
		}
	}
</script>


<body class="J_scroll_fixed">
	<div class="wrap J_check_wrap">
		<div class="h_a">
			<c:if test="${!empty dto}">编辑</c:if>
			<c:if test="${empty dto}">新增</c:if>
			-用户角色
		</div>
		<form class="J_ajaxForm" method="post" action="${ctx}/ralRole/saveUpdate">
			<input id="ids" type="hidden" name="ids" /> <input type="hidden" id="roleId" name="roleId" value="${dto.roleId}" />

			<div class="table_full">
				<table>
					<tr>
						<td>角色名称</td>
						<td><input type="text" class="input length_5" id="name" name="name" value="${dto.name}" /></td>
					</tr>
					<tr>
						<td>角色描述</td>
						<td><input type="text" class="input length_5" id="message" name="message" value="${dto.message}" /></td>
					</tr>
					<tr>
						<td valign="top">角色权限</td>
						<td><div id="roleTree">
								<input type="hidden" id="showUrl" value="false" /> <input type="hidden" id="parentId" /> <input type="hidden" id="selectId" /> <input
									type="hidden" id="selectName" /> <input type="hidden" id="isParent" />
								<ul id="treeDate" class="ztree" style="padding-top: 0px;"></ul>
							</div></td>
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