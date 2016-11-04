<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="page-header">
	<button id="btnAdd" type="button" onclick="base.common.addModel('/sys/role/addUserUI.html')" class="btn btn-primary btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;添加
	</button>
	<!-- <button id="btnEdit" type="button" onclick="webside.common.editModel('/resource/editUI.html')" class="btn btn-info btn-sm">
		 <i class="fa fa-pencil-square-o"></i>&nbsp;编辑
	</button> -->
	<button id="btnDel" type="button" onclick="base.common.delModel('/sys/role/deleteBatch.html', customSearch)" class="btn btn-danger btn-sm">
		<i class="fa fa-trash-o"></i>&nbsp;批量删除
	</button>
	
	<button id="btnSetResource" type="button" onclick="base.common.editModel('/sys/role/assortUI.html')" class="btn btn-info btn-sm">
		<i class="fa fa-gears"></i>&nbsp;分配角色
	</button>
	
</div>
<div class="input-group">
     <input id="searchKey" type="text" class="input form-control" placeholder="角色名称...">
     <span class="input-group-btn">
         <button id="btnSearch" class="btn btn-primary btn-sm" type="button"> <i class="fa fa-search"></i> 搜索</button>
     </span>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable" style="min-height: 127px;">
		<div class="widget-box transparent ui-sortable-handle" style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<h4 class="widget-title lighter">角色列表</h4>
				<div class="widget-toolbar no-border">
					<a href="#" data-action="fullscreen" class="orange2"> 
						<i class="ace-icon fa fa-arrows-alt"></i>
					</a> 
					<a href="#" data-action="collapse" class="green"> 
						<i class="ace-icon fa fa-chevron-up"></i>
					</a>
				</div>
			</div>
			<div class="widget-body" style="display: block;">
				<div class="widget-main padding-6 no-padding-left no-padding-right">
					<div id="dtGridContainer" class="dlshouwen-grid-container"></div>
					<div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${pageContext.request.contextPath}/static/js/modules/sys/userList.js"></script>