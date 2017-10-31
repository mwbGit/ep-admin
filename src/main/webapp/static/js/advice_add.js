

    if (activity_id != null && activity_id != '') {
        $('#activityId').val(activity_id);
        $('#menuTitle').html("修改资讯");
        $('#titleSpan').html("修改资讯");
        detail(activity_id)

    }
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        contentType: 'application/json',
        url: $ctx + "/resource/activity/type/list",
        // data: {},
        success: function (data) {
            var trs = "";
            $.each(data.data, function (n, value) {
                trs += '<option value=' + value.label;

                if (typeId != null && typeId == value.value) {
                    trs += ' selected ';
                }

                trs += '>' + value.label + '</option>';

            });

            $("#activityType").html(trs);
        }
    });



function detail(id) {
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        contentType: 'application/json',
        url: $ctx + "/advice/detail?id=" + id,
        // data: {},
        success: function (data) {

            $("#id").val(data[0].id);
            $("#title").val(data[0].title);
            $("#typeId").val(data[0].typeId);
            $("#content").val(data[0].content);
            $("#miniText").val(data[0].miniText);
            //$("#myEditor").val(data.content);
            $("#imgShow").attr("src", data[0].img);

            typeId = data.typeId;
            addressId = data.addressId;
            addressDetailId = data.addressDetailId;
            if (data.price == null) {
                showFee = false;
            }
        }
    });
}

//提交表单
function validateFromSub() {
    /* if (!testStrMsg($("#title").val(), "标题")) {
     return;
     } else if ($("#title").val().length > 32) {
     alert("标题过长")
     }

     if (!testStrMsg($("#d4311").val(), "活动时间") || !testStrMsg($("#d4312").val(), "活动时间")) {
     return;
     }

     if ($("#fee").val() == 'true') {
     if (!testPrice($("#price").val(), "金额")) {
     return;
     }
     }

     if ($("#imgUpload").val() == null && $("#imgShow").attr("src") != '') {
     alert("上传封面！");
     return;
     }

     if (!testStrMsg($("#myEditor").val(), "内容")) {
     return;
     }

     if (!testStrMsg($("#addressDetail").val(), "地址")) {
     return;
     }*/


    var form = new FormData(document.getElementById("editForm"));
    $.ajax({
        type: 'POST',
        data: form,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        url: $ctx + "/advice/add",
        success: function (data) {
            reLoad(data);
        }
    });
}
function reLoad(data) {
    alert(data.message)
    if (data.code == '0') {
        ret();
    }
}

function ret() {
    $('#menuTitle').html("内容管理");
    $('#dashboard').load($ctx + "/views/advice_manager.jsp");
}



