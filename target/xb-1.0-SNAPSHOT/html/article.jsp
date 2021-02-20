<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>文章</title>

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
                <h2 class="h5 no-margin-bottom">发布文章</h2>
            </div>
        </div>

        <section class="no-padding-bottom">

            <div class="list-group myList">
                <!--搜索文章的条件-->
                <div class="myTitle">
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="inlineFormInput" class="sr-only">Name</label>
                            <input id="inlineFormInput" type="text" placeholder="按标题名字查找" class="mr-sm-3 form-control">
                        </div>
                        <div class="form-group">
                            <input type="submit" value="查询" class="btn btn-primary">
                        </div>
                    </form>

                    <input id = "addArt" type="submit" value="发布文章" class="btn btn-primary">

                </div>

                <ul>
                    <li class="list-group-item">
                        <div style="float: right;">
                            <span><strong>收藏数：</strong>100</span>
                            <span>&nbsp;</span>
                            <span>&nbsp;</span>
                            <span>&nbsp;</span>
                            <span><strong>浏览数：</strong>13052021</span>
                        </div>
                        <a href="/html/article_detail.jsp">如何做一名合格的Java工程师？</a>
                        <p class="h6"><strong>作者：</strong>小标</p>
                        <p class="h6"><strong>时间：</strong>2019-10-30 09:52:12</p>
                        <p style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis">做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名工程师</p>
                    </li>

                    <li class="list-group-item">
                        <div style="float: right;">
                            <span><strong>收藏数：</strong>100</span>
                            <span>&nbsp;</span>
                            <span>&nbsp;</span>
                            <span>&nbsp;</span>
                            <span><strong>浏览数：</strong>13052021</span>
                        </div>
                        <a href="article_detail.jsp">如何做一名合格的Java工程师？</a>
                        <p class="h6"><strong>作者：</strong>小标</p>
                        <p class="h6"><strong>时间：</strong>2019-10-30 09:52:12</p>
                        <p style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis">做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名工程师</p>
                    </li>

                    <li class="list-group-item">
                        <div style="float: right;">
                            <span><strong>收藏数：</strong>100</span>
                            <span>&nbsp;</span>
                            <span>&nbsp;</span>
                            <span>&nbsp;</span>
                            <span><strong>浏览数：</strong>13052021</span>
                        </div>
                        <a href="article_detail.jsp">如何做一名合格的Java工程师？</a>
                        <p class="h6"><strong>作者：</strong>小标</p>
                        <p class="h6"><strong>时间：</strong>2019-10-30 09:52:12</p>
                        <p style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis">做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名工程师</p>
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
	//添加文章跳转链接
	$("#addArt").click(function () {
	    window.location.href = '/html/article_add.jsp'
	});
</script>