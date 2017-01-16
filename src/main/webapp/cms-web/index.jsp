<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<meta name="keywords" content="关键字" />
<meta name="description" content="描述" />

<link href="${pageContext.request.contextPath}/static/cms-web/images/logo/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<title>首页</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/cms-web/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/cms-web/css/myindex.css"/>

<script src="${pageContext.request.contextPath}/static/cms-web/js/jquery-2.0.3.min.js"></script>
<script src="${pageContext.request.contextPath}/static/cms-web/js/bootstrap.min.js"></script>

<script src="${pageContext.request.contextPath}/static/cms-web/js/myindex.js"></script>

</head>
<body>
<!-- 头部 -->
<div class="g-headwrap">
	<div class="mt-nav">
		<div class="container">
			<div  class="row clearfix">
				<div class="m-logo">
					<a href="#">
						<img src="${pageContext.request.contextPath}/static/cms-web/images/logo.png" width="100" style="display: inline;"/>
					</a>
				</div>
				<div class="header-phone visible-xs pull-right">
					<button type="button" class="navbar-toggle ">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>
				<div class="m-navrgt f-fr f-pr j-navright">
					<div class="userinfo f-fr f-pr hidden-xs hidden-sm">
						<div class="unlogin f-fr">
							<a>全球站点</a>
						</div>
					</div>
					<div ></div>
				</div>
				<div class="m-nav j-navFind hidden-xs">
					<a id="proHome" class="nitem" href="javascript:void(null)">家用产品1</a>
					<a id="proHome" class="nitem" href="javascript:void(null)">家用产品2</a>
					<a id="proHome" class="nitem" href="javascript:void(null)">家用产品3</a>
				</div>
			</div>
		</div>
	</div>
	<div class="nav-phone hidden-sm hidden-md hidden-lg hidden">
		<b></b>
		<ul style="display: block;">
			<li class="menu01"><a href="javascript:void(null)">家用产品1</a></li>
			<li class="menu01"><a href="javascript:void(null)">家用产品2</a></li>
			<li class="menu01"><a href="javascript:void(null)">家用产品3</a></li>
		</ul>
	</div>
</div>
<!--轮播图-->
<div class="container-fluid">
	<div class="row banner-row">
		<div id="header-banner" class="carousel slide" data-ride="carousel">
			<!-- 轮播（Carousel）指标 -->
			<div class="carousel-indicators-wrap">
				<div class="container">
					<ol class="carousel-indicators">
						<li data-target="#header-banner" data-slide-to="0" class="active"></li>
						<li data-target="#header-banner" data-slide-to="1"></li>
					</ol>
				</div>
			</div>
			<!-- 轮播（Carousel）项目 -->
			<div class="carousel-inner">
				<div class="item active">
					<img src="${pageContext.request.contextPath}/static/cms-web/images/ad/2016826935506111.jpg" alt="First slide">
				</div>
				<div class="item">
					<img src="${pageContext.request.contextPath}/static/cms-web/images/ad/20168201737559559.jpg" alt="Second slide">
				</div>
			</div>
			<!-- 轮播（Carousel）导航 -->
			<div class="banner-btn btn-left">
				<a href="#header-banner" role="button" data-slide="prev"><i></i></a>
			</div>
			<div class="banner-btn btn-right">
				<a href="#header-banner" role="button" data-slide="next"><i></i></a>
			</div>
			
		</div>
	</div>
