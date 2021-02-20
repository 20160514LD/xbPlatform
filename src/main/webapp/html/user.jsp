<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>查看用户</title>
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
                <h2 class="h5 no-margin-bottom">用户列表</h2>
            </div>
        </div>
        <section class="no-padding-bottom">
            <div class="title">
                <form class="form-inline">
                    <div class="form-group">
                        <label for="inlineFormInput" class="sr-only">Name</label>
                        <input id="inlineFormInput" type="text" placeholder="按名字查找" class="mr-sm-3 form-control">
                    </div>
                    <div class="form-group">
                        <input type="submit" value="查询" class="btn btn-primary">
                    </div>
                </form>

            </div>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>年龄</th>
                        <th>简介</th>
                        <th>操作</th>
                        <th>加关注</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>小东</td>
                        <td>0-表示女</td>
                        <td>19</td>
                        <td>我很美</td>
                        <td>
                            <input type="submit" value="详细信息" class="btn-xs btn-primary userDetail">
                        </td>
            
                        <td>
            
                            <input type="checkbox" value="" class="checkbox-template">
            
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>小标</td>
                        <td>1-表示男</td>
                        <td>20</td>
                        <td>我很帅</td>
                        <td>
                            <input type="submit" value="详细信息" id="userDetail" class="btn-xs btn-primary userDetail">
                        </td>
                        <td>
                            <input type="checkbox" value="" class="checkbox-template">
                        </td>
                    </tr>
                    </tbody>
                </table>
            
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

<!-- JavaScript files-->


<script type="text/javascript">
	
	$(function(){
		
		//用户列表，查看详情，通过class名字userDetail来找
		$("#userDetail").click(function () {
		    console.log("你点击了用户详情");
		    window.location.href = '/html/user_detail.jsp'
		});
		
		$(".table").find("input[type='checkbox']").on("click",function () {
		    if($(this).prop("checked")){
		        layer.msg("关注成功")
		    }else{
		        layer.msg("取关成功")
		
		    }
		
		})
		
	})
	
</script>
</body>
</html>