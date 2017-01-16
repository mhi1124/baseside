$().ready(function() {
	//日期控件
	$("#daozhangTime").jeDate({
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
	//添加校验规则
	jQuery.validator.addMethod("daozhangAmount", function(value, element) {
		var withdrawAmount = $("#hwithdrawAmount").val();
		var ret = true;
		if(withdrawAmount != ""){
			if(Number(value)>Number(withdrawAmount)){
				ret = false;
			}
		}
		return ret;
    }, "输入的到账金额大于提现金额，请重新输入!");
	
	var formid = "accountFundsForm";
	$('#'+formid).validate({
        errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
        	daozhangAmount:{
            	required : true,
            	amount: 'required',
            	daozhangAmount:'required'
            }
        },
        messages : {
        	daozhangAmount:{
                required : "请填写提款金额",
                amount : "请输入保留两位小数大于0的数字",
                daozhangAmount:"输入的到账金额大于提现金额，请重新输入!"
            }
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
            var url = "/plat/editAccountFunds.html";
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
	var daozhangAmount = $("#daozhangAmount").val();
	//var platNo = $("#platNo").val();
	var withdrawAmount = $("#hwithdrawAmount").val();
	if(daozhangAmount>0){
		var shouxufei = withdrawAmount-daozhangAmount;
		if(shouxufei<0){
			shouxufei=0;
		}
		$("#shouxufei").val(shouxufei);
	}else{
		$("#shouxufei").val(0);
	}
}
