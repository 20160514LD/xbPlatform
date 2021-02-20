<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>部门</title>

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
                <h2 class="h5 no-margin-bottom">全部部门</h2>
            </div>
        </div>
        <section class="no-padding-bottom">

            <div class="list-group subject">
                <div class="list-group-item">
                    <a href="#deptDetail1" data-toggle="collapse">研发部 &nbsp;3人</a>
                    <div id="deptDetail1" class="collapse deptDetail">
                        <ul>
                            <li>
                                <a href="#">小东</a>
                            </li>
                            <li>
                                <a href="#">小方</a>
                            </li>
                            <li>
                                <a href="#">小标</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="list-group-item">
                    <a href="#deptDetail2" data-toggle="collapse">行政部 &nbsp;2人</a>
                    <div id="deptDetail2" class="collapse deptDetail">
                        <ul>
                            <li>
                                <a href="#">小标</a>
                            </li>
                            <li>
                                <a href="#">小准</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="list-group-item">
                    <a href="#deptDetail3" data-toggle="collapse">总裁办公室 &nbsp;1人</a>
                    <div id="deptDetail3" class="collapse deptDetail">
                        <ul>
                            <li>
                                <a href="#">东方标准</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>

        <jsp:include page="/incloud/footer.jsp" />
    </div>
</div>

</body>
</html>