jQuery(document).ready(function () {
    $('#community_manager').dataTable({
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
                "mDataProp": "name",
                "sTitle": "社区名称",
                "sDefaultContent": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    var str = '';

                    str += '<a href="javascript:;" onclick="editCommunitySpace(' + full.id + ')">' + val + '</a>';

                    return str;
                }
            }, {
                "mDataProp": "tag",
                "sTitle": "标签",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "stationTotal",
                "sTitle": "总工位数量",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "rentNum",
                "sTitle": "租赁工位数量",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "surplusNum",
                "sTitle": "剩余工位数量",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "meetingNum",
                "sTitle": "会议室数量",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "activityNum",
                "sTitle": "活动场所数量",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "updatedByName",
                "sTitle": "操作员",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "online",
                "sTitle": "操作",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    var str = '';

                    str += '&nbsp&nbsp<a href="javascript:;" onclick="editCommunity(' + full.id + ')">' +
                        '<span  data-toggle="tooltip"  title="编辑" ><i class="icon-edit"></i></span></a>&nbsp';

                    if (val == 'N') {
                        str += '<a href="javascript:;"  onclick="publish(' + full.id + ')">' +
                            '<span   data-toggle="tooltip"  title="发布" > ' +
                            '<i class="icon-bullhorn" ></i></span></a> &nbsp';
                    }

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
            "sSearch": "标题",
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
        "sAjaxSource": $ctx + "/community/list",
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
function publish(id) {
    if (window.confirm("确定发布？")) {
        $.ajax({
            dataType: 'json',
            type: "POST",
            async: false,
            contentType: 'application/json',
            url: $ctx + "/community/publish?id=" + id,
            // data: {},
            success: function (data) {
                reLoad(data);
            }
        });
    }
}

function reLoad(data) {
    alert(data.message)
    if (data.code == '0') {
        $('.close').click();
        $('#dashboard').load($ctx + "/views/community_manager.jsp");
    }
}
function editCommunity(id) {
    var p = {
        id: id
    };

    $('#dashboard').load($ctx + "/views/community_add.jsp", p);
}

function editCommunitySpace(id) {
    var p = {
        id: id
    };

    $('#dashboard').load($ctx + "/views/community_space_manager.jsp", p);
}

