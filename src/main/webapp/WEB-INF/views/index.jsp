<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%-- basic styles --%>
<link href="${ctx}/static/plug-ins/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/static/plug-ins/assets/css/font-awesome.min.css" />
<!--[if IE 7]>
  <link rel="stylesheet" href="${ctx}/static/plug-ins/assets/css/font-awesome-ie7.min.css" />
<![endif]-->
<!-- ace styles -->
<link rel="stylesheet" href="${ctx}/static/plug-ins/assets/css/ace.min.css" />
<link rel="stylesheet" href="${ctx}/static/plug-ins/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${ctx}/static/plug-ins/assets/css/ace-skins.min.css" />

<!--[if lte IE 8]>
  <link rel="stylesheet" href="${ctx}/static/plug-ins/assets/css/ace-ie.min.css" />
<![endif]-->
<!-- ace settings handler -->
<script src="${ctx}/static/plug-ins/assets/js/ace-extra.min.js"></script>
<!--[if lt IE 9]>
<script src="${ctx}/static/plug-ins/assets/js/html5shiv.js"></script>
<script src="${ctx}/static/plug-ins/assets/js/respond.min.js"></script>
<![endif]-->
<!-- font-awesome -->
<link rel="stylesheet" href="${ctx}/static/plug-ins/fonts/fontawesome/font-awesome.min.css" />
<!-- DLShouWen Grid -->
<link rel="stylesheet" href="${ctx}/static/plug-ins/dlshouwen.grid.v1.2.1/dlshouwen.grid.css" />
<!-- datePicker -->
<link rel="stylesheet" href="${ctx}/static/plug-ins/datePicker/skin/WdatePicker.css" />
<link rel="stylesheet" href="${ctx}/static/plug-ins/datePicker/skin/default/datepicker.css" />
</head>
<body>
<div class="navbar navbar-default" id="navbar">
	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left">
			<a href="#" class="navbar-brand">
				<small>
					<i class="icon-leaf"></i>
					ACE后台管理系统
				</small>
			</a><!-- /.brand -->
		</div><!-- /.navbar-header -->
	</div>
