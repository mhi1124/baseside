<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<link rel="stylesheet" href="${ctx }/static/plug-ins/select2/select2.min.css" />
<style>
.iconShow
{
	padding-left: 10px; 
	padding-top: 3px;
}
.source-icon
{
	 width:95%;
	 float:left;
}
@media screen and (max-width: 374px){
	.source-icon
	{
		 width:82%;
	}
}
@media screen and (min-width: 375px) and (max-width: 449px){
	.source-icon
	{
		 width:85%;
	}
}
@media screen and (min-width: 450px) and (max-width: 1279px) {
	.source-icon
	{
		 width:90%;
	}
}
@media screen and (min_width: 1280px) {
	.source-icon
	{
		 width:90%;
	}
}
</style>
<div class="page-header">
	<h1>
		<c:if test="${empty menu}">
		新增资源
		</c:if>
		<c:if test="${!empty menu}">
		编辑资源
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="menuForm" name="menuForm" class="form-horizontal" method="post">
			<c:if test="${!empty menu}">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"> 
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"> 
				<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"> 
				<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
				<input type="hidden" name="sid" id="mid" value="${menu.sid }">
			</c:if>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="type">资源类型</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<select class="form-control" id="type" name="menuType" style="width: 100%">
							<option value="2"
								<c:if test="${menu.menuType eq 2 }">selected="selected"</c:if>
							>菜单</option>
							<option value="3"
								<c:if test="${menu.menuType eq 3 }">selected="selected"</c:if>
							>按钮</option>
						</select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="menuStatus">资源状态</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<select class="form-control" id="menuStatus" name="menuStatus" style="width: 100%">
							<option value="1"
								<c:if test="${menu.menuStatus eq 1 }">selected="selected"</c:if>
							>正常</option>
							<option value="2"
								<c:if test="${menu.menuStatus eq 2 }">selected="selected"</c:if>
							>暂停使用</option>
						</select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="parentId">上级名称</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<select class="form-control" id="parentId" name="pid" style="width: 100%">
							<option value=""></option>
							<option value=" " <c:if test="${menu.pid eq null}">selected="selected"</c:if>>目录菜单</option>
							<c:forEach var="item" items="${menus }">
								<option value="${item.id }"
									<c:if test="${menu.pid eq item.id}">selected="selected"</c:if>>${item.text }</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="menuName">资源名称</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" name="menuName" id="menuName" type="text"
						value="${menu.menuName }" placeholder="资源名称..." />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="sourceKey">权限标识</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" name="sourceKey" id="sourceKey" type="text"
						value="${menu.sourceKey }" placeholder="权限标识..." />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="icon">资源标识</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control source-icon" name="icon" id="icon" type="text"
						value="${menu.icon }" placeholder="资源标识..." />
						<i id="iconShow" data-rel="tooltip" data-placement="left" data-original-title="点击清除图标" class="<c:if test="${fn:length(menu.icon) gt 0 }">${menu.icon } green fa-2x iconShow tooltip-success</c:if>"></i>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="menuUrl">资源URL</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" name="menuUrl" id="menuUrl" type="text"
						value="${menu.menuUrl }" placeholder="资源URL..." />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="menuRemark">资源描述</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" name="menuRemark" id="menuRemark" type="text"
						value="${menu.menuRemark }" placeholder="资源描述..." />
					</div>
				</div>
			</div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#menuForm').submit();<!-- commitForm(); -->" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i>&nbsp;
		<c:if test="${empty menu}">
		添加
		</c:if>
		<c:if test="${!empty menu}">
		保存
		</c:if>
	</button>
	<button id="btn" type="button" class="btn btn-info btn-sm" onclick="base.common.loadPage('/sys/menu/toMenuList.html')">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>
<script type="text/javascript" src="${ctx }/static/plug-ins/select2/select2.min.js"/>
<script type="text/javascript" src="${ctx }/static/plug-ins/select2/zh-CN.js"/>
<script type="text/javascript" src="${ctx}/static/plug-ins/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx }/static/js/modules/sys/menuForm.js"/>
