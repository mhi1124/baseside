$(function() {
    $("[nav-menu]").each(function() {
        $(this).bind("click", function() {
            var nav = $(this).attr("nav-menu");
            var sn = nav.split(",");
            if(sn[sn.length-1] == '/sirona' || sn[sn.length-1] == '/druid')
            {
                window.open(sys.rootPath + sn[sn.length - 1]);
                
                //处理目录类型的点击事件
                $(this).parent("li").siblings().find("ul.nav-show").removeClass('nav-show').addClass('nav-hide').attr('style','display:none');
                //处理菜单类型的点击事件
                $(this).parent().parent().parent("li").siblings().find("ul.nav-show").removeClass('nav-show').addClass('nav-hide').attr('style','display:none');
                $("ul.nav-list").find("li.active").removeClass("active").removeClass('open');
                $(this).parent().addClass("active").parent().parent("li").addClass('active open');
                
                //清除用户信息菜单样式
                $(".user-menu").find('li').each(function() {
                    $(this).removeClass('active');
                });
                
            }else
            {
            
            var breadcrumb = '<li><i class="ace-icon fa fa-home home-icon"></i><a href="javascript:init();">首页</a></li>';
            for (var i = 0; i < sn.length - 1; i++) {
                breadcrumb += '<li class="active">' + sn[i] + '</li>';
            }
            
            //设置面包屑内容
            $(".breadcrumb").html(breadcrumb);
            //加载页面
            $(".page-content").load(sys.rootPath + sn[sn.length - 1]);
            //处理目录类型的点击事件
            $(this).parent("li").siblings().find("ul.nav-show").removeClass('nav-show').addClass('nav-hide').attr('style','display:none');
            //处理菜单类型的点击事件
            $(this).parent().parent().parent("li").siblings().find("ul.nav-show").removeClass('nav-show').addClass('nav-hide').attr('style','display:none');
            $("ul.nav-list").find("li.active").removeClass("active").removeClass('open');
            $(this).parent().addClass("active").parent().parent("li").addClass('active open');
            
            //清除用户信息菜单样式
            $(".user-menu").find('li').each(function() {
                $(this).removeClass('active');
            }); 
            
            }
            
        });
    });

    //$("#ace-settings-breadcrumbs").click();
});

//监听浏览器窗口大小变化
$(window).resize(function() {
    $("html").getNiceScroll().resize();
});

/**
 *加载非菜单展示页面
 * @param nav 待加载的资源URL
 */
function loadPage(nav) {
    //加载页面
    $(".page-content").load(sys.rootPath + nav);
}