</div>
<div class="main-container" id="main-container">
	<script type="text/javascript">
		try{ace.settings.check('main-container' , 'fixed')}catch(e){}
	</script>

	<div class="main-container-inner">
		<a class="menu-toggler" id="menu-toggler" href="#">
			<span class="menu-text"></span>
		</a>
	
		<div class="sidebar" id="sidebar">
			<script type="text/javascript">
				try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
			</script>
	
			<div class="sidebar-shortcuts" id="sidebar-shortcuts">
				<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
					<button class="btn btn-success">
						<i class="icon-signal"></i>
					</button>
	
					<button class="btn btn-info">
						<i class="icon-pencil"></i>
					</button>
	
					<button class="btn btn-warning">
						<i class="icon-group"></i>
					</button>
	
					<button class="btn btn-danger">
						<i class="icon-cogs"></i>
					</button>
				</div>
	
				<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
					<span class="btn btn-success"></span>
	
					<span class="btn btn-info"></span>
	
					<span class="btn btn-warning"></span>
	
					<span class="btn btn-danger"></span>
				</div>
			</div><!-- #sidebar-shortcuts -->
	
			<ul class="nav nav-list">
				<li class="active">
					<a href="#">
						<i class="icon-dashboard"></i>
						<span class="menu-text"> 控制台 </span>
					</a>
				</li>
				<c:forEach var="menu" items="${menus}" varStatus="s">
					<li level="level1" class="">
						<a href="javascript:void(0)" <c:if test="${fn:length(menu.menuUrl) gt 0 }"> nav-menu="${menu.menuName },${menu.menuUrl }"</c:if> <c:if test="${fn:length(menu.childMenus) gt 0 }"> class="dropdown-toggle"</c:if>>
							<i class="${menu.icon }"></i>
							<span class="menu-text"> ${menu.menuName } </span>
						</a>
						<c:if test="${fn:length(menu.childMenus) gt 0 }">
							<ul class="submenu">
								<c:forEach var="firstMenu" items="${menu.childMenus}" varStatus="idx1">
									<li level="level2" class="">
										<a href="javascript:void(0)" <c:if test="${fn:length(firstMenu.menuUrl) gt 0 }">nav-menu="${menu.menuName },${firstMenu.menuName },${firstMenu.menuUrl }"</c:if><c:if test="${fn:length(firstMenu.childMenus) gt 0 }"> class="dropdown-toggle"</c:if>> 
											<i class="menu-icon fa fa-caret-right"></i> ${firstMenu.menuName }
											<c:if test="${fn:length(firstMenu.childMenus) gt 0 }"><b class="arrow  fa fa-angle-down"></b></c:if>
										</a>
										<c:if test="${fn:length(firstMenu.childMenus) gt 0 }">
											<ul class="submenu">
												<!-- 三级 -->
												<c:forEach var="secondMenu" items="${firstMenu.childMenus}" varStatus="idx2">
													<li level="level3" class="">
														<a href="javascript:void(0)" <c:if test="${fn:length(secondMenu.menuUrl) gt 0 }">nav-menu="${menu.menuName },${firstMenu.menuName },${secondMenu.menuName },${secondMenu.menuUrl }"</c:if><c:if test="${fn:length(secondMenu.childMenus) gt 0 }"> class="dropdown-toggle"</c:if>> 
															<i class="menu-icon fa fa-caret-right"></i> ${secondMenu.menuName }
															<c:if test="${fn:length(secondMenu.childMenus) gt 0 }"><b class="arrow  fa fa-angle-down"></b></c:if>
														</a>
														<c:if test="${fn:length(secondMenu.childMenus) gt 0 }">
															<ul class="submenu">
																<!-- 四级 -->
																<c:forEach var="thridMenu" items="${secondMenu.childMenus}" varStatus="idx3">
																<li level="level4" class="">
																	<a href="javascript:void(0)" <c:if test="${fn:length(thridMenu.menuUrl) gt 0 }">nav-menu="${menu.menuName },${firstMenu.menuName },${secondMenu.menuName },${thridMenu.menuName },${secondMenu.menuUrl }"</c:if><c:if test="${fn:length(thridMenu.childMenus) gt 0 }"> class="dropdown-toggle"</c:if>> 
																		<i class="menu-icon fa fa-caret-right"></i> ${thridMenu.menuName }
																		<c:if test="${fn:length(thridMenu.childMenus) gt 0 }"><b class="arrow  fa fa-angle-down"></b></c:if>
																	</a>
																</li>
																</c:forEach>
															</ul>
														</c:if>
													</li>
												</c:forEach>
											</ul>
										</c:if>
									</li>
								</c:forEach>
							</ul>
						</c:if>
					</li>
				</c:forEach>
			</ul><!-- /.nav-list -->
	
			<div class="sidebar-collapse" id="sidebar-collapse">
				<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
			</div>
	
			<script type="text/javascript">
				try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
			</script>
		</div>
	
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
				</script>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home home-icon"></i>
						<a href="#">首页</a>
					</li>
					<li class="active">控制台</li>
				</ul><!-- .breadcrumb -->
			</div>
			<div class="page-content">
	
			</div><!-- /.page-content -->
		</div><!-- /.main-content -->
		<div class="ace-settings-container" id="ace-settings-container">
			<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
				<i class="icon-cog bigger-150"></i>
			</div>

			<div class="ace-settings-box" id="ace-settings-box">
				<div>
					<div class="pull-left">
						<select id="skin-colorpicker" class="hide">
							<option data-skin="default" value="#438EB9">#438EB9</option>
							<option data-skin="skin-1" value="#222A2D">#222A2D</option>
							<option data-skin="skin-2" value="#C6487E">#C6487E</option>
							<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
						</select>
					</div>
					<span>&nbsp; 选择皮肤</span>
				</div>

				<div>
					<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
					<label class="lbl" for="ace-settings-navbar"> 固定导航条</label>
				</div>

				<div>
					<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
					<label class="lbl" for="ace-settings-sidebar"> 固定滑动条</label>
				</div>

				<div>
					<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
					<label class="lbl" for="ace-settings-breadcrumbs">固定面包屑</label>
				</div>

				<div>
					<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
					<label class="lbl" for="ace-settings-rtl">切换到左边</label>
				</div>

				<div>
					<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
					<label class="lbl" for="ace-settings-add-container">
						切换窄屏
						<b></b>
					</label>
				</div>
			</div>
		</div><!-- /#ace-settings-container -->
	</div><!-- /.main-container-inner -->

	<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
		<i class="icon-double-angle-up icon-only bigger-110"></i>
	</a>
</div><!-- /.main-container -->

<!--[if !IE]> -->
<script src="${ctx}/static/plug-ins/assets/js/jquery-2.0.3.min.js"></script>
<!-- <![endif]-->

<!--[if IE]>
<script src="${ctx}/static/plug-ins/assets/js/jquery-1.10.2.min.js"></script>
<![endif]-->
<script src="${ctx}/static/plug-ins/assets/js/jquery.mobile.custom.min.js"></script>
<script src="${ctx}/static/plug-ins/assets/js/bootstrap.min.js"></script>
<script src="${ctx}/static/plug-ins/assets/js/typeahead-bs2.min.js"></script>
<!-- ace scripts -->
<script src="${ctx}/static/plug-ins/assets/js/ace-elements.min.js"></script>
<script src="${ctx}/static/plug-ins/assets/js/ace.min.js"></script>
<script src="${ctx}/static/js/index.js"></script>
<!-- DLShouWen Grid -->
<script src="${ctx}/static/plug-ins/dlshouwen.grid.v1.2.1/dlshouwen.grid.js"></script>
<script src="${ctx}/static/plug-ins/dlshouwen.grid.v1.2.1/i18n/zh-cn.js"></script>

<!-- datePicker -->
<script src="${ctx}/static/plug-ins/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(function() {
    base.index.initNavigation();
    base.index.menu.initMenuEvent();
});
</script>
</body>
</html>