</div>
<!--第一列数据-->
<div class="homrow1">
	<div class="container">
		<div class="tab">
			<h3 class="ht">区域代理</h3>
			<div class="list hidden-xs">
				<a>桂林地区</a><span>|</span>
				<a>梧州地区</a><span>|</span>
				<a>百色地区</a>
			</div>
		</div>
		<div class="item">
			<div class="row subrow1">
				<div class="col-xs-6 col-sm-3">
					<div class="thumbnail">
						<a href="#" target="_blank">
							<img src="${pageContext.request.contextPath}/static/cms-web/images/product/s_20167111819121774.jpg" style="display: block;"/>
						</a>
					</div>
					<div class="caption">
						<span>12月</span>
						&nbsp;&nbsp;详情&nbsp;&nbsp;
						<a>参数</a>
					</div>
				</div>
				<div class="col-xs-6 col-sm-3">
					<div class="thumbnail">
						<a href="#" target="_blank">
							<img src="${pageContext.request.contextPath}/static/cms-web/images/product/s_20167111819121774.jpg" style="display: block;"/>
						</a>
					</div>
					<div class="caption">
						<span>12月</span>
						&nbsp;&nbsp;详情&nbsp;&nbsp;
						<a>参数</a>
					</div>
				</div>
				<div class="col-xs-6 col-sm-3">
					<div class="thumbnail">
						<a href="#" target="_blank">
							<img src="${pageContext.request.contextPath}/static/cms-web/images/product/s_20167111819121774.jpg" style="display: block;"/>
						</a>
					</div>
					<div class="caption">
						<span>12月</span>
						&nbsp;&nbsp;详情&nbsp;&nbsp;
						<a>参数</a>
					</div>
				</div>
				<div class="col-xs-6 col-sm-3">
					<div class="thumbnail">
						<a href="#" target="_blank">
							<img src="${pageContext.request.contextPath}/static/cms-web/images/product/s_20167111819121774.jpg" style="display: block;"/>
						</a>
					</div>
					<div class="caption">
						<span>12月</span>
						&nbsp;&nbsp;详情&nbsp;&nbsp;
						<a>参数</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!--第二列数据-->
<div class="homrow2">
	<div class="container">
		<div class="tab">
			<h3 class="ht">区域代理</h3>
			<div class="list hidden-xs">
				<a>桂林地区</a><span>|</span>
				<a>梧州地区</a><span>|</span>
				<a>百色地区</a>
			</div>
		</div>
		<div class="item">
			<div class="row subrow2">
				<div class="col-xs-12 col-sm-6 col-md-4">
					<a href="#">
						<div class="thumbnail">
							<img src="${pageContext.request.contextPath}/static/cms-web/images/product/ee2.jpg" style="display: block;"/>
						</div>
						<div class="caption" style="border:solid 1px #ececec;border-top:none;">
							<div class="hsbcaptitle">
								<strong>无线通解决方案</strong>
								推出基于场景化的无线解决方案，大幅度降低客户部署和维护成本
								推出基于场景化的无线解决方案，大幅度降低客户部署和维护成本
								推出基于场景化的无线解决方案，大幅度降低客户部署和维护成本
							</div>
						</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-4">
					<a href="#">
						<div class="thumbnail">
							<img src="${pageContext.request.contextPath}/static/cms-web/images/product/ee2.jpg" style="display: block;"/>
						</div>
						<div class="caption" style="border:solid 1px #ececec;border-top:none;">
							<div class="hsbcaptitle">
								<strong>无线通解决方案</strong>
								推出基于场景化的无线解决方案，大幅度降低客户部署和维护成本
								推出基于场景化的无线解决方案，大幅度降低客户部署和维护成本
								推出基于场景化的无线解决方案，大幅度降低客户部署和维护成本
							</div>
						</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-4">
					<a href="#">
						<div class="thumbnail">
							<img src="${pageContext.request.contextPath}/static/cms-web/images/product/ee2.jpg" style="display: block;"/>
						</div>
						<div class="caption" style="border:solid 1px #ececec;border-top:none;">
							<div class="hsbcaptitle">
								<strong>无线通解决方案</strong>
								推出基于场景化的无线解决方案，大幅度降低客户部署和维护成本
								推出基于场景化的无线解决方案，大幅度降低客户部署和维护成本
								推出基于场景化的无线解决方案，大幅度降低客户部署和维护成本
							</div>
						</div>
					</a>
				</div>
			</div>
		</div>
	</div>
</div>

