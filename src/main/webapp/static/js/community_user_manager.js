jQuery(document).ready(function () {
    $('#community_user_manager').dataTable({
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
                "bVisible": false //此列不显示
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
            }, {
                "mDataProp": "company",
                "sTitle": "公司",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "spaceNames",
                "sTitle": "入住空间",
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
                "mDataProp": "deleted",
                "sTitle": "操作",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    var str = '';

                    if (val) {
                        str += '<a href="#"  onclick="deleteUser(' + full.id + ')">' +
                            '<span  style="color: red"  data-toggle="tooltip"  title="用户已被禁用,点击启用" > ' +
                            '<i class="icon-warning-sign" style="width: 50px;height: 50px"></i></span></a> ';
                    } else {

                        str += '<a href="#"  onclick="deleteUserManager(' + full.id + ')"><span  style="color: blue"  data-toggle="tooltip"  title="点击禁用" > ' +
                            '<i class="icon-warning-sign" style="width: 50px;height: 50px"></i></span></a> ';
                    }

                    str += '&nbsp&nbsp<span><a href="#add-config" data-toggle="modal"  onclick="addUserBut(' + full.id + ')"><i class="icon-edit" style="width: 50px;height: 50px"></i></span></a>';

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
        "sAjaxSource": $ctx + "/user/list?managed=true",
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

function addUserBut(id) {
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        // contentType: 'application/json',
        url: $ctx +"/user/list?managed=false",
        // data: {},
        success: function (data) {
            var str = '';
            $.each(data.data, function (n, value) {
                str += '<tr><td>' + value.name + '</td>' +
                    '<td><button onclick="modifyUserManager(' + value.id + ')"></button></td>' +
                    '</tr>';
            });

            $("#showUsers").html(str);
        }
    });
}

function reLoad(data) {
    alert(data.message)
    if (data.code == '0') {
        $('.close').click();
        $('#dashboard').load( $ctx +"/views/community_user_manager.jsp");
    }
}

function deleteUserManager(id) {
    $.ajax({
        dataType: 'json',
        async: false,
        url: $ctx + "/user/modify/managed?id=" + id +"&managed=false",
        // data: "{}",
        success: function (data) {
            reLoad(data);
        }
    });
}

function modifyUserManager(id) {
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        // contentType:'application/json',
        url: $ctx + "/user/modify/managed?id=" + id +"&managed=true",
        data: data,
        success: function (data) {
            reLoad(data);
        }
    });

}