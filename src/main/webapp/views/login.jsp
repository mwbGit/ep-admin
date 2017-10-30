<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!-->
<html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

    <meta charset="utf-8"/>

    <title>E园</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>

    <meta content="" name="description"/>

    <meta content="" name="author"/>

    <!-- BEGIN GLOBAL MANDATORY STYLES -->

    <link href="${ctx }/static/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx }/static/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx }/static/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx }/static/media/css/style-metro.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx }/static/media/css/style.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx }/static/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx }/static/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

    <link href="${ctx }/static/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>

    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN PAGE LEVEL STYLES -->

    <link href="${ctx }/static/media/css/login-soft.css" rel="stylesheet" type="text/css"/>

    <!-- END PAGE LEVEL STYLES -->

    <link rel="shortcut icon" href="${ctx }/static/media/image/favicon.ico"/>

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="login">

<!-- BEGIN LOGO -->

<div class="logo">

    <img src="${ctx }/static/media/logo/epark-logo.png" alt=""/>

</div>

<!-- END LOGO -->

<!-- BEGIN LOGIN -->

<div class="content">

    <!-- BEGIN LOGIN FORM -->

    <form class="form-vertical" action="${ctx }/login" method="post" id="subForm">

        <h3 class="form-title">账号登陆${show}</h3>

        <div class="alert alert-error hide" id="show-alert">

            <button class="close" data-dismiss="alert"></button>

            <span>请输入用户名和密码</span>

        </div>

        <div class="control-group">

            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->

            <label class="control-label visible-ie8 visible-ie9">用户名</label>

            <div class="controls">

                <div class="input-icon left">

                    <i class="icon-user"></i>

                    <input class="m-wrap placeholder-no-fix" type="text" placeholder="用户名" id="username" name="username"
                           value="admin"/>

                </div>

            </div>

        </div>

        <div class="control-group">

            <label class="control-label visible-ie8 visible-ie9">密码</label>

            <div class="controls">

                <div class="input-icon left">

                    <i class="icon-lock"></i>

                    <input class="m-wrap placeholder-no-fix" type="password" placeholder="密码" id="password"
                           name="password" value="admin"/>

                </div>

            </div>

        </div>

        <div class="form-actions">


            <button type="button" class="btn blue pull-right" id="submit" onclick="login()">

                登录 <i class="m-icon-swapright m-icon-white"></i>

            </button>

        </div>


    </form>


</div>

<!-- END LOGIN -->

<!-- BEGIN COPYRIGHT -->

<div class="copyright">

    2017 &copy; EPARK-Admin

</div>

<!-- END COPYRIGHT -->

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

<!-- BEGIN CORE PLUGINS -->

<script src="${ctx }/static/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

<script src="${ctx }/static/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/bootstrap.min.js" type="text/javascript"></script>

<!--[if lt IE 9]>

<script src="${ctx }/static/media/js/excanvas.min.js"></script>

<script src="${ctx }/static/media/js/respond.min.js"></script>

<![endif]-->

<script src="${ctx }/static/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery.blockui.min.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery.cookie.min.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery.uniform.min.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL PLUGINS -->

<script src="${ctx }/static/media/js/jquery.validate.min.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery.backstretch.min.js" type="text/javascript"></script>

<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->

<script src="${ctx }/static/media/js/app.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/login-soft.js" type="text/javascript"></script>

<!-- END PAGE LEVEL SCRIPTS -->

<script>
    var $ctx = "${ctx }";
    jQuery(document).ready(function () {

        App.init();

        Login.init();

    });
    document.onkeyup = function (e) {//按键信息对象以函数参数的形式传递进来了，就是那个e
        var code = e.charCode || e.keyCode;  //取出按键信息中的按键代码(大部分浏览器通过keyCode属性获取按键代码，但少部分浏览器使用的却是charCode)
        if (code == 13) {
            login();
        }
    };

    function login() {
        var username = $("#username").val().trim();
        var password = $("#password").val().trim();
        if (username == '' || password == '') {
            $("#show-alert").show();

            return false;
        } else {
            $("#show-alert").hide();

            var data = $("#subForm").serialize();

            $.ajax({
//                      dataType: 'json',
                type: "POST",
                async: false,
//                      contentType: 'application/json',
                url: $ctx + "/login",
                data: data,
                success: function (data) {
                    if (data.code != "0") {
                        alert(data.message);
                    } else {
                        window.location.href = $ctx + "/views/index.jsp";
                    }
                }
            });
        }
    }

</script>

<!-- END JAVASCRIPTS -->

</body>

<!-- END BODY -->

</html>