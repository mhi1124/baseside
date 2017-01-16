var dtGridColumns = [{
    id : 'platNo',
    title : '平台',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'platAccount',
    title : '平台账号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
}, {
    id : 'paypalCurrency',
    title : 'PAYPAL币别',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
}, {
    id : 'bankCard',
    title : '银行卡',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
}, {
	id : 'type',
    title : '提现类型',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs'
},{
    id : 'operate',
    title : '操作',
    type : 'string',
    columnClass : 'btn-group',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs|sm',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	var tag = '<button onclick="base.common.editModel(\'/plat/editUI.html?id='+record.sid+'\', customSearch)" class="btn btn-xs btn-info"><i class="icon-edit bigger-120"></i></button>';
    	tag += '<button onclick="base.common.delModel(\'/plat/deleteBatch.html?ids='+record.sid+'\', customSearch)" class="btn btn-xs btn-danger"><i class="icon-trash bigger-120"></i></button>';
    	return tag;
    }
}];

var dtGridOption = {
	    lang : 'zh-cn',
	    ajaxLoad : true,
	    check : true,
	    checkWidth :'37px',
	    extraWidth : '37px',
	    loadURL : ctx + '/plat/accountList.html',
	    columns : dtGridColumns,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    tools : '',
	    exportFileName :'用户信息',
	    pageSize : 10,
	    pageSizeLimit : [10, 20, 30]
	};
var grid = $.fn.dlshouwen.grid.init(dtGridOption);
//var startTime,endTime;

$(function() {
	//startTime=document.getElementById("startTime").value;
	//endTime=document.getElementById("endTime").value;
	//grid.parameters = new Object();
	//grid.parameters['baginCreatTime'] = startTime;
    //grid.parameters['endCreantTime'] = endTime;
    
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
	//startTime=document.getElementById("startTime").value;
	//endTime=document.getElementById("endTime").value;
	grid.parameters = new Object();
    grid.parameters['platAccount'] = $("#searchKey").val();
    //grid.parameters['baginCreatTime'] = startTime;
    //grid.parameters['endCreantTime'] = endTime;
    grid.refresh(true);
}

/*function getAmount(withdrawCurrency,value){
	var tag = '';
	if(value!=0){
		tag = withdrawCurrency+value
	}
	return tag;
}*/