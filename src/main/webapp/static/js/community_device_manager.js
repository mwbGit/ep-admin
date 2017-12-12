jQuery(document).ready(function () {
    $('#community_device_manager').dataTable({
        // "bScrollInfinite": true,
        // "bScrollCollapse": true,
        // "sScrollY": "200px",//长200像素,
        "bPaginate": true, //是否显示（应用）分页器
        "bLengthChange": true, //开关，是否显示每页大小的下拉框
        "bSort": false,
        "bAutoWidth" : true, //是否自适应宽度
        "bFilter" : false, //是否启动过滤、搜索功能

        "aoColumns": [
            {
                "mDataProp": "sequence",
                "sTitle": "序号",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "name",
                "sTitle": "名称",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "img",
                "sTitle": "Icon",
                "sDefaultContent": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    return "<img src='" + val + "' style='width: 40px;height: 40px'/>";
                }
            }, {
                "mDataProp": "first",
                "sTitle": "排序",
                "sDefaultContent": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    var str = '';
                    if (!full.first) {
                        str += '<a href="#" onclick="modifyDeviceOrderUp(' + full.id + ')" class="btn"><i class="icon-long-arrow-up" ></i></a>';
                    }
                    if (!full.end) {
                        str += '<a href="#" onclick="modifyDeviceOrderDown(' + full.id + ')" class="btn"><i class=" icon-long-arrow-down" ></i></a>';
                    }
                    return str;
                }
            },  {
                "mDataProp": "id",
                "sTitle": "操作",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    return '<a class="edit config" href="#modify-config"' +
                        'data-toggle="modal" onclick="modifyDevice(' + full.id + ',\'' + full.name + '\')"> ' +
                        '<i class="icon-edit"></i>' +
                        '</a><span>&nbsp&nbsp </span><a class="delete" onclick="deleteDevice(' + val + ')"' +
                        ' href="javascript:;" class="btn mini black"><i class="icon-trash"></i></a>';
                }
            }],
        // set the initial value
        "iDisplayLength": 10,
        "aLengthMenu": [
            [5, 10, 20, 50],
            [5, 10, 20, 50] // change per page values here
        ],
        "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
        "sPaginationType": "bootstrap",
        // "bInfo": false, //是否显示页脚信息，DataTables插件左下角显示记录数

        "oLanguage": {
            "sSearch" : "姓名",
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
        "sAjaxSource": $ctx + "/community/device/list",
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

});

function modifyDevice(id, name) {
    $("#deviceId").val(id);
    $("#deviceName").val(name);

}

function addDeviceBut() {
    if (!testStrMsg($("#addDeviceImgName").val(), "名称")) {
        return;
    } else if ($("#addDeviceImgName").val().length > 32) {
        alert("名称过长")
        return;
    }

    if ($("#imgUpload").val() == '') {
        alert("上传Icon！");
        return;
    }

    var form = new FormData(document.getElementById("addDeviceFrom"));

    $.ajax({
        type: 'POST',
        data: form,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        url: $ctx +"/community/device/add",
        success: function (data) {
            reLoad(data);
        }
    });
}

function reLoad(data) {
    alert(data.message)
    if (data.code == '0') {
        $('.close').click();
        $('#dashboard').load( $ctx +"/views/community_device_manager.jsp");
    }
}

function deleteDevice(id) {
    if (window.confirm("确定移除？")) {
        $.ajax({
            dataType: 'json',
            async: false,
            url: $ctx + "/community/device/delete?id=" + id,
            // data: "{}",
            success: function (data) {
                reLoad(data);
            }
        });
    }
}

function modifyDeviceOrderUp(id) {
    modifyType(id, true);
}

function modifyDeviceOrderDown(id) {
    modifyType(id, false);
}

function modifyType(id, asc) {
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        // contentType:'application/json',
        url: $ctx + "/community/device/modify/order?id=" + id + "&asc=" + asc,
        // data: data,
        success: function (data) {
            reLoad(data);
        }
    });
}

function modifyDeviceBut() {
    if (!testStrMsg($("#deviceName").val(), "名称")) {
        return;
    } else if ($("#deviceName").val().length > 32) {
        alert("名称过长")
        return;
    }

    var form = new FormData(document.getElementById("modifyDeviceFrom"));

    $.ajax({
        type: 'POST',
        data: form,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        url: $ctx +"/community/device/modify",
        success: function (data) {
            reLoad(data);
        }
    });
}