var typeName = 1;
jQuery(document).ready(function () {
    init("Meeting");
    init("Activity");

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
                "sTitle": "封面",
                "sDefaultContent": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    return '<img style="width: 50px;height: 50px" src="' + val + '"/>';
                }
            }, {
                "mDataProp": "name",
                "sTitle": "名称",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "capacity",
                "sTitle": "容纳人数",
                "sDefaultContent": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    return val + "人"
                }
            }, {
                "mDataProp": "amount",
                "sTitle": "计费标准",
                "sDefaultContent": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    return val + '/元/小时';
                }
            }, {
                "mDataProp": "position",
                "sTitle": "位置",
                "sDefaultContent": "",
                "sClass": "center"
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
                "mDataProp": "id",
                "sTitle": "操作",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    var str = '';

                    str += '&nbsp&nbsp<a href="#" ' +
                        ' onclick="edit(' + val + ')">' +
                        '<span  data-toggle="tooltip"  title="编辑" ><i class="icon-edit"></i></span></a>&nbsp';


                    str += '<a href="javascript:;"  onclick="deleteSpace(' + val + ')">' +
                        '<span   data-toggle="tooltip"  title="删除">' +
                        '<i class=" icon-remove" ></i></span></a>';

                    return str;
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
        "sAjaxSource": $ctx + "/community/space/list?type=" + id + "&communityId=" + community_id,
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

    // initResource();
}

function add() {
    var p = {
        id: community_id,
        type: typeName
    };

    $('#dashboard').load($ctx + "/views/community_space_add.jsp", p);
}

function edit(id) {
    var p = {
        id: community_id,
        spaceId: id,
        type: typeName
    };

    $('#dashboard').load($ctx + "/views/community_space_add.jsp", p);
}

function typeChange(type) {
    typeName = type;
    if (type == "0") {
        $('#addType').text("添加活动场所");
    } else {
        $('#addType').text("添加会议室");
    }
}

function reLoad(data) {
    alert(data.message)
    if (data.code == '0') {
        $('.close').click();

        var p = {
            id: community_id,
            type: typeName
        };

        $('#dashboard').load($ctx + "/views/community_space_manager.jsp", p);
    }
}

function deleteSpace(id) {
    if (window.confirm("确定删除？")) {

        $.ajax({
            dataType: 'json',
            async: false,
            url: $ctx + "/community/space/delete?id=" + id,
            // data: "{}",
            success: function (data) {
                reLoad(data);
            }
        });
    }
}
