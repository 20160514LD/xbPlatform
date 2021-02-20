<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>会议系统</title>

</head>
<body>
<jsp:include page="/incloud/header.jsp" />
<div class="d-flex align-items-stretch">
    <!-- Sidebar Navigation-->
    <jsp:include page="/incloud/sidebar.jsp" />
    <!-- Sidebar Navigation end-->
    <div class="page-content">
        <div class="page-header">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom">会议系统</h2>
            </div>
        </div>

        <section class="no-padding-bottom">

            <div class="list-group ">
                <!--搜索文章的条件-->
                <div class="myTitle">
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="inlineFormInput" class="sr-only">Name</label>
                            <input id="inlineFormInput" type="text" placeholder="按标题名字查找" class="mr-sm-3 form-control">
                        </div>
                        
                        <!--选择状态-->
                        <div class="form-group" style="margin-right: 20px;">
                            <select name="dep" class="form-control selectpicker" id="status">
                                <option>请选择状态</option>
                                <option>未开始</option>
                                <option>正在进行</option>
                                <option>已结束</option>
                            </select>
                        </div>
						<div class="form-group">
						    <input type="submit" value="查询" class="btn btn-primary mr-sm-3 ">
						</div>
                    </form>

                    <input id = "addMeet" type="submit" value="发布会议" class="btn btn-primary">


                </div>

                <ul class="myList">
                    <li class="list-group-item">
                        <div style="float: right;">
                            <span><strong>状态：</strong>正在进行</span>
                        </div>
                        <a href="meeting_detail.jsp">关于《小标会议平台》项目部分模块功能升级</a>
                        <p class="h6"><strong>部门：</strong>研发部</p>
                        <p class="h6"><strong>发布日期：</strong>2019-10-30</p>
                        <p class="h6"><strong>开始时间：</strong>2019-10-30 15:40</p>
                        <p style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis">
                            小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：
                            小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：
                            小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：
                            小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级</p>
                    </li>

                    <li class="list-group-item">
                        <div style="float: right;">
                            <span><strong>状态：</strong>正在进行</span>
                        </div>
                        <a href="meeting_detail.jsp">关于《小标会议平台》项目部分模块功能升级</a>
                        <p class="h6"><strong>部门：</strong>研发部</p>
                        <p class="h6"><strong>发布日期：</strong>2019-10-30</p>
                        <p class="h6"><strong>开始时间：</strong>2019-10-30 15:40</p>
                        <p style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis">
                            小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：
                            小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：
                            小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：
                            小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级</p>
                    </li>

                    <li class="list-group-item">
                        <div style="float: right;">
                            <span><strong>状态：</strong>正在进行</span>
                        </div>
                        <a href="meeting_detail.jsp">关于《小标会议平台》项目部分模块功能升级</a>
                        <p class="h6"><strong>部门：</strong>研发部</p>
                        <p class="h6"><strong>发布日期：</strong>2019-10-30</p>
                        <p class="h6"><strong>开始时间：</strong>2019-10-30 15:40</p>
                        <p style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis">
                            小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：
                            小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：
                            小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：
                            小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级：小标会议平台有如下模块需要升级</p>
                    </li>

                </ul>

                <nav class="text-center" aria-label="Page navigation">
                    <ul class="pagination">
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li>
                            <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </section>

        <jsp:include page="/incloud/footer.jsp" />
    </div>
</div>

</body>
</html>
<script type="text/javascript">
	
	//发布会议跳转链接
	$("#addMeet").click(function () {
	    window.location.href = '/html/meeting_add.jsp'
	});
</script>