jQuery(document).ready(function () {
    $.ajax({
        dataType: 'json',
        async: false,
        url: $ctx + "/user/list?managed=true",
        success: function (data) {
            var trs = "";
            $.each(data.aaData, function (n, value) {
                trs += '<option value="' + value.id;

                // if (typeId != null && typeId  == value.value){
                //     trs += ' selected ';
                // }

                trs +=  '">&nbsp' + value.name + '</option>';

            });
            $('#device_list').html(trs);
        }
    });

    $.ajax({
        dataType: 'json',
        async: false,
        url: $ctx + "/community/device/list",
        success: function (data) {
            var trs = "";
            $.each(data.aaData, function (n, value) {
                trs += '<option value="' + value.id;

                // if (typeId != null && typeId  == value.value){
                //     trs += ' selected ';
                // }

                trs +=  '">&nbsp' + value.name + '</option>';

            });

            $('#user_list').html(trs);
        }
    });

    $('#device_list').multiSelect();
    $('#user_list').multiSelect();
});

function addUserBut(curPage) {
    var iDisplayLength = 2;
    var lastStart = $("#lastStart").val();
    if (curPage == 0) {
        lastStart = 0;
    }
    var iDisplayStart = parseInt(lastStart) + (iDisplayLength * curPage);

    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        // contentType: 'application/json',
        url: $ctx + "/user/list?managed=false&iDisplayLength=" + iDisplayLength + "&iDisplayStart=" + iDisplayStart,
        // data: {},
        success: function (data) {
            $("#lastStart").val(iDisplayStart);

            var str = '';
            if (data.totalCount == 0) {
                str += '无可添加用户！';
                $("#per").hide();
                $("#next").hide();

            } else {
                $.each(data.aaData, function (n, value) {
                    str += '<tr><td> <img src="' + value.img + '" style="width: 40px;height: 40px"/></td>' +
                        '<td>' + value.name + '</td> <td>' + value.mobile + '</td>' +
                        '<td><button class="btn blue" onclick="modifyUserManager(' + value.id + ')" >选择</button></td>' +
                        '</tr>';
                });

                str += '<tr><td></td><td colspan="2" style="text-align: center"><button id="per" class="btn" onclick="addUserBut(-1)" >上一页</button>' +
                    '<button id="next" class="btn" onclick="addUserBut(1)" >下一页</button></td>' +
                    '<td></td></tr>';

                $("#showUsers").html(str);
                if (data.totalCount > iDisplayStart + iDisplayLength) {
                    $("#next").show();
                } else {
                    $("#next").hide();
                }
                if (iDisplayStart >= iDisplayLength) {
                    $("#per").show();
                } else {
                    $("#per").hide();
                }
            }
        }
    });
}

function reLoad(data) {
    alert(data.message)
    if (data.code == '0') {
        $('.close').click();
        $('#dashboard').load($ctx + "/views/community_user_manager.jsp");
    }
}

function deleteUserManager(id) {
    if (window.confirm("确定移除？")) {
        $.ajax({
            dataType: 'json',
            async: false,
            url: $ctx + "/user/modify/managed?id=" + id + "&managed=false",
            // data: "{}",
            success: function (data) {
                reLoad(data);
            }
        });
    }
}

function modifyUserManager(id) {
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        url: $ctx + "/user/modify/managed?id=" + id + "&managed=true",
        success: function (data) {
            reLoad(data);
        }
    });

}