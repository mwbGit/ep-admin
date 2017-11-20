var typeId;
jQuery(document).ready(function () {
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
        url: $ctx + "/advice/type/list",
        // data: {},
        success: function (data) {
            var trs = "";
            $.each(data.aaData, function (n, value) {
                trs += '<option value=' + value.name;

                if (typeId != null && typeId == value.id) {
                    trs += ' selected ';
                }

                trs += '>' + value.name + '</option>';

            });

            $("#activityType").html(trs);
        }
    });
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
            $("#myEditor").val(data[0].content);
            $("#miniText").val(data[0].miniText);
            //$("#myEditor").val(data.content);
            $("#imgShow").attr("src", data[0].imgs[0]);
            $("#imgShow2").attr("src", data[0].imgs[1]);
            $("#imgShow3").attr("src", data[0].imgs[2]);
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

/*
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
function validateFromSub() {
    if (!testStrMsg($("#title").val(), "标题")) {
        return;
    } else if ($("#title").val().length > 32) {
        alert("标题过长")
    }
    if (!testStrMsg($("#activityType").val(), "分类")) {
        return;
    }

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

/*function validateFromSub2() {
var type2 = "laster";
    $.ajax({
        type: 'get',

        async: false,
        cache: false,
        contentType: false,
        processData: false,
        url: $ctx + "/wx/information/list?theneid="+49+"&count="+3+"&type="+type2,
        success: function (data) {
            reLoad(data);
        }
    });
}*/
/*function validateFromSub2() {
    var type2 = "laster";
    $.ajax({
        type: 'get',

        async: false,
        cache: false,
        contentType: false,
        processData: false,
        url: $ctx + "/wx/information/topInformation",
        success: function (data) {
            reLoad(data);
        }
    });
}*/
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



