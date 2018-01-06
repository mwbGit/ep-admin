var  typeId;
var  addressId;
var  addressDetailId;
var showFee = true;
jQuery(document).ready(function () {

    if (activity_id != null && activity_id != '') {
        $('#activityId').val(activity_id);
        $('#menuTitle').html("修改活动");
        $('#titleSpan').html("修改活动");
        detail(activity_id)

    }

    showFeeSelect();

    // initiate layout and plugins
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
                trs += '<option value=' + value.value;

                if (typeId != null && typeId  == value.value){
                    trs += ' selected ';
                }

                trs +=  '>' + value.label + '</option>';

            });

            $("#activityType").html(trs);
        }
    });

    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        contentType: 'application/json',
        url: $ctx + "/resource/address/list",
        // data: {},
        success: function (data) {
            var trs = "";
            $.each(data.data, function (n, value) {
                trs += '<option value=' + value.value

                if (addressId != null && addressId  == value.value){
                    trs += ' selected ';
                }
                trs += '>' + value.label + '</option>';

                if (n == 0) {
                    changeAddress(value.value)
                }
            });

            $("#address").html(trs);
        }
    });

    //select改变事件
    $("#address").change(function () {
        addressDetailId = null;
        addressId = null;
        changeAddress($(this).val());
    });

    $("#fee").change(function () {
        var params = $(this).val();
        if (params == 'false') {
            $("#price").val('');
            $("#price").attr("disabled", 'true');
        } else {
            $("#price").removeAttr("disabled");
        }
    });
});

function detail(id) {
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        contentType: 'application/json',
        url: $ctx + "/activity/detail?id=" + id,
        // data: {},
        success: function (data) {
            $("#title").val(data.title);
            $("#limit").val(data.limit);
            $("#d4311").val(data.startTime);
            $("#d4312").val(data.endTime);
            $("#price").val(data.price);
            $("#myEditor").val(data.content);
            $("#imgShow").attr("src", data.img);

            typeId = data.typeId;
            addressId = data.addressId;
            addressDetailId = data.addressDetailId;
            if (data.price == null) {
                showFee = false;
            }
        }
    });
}

function showFeeSelect() {

    var str = ' <option value="true">是</option> <option value="false" ';
    if (!showFee) {
        str += ' selected ';
        $("#price").attr("disabled", 'true');
    }
    str += '>否</option>';

    $("#fee").html(str);

};

function changeAddress(id) {
    if (addressId != null){
        id = addressId;
    }

    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        contentType: 'application/json',
        url: $ctx + "/resource/address/detail/list?addressId=" + id,
        // data: {},
        success: function (data) {
            var trs = "";
            $.each(data.data, function (n, value) {
                trs += '<option value=' + value.value;

                if (addressDetailId != null && addressDetailId  == value.value){
                    trs += ' selected ';
                }
                trs += '>' + value.label + '</option>';
            });

            $("#addressDetail").html(trs);
        }
    });
}

function validateFromSub() {
    if (!testStrMsg($("#title").val(), "标题")) {
        return;
    } else if ($("#title").val().length > 32) {
        alert("标题过长")
        return;
    }

    if (!testStrMsg($("#d4311").val(), "活动时间") || !testStrMsg($("#d4312").val(), "活动时间")) {
        return;
    }

    if ($("#fee").val() == 'true') {
        if (!testPrice($("#price").val(), "金额")) {
            return;
        }
    }

    if ($("#imgUpload").val() == '' && $("#imgShow").attr("src") == '') {
        alert("上传封面！");
        return;
    }


    if (!testStrMsg($("#myEditor").val(), "内容")) {
        return;
    }

    if (!testStrMsg($("#addressDetail").val(), "地址")) {
        return;
    }

    var form = new FormData(document.getElementById("form_activity_add"));

    $.ajax({
        type: 'POST',
        data: form,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        url: $ctx + "/activity/add",
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
    $('#menuTitle').html("活动管理");
    $('#dashboard').load($ctx + "/views/activity_manager.jsp");
}