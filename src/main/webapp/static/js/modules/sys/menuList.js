var dtGridColumns = [{
    id : 'id',
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
        if(value==0)
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
        if(value==0)
        {
            return '<span class="label label-sm label-success arrowed arrowed-in">正常</span>';
        }else
        {
            return '<span class="label label-sm label-info arrowed arrowed-right">暂停使用</span>';
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
});