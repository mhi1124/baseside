<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${ctx }/static/plug-ins/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
  
<div class="page-header">
	<h1>
		权限设置-<small>[${role.roleName }]</small>
	</h1>
</div>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		<form id="permissionForm" name="permissionForm" class="form-horizontal" role="form" method="post">
		<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
		<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
		<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
		<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
		<input type="hidden" id="id" name="id" value="${role.sid }">
		   <!-- 资源树 -->
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="description">资源</label>
		      <div class="col-sm-10">
		         <div id="tree" class="ztree"></div>
		      </div>
		   </div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="sumbitForm('/role/assort.html' , '/role/roleListUI.html')" class="btn btn-success btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;
		保存
	</button>
		<button id="btn" type="button" onclick="base.common.loadPage('/role/roleListUI.html')" class="btn btn-info btn-sm">
		<i class="fa fa-trash-o"></i>&nbsp;返回
	</button>
</div>

<script type="text/javascript" src="${ctx }/static/plug-ins/zTree/js/jquery.ztree.core.js"></script> 
<script type="text/javascript" src="${ctx }/static/plug-ins/zTree/js/jquery.ztree.excheck.js"></script> 

<script type="text/javascript">
var treeObj;
$(document).ready(function(){
	var mdata = '';
	var zNodes = ${treeNodes};
	var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	treeObj = $.fn.zTree.init($("#tree"), setting, zNodes);
});

function sumbitForm(commitUrl, listUrl){
	debugger;
	var nodes = treeObj.getCheckedNodes(true);
	
	var menuid="";
	for(var i=0;i<nodes.length;i++){
		menuid+=nodes[i].id+",";
	}
	$.ajax({
        type : "POST",
        url : ctx  + commitUrl,
        data : {
            "roleId" : $("#id").val(),
            "menuIds" : menuid
        },
        dataType : "json",
        beforeSend : function() {
            index = layer.load();
        },
        success : function(resultdata) {
            layer.close(index);
            if (resultdata.success) {
                layer.msg(resultdata.message, {
                    icon : 1
                });
                base.common.loadPage(listUrl + '?page=' + $("#pageNum").val() + '&rows=' + $("#pageSize").val() + '&sidx=' + $("#orderByColumn").val() + '&sord=' + $("#orderByType").val());
            } else {
                layer.msg(resultdata.message, {
                    icon : 5
                });
            }
        },
        error : function(errorMsg) {
            layer.close(index);
            layer.msg('服务器未响应,请稍后再试', {
                icon : 2
            });
        }
    });
}

</script>