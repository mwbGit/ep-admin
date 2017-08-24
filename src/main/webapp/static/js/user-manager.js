jQuery(document).ready(function () {
    $('#user_manager').dataTable({
        // "bScrollInfinite": true,
        // "bScrollCollapse": true,
        // "sScrollY": "200px",//长200像素,
        "bPaginate": true, //是否显示（应用）分页器
        "bLengthChange": true, //开关，是否显示每页大小的下拉框
        "bSort": false,

        "aoColumns": [
            {
                "mDataProp": "id",
                "sTitle": "序号",
                "sDefaultContent": "",
                "sClass": "center",
                "bVisible": false //此列不显示
            }, {
                "mDataProp": "img",
                "sTitle": "头像",
                "sDefaultContent": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    return '<img style="width: 50px;height: 50px" src="' + val + '"/>';
                }
            }, {
                "mDataProp": "name",
                "sTitle": "姓名",
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
            },{
                "mDataProp": "mobile",
                "sTitle": "入住空间",
                "sDefaultContent": "",
                "sClass": "center"
            },{
                "mDataProp": "updatedByName",
                "sTitle": "操作人",
                "sDefaultContent": "",
                "sClass": "center"
            },{
                "mDataProp": "updateDate",
                "sTitle": "操作时间",
                "sDefaultContent": "",
                "sClass": "center"
            },{
                "mDataProp": "updateDate",
                "sTitle": "操作",
                "sDefaultContent": "aaa",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    return '操作';
                }
            }],
        // set the initial value
        "iDisplayLength": 5,
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
        // "aoColumnDefs": [{
        //     'bSortable': false,
        //     'aTargets': [0, 1, 2, 3, 4,5,6]
        // }
        // ],
        "bProcessing": true,
        "bServerSide": true,
        "sAjaxSource": $ctx + "/user/list",
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {
            ///* 用来改写用户权限的 */
            //if (aData.ISADMIN == '1')
            //    $('td:eq(5)', nRow).html('管理员');
            //if (aData.ISADMIN == '2')
            //    $('td:eq(5)', nRow).html('资料下载');
            //if (aData.ISADMIN == '3')
            //    $('td:eq(5)', nRow).html('一般用户');

            //alert(aData);
            return nRow;
        },
        "fnServerData": function (sSource, aDataSet, fnCallback, oSettings) {
            oSettings.jqXHR = $.ajax({
                dataType: 'json',
                type: "POST",
                //contentType:'application/json',
                url: sSource,
                data: aDataSet,
                success: fnCallback
            });


            //$.post( sSource, aoData, function (json) {
            //    /* Do whatever additional processing you want on the callback, then tell DataTables */
            //    fnCallback(json)
            //} );

            //$.each(data.value, function(i,item){
            //    table.fnAddData(item);
            //});
        }
    });

});