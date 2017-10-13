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
    } else if (item == "user") {
        $('#dashboard').load($ctx + "/views/user_manager.jsp");
        $('#menuTitle').html("用户列表");
    } else if (item == "activity_type") {
        $('#dashboard').load($ctx + "/views/activity_type.jsp");
        $('#menuTitle').html("活动分类管理");
    } else if (item == "activity_add") {
        $('#dashboard').load($ctx + "/views/activity_add.jsp");
        $('#menuTitle').html("添加活动");
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