<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="page-header">
	<h1>
		<c:if test="${empty platAccount}">
		新增账号
		</c:if>
		<c:if test="${!empty platAccount}">
		编辑账号
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="platAccountForm" name="platAccountForm" class="form-horizontal" method="post">
			<c:if test="${!empty platAccount}">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"> 
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"> 
				<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"> 
				<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
				<input type="hidden" name="sid" id="id" value="${platAccount.sid}">
			</c:if>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="platNo">平台</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<select class="form-control" name="platNo" id="platNo" style="width: 100%">
							<option value=""></option>
							<c:choose>
								<c:when test="${!empty platAccount}">
									<c:forEach var="plat" items="${platList }">
										<option value="${plat}" <c:if test="${platAccount.platNo eq plat}"> selected="selected" </c:if>>${plat}</option>
									</c:forEach>							
								</c:when>
								<c:otherwise>
									<c:forEach var="plat" items="${platList }">
										<option value="${plat}">${plat}</option>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="platAccount">平台账号</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" name="platAccount" id="platAccount" type="text"
						 value="${platAccount.platAccount}" placeholder="平台账号..." />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="paypalCurrency">PAYPAL币别</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" name="paypalCurrency" id="paypalCurrency" type="text"
			             value="${platAccount.paypalCurrency}" placeholder="PAYPAL币别..."/>
					</div>
				</div>
			</div>
			<div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="bankCard">银行卡</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="bankCard" id="bankCard" type="text"
		         value="${platAccount.bankCard }" placeholder="银行卡..."/>
		      </div>
		      </div>
		   </div>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="type">提现类型</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="type" id="type" type="text"
		         value="${platAccount.type }" placeholder="提现类型..."/>
		      </div>
		      </div>
		   </div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#platAccountForm').submit();" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i>&nbsp;
		<c:if test="${empty platAccount}">
		添加
		</c:if>
		<c:if test="${!empty platAccount}">
		保存
		</c:if>
	</button>
	<button id="btn" type="button" class="btn btn-info btn-sm" onclick="base.common.loadPage('/plat/accountListUI.html')">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>
<script type="text/javascript" src="${ctx }/static/plug-ins/select2/select2.min.js"/>
<script type="text/javascript" src="${ctx }/static/plug-ins/select2/zh-CN.js"/>
<script type="text/javascript" src="${ctx}/static/plug-ins/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx }/static/js/modules/plat/accountForm.js"/>
