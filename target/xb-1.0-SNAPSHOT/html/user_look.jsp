<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<title>个人中心</title>

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
						<h2 class="h5 no-margin-bottom">个人中心</h2>
					</div>
				</div>
				<section class="no-padding-bottom">
					<!-- Form Elements -->
					<div class="col-lg-12">
						<div class="block">
							<div class="title"><strong>我的头像</strong></div>
							<div class="avatar"><img src="${user.pic}" id="pic" alt="加载中..." style="width: 150px;height: 150px"
								 class="img-thumbnail rounded-circle"></div>
							<div style="margin-top: 15px" class="text-left">
								<!-- 真正的文件上传表单 -->
								<input name="pic" type="file" id="picFile" style="display: none" />
							</div>
							<div class="title">
								<br>
								<p class="h5"><strong>关注数：</strong><span>&nbsp;</span><span>&nbsp;</span><span>${focus}</span></p>
								<br>
								<p class="h5"><strong>被看数：</strong><span>&nbsp;</span><span>&nbsp;</span><span>${user.look}</span></p>
								<br>
							</div>
							<div class="title"><strong>我的数据</strong></div>
							<div class="block-body">
								<form class="form-horizontal" action="/user/update" method="post">
									<input type="hidden" name="id" value="${loginUser.id}">
									<div class="form-group row">
										<label class="col-sm-3 form-control-label">真实姓名</label>
										<div class="col-sm-9">
											<input type="text" value="${user.realName}" name="realName" class="form-control">
										</div>
									</div>
									<div class="line"></div>
									<div class="form-group row">
										<label class="col-sm-3 form-control-label">所属部门</label>
										<div class="col-sm-9">
											<select class="selectpicker" data-live-search="true" name="deptId">
												<c:forEach items="${deptList}" var="dept" varStatus="i">
													<option value="${dept.id}" ${dept.id==user.deptId? 'selected':''}>${dept.name}</option>
												</c:forEach>

											</select>
										</div>
									</div>
									<div class="line"></div>
									<div class="form-group row">
										<label class="col-sm-3 form-control-label">电话</label>
										<div class="col-sm-9">
											<input type="text" value="${user.phone}" name="phone" class="form-control">
										</div>
									</div>
									<div class="line"></div>
									<div class="form-group row">
										<label class="col-sm-3 form-control-label">年龄</label>
										<div class="col-sm-9">
											<input type="text" placeholder="20" name="age" value="${user.age}" class="form-control">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-3 form-control-label">性别 </label>
										<div class="col-sm-9">
											<div class="i-checks">
												<input id="radioCustom1" type="radio"
													   value="1" ${loginUser.gender=='1'?'checked':''}
													   name="gender" class="radio-template">
												<label for="radioCustom1">男</label>
												<span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span>
												<input id="radioCustom2" type="radio"
													   value="0" ${loginUser.gender=='0'?'checked':''}
													   name="gender" class="radio-template">
												<label for="radioCustom2">女</label>
											</div>
										</div>


									</div>
									<div class="line"></div>
									<div class="form-group row">
										<label class="col-sm-3 form-control-label">注册时间</label>
										<div class="col-sm-9">
											<input type="text" disabled="" placeholder="${user.registerTime}" class="form-control">
										</div>
									</div>
									<div class="line"></div>
									<div class="form-group row">
										<label class="col-sm-3 form-control-label">登录时间</label>
										<div class="col-sm-9">
											<input type="text" disabled="" placeholder="${user.loginTime}" class="form-control">
										</div>
									</div>
									<div class="line"></div>

									<div class="form-group row">
										<label class="col-sm-3 form-control-label">是否私密 <br><small class="text-primary">默认为否，可以不设置</small></label>
										<div class="col-sm-9">
											<div class="i-checks">
												<input id="checkboxCustom1" type="checkbox" name="isSecret" value="0" ${user.isSecret==0?'checked':''} class="checkbox-template">
												<label for="checkboxCustom1">是否私密</label>
											</div>
										</div>

									</div>
									<div class="line"></div>
									<div class="text-center">
										<input type="submit" class="btn btn-primary" value="保存">
									</div>
								</form>
							</div>
						</div>
					</div>
				</section>

				<jsp:include page="/incloud/footer.jsp" />
			</div>
		</div>


	</body>
</html>
<script>
   $(function () {
       $("#pic").click(function () {

           $("#picFile").click();

       })
   })

	$('#picFile').change(function () {
		//构建一个虚拟表单，此表单类型默认为 multipart/form-data 类型
		var formData = new FormData()
		//给表单追加参数
		formData.append("iconFile",document.getElementById("picFile").files[0])

		$.ajax({
			url: '/user/updatePic',
			processData: false, //默认为 true,对请求传递的参数（formData）不做编码处理
			contentType: false,	//不对请求头做处理（ajax请求默认的 contentType为 application/x-www-form-urlencoded）
			data: formData,
			type: "post",
			async: true,
			success: function (res) {
				layer.msg('上传成功')
				//上传成功后制空
				$('#picFile').val('')
				$('#pic').attr('src',res)
				$('#loginUserpic').attr('src',res)
			},
			error: function () {
				layer.msg('服务器忙')
			}
		})
	})
</script>
