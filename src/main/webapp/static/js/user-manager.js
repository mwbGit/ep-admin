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
                "mDataProp": "spaceNames",
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
                "mDataProp": "deleted",
                "sTitle": "操作",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    var str = '';

                    if (val) {
                        str += '<a href="#"  onclick="deleteUser('+full.id +')">' +
                            '<span  style="color: red"  data-toggle="tooltip"  title="用户已被禁用,点击启用" > ' +
                            '<i class="icon-warning-sign" style="width: 50px;height: 50px"></i></span></a> ';
                    } else {

                    str += '<a href="#"  onclick="deleteUser('+full.id +')"><span  style="color: blue"  data-toggle="tooltip"  title="点击禁用" > ' +
                        '<i class="icon-warning-sign" style="width: 50px;height: 50px"></i></span></a> ';
                    }

                    str +='&nbsp&nbsp<i class="icon-edit" style="width: 50px;height: 50px"></i></span>';

                    return str;
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
        "bProcessing": true,
        "bServerSide": true,
        "sAjaxSource": $ctx + "/user/list",
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

function modifyUserBut() {
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        contentType: 'application/json',
        url: "/resource/space/list",
        // data: {},
        success: function (data) {
            var trs = "";
            $.each(data.data, function (n, value) {
                trs += '<option value=' + value.value + '>' + value.label + '</option>';
            });

            $(".dimensionTypes").html(trs);
        }
    });
}

function reLoad() {
    $('.close').click();
    $('#dashboard').load("/views/user_manager.jsp");
}

function deleteUser(id) {
    $.ajax({
        dataType: 'json',
        async: false,
        url: $ctx + "/user/delete?id=" + id,
        // data: "{}",
        success: function (data) {
            alert("成功");
            reLoad();
        }
    });
}