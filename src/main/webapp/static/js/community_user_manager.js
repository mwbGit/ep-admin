jQuery(document).ready(function () {
    $('#community_user_manager').dataTable({
        // "bScrollInfinite": true,
        // "bScrollCollapse": true,
        // "sScrollY": "200px",//长200像素,
        "bPaginate": true, //是否显示（应用）分页器
        "bLengthChange": true, //开关，是否显示每页大小的下拉框
        "bSort": false,
        "bAutoWidth": true, //是否自适应宽度
        "bFilter": true, //是否启动过滤、搜索功能

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
                "mDataProp": "img",
                "sTitle": "头像",
                "sDefaultContent": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    return "<img src='" + val + "' style='width: 40px;height: 40px'/>";
                }
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
                "sTitle": "是否禁用",
                "sDefaultContent": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    if (val) {
                        return "是";
                    } else {
                        return "否";
                    }
                }
            }, {
                "mDataProp": "id",
                "sTitle": "操作",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    var str = '';
                    str += '<a href="javascript:;"  onclick="deleteUserManager(' + val + ')">' +
                        '<span  data-toggle="tooltip"  title="移除">' +
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

function addUserBut(curPage) {
    var iDisplayLength = 2;
    var lastStart = $("#lastStart").val();
    if (curPage == 0) {
        lastStart = 0;
    }
    var iDisplayStart = parseInt(lastStart) + (iDisplayLength * curPage);

    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        // contentType: 'application/json',
        url: $ctx + "/user/list?managed=false&iDisplayLength=" + iDisplayLength + "&iDisplayStart=" + iDisplayStart,
        // data: {},
        success: function (data) {
            $("#lastStart").val(iDisplayStart);

            var str = '';
            if (data.totalCount == 0) {
                str += '无可添加用户！';
                $("#per").hide();
                $("#next").hide();

            } else {
                $.each(data.aaData, function (n, value) {
                    str += '<tr><td> <img src="' + value.img + '" style="width: 40px;height: 40px"/></td>' +
                        '<td>' + value.name + '</td> <td>' + value.mobile + '</td>' +
                        '<td><button class="btn blue" onclick="modifyUserManager(' + value.id + ')" >选择</button></td>' +
                        '</tr>';
                });

                str += '<tr><td></td><td colspan="2" style="text-align: center"><button id="per" class="btn" onclick="addUserBut(-1)" >上一页</button>' +
                    '<button id="next" class="btn" onclick="addUserBut(1)" >下一页</button></td>' +
                    '<td></td></tr>';

                $("#showUsers").html(str);
                if (data.totalCount > iDisplayStart + iDisplayLength) {
                    $("#next").show();
                } else {
                    $("#next").hide();
                }
                if (iDisplayStart >= iDisplayLength) {
                    $("#per").show();
                } else {
                    $("#per").hide();
                }
            }
        }
    });
}

function reLoad(data) {
    alert(data.message)
    if (data.code == '0') {
        $('.close').click();
        $('#dashboard').load($ctx + "/views/community_user_manager.jsp");
    }
}

function deleteUserManager(id) {
    if (window.confirm("确定移除？")) {
        $.ajax({
            dataType: 'json',
            async: false,
            url: $ctx + "/user/modify/managed?id=" + id + "&managed=false",
            // data: "{}",
            success: function (data) {
                reLoad(data);
            }
        });
    }
}

function modifyUserManager(id) {
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        url: $ctx + "/user/modify/managed?id=" + id + "&managed=true",
        success: function (data) {
            reLoad(data);
        }
    });

}