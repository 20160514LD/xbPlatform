<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav id="sidebar">
    <!-- Sidebar Header-->
    <div class="sidebar-header d-flex align-items-center">
        <div id="avatar" class="avatar">
            <a href="/user/userDetail?id=${loginUser.id}"><img src="${loginUser.pic}" alt="..." class="img-fluid rounded-circle"></a>
        </div>
        <div class="title">
            <h1 class="h5">${loginUser.realName}</h1>
            <p>${loginUser.deptName}</p>
        </div>
    </div>
    <!-- Sidebar Navidation Menus--><span class="heading">Main</span>
    <ul class="list-unstyled">
        <li><a href="/home/index"> <i class="icon-home"></i>主页 </a></li>
        <li><a href="#userDropdown"  data-toggle="collapse" aria-expanded="true"> <i class="icon-windows"></i>用户列表</a>
            <ul id="userDropdown" class="collapse show">
                <li><a href="/user/findPage?currPage=1">查看用户</a></li>
                <li><a href="/user/findFocusPage?currPage=1">我关注的用户</a></li>
                <li><a href="/article/findPage?currPage=1">发布文章</a></li>
                <li><a href="/article/favoritePage?currPage=1">我的收藏</a></li>
            </ul>
        </li>
        <!--<li><a href="login.jsp"> <i class="icon-logout"></i>Login page </a></li>-->

        <li><a href="#depDropdown"  data-toggle="collapse"> <i class="icon-windows2"></i>部门列表</a>
            <ul id="depDropdown" class="collapse show ">
                <li><a href="/dept/findAllMap">全部部门</a></li>
                <li><a href="/meeting/findPage?currPage=1">会议系统</a></li>
            </ul>
        </li>

    </ul>

</nav>
