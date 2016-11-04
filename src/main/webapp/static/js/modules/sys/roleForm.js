$().ready(function() {
	$('#roleForm').validate({
        errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
        	roleName : {
                required : true,
                remote : {
                    param : {
                        url : ctx + '/sys/role/validateRoleName.html',
                        cache : false
                    },
                    depends : function() {
                        return typeof ($("#rid").val()) == "undefined";
                    }
                }
            },
            roleKey : {
                required : true
            }
        },
        messages : {
        	roleName : {
                required : "请填写角色名称",
                remote : "该角色已存在"
            },
        	roleKey : "请填写资源名称"
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
            var rid = $("#rid").val();
            var url = "";
            if (undefined != rid ) {
                url = '/sys/role/editRole.html';
            } else {
                url = '/sys/role/addRole.html';
            }
            base.common.commit('roleForm', url, '/sys/role/roleListUI.html');
        }
    });
});