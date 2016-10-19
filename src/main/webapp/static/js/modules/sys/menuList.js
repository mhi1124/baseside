var dtGridColumns = [{
    id : 'sid',
    title : '编号',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'menuName',
    title : '资源名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'menuType',
    title : '资源类型',
    type : 'int',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if(value==1){
    		return '<span class="label label-sm label-success arrowed arrowed-in">目录</span>';
    	}else if(value==2)
        {
            return '<span class="label label-sm label-success arrowed arrowed-in">菜单</span>';
        }else
        {
            return '<span class="label label-sm label-info arrowed arrowed-right">按钮</span>';
        }
    }
}, {
    id : 'menuStatus',
    title : '资源状态',
    type : 'int',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if(value==1){
        	return '<span class="label label-sm label-success arrowed arrowed-in">正常</span>';
        }
        else if(value==2)
        {
        	return '<span class="label label-sm label-info arrowed arrowed-right">暂停使用</span>';
        }else
        {
        	return '<span class="label label-sm label-success arrowed arrowed-in">删除</span>';
        }
    }
}, {
    id : 'icon',
    title : '图标',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if(value != null && value != "")
        {
            return '<i style="font-size:18px;" class="'+ value +' blue" ></i>';
        }
        else
        {
            return '';
        }
    }
}, {
    id : 'menuUrl',
    title : '资源url',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs|sm'
}, {
    id : 'operate',
    title : '操作',
    type : 'string',
    columnClass : 'btn-group',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs|sm',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	//debugger;
    	var tag = '<button onclick="base.common.editModel(\'/menu/editUI.html?id='+record.sid+'\', customSearch)" class="btn btn-xs btn-info"><i class="icon-edit bigger-120"></i></button>';
    	tag += '<button onclick="base.common.delModel(\'/menu/deleteBatch.html?ids='+record.sid+'\', customSearch)" class="btn btn-xs btn-danger"><i class="icon-trash bigger-120"></i></button>';
    	return tag;
       
    }
}];

var dtGridOption = {
	    lang : 'zh-cn',
	    ajaxLoad : true,
	    check : true,
	    checkWidth :'37px',
	    extraWidth : '37px',
	    loadURL : ctx + '/menu/menuList.html',
	    columns : dtGridColumns,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    tools : '',
	    exportFileName :'资源信息',
	    pageSize : 10,
	    pageSizeLimit : [10, 20, 30]
	};
var grid = $.fn.dlshouwen.grid.init(dtGridOption);

$(function() {
    grid.load();
    $("#btnSearch").click(customSearch);
    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            customSearch();
        }
    };
});

//搜索菜单
function customSearch(){
	grid.parameters = new Object();
    grid.parameters['menuName'] = $("#searchKey").val();
    grid.refresh(true);
}