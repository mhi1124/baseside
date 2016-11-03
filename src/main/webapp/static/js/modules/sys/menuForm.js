$(function(){
	$('#parentId').select2({
        placeholder : "请选择上级资源...",
        language : "zh-CN",
        minimumResultsForSearch : Infinity,
        templateResult : formatState,
        templateSelection : function(selection) {
            return $.trim(selection.text);
        }
    });
	
	function formatState(state) {
        if (!state.id) {
            return state.text;
        }
        var $state = $('<span><i class="fa fa-file-text-o green"></i>&nbsp;&nbsp;' + state.text + '</span>');
        return $state;
    };
    
});
/*function commitForm(){
	
	base.common.commit('menuForm', '/menu/addMenu.html', '/menu/toMenuList.html');
}*/

$().ready(function() {
	$('#menuForm').validate({
        errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
            parentId : {
                required : true
            },
            menuName : {
                required : true
            },sourceKey : {
            	required : true
            },
            type : {
                required : true
            },
            menuUrl : {
                required : function() {
                    return false;
                }
            },
            icon : {
                required : function() {
                    return false;
                }
            }
        },
        messages : {
            parentId : "请选择上级资源",
            menuName : "请填写资源名称",
            type : "请选择资源类型",
            sourceKey : "请填写权限标识",
            menuUrl : "请填写资源URL",
            icon : "请选择菜单图标"
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
        	debugger;
            var mid = $("#mid").val();
            var url = "";
            if (undefined != mid ) {
                url = '/menu/editMenu.html';
            } else {
                url = '/menu/addMenu.html';
            }
            base.common.commit('menuForm', url, '/menu/toMenuList.html');
        }
    });
	//按钮图标事件
	$("#icon").bind('focus', function(event) {
        var iconLayer = layer.open({
            type : 2,
            scrollbar : false,
            content : ctx + '/menu/icon.html',
            area : 'auto',
            maxmin : true,
            shift : 4,
            title : '<i class="fa fa-cogs"></i>&nbsp;选择图标'
        });
        //弹出即全屏
        layer.full(iconLayer);
    });
	$("#iconShow").bind('click', function(event) {
        $("#icon").val('');
        $(this).removeClass();
    });
    $('[data-rel=tooltip]').tooltip();
});