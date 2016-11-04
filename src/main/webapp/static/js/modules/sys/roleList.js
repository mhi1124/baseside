var dtGridColumns = [{
    id : 'roleName',
    title : '角色名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'roleKey',
    title : '角色key',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
}, {
    id : 'status',
    title : '状态',
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
        	return '<span class="label label-sm label-info arrowed arrowed-right">锁定</span>';
        }else
        {
        	return '<span class="label label-sm label-success arrowed arrowed-in">删除</span>';
        }
    }
}, {
	id : 'description',
    title : '角色描述',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs'
}, {
	id : 'createTime',
    title : '创建时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs'
}, {
    id : 'updateTime',
    title : '更新时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == null) {
            return '';
        } else {
            return value;
        }
    }
},{
    id : 'operate',
    title : '操作',
    type : 'string',
    columnClass : 'btn-group',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs|sm',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	//debugger;
    	var tag = '<button onclick="base.common.editModel(\'/sys/role/editUI.html?id='+record.sid+'\', customSearch)" class="btn btn-xs btn-info"><i class="icon-edit bigger-120"></i></button>';
    	tag += '<button onclick="base.common.delModel(\'/sys/role/deleteBatch.html?ids='+record.sid+'\', customSearch)" class="btn btn-xs btn-danger"><i class="icon-trash bigger-120"></i></button>';
    	return tag;
       
    }
}];

var dtGridOption = {
	    lang : 'zh-cn',
	    ajaxLoad : true,
	    check : true,
	    checkWidth :'37px',
	    extraWidth : '37px',
	    loadURL : ctx + '/sys/role/roleList.html',
	    columns : dtGridColumns,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    tools : '',
	    exportFileName :'角色信息',
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
    grid.parameters['roleName'] = $("#searchKey").val();
    grid.refresh(true);
}