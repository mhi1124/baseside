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
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'withdrawAmount',
    title : '提现金额',
    type : 'double',
    columnClass : 'text-center',
    columnStyle:'font-size:medium;',/*background:#00a2ca;color:white;*/
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	return getAmount(record.withdrawSign,value);
    }
}, {
    id : 'withdrawTime',
    title : '提现时间',
    type: 'date',
    format : 'yyyy-MM-dd',
    otype : 'string',
    oformat : 'yyyy-MM-dd',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'daozhangAmount',
    title : '到账金额',
    type : 'double',
    columnClass : 'text-center',
    columnStyle:'font-size:medium;',/*background:#00a2ca;color:white;*/
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	return getAmount(record.daozhangSign,value);
    }
}, {
    id : 'daozhangTime',
    title : '到账时间',
    type: 'date',
    format : 'yyyy-MM-dd',
    otype : 'string',
    oformat : 'yyyy-MM-dd',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if(null == value && '' == value){
    		return "";
    	}else{
    		return value.substring(0,10) ;
    	}
    }
},
{
    id : 'shouxufei',
    title : '手续费',
    type : 'double',
    columnClass : 'text-center',
    columnStyle:'font-size:medium;',/*background:#00a2ca;color:white;*/
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	return getAmount(record.daozhangSign,value);
    	
    }
}, {
    id : 'estimateAmount',
    title : '预计到账金额',
    type : 'double',
    columnClass : 'text-center',
    columnStyle:'font-size:medium;',/*background:#00a2ca;color:white;*/
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	return getAmount('￥',value);
    }
}];

function getAmount(withdrawCurrency,value){
	var tag = '';
	if(value!=0){
		tag = withdrawCurrency+value
	}
	return tag;
}

var dtGridOption = {
	    lang : 'zh-cn',
	    ajaxLoad : true,
	    check : true,
	    checkWidth :'37px',
	    extraWidth : '37px',
	    loadURL : ctx + '/plat/accountFundsList.html',
	    columns : dtGridColumns,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    tools : '',
	    exportFileName :'用户信息',
	    pageSize : 10,
	    pageSizeLimit : [10, 20, 30]
	};
var grid = $.fn.dlshouwen.grid.init(dtGridOption);
var startTime,endTime;
$(function() {
	startTime=document.getElementById("startTime").value;
	endTime=document.getElementById("endTime").value;
	grid.parameters = new Object();
	grid.parameters['baginCreatTime'] = startTime;
    grid.parameters['endCreantTime'] = endTime;
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

function ajaxEdit(nav,type,callback){
	 var index;
     var rows = grid.getCheckedRecords();
     var flag = true;
     if(!(nav.indexOf("?") > 0 ) && rows.length == 0){
     	flag = false;
     }
     
     if (flag ) {
    	 if(rows.platNo=='AM'){
    		 //获取选择的行
             if(!(nav.indexOf("?") > 0 )){
             	var rows = grid.getCheckedRecords();
             	if(rows.length==1){
             		nav += '?id='+ rows[0].sid;
             	}else{
             		layer.msg("你没有选择行或选择了多行数据", {
                         icon : 0
                     });
             	}
             }
             base.common.loadPage(nav);
    	 }else{
    		 layer.msg("您选择的数据无法P卡转出！请重新选择", {
                 icon : 0
             });
    	 }
     } else {
         layer.msg("你没有选择行或选择了多行数据", {
             icon : 0
         });
     }
}

//搜索菜单
function customSearch(){
	startTime=document.getElementById("startTime").value;
	endTime=document.getElementById("endTime").value;
	grid.parameters = new Object();
    grid.parameters['platAccount'] = $("#searchKey").val();
    grid.parameters['baginCreatTime'] = startTime;
    grid.parameters['endCreantTime'] = endTime;
    grid.parameters['inp'] = $('#inP option:selected').val();
    grid.refresh(true);
}