<!--第三列数据-->
<div class="homrow2" style="background-color:#fff;">
	<div class="container">
		<div class="tab">
			<h3 class="ht">区域代理</h3>
			<div class="list hidden-xs">
				<a>桂林地区</a><span>|</span>
				<a>梧州地区</a><span>|</span>
				<a>百色地区</a>
			</div>
		</div>
		<div class="item">
			<div class="row subrow2">
				<div class="col-xs-12 col-sm-6 col-md-4">
					<a href="#">
						<div class="thumbnail">
							<img src="${pageContext.request.contextPath}/static/cms-web/images/product/20161211020294517.jpg" style="display: block;"/>
						</div>
						<div class="caption">
							<h4 class="homenewtitle">腾达交换机助力山东华能德州电厂监控工程</h4>
							华能德州电厂位于山东省德州市古运河畔，地理位置优越，交通运输便捷，是一座现代化大型路口火力发电厂。 
						</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-4">
					<a href="#">
						<div class="thumbnail">
							<img src="${pageContext.request.contextPath}/static/cms-web/images/product/20161211020294517.jpg" style="display: block;"/>
						</div>
						<div class="caption">
							<h4 class="homenewtitle">腾达交换机助力山东华能德州电厂监控工程</h4>
							华能德州电厂位于山东省德州市古运河畔，地理位置优越，交通运输便捷，是一座现代化大型路口火力发电厂。 
						</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-4">
					<a href="#">
						<div class="thumbnail">
							<img src="${pageContext.request.contextPath}/static/cms-web/images/product/20161211020294517.jpg" style="display: block;"/>
						</div>
						<div class="caption">
							<h4 class="homenewtitle">腾达交换机助力山东华能德州电厂监控工程</h4>
							华能德州电厂位于山东省德州市古运河畔，地理位置优越，交通运输便捷，是一座现代化大型路口火力发电厂。 
						</div>
					</a>
				</div>
			</div>
		</div>
	</div>
</div>

<!--第四列数据-->
<div class="homrow2">
	<div class="container">
		<div class="row subrow1">
			<div class="fwitem">
				<a href="#" target="_blank">
					<div class="item">
						<h4>常见问题</h4>
						<img src="${pageContext.request.contextPath}/static/cms-web/images/help/hp1.png"/>
						<div class="desc">产品指南和问题解决方案，帮助客户熟悉产品和自助解决问题</div>
						<span class="more">查看详情 >></span>
					</div>
				</a>
			</div>
			<div class="fwitem">
				<a href="#" target="_blank">
					<div class="item">
						<h4>常见问题</h4>
						<img src="${pageContext.request.contextPath}/static/cms-web/images/help/hp2.png"/>
						<div class="desc">产品指南和问题解决方案，帮助客户熟悉产品和自助解决问题</div>
						<span class="more">查看详情 >></span>
					</div>
				</a>
			</div>
			<div class="fwitem">
				<a href="#" target="_blank">
					<div class="item">
						<h4>常见问题</h4>
						<img src="${pageContext.request.contextPath}/static/cms-web/images/help/hp3.png"/>
						<div class="desc">产品指南和问题解决方案，帮助客户熟悉产品和自助解决问题</div>
						<span class="more">查看详情 >></span>
					</div>
				</a>
			</div>
			<div class="fwitem">
				<a href="#" target="_blank">
					<div class="item">
						<h4>常见问题</h4>
						<img src="${pageContext.request.contextPath}/static/cms-web/images/help/hp4.png"/>
						<div class="desc">产品指南和问题解决方案，帮助客户熟悉产品和自助解决问题</div>
						<span class="more">查看详情 >></span>
					</div>
				</a>
			</div>
			<div class="fwitem">
				<a href="#" target="_blank">
					<div class="item">
						<h4>常见问题</h4>
						<img src="${pageContext.request.contextPath}/static/cms-web/images/help/hp5.png"/>
						<div class="desc">产品指南和问题解决方案，帮助客户熟悉产品和自助解决问题</div>
						<span class="more">查看详情 >></span>
					</div>
				</a>
			</div>
		</div>
	</div>
