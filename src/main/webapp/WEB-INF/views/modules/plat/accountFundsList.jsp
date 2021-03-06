<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="page-header">
	<button id="btnAdd" type="button" onclick="base.common.addModel('/plat/addAccountFundsUI.html')" class="btn btn-primary btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;添加提现记录
	</button>
	<button id="btnEdit" type="button" onclick="base.common.editModel('/plat/editFundsUI.html')" class="btn btn-info btn-sm">
		 <i class="fa fa-pencil-square-o"></i>&nbsp;到账
	</button>
	<button id="btnEdit" type="button" onclick="ajaxEdit('/plat/amountToPcarBatch.html','0')" class="btn btn-success btn-sm">
		 <i class="fa fa-pencil-square-o"></i>&nbsp;P卡转出
	</button>
	<button id="btnEdit" type="button" onclick="ajaxEdit('/plat/amountToBankBatch.html','1')" class="btn btn-success btn-sm">
		 <i class="fa fa-pencil-square-o"></i>&nbsp;实际到账
	</button>
	<button id="btnDel" type="button" onclick="base.common.delModel('/plat/deleteFundsBatch.html', customSearch)" class="btn btn-danger btn-sm">
		<i class="fa fa-trash-o"></i>&nbsp;批量删除
	</button>
	
</div>
<div class="input-group">
	<div class="col-sm-2">
		<div class="clearfix">
			<input class="form-control" id="searchKey" type="text" placeholder="账号名称..." />
		</div>
	</div>
	<div class="col-sm-2" style="width: 120px">
		<div class="clearfix" >
			<select class="form-control" name="inP" id="inP">
				<option value="1">是否到账</option>
				<option value="2">未到账</option>
				<option value="3">已到账</option>
			</select>
		</div>
	</div>
	
	<!-- <div class="col-sm-2" style="width: 140px">
		<div class="clearfix" >
			<select class="form-control" name="inP" id="inP">
				<option value="1">全部</option>
				<option value="2">未到账</option>
				<option value="3">已到账</option>
			</select>
		</div>
	</div> -->
	
	<label class="col-sm-1 no-padding-right">开始时间:</label>
	<div class="col-sm-3" style="width: auto;">
		<div class="clearfix">
			<input class="form-control" id="startTime" type="date" value="${startTime}" placeholder="提现时间..." />
		</div>
	</div>
	<label class="col-sm-1 no-padding-right">结束时间:</label>
	<div class="col-sm-3" style="width: auto;">
		<div class="clearfix">
			<input class="form-control" id="endTime" type="date" value="${endTime}" placeholder="提现时间..." />
		</div>
	</div>
     <span class="input-group-btn">
         <button id="btnSearch" class="btn btn-primary btn-sm" type="button"> <i class="fa fa-search"></i> 搜索</button>
     </span>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable" style="min-height: 127px;">
		<div class="widget-box transparent ui-sortable-handle" style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<h4 class="widget-title lighter">结汇详情列表</h4>
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
<script src="${pageContext.request.contextPath}/static/js/modules/plat/accountFundsList.js"></script>