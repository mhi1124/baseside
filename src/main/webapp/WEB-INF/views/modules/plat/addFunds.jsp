<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<link type="text/css" rel="stylesheet" href="${ctx }/static/plug-ins/jedate/skin/jedate.css">
<div class="page-header">
	<h1>
		添加提现记录
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="accountFundsForm" name="accountFundsForm" class="form-horizontal" method="post">
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="platNo">平台</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<select class="form-control" name="platNo" id="platNo" style="width: 100%" onchange="selectAccount(this);">
							<option value=""></option>
								<c:forEach var="platNo" items="${platNos }">
									<option value="${platNo}">${platNo}</option>
								</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="platAccount">平台账号</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<select class="form-control" name="platAccount" id="platAccount" style="width: 100%" >
						</select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="withdrawAmount">提现金额</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" name="withdrawAmount" id="withdrawAmount" type="text"
						  placeholder="提现金额..." />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="withdrawCurrency">提现币种</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<select class="form-control" name="withdrawCurrency" id="withdrawCurrency" style="width: 100%">
							<option value=""></option>
							<c:forEach var="currency" items="${currencyList }">
								<option value="${currency}">${currency}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="form-group datep">
				<label class="control-label col-sm-1 no-padding-right" for="withdrawTime">提现时间</label>
				<div class="col-sm-3">
					<div class="clearfix ">
						<input class="datainp wicon" name="withdrawTime" id="withdrawTime" type="text"  
							  placeholder="提现时间..." readonly/>
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
		添加
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
<script type="text/javascript" src="${ctx }/static/js/modules/plat/accountFundsForm.js"/>
