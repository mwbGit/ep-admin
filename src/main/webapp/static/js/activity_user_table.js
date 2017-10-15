jQuery(document).ready(function () {
    $('#activity_user_table').dataTable({
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
                "mDataProp": "orderId",
                "sTitle": "序号",
                "sDefaultContent": "",
                "sClass": "center",
                "bVisible": false //此列不显示
            }, {
                "mDataProp": "userSpaces",
                "sTitle": "所属园区",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "company",
                "sTitle": "公司名称",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "name",
                "sTitle": "用户名",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "sex",
                "sTitle": "性别",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "mobile",
                "sTitle": "手机号",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "createDate",
                "sTitle": "报名时间",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "price",
                "sTitle": "报名费用",
                "sDefaultContent": "",
                "sClass": "center"
            }],
        // set the initial value
        "iDisplayLength": 20,
        "aLengthMenu": [
            [5, 10, 20, 50],
            [5, 10, 20, 50] // change per page values here
        ],
        "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
        "sPaginationType": "bootstrap",
        // "bInfo": false, //是否显示页脚信息，DataTables插件左下角显示记录数

        "oLanguage": {
            "sSearch" : "用户名",
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
        "sAjaxSource": $ctx + "/activity/user/list?activityId=" + activity_id,
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

function userExcel() {
    $('#user_excel_id').attr("href", $ctx + "/activity/user/list/excel?activityId=" + activity_id);
}