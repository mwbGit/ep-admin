jQuery(document).ready(function () {
    init("HOME_PAGE");
    init("E_CARD");


});
function init(id) {
    $('#' + id).dataTable({
        // "bScrollInfinite": true,
        // "bScrollCollapse": true,
        // "sScrollY": "200px",//长200像素,
        "bPaginate": true, //是否显示（应用）分页器
        "bLengthChange": true, //开关，是否显示每页大小的下拉框
        "bSort": false,
        "bAutoWidth": true, //是否自适应宽度
        "bFilter": false, //是否启动过滤、搜索功能

        "aoColumns": [
            {
                "mDataProp": "id",
                "sTitle": "序号",
                "sDefaultContent": "",
                "sClass": "center",
                "bVisible": false //此列不显示
            }, {
                "mDataProp": "img",
                "sTitle": "图片",
                "sDefaultContent": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    return '<img style="width: 50px;height: 50px" src="' + val + '"/>';
                }
            }, {
                "mDataProp": "title",
                "sTitle": "标题",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "typeCode",
                "sTitle": "跳转详情",
                "sDefaultContent": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    if (val == "CUSTOM") {
                        return full.linkUrl;
                    } else if (val == "NONE") {
                        return "无";
                    }

                    return "活动详情"
                }
            }, {
                "mDataProp": "online",
                "sTitle": "是否显示",
                "sDefaultContent": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    if (val) {
                        return "是";
                    }
                    return '否';
                }
            }, {
                "mDataProp": "id",
                "sTitle": "排序",
                "sDefaultContent": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    var str = '';
                    if (!full.first) {
                        str += '<a href="#" onclick="modifyTypeOrderUp(' + val + ')" class="btn"><i class="icon-long-arrow-up" ></i></a>';
                    }
                    if (!full.end) {
                        str += '<a href="#" onclick="modifyTypeOrderDown(' + val + ')" class="btn"><i class=" icon-long-arrow-down" ></i></a>';
                    }
                    return str;
                }
            }, {
                "mDataProp": "updatedByName",
                "sTitle": "操作人",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "updateDate",
                "sTitle": "操作时间",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "online",
                "sTitle": "操作",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    var str = '';

                    str += '&nbsp&nbsp<a href="#add-config" data-toggle="modal"' +
                        ' onclick="initResource(\'' + full.typeCode + '\',\'' + full.img + '\',\'' + full.linkUrl + '\',\''
                        + full.title + '\',' + full.activityId + ',' + full.id + ')">' +
                        '<span  data-toggle="tooltip"  title="编辑" ><i class="icon-edit"></i></span></a>&nbsp';

                    if (!val) {
                        str += '<a href="javascript:;"  onclick="publish(' + full.id + ')">' +
                            '<span   data-toggle="tooltip"  title="发布" > ' +
                            '<i class="icon-bullhorn" ></i></span></a> &nbsp';
                    }

                    str += '<a href="javascript:;"  onclick="deleteBanner(' + full.id + ')">' +
                        '<span   data-toggle="tooltip"  title="删除">' +
                        '<i class=" icon-remove" ></i></span></a>';

                    return str;
                }
            }],
        // set the initial value
        "iDisplayLength": 5,
        "aLengthMenu": [
            [5, 10, 20, 50],
            [5, 10, 20, 50] // change per page values here
        ],
        "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
        "sPaginationType": "bootstrap",
        // "bInfo": false, //是否显示页脚信息，DataTables插件左下角显示记录数

        "oLanguage": {
            "sSearch": "姓名",
            "sProcessing": "正在加载中......",
            "sZeroRecords": "对不起，查询不到相关数据！",
            "sInfo": "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
            "sLengthMenu": "显示 _MENU_ 条",
            "sInfoEmpty": "记录数为0",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上一页",
                "sNext": "下一页",
                "sLast": "末页"
            }
        },
        "bProcessing": true,
        "bServerSide": true,
        "sAjaxSource": $ctx + "/banner/list?positionCode=" + id,
        "fnServerData": function (sSource, aDataSet, fnCallback, oSettings) {
            oSettings.jqXHR = $.ajax({
                dataType: 'json',
                type: "POST",
                //contentType:'application/json',
                url: sSource,
                data: aDataSet,
                success: fnCallback
            });
        }
    });
    initResource();
    $("#typeCode").change(function () {
        var params = $(this).val();
        if (params == 'CUSTOM') {
            $("#linkUrlGroup").show();
            $("#activityId").hide();
        } else if (params == 'ACTIVITY') {
            $("#linkUrlGroup").hide();
            $("#activityId").show();
        } else {
            $("#linkUrlGroup").hide();
            $("#activityId").hide();
        }
    });
}
function add() {
    $("#reset").click();
    $("#linkUrlGroup").hide();
    $("#imgShow").attr("src", "");

}

