var resourceList ;

$(function () {
    // window.location.assign($ctx + "/views/index.jsp");
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        contentType: 'application/json',
        url: $ctx + "/resource/list",
        // data: data,
        success: function (data) {
            if (data.empty) {
                return;
            }
            resourceList = data;
        },
        complete: function (XMLHttpRequest, status) {
           if (status == 'error' || status == 'parsererror'){
               window.location.href = $ctx + "/views/login.jsp";
           }
        }
    });

});
function testNumber(number) {
    var re = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
    if (number== null || number =='' || !re.test(number)) {
        alert("非法数字！");
        return false;
    }

    return true;
}

function testPrice(num, msg) {
    var exp = /^[\-\+]?([0-9]\d*|0|[1-9]\d{0,2}(,\d{3})*)(\.\d+)?$/;
    if(!testStrMsg(num,msg)){
        return false;
    }

    if (!exp.test(num)){
        msg += "格式不正确！";
        alert(msg);
        return false;
    }
    return true;
}

function testStr(str) {
    if (str== null || str ==''){
        alert("不能为空！");
        return false;
    }
    return true;
}

function testStrMsg(str, msg) {
    msg += "不能为空！";
    if (str== null || str ==''){
        alert(msg);
        return false;
    }
    return true;
}

function loadJsp(item) {
    $('#showAddItem').hide();

    if (item == "analysis") {
        // $('#analysis').attr("class", "active");

        $('#dashboard').load($ctx + "/views/analysis.jsp");
        $('#menuTitle').html("数据分析");
    } else if (item == "dimension") {
        $('#dashboard').load($ctx + "/views/dimension.jsp");
        $('#menuTitle').html("吐槽管理");
        $('#showAddItem').show();
    } else if (item == "user_manager") {
        $('#dashboard').load($ctx + "/views/user_manager.jsp");
        $('#menuTitle').html("用户列表");
    } else if (item == "activity_type") {
        $('#dashboard').load($ctx + "/views/activity_type.jsp");
        $('#menuTitle').html("活动分类管理");
    } else if (item == "activity_add") {
        $('#dashboard').load($ctx + "/views/activity_add.jsp");
        $('#menuTitle').html("添加活动");
    } else if (item == "activity_manager") {
        $('#dashboard').load($ctx + "/views/activity_manager.jsp");
        $('#menuTitle').html("活动内容管理");
    } else if (item == "banner_manager") {
        $('#dashboard').load($ctx + "/views/banner_manager.jsp");
        $('#menuTitle').html("轮播图管理");
    }
}

function subPassword() {
    var password = $('#password').val().trim();
    var rpassword = $('#rpassword').val().trim();
    if (password == '' || password.length < 6) {
        $('#pass').show();
        $('#rpass').hide();
    } else if (rpassword == '' || rpassword.length < 6) {
        $('#pass').hide();
        $('#rpass').show();
        $('#rpass').html("至少六位密码");
    } else if (password != rpassword) {
        $('#pass').hide();
        $('#rpass').show();
        $('#rpass').html("两次输入不一致");
    } else {
        $('#pass').hide();
        $('#rpass').hide();
        $.ajax({
            dataType: 'json',
            type: "POST",
            async: false,
            // contentType: 'application/json',
            url: $ctx +"/user/reset/password",
            data: {"password": password},
            success: function (data) {
                alert(data.message);
                window.location.href = $ctx + "/views/login.jsp";
            }
        });
    }
}