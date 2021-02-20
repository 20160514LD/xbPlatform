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
                <c:forEach items="${deptMapList}" var="deptMap" varStatus="i">
                    <div class="list-group-item">
                        <a href="#deptDetail${i.count}" data-toggle="collapse">${deptMap.name} &nbsp;${deptMap.deptCount}人</a>
                        <div id="deptDetail${i.count}" class="collapse deptDetail">
                            <ul>
                                <c:forEach items="${deptMap.userMapList}" var="userMap">
                                    <li>
                                        <a href="#">${userMap.realName}</a>
                                    </li>
                                </c:forEach>

                            </ul>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </section>

        <jsp:include page="/incloud/footer.jsp" />
    </div>
</div>

</body>
</html>