var users = null;
var devices = null;
var pictures = null;
jQuery(document).ready(function () {
    if (community_id != null && community_id != '') {
        $('#communityId').val(community_id);
        $('#titleSpan').html("修改");
        detail(community_id)
    }

    $.ajax({
        dataType: 'json',
        async: false,
        url: $ctx + "/user/list?managed=true",
        success: function (data) {
            var trs = "";
            $.each(data.aaData, function (n, value) {
                trs += '<option value="' + value.id;
                if (users !== null) {
                    $.each(users, function (i, v) {
                        if (v == value.id) {
                            trs += '" selected ';

                        }
                    });
                }

                trs += '">&nbsp' + value.name + '</option>';

            });

            $('#user_list').html(trs);
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

                if (devices !== null) {
                    $.each(devices, function (i, v) {
                        if (v == value.id) {
                            trs += '" selected ';
                        }
                    });
                }
                trs += '">&nbsp' + value.name + '</option>';

            });

            $('#device_list').html(trs);
        }
    });

    $('#device_list').multiSelect();
    $('#user_list').multiSelect();
});

function detail(id) {
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        contentType: 'application/json',
        url: $ctx + "/community/detail?id=" + id,
        // data: {},
        success: function (data) {
            $("#name").val(data.name);
            $("#tag").val(data.tag);
            $("#tips").html(data.tips);
            $("#myEditor").val(data.content);
            // $("#imgShow").attr("src", data.img);

            users = data.userIds;
            devices = data.devices;
            pictures = data.pictures;

        }
    });
}


function preservation() {
    if (!testStrMsg($("#name").val(), "名称")) {
        return;
    } else if (!testStrMsg($("#tag").val(), "标签")) {
        return;
    }

    var form = new FormData(document.getElementById("form_community_add"));

    $.ajax({
        type: 'POST',
        data: form,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        url: $ctx + "/community/add",
        success: function (data) {
            reLoad(data);
        }
    });
}

function reLoad(data) {
    alert(data.message)
    if (data.code == '0') {
        $('.close').click();
        $('#dashboard').load($ctx + "/views/community_manager.jsp");
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