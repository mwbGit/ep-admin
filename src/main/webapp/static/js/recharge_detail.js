jQuery(document).ready(function () {
    $('#recharge_detail').dataTable({
        // "bScrollInfinite": true,
        // "bScrollCollapse": true,
        // "sScrollY": "200px",//长200像素,
        "bPaginate": true, //是否显示（应用）分页器
        "bLengthChange": true, //开关，是否显示每页大小的下拉框
        "bSort": false,
        "bAutoWidth" : true, //是否自适应宽度
        "bFilter" : true, //是否启动过滤、搜索功能

        "aoColumns": [
            {
                "mDataProp": "id",
                "sTitle": "序号",
                "sDefaultContent": "",
                "sClass": "center",
                "bVisible": true //此列不显示
            }, {
                "mDataProp": "userName",
                "sTitle": "用户名",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "tel",
                "sTitle": "手机号",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "rechargeAmount",
                "sTitle": "金额",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "price",
                "sTitle": "售价",
                "sDefaultContent": "",
                "sClass": "center"
            },{
                "mDataProp": "date",
                "sTitle": "日期",
                "sDefaultContent": "",
                "sClass": "center"
            },{
                "mDataProp": "payStatus",
                "sTitle": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    var str = '';
                    if (full.payStatus == '0') {
                        str = '待支付';
                    }else if (full.payStatus == '1') {
                        str = '已取消';
                    }else if (full.payStatus == '2') {
                        str = '已支付';
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
            "sSearch" : "标题",
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
        "sAjaxSource": $ctx + "/recharge/list",
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

function reLoad(data) {
    alert(data.message)
    if (data.code == '0') {
        $('.close').click();
        $('#dashboard').load($ctx + "/views/recharge_detail.jsp");
    }
}
