var formid = "platAccountForm";
$().ready(function() {
	$('#'+formid).validate({
        errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
        	platNo : {
                required : true
            },
            platAccount : {
                required : true,
                remote : {
                    param : {
                        url : ctx + '/plat/validateAccount.html',
                        cache : false
                    },
                    depends : function() {
                        return typeof ($("#id").val()) == "undefined";
                    }
                }
            },
            paypalCurrency : {
                required : true
            },
            bankCard : {
                required : true
            }
        },
        messages : {
        	platNo : {
                required : "请填写平台"
            },
            platAccount : {
                required : "请填写平台账号",
                remote : "该账号已经存在,请使用其他账号"
            },
            paypalCurrency : {
                required : "请填写PAYPAL币别"
            },
            bankCard : "请填写银行卡"
            
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
                url = '/plat/editAccount.html';
            } else {
                url = '/plat/addAccount.html';
            }											
            base.common.commit(formid, url, '/plat/accountListUI.html');
        }
    });
});