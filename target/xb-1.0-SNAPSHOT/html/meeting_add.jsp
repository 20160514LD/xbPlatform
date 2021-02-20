<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>发布会议</title>
    <style>
        /*这个是这页面特有的，需要单独写出来*/
        .myTitle > input {
            float: right;
            margin-left: 15px;
        }

    </style>
</head>
<body>
<jsp:include page="/incloud/header.jsp"/>

<div class="d-flex align-items-stretch">
    <!-- Sidebar Navigation-->
    <jsp:include page="/incloud/sidebar.jsp"/>
    <!-- Sidebar Navigation end-->
    <div class="page-content">
        <div class="page-header">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom">发布会议</h2>
            </div>
        </div>

        <section class="no-padding-bottom">

            <div class="block-body">
                <form>
                    <div class="form-group">
                        <label class="form-control-label">标题</label>
                        <input type="text" placeholder="会议标题" class="form-control">
                    </div>
                    <!--选择部门-->
                    <div class="form-group">
                        <label class="form-control-label">部门：</label>
                        <select class="selectpicker" data-live-search="true" id="depts" name="deptId">
                            <option>研发部</option>
                            <option>销售部</option>
                            <option>行政部</option>
                            <option>财务部</option>
                            <option>总裁办公室</option>
                        </select>
						<input type="hidden" name="deptName" id="deptName">
                        <label class="form-control-label">抄送人：</label>
                        <select class="selectpicker" data-live-search="true" multiple id="users" name="makeUser">
                            <option>小东</option>
                            <option>小方</option>
                            <option>小标</option>
                            <option>小准</option>
                            <option>东方标准</option>
                        </select>
                    </div>
                    <!--开始时间-->
                    <div class="form-group">
                        <label class="form-control-label">开始时间</label>

                        <input type="text" class="form-control" id="startTime" data-date-format="yyyy-mm-dd hh:ii:ss">
                        <script>
                            $(function () {
								// 初始化日期选择框
                                $('#startTime').datetimepicker();
                            })
                        </script>

                    </div>
					
					<!--结束时间-->
					<div class="form-group">
					    <label class="form-control-label">结束时间</label>
					
					    <input type="text" class="form-control" id="endTime" data-date-format="yyyy-mm-dd hh:ii:ss">
					</div>
					
					<script>
					    $(function () {
							// 初始化日期选择框
					        $('#endTime').datetimepicker();
					    })
					</script>
                    <div class="form-group">
                        <label class="form-control-label">会议内容</label>
                        <textarea class="form-control" rows="5"></textarea>
                    </div>
                    <div class="text-center form-group">
                        <input type="submit" value="发布" class="btn btn-primary">
                        <input type="reset" value="清空" class="btn btn-info">
                    </div>
                </form>
            </div>
        </section>
        <jsp:include page="/incloud/footer.jsp"/>
    </div>
</div>


</body>
</html>