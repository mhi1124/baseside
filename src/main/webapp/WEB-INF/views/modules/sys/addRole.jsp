<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="page-header">
	<h1>
		<c:if test="${empty role}">
		新增角色
		</c:if>
		<c:if test="${!empty role}">
		编辑角色
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="roleForm" name="roleForm" class="form-horizontal" method="post">
			<c:if test="${!empty role}">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"> 
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"> 
				<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"> 
				<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
				<input type="hidden" name="sid" id="rid" value="${role.sid }">
			</c:if>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="roleName">角色名称</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" name="roleName" id="roleName" type="text"
						value="${role.roleName }" placeholder="角色名称..." />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="roleKey">角色标识</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" name="roleKey" id="roleKey" type="text"
						value="${role.roleKey }" placeholder="角色标识..." />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="description">资源描述</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" name="description" id="description" type="text"
						value="${role.description }" placeholder="资源描述..." />
					</div>
				</div>
			</div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#roleForm').submit();" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i>&nbsp;
		<c:if test="${empty role}">
		添加
		</c:if>
		<c:if test="${!empty role}">
		保存
		</c:if>
	</button>
	<button id="btn" type="button" class="btn btn-info btn-sm" onclick="base.common.loadPage('/sys/role/roleListUI.html')">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>
<script type="text/javascript" src="${ctx }/static/plug-ins/select2/select2.min.js"/>
<script type="text/javascript" src="${ctx }/static/plug-ins/select2/zh-CN.js"/>
<script type="text/javascript" src="${ctx}/static/plug-ins/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx }/static/js/modules/sys/roleForm.js"/>
