<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>用户详情</title>

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
                <h2 class="h5 no-margin-bottom">用户详情</h2>
            </div>
        </div>
        <section class="no-padding-bottom">
            <!-- Form Elements -->
            <div class="col-lg-12">
                <div class="block">
                    <div class="title"><strong>TA的头像</strong></div>
                    <div class="avatar"><img src="../img/avatar-6.jpg" alt="加载中..." style="width: 150px;height: 150px" class="img-thumbnail rounded-circle"></div>

                    <div class="title">
                        <br>
                        <p class="h5"><strong>粉丝数：</strong><span>&nbsp;</span><span>&nbsp;</span><span>52</span></p>
                        <br>
                        <p class="h5"><strong>被看数：</strong><span>&nbsp;</span><span>&nbsp;</span><span>1168</span></p>
                        <br>
                    </div>
                    <div class="title"><strong>TA的数据</strong></div>
                    <div class="block-body">
                        <form class="form-horizontal">
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">真实姓名</label>
                                <div class="col-sm-9">
                                    <input disabled="disabled" type="text" value="东方标准" class="form-control">
                                </div>
                            </div>
                            <div class="line"></div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">所属部门</label>
                                <div class="col-sm-9">
                                    <input disabled="disabled" type="text" value="东方标准" class="form-control">
                                </div>
                            </div>
                            <div class="line"></div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">电话</label>
                                <div class="col-sm-9">
                                    <input disabled="disabled" type="text" value="020-xxxxxxxx" name="password" class="form-control">
                                </div>
                            </div>
                            <div class="line"></div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">年龄</label>
                                <div class="col-sm-9">
                                    <input disabled="disabled" type="text" placeholder="20" class="form-control">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">性别 </label>
                                <div class="col-sm-9">

                                    <div class="i-checks">
                                        <input disabled="disabled" id="radioCustom1" type="radio" value="1" name="sex" class="radio-template">
                                        <label for="radioCustom1">男</label>
                                        <span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span>
                                        <input disabled="disabled" id="radioCustom2" type="radio" value="0" name="sex" class="radio-template">
                                        <label for="radioCustom2">女</label>
                                    </div>
                                </div>


                            </div>
                            <div class="line"></div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">注册时间</label>
                                <div class="col-sm-9">
                                    <input type="text" disabled="" placeholder="2019-10-30 09:30:00" class="form-control">
                                </div>
                            </div>
                            <div class="line"></div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">登录时间</label>
                                <div class="col-sm-9">
                                    <input type="text" disabled="" placeholder="2019-10-30 19:30:00" class="form-control">
                                </div>
                            </div>
                            <div class="line"></div>

                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">是否私密 <br><small class="text-primary">默认为否，可以不设置</small></label>
                                <div class="col-sm-9">
                                    <div class="i-checks">
                                        <input disabled="disabled" id="checkboxCustom1" type="checkbox" value="" class="checkbox-template">
                                        <label for="checkboxCustom1">是否私密</label>
                                    </div>

                                </div>


                            </div>
                            <div class="line"></div>
                            <!--<div class="text-center">-->
                                <!--<input type="submit" class="btn btn-primary" value="保存">-->
                            <!--</div>-->
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
