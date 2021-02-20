<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>文章详情</title>
    <style>
        .myList li{
            display: inline-block;
        }
    </style>
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
                <h2 class="h5 no-margin-bottom">文章详情</h2>
            </div>
        </div>

        <section class="no-padding-bottom">

            <div class="myTitle">
                <h3 class="text-center">如何做一名合格的Java工程师</h3>
                <input type="submit" value="取消收藏" class="btn btn-info">

            </div>

            <div class="myContent">
                <p class="h6"><strong>发布人：</strong>小标</p>
                <p class="h6"><strong>创建时间：</strong>2019-10-30 09:52:12</p>
                <p class="h6"><strong>浏览次数：</strong>100</p>
                <p class="h6"><strong>收藏次数：</strong>13052021</p>
                <textarea style="padding: 2px" disabled="disabled" class="form-control" rows="11">做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力， 需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力， 需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力， 需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力， 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力， 需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力， 需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力， 需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力， 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力， 需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力， 需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力， 需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力，需要持之以恒的毅力， 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力 做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力，做一名Java工程师需要付出非常大的努力</textarea>

            </div>

            <div class="myList">
                <p>您关注的小伙伴等人也收藏了该文章</p>
                <ul>
                    <li class="list-group-item">
                        <a href="#">小东</a>
                    </li>
                    <li class="list-group-item">
                        <a href="#">小标</a>
                    </li>

                </ul>
            </div>
        </section>

        <jsp:include page="/incloud/footer.jsp" />
    </div>
</div>


</body>
</html>