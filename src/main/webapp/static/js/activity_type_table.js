var oTable;
jQuery(document).ready(function () {
    oTable = $('#type_table').dataTable({
        // "bScrollInfinite": true,
        // "bScrollCollapse": true,
        // "sScrollY": "200px",//长200像素,
        "bPaginate": true, //是否显示（应用）分页器
        "bLengthChange": true, //开关，是否显示每页大小的下拉框
        "bSort": false,

        "aoColumns": [
            {
                "mDataProp": "id",
                "sTitle": "ID",
                "sDefaultContent": "",
                "sClass": "center",
                "bVisible": false //此列不显示
            }, {
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
                "mDataProp": "first",
                "sTitle": "排序",
                "sDefaultContent": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    var str = '';
                    if (!full.first) {
                        str += '<a href="#" onclick="modifyTypeOrderUp(' + full.id + ')" class="btn"><i class="icon-long-arrow-up" ></i></a>';
                    }
                    if (!full.end) {
                        str += '<a href="#" onclick="modifyTypeOrderDown(' + full.id + ')" class="btn"><i class=" icon-long-arrow-down" ></i></a>';
                    }
                    return str;
                }
            }, {
                "mDataProp": "id",
                "sTitle": "操作",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    return '<a class="edit config" href="#modify-config"' +
                        'data-toggle="modal" onclick="modifyTypeName(' + full.id + ',\'' + full.name + '\')"> ' +
                        '<i class="icon-edit"></i>' +
                        '</a><span>&nbsp&nbsp </span><a class="delete" onclick="deleteType(' + val + ')"' +
                        ' href="javascript:;" class="btn mini black"><i class="icon-trash"></i></a>';
                }
            }],
        // set the initial value
        "iDisplayLength": 5,
        "aLengthMenu": [
            [5, 10, 20,50],
            [5, 10, 20, 50] // change per page values here
        ],
        "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
        "sPaginationType": "bootstrap",
        // "bInfo": false, //是否显示页脚信息，DataTables插件左下角显示记录数
        "bFilter": false, //是否启动过滤、搜索功能

        "oLanguage": {
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
        "sAjaxSource": $ctx + "/activity/type/list",
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

function modifyTypeName(id, name) {
    $("#typeId").val(id);
    $("#typeName").val(name);

}
function modifyTypeNameBut() {
    var data = $("#modifyTypeFrom").serialize();
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        // contentType: 'application/json',
        url: "/activity/type/modify",
        data: data,
        success: function (data) {
            reLoad(data);
        }
    });
}

function addTypeNameBut() {
    var data = $("#addFrom").serialize();
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        // contentType: 'application/json',
        url: "/activity/type/add",
        data: data,
        success: function (data) {
            reLoad(data);
        }
    });
}

function reLoad(data) {
    alert(data.message)
    if (data.code == '0') {
        $('.close').click();
        $('#dashboard').load("/views/activity_type.jsp");
    }
}

function deleteType(id) {
    if (window.confirm("确定删除？")) {
        $.ajax({
            dataType: 'json',
            async: false,
            url: $ctx + "/activity/type/delete?id=" + id,
            // data: "{}",
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
        url: $ctx + "/activity/type/modify?id=" + id + "&name=" + name + "&asc=" + asc,
        // data: data,
        success: function (data) {
            reLoad(data);
        }
    });

}
