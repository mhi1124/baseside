<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<link type="text/css" rel="stylesheet" href="${ctx }/static/plug-ins/jedate/skin/jedate.css">
<div class="page-header">
	<h1>
		实际到帐
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="accountFundsForm" name="accountFundsForm" class="form-horizontal" method="post">
			<input type="hidden" name="sid" id="id" value="${funds.sid}">
			<input type="hidden" id="hwithdrawAmount" value="${funds.withdrawAmount}">
			<div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="platNo">平台</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="platNo" value="${funds.platNo}" type="text" disabled="disabled"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="platAccount">平台账号</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="platAccount" value="${funds.platAccount}" type="text" disabled="disabled"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="withdrawAmount">提现金额</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="withdrawAmount" type="text" value="${funds.withdrawSign}${funds.withdrawAmount}"  disabled="disabled"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="daozhangCurrency">到账币种</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<select class="form-control" name="daozhangCurrency" id="daozhangCurrency" style="width: 100%">
							<option value=""></option>
							<c:forEach var="currency" items="${currencyList }">
								<option value="${currency}" <c:if test="${funds.withdrawCurrency eq currency}"> selected="selected" </c:if>>${currency}</option>
							</c:forEach>							
						</select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="daozhangAmount">实际到账金额</label>
				<div class="col-sm-3">
					<div class="clearfix">
						<input class="form-control" name="daozhangAmount" onchange="setHandlingCharge();" id="daozhangAmount" type="text"
						 value="${funds.daozhangAmount}" placeholder="实际到账金额..." />
					</div>
				</div>
			</div>
			<div class="form-group datep">
				<label class="control-label col-sm-2 no-padding-right" for="daozhangTime">到账时间</label>
				<div class="col-sm-3">
					<div class="clearfix ">
						<input class="datainp wicon" name="daozhangTime" id="daozhangTime" type="text"  
							  placeholder="到账时间..." readonly/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="shouxufei">手续费</label>
				<div class="col-sm-3">
					<div class="clearfix">
						<input class="form-control" name="shouxufei" id="shouxufei" type="text"
						 value="${funds.shouxufei}" placeholder="手续费..." />
					</div>
				</div>
			</div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#accountFundsForm').submit();" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i>&nbsp;
		保存
	</button>
	<button id="btn" type="button" class="btn btn-info btn-sm" onclick="base.common.loadPage('/plat/accountFundsListUI.html')">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>
<script type="text/javascript" src="${ctx }/static/plug-ins/select2/select2.min.js"/>
<script type="text/javascript" src="${ctx }/static/plug-ins/select2/zh-CN.js"/>
<script type="text/javascript" src="${ctx}/static/plug-ins/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx }/static/js/modules/mySelect2.js"/>
<script type="text/javascript" src="${ctx}/static/plug-ins/jedate/jquery.jedate.min.js"></script>
<script type="text/javascript" src="${ctx }/static/js/modules/plat/editFundsForm.js"/>