function initResource(typeCode, img, url, title, activityId, id) {
    if (typeCode != null) {
        $("#positionGroup").hide();
        $("#bannerId").val(id);
        $("#title").val(title);
        $("#imgShow").attr("src", img);
        if (typeCode == 'CUSTOM') {
            $("#linkUrl").val(url);
            $("#linkUrlGroup").show();
            $("#activityId").hide();
        } else if (typeCode == 'ACTIVITY') {
            $("#linkUrlGroup").hide();
            $("#activityId").show();
        } else {
            $("#linkUrlGroup").hide();
            $("#activityId").hide();
        }
    } else {
        $("#positionGroup").show();
    }

    var trs = "";
    $.each(resourceList.bannerTypes, function (n, value) {
        trs += '<option ';
        if (typeCode != null && value.value == typeCode) {
            trs += ' selected ';
        }
        trs += 'value=' + value.value + '>' + value.label + '</option>';
    });

    $("#typeCode").html(trs);

    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        contentType: 'application/json',
        url: $ctx + "/resource/activity/list",
        // data: {},
        success: function (data) {
            var trs = '';
            $.each(data.data, function (n, value) {
                trs += '<option ';
                if (activityId != null && value.value == activityId && typeCode == 'ACTIVITY') {
                    trs += ' selected ';
                }
                trs += '  value=' + value.value + '>' + value.label + '</>';
            });

            $("#activityId").html(trs);
        }
    });

}

function bannerSub() {
    var form = new FormData(document.getElementById("bannerForm"));

    var url = "add";
    var id = $("#bannerId").val();

    if (id != null && id != '') {
        url = "modify";
    }

    if ($("#typeCode").val() == "CUSTOM") {
        if (!testStrMsg($("#linkUrl").val(), "链接地址")) {
            return;
        }
    }

    if (!testStrMsg($("#title").val(), "标题")) {
        return;
    }

    if ($("#imgUpload").val() == '' && $("#imgShow").attr("src") == '') {
        alert("上传封面！");
        return;
    }


    $.ajax({
        type: 'POST',
        data: form,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        url: $ctx + "/banner/" + url,
        success: function (data) {
            reLoad(data);
        }
    });
}

function reLoad(data) {
    alert(data.message)
    if (data.code == '0') {
        $('.close').click();
        $('#dashboard').load($ctx + "/views/banner_manager.jsp");
    }
}

function deleteBanner(id) {
    if (window.confirm("确定删除？")) {

        $.ajax({
            dataType: 'json',
            async: false,
            url: $ctx + "/banner/delete?id=" + id,
            // data: "{}",
            success: function (data) {
                reLoad(data);
            }
        });
    }
}

function publish(id) {
    if (window.confirm("确定前台显示？")) {
        $.ajax({
            dataType: 'json',
            // type: "POST",
            async: false,
            // contentType:'application/json',
            url: $ctx + "/banner/publish?id=" + id,
            // data: data,
            success: function (data) {
                reLoad(data);
            }
        });

    }
}

function modifyTypeOrderUp(id) {
    modifyType(id, null, true);
}

function modifyTypeOrderDown(id) {
    modifyType(id, null, false);
}

function modifyType(id, name, asc) {
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        // contentType:'application/json',
        url: $ctx + "/banner/modify/sequence?id=" + id + "&asc=" + asc,
        // data: data,
        success: function (data) {
            reLoad(data);
        }
    });

}