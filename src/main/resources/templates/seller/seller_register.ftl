
<html>
    <#include "../common/header.ftl" >

<body>

<header class="page-header visible-sm visible-md">
    <h1>form</h1>
</header>

<div>
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3" style="margin-top: 50px">

            <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title ssv-big-bold-label">注册</h3>
            </div>

                <div class="panel-body">
                    <!-- 表单  水平表单-->
                    <form class="form-horizontal" role="form" method="post" action="/sell/seller/register">
                        <!-- 电子邮件 has-feedback：反馈图标-->
                        <div class="form-group has-feedback">
                            <#--<label class="col-sm-3 control-label" for="email">Email</label>-->
                            <div class="col-sm-9">
                                <input type="email" class="form-control" id="email" name="email"
                                       placeholder="请输入邮箱" required>
                                <span class=""></span>
                            </div>
                        </div>
                        <!-- 用户名 -->
                        <div class="form-group has-feedback">
                            <#--<label class="col-sm-3 control-label" for="username">UserName</label>-->
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="username" name="username"
                                       placeholder="请输入用户名" required>
                                <span class=""></span>
                            </div>
                        </div>
                        <!-- 密码 -->
                        <div class="form-group has-feedback">
                            <#--<label class="col-sm-3 control-label" for="password">PassWord</label>-->
                            <div class="col-sm-9">
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="请输入密码" required>
                                <span class=""></span>
                            </div>
                        </div>
                        <!-- 功能按钮 -->
                        <p class="text-right">
                            <button id="btnSubmit" type="submit" class="btn btn-primary">
                                Submit
                            </button>
                            <button type="reset" class="btn btn-danger">
                                Reset
                            </button>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>

<footer class="navbar-fixed-bottom text-center" style="padding-bottom: 10px">
    &copy; 2015
</footer>
</body>

<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script>
    function checkInput(txt){
        //追加样式是先移除样式表后追加
        if(txt.val()==""){
            txt.parent().parent()
                    .removeClass()//为空，出现一个x的小图标
                    .addClass("form-group has-error has-feedback");
            txt.next().removeClass()
                    .addClass("glyphicon glyphicon-remove form-control-feedback");
            return false;
        }else{
            txt.parent().parent()
                    .removeClass()//验证成功后出现一个对号的小图标
                    .addClass("form-group has-success has-feedback");
            txt.next().removeClass()
                    .addClass("glyphicon glyphicon-ok form-control-feedback");
            return true;
        }
    }
    $(function(){
        //逐一的验证
        $("#email").change(function(){checkInput($("#email"));});
        $("#username").change(function(){checkInput($("#username"));});
        $("#password").change(function(){checkInput($("#password"));});

        //提交按钮事件，false:不能提交，抑制提交按钮
        $("#btnSubmit").click(function(){
            if(checkInput($("#email")) && checkInput($("#username")) &&
                    checkInput($("#password"))){
                return true;
            }
            return false;
        });
    });
</script>
</html>