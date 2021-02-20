<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>我的用户列表</title>

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
                <h2 class="h5 no-margin-bottom">我的关注列表</h2>
            </div>
        </div>
        <section class="no-padding-bottom">

            <div class="list-group myList">
                <ul>
                    <li class="list-group-item">
                        <a href="user_detail.jsp">小东</a>
                        <div style="float: right" class="text-right">
                            <input type="submit" class="btn btn-danger" value="取消关注">
                        </div>
                    </li>
                    <li class="list-group-item">
                        <a href="user_detail.jsp">小标</a>
                        <div style="float: right" class="text-right">
                            <input type="submit" class="btn btn-danger" value="取消关注">
                        </div>
                    </li>
                    <li class="list-group-item">
                        <a href="user_detail.jsp">小艾</a>
                        <div style="float: right" class="text-right">
                            <input type="submit" class="btn btn-danger" value="取消关注">
                        </div>
                    </li>
                    <li class="list-group-item">
                        <a href="user_detail.jsp">小赵</a>
                        <div style="float: right" class="text-right">
                            <input type="submit" class="btn btn-danger" value="取消关注">
                        </div>
                    </li>
                    <li class="list-group-item">
                        <a href="user_detail.jsp">小王</a>
                        <div style="float: right" class="text-right">
                            <input type="submit" class="btn btn-danger" value="取消关注">
                        </div>
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