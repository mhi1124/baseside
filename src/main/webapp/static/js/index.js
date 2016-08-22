var base = {
	index:{
		 initHomePage : function() {
	            $(".page-content").load(ctx + "/welcome.jsp");
	            $(".breadcrumb").html('<li><i class="ace-icon fa fa-home home-icon"></i><a href="javascript:base.index.initHomePage();">首页</a></li><li class="active">控制台</li>');
	        },
	      menu : {
	    	  initMenuEvent : function() {
	                $("[nav-menu]").each(function() {
	                    $(this).bind("click", function() {
	                        var nav = $(this).attr("nav-menu");
	                        var sn = nav.split(",");
	                        //清除用户信息菜单样式
	                        $(".user-menu").find('li').each(function() {
	                            $(this).removeClass('active');
	                        });
	                        //处理监控-新窗口打开
	                        if (sn[sn.length - 1] == '/sirona' || sn[sn.length - 1] == '/druid') {
	                            window.open(sys.rootPath + sn[sn.length - 1]);
	                        } else {
	                            var breadcrumb = '<li><i class="ace-icon fa fa-home home-icon"></i><a href="javascript:base.index.initHomePage();">首页</a></li>';
	                            for (var i = 0; i < sn.length - 1; i++) {
	                                breadcrumb += '<li class="active">' + sn[i] + '</li>';
	                            }
	                            //设置面包屑内容
	                            $(".breadcrumb").html(breadcrumb);
	                            //加载页面
	                            $(".page-content").load(ctx + sn[sn.length - 1]);
	                        }
	                        var level = $(this).parent("li").attr("level");
	                        if (level == 'level1' || level == 'level2') {
	                            //处理目录类型的点击事件-两级菜单
	                            $(this).parent("li").siblings().find("ul.nav-show").removeClass('nav-show').addClass('nav-hide').attr('style', 'display:none');
	                            //处理菜单类型的点击事件
	                            $(this).parent().parent().parent("li").siblings().find("ul.nav-show").removeClass('nav-show').addClass('nav-hide').attr('style', 'display:none');
	                            $("ul.nav-list").find("li.active").removeClass("active").removeClass('open');
	                            $(this).parent().addClass("active").parent().parent("li").addClass('active open');
	                        } else if (level == 'level3') {
	                            //处理目录类型的点击事件-三级菜单
	                            $(this).parent("li").siblings().find("ul.nav-show").removeClass('nav-show').addClass('nav-hide').attr('style', 'display:none');
	                            //处理菜单类型的点击事件
	                            $(this).parent().parent().parent().parent().parent("li").siblings().find("ul.nav-show").removeClass('nav-show').addClass('nav-hide').attr('style', 'display:none');
	                            $("ul.nav-list").find("li.active").removeClass("active").removeClass('open');
	                            $(this).parent().addClass("active").parent().parent().parent().parent("li").addClass('active open');
	                        } else {
	                            //处理目录类型的点击事件-四级菜单
	                            $(this).parent("li").siblings().find("ul.nav-show").removeClass('nav-show').addClass('nav-hide').attr('style', 'display:none');
	                            //处理菜单类型的点击事件
	                            $(this).parent().parent().parent().parent().parent().parent().parent("li").siblings().find("ul.nav-show").removeClass('nav-show').addClass('nav-hide').attr('style', 'display:none');
	                            $("ul.nav-list").find("li.active").removeClass("active").removeClass('open');
	                            $(this).parent().addClass("active").parent().parent().parent().parent().parent().parent("li").addClass('active open');
	                        }
	                    });
	                });
	            }
	      	},
	      	initNavigation : function() {
	            $("#ace-settings-navbar").click();
	            $("#ace-settings-sidebar").click();
	        }
		}
}