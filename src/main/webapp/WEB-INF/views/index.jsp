<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link href="${ctx}/static/bootstrap/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/static/assets/css/font-awesome.min.css" />

<!--[if IE 7]>
  <link rel="stylesheet" href="${ctx}/static/assets/css/font-awesome-ie7.min.css" />
<![endif]-->
<link href="${ctx}/static/assets/css/ace.min.css" rel="stylesheet" />

<!--[if lte IE 8]>
	<link rel="stylesheet" href="${ctx}/static/assets/css/ace-ie.min.css" />
<![endif]-->


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
				<li>
					<a href="#" class="dropdown-toggle">
						<i class="icon-desktop"></i>
						<span class="menu-text"> UI 组件 </span>
						<b class="arrow icon-angle-down"></b>
					</a>
	
					<ul class="submenu">
						<li>
							<a href="#">
								<i class="icon-double-angle-right"></i>
								组件
							</a>
						</li>
						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-double-angle-right"></i>
								三级菜单
								<b class="arrow icon-angle-down"></b>
							</a>
	
							<ul class="submenu">
								<li>
									<a href="#">
										<i class="icon-leaf"></i>
										第一级
									</a>
								</li>
	
								<li>
									<a href="#">
										<i class="icon-pencil"></i>
										第四级
										<b class="arrow icon-angle-down"></b>
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
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
	</div><!-- /.main-container-inner -->

	<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
		<i class="icon-double-angle-up icon-only bigger-110"></i>
	</a>
</div><!-- /.main-container -->

<!--[if !IE]> -->
	<script src="${ctx}/static/assets/js/jquery-2.0.3.min.js"></script>
<!-- <![endif]-->
<!--[if IE]>
	<script src="${ctx}/static/assets/js/jquery-1.10.2.min.js"></script>
<![endif]-->
<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery || document.write("<script src='${ctx}/static/assets/js/jquery-2.0.3.min.js'>"+"<"+"script>");
</script>
<!-- <![endif]-->
<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${ctx}/static/assets/js/jquery-1.10.2.min.js'>"+"<"+"script>");
</script>
<![endif]-->
<script src="${ctx}/static/assets/js/bootstrap.min.js"></script>
<script src="${ctx}/static/assets/js/ace.min.js"></script>
<script src="${ctx}/static/js/index.js"></script>
</body>
</html>