</div>


<div class="footer">
	<div class="top hidden-xs hidden-sm">
		<div class="container">
			<div class="row">
				<dl class="col-xs-4  col-sm-2 footer-top-dl">
				    <dt>
						<a href="javascript:void(0)">服务支持</a>
				    </dt>
				    <dd>
						<a href="#" target="_blank" id="footerdownload" >下载中心</a>
				    </dd>
				    <dd>
						<a href="#" target="_blank" id="footerhelps" >帮助文档</a>
				    </dd>
				    <dd>
						<a href="#" target="_blank" id="footervideos">视频中心</a>
				    </dd>
			   </dl>
			   <dl class="col-xs-4  col-sm-2 footer-top-dl">
				    <dt>
						<a href="javascript:void(0)">服务支持</a>
				    </dt>
				    <dd>
						<a href="#" target="_blank" id="footerdownload" >下载中心</a>
				    </dd>
				    <dd>
						<a href="#" target="_blank" id="footerhelps" >帮助文档</a>
				    </dd>
				    <dd>
						<a href="#" target="_blank" id="footervideos">视频中心</a>
				    </dd>
			   </dl>
			   <dl class="col-xs-4  col-sm-2 footer-top-dl">
				    <dt>
						<a href="javascript:void(0)">服务支持</a>
				    </dt>
				    <dd>
						<a href="#" target="_blank" id="footerdownload" >下载中心</a>
				    </dd>
				    <dd>
						<a href="#" target="_blank" id="footerhelps" >帮助文档</a>
				    </dd>
				    <dd>
						<a href="#" target="_blank" id="footervideos">视频中心</a>
				    </dd>
			   </dl>
			   <dl class="col-xs-4  col-sm-2 footer-top-dl">
				    <dt>
						<a href="javascript:void(0)">服务支持</a>
				    </dt>
				    <dd>
						<a href="#" target="_blank" id="footerdownload" >下载中心</a>
				    </dd>
				    <dd>
						<a href="#" target="_blank" id="footerhelps" >帮助文档</a>
				    </dd>
				    <dd>
						<a href="#" target="_blank" id="footervideos">视频中心</a>
				    </dd>
			   </dl>
			   <dl class="col-xs-4  col-sm-2 footer-top-dl">
				    <dt>
						<a href="javascript:void(0)">服务支持</a>
				    </dt>
				    <dd>
						<a href="#" target="_blank" id="footerdownload" >下载中心</a>
				    </dd>
				    <dd>
						<a href="#" target="_blank" id="footerhelps" >帮助文档</a>
				    </dd>
				    <dd>
						<a href="#" target="_blank" id="footervideos">视频中心</a>
				    </dd>
			   </dl>
			   <dl class="col-xs-4  col-sm-2 footer-top-dl">
				    <dt>
						<a href="javascript:void(0)">服务支持</a>
				    </dt>
				    <dd>
						<a href="#" target="_blank" id="footerdownload" >下载中心</a>
				    </dd>
				    <dd>
						<a href="#" target="_blank" id="footerhelps" >帮助文档</a>
				    </dd>
				    <dd>
						<a href="#" target="_blank" id="footervideos">视频中心</a>
				    </dd>
			   </dl>
			</div>
		</div>
	</div>
	<div class="bottom ">
		<div class="container footer-cont-bottom">
			 <a href="/service/page/items.html" target="_blank" id="footerlaw" >使用条款</a>&nbsp;&nbsp;
			 <a href="/service/page/privates.html" target="_blank" id="footerprotect" >隐私保护</a> &nbsp;&nbsp;
			 <br class="visible-xs" />&nbsp;&nbsp;*********** &copy; 
			 <span id="toyear" style="color:#fff;">2016</span> 版权所有 粤ICP备********号
		</div>
    </div>
</div>
</body>
</html>