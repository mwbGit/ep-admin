jQuery(document).ready(function () {

    $('#sample_2').dataTable({
        // "bScrollInfinite": true,
        // "bScrollCollapse": true,
        // "sScrollY": "200px",//长200像素,
        "bPaginate": true, //是否显示（应用）分页器
        "bLengthChange": true, //开关，是否显示每页大小的下拉框

        "aoColumns": [
            {
                "mDataProp": "id",
                "sTitle": "序号",
                "sDefaultContent": "",
                "sClass": "center",
                "bVisible": false //此列不显示
            }, {
                "mDataProp": "name",
                "sTitle": "服务项",
                "sDefaultContent": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    return full.name;
                }
            }, {
                "mDataProp": "dimensionName",
                "sTitle": "维度名称",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "dimensionType",
                "sTitle": "所属类别",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "grade",
                "sTitle": "评分",
                "sDefaultContent": "",
                "sClass": "center"
            },{
                "mDataProp": "content",
                "sTitle": "吐槽内容",
                "sDefaultContent": "",
                "sClass": "center"
            },{
                "mDataProp": "img",
                "sTitle": "图片",
                "sDefaultContent": "",
                "sClass": "center"
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
        "aoColumnDefs": [{
            'bSortable': false,
            'aTargets': [0, 1, 2, 3, 4]
        }
        ],
        "bProcessing": true,
        "bServerSide": true,
        "sAjaxSource": "/complain/list",
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