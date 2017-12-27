var users = null;
var devices = null;
jQuery(document).ready(function () {
    if (space_id != null && space_id != '') {
        $('#id').val(space_id);
        $('#titleSpan').html("修改");
        detail(space_id)
    }

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
    $('#communityId').val(community_id);
    $('#typeId').val(type);
});

function detail(id) {
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        contentType: 'application/json',
        url: $ctx + "/community/space/detail?id=" + id,
        // data: {},
        success: function (data) {
            $("#name").val(data.name);
            $("#position").val(data.position);
            $("#capacity").val(data.capacity);
            $("#amount").val(data.amount);
            $("#imgShow").attr("src", data.img);

            devices = data.devices;
        }
    });
}


function preservation() {
    if (!testStrMsg($("#name").val(), "名称")) {
        return;
    } else if (!testStrMsg($("#position").val(), "位置")) {
        return;
    } else if (!testStrMsg($("#capacity").val(), "容纳人数")) {
        return;
    } else if (!testStrMsg($("#amount").val(), "计费标准")) {
        return;
    } else if ($("#imgUpload").attr("src") == '') {
        alert("上传封面！");
        return;
    }

    var form = new FormData(document.getElementById("form_community_space_add"));

    $.ajax({
        type: 'POST',
        data: form,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        url: $ctx + "/community/space/add",
        success: function (data) {
            reLoad(data);
        }
    });
}

function reLoad(data) {
    alert(data.message)
    if (data.code == '0') {
        $('.close').click();
        var p = {
            id: community_id,
            type: type
        };
        $('#dashboard').load($ctx + "/views/community_space_manager.jsp", p);
    }
}

function ret(data) {
    var p = {
        id: community_id,
        type: type
    };
    $('#dashboard').load($ctx + "/views/community_space_manager.jsp", p);
}

