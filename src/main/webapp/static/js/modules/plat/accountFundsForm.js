$().ready(function() {
	//日期控件
	$("#withdrawTime").jeDate({
	    isinitVal:true,
	    festival:false,
	    ishmsVal:false,
	    maxDate: $.nowDate(0),
	    format:"YYYY-MM-DD hh:mm:ss",
	});
	
	//添加校验规则
	jQuery.validator.addMethod("amount", function(value, element) {
        return this.optional(element) || /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/.test(value);
    }, "请输入保留两位小数的数字");
	var formid = "accountFundsForm";
	$('#'+formid).validate({
        errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
        	platNo:{
                required : true
            },
            platAccount:{
            	required : true
            },
            withdrawAmount:{
            	required : true,
            	amount: 'required'
            },
            withdrawCurrency:{
            	required : true
            }
           
        },
        messages : {
        	platNo:"请选择平台",
        	platAccount:"请选择账号",
        	withdrawAmount:{
                required : "请填写提款金额",
                amount : "请输入保留两位小数大于0的数字"
            },
        	withdrawCurrency:"请选择提现币种"
        	
        },
        highlight : function(e) {
            $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
        },
        success : function(e) {
            $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
            $(e).remove();
        },
        errorPlacement : function(error, element) {
            if (element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                var controls = element.closest('div[class*="col-"]');
                if (controls.find(':checkbox,:radio').length > 1)
                    controls.append(error);
                else
                    error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
            } else if (element.is('.select2')) {
                error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
            } else if (element.is('.chosen-select')) {
                error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
            } else
                error.insertAfter(element.parent());
        },
        submitHandler : function(form) {
            var id = $("#id").val();
            var url = "";
            if (undefined != id ) {
                url = '/plat/editAccountFunds.html';
            } else {
                url = '/plat/addAccountFunds.html';
            }											
            base.common.commit(formid, url, '/plat/accountFundsListUI.html');
        }
    });
	
});

//请求账号
function selectAccount(object){
	var layselect = layer.load();
	var value = object.value;
	BindSelect("platAccount",ctx + "/plat/getAccounts.html?platNo="+value);
	layer.close(layselect)
}

//设手续费
function setHandlingCharge(){
	var realAmount = $("#realAmount").val();
	//var platNo = $("#platNo").val();
	var withdrawAmount = $("#withdrawAmount").val();
	if(realAmount>0){
		var handlingCharge = withdrawAmount-realAmount;
		if(handlingCharge<0){
			handlingCharge=0;
		}
		$("#handlingCharge").val(handlingCharge);
	}else{
		$("#handlingCharge").val(0);
	}
}
