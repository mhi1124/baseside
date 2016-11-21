<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="page-header">
	<h1>
		<c:if test="${empty user}">
		新增用户
		</c:if>
		<c:if test="${!empty user}">
		编辑用户
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="userForm" name="userForm" class="form-horizontal" method="post">
			<c:if test="${!empty user}">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"> 
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"> 
				<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"> 
				<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
				<input type="hidden" name="sid" id="rid" value="${user.sid }">
			</c:if>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="roleName">邮箱</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" name="accountName" id="accountName" type="email"
						value="${user.accountName }" placeholder="角色名称..." />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="roleKey">密码</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" name="password" id="password" type="password"
						 placeholder="密码..." />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="description">确认密码</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" name="repassword" id="repassword" type="password"
			          placeholder="确认密码..."/>
					</div>
				</div>
			</div>
			<div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="userName">真实姓名</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="userName" id="userName" type="text"
		         value="${user.userName }" placeholder="真实姓名..."/>
		      </div>
		      </div>
		   </div>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="userName">所属角色</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		        <select class="form-control" name="role.sid" id="roleId" style="width: 100%">
					<option value=""></option>
					<c:choose>
						<c:when test="${!empty user}">
							<c:forEach var="role" items="${roleList }">
								<option value="${role.sid }" <c:if test="${user.role.sid eq role.sid}"> selected="selected" </c:if>>${role.roleName }</option>
							</c:forEach>							
						</c:when>
						<c:otherwise>
							<c:forEach var="role" items="${roleList }">
								<option value="${role.sid }">${role.roleName }</option>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					</select>
				</div>
		      </div>
		   </div> 
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="description">用户描述</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="description" id="description" type="text"
		         value="${user.description }" placeholder="用户描述..."/>
		      </div>
		      </div>
		   </div> 
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#userForm').submit();" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i>&nbsp;
		<c:if test="${empty user}">
		添加
		</c:if>
		<c:if test="${!empty user}">
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
<script type="text/javascript" src="${ctx }/static/js/modules/sys/userForm.js"/>
