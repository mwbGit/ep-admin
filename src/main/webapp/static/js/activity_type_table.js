jQuery(document).ready(function () {
    $('#type_table').dataTable({
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
                        str += '<a href="#" onclick="modifyTypeOrderUp('+ id+')" class="btn"><i class="icon-long-arrow-up" ></i></a>';
                    }
                    if (!full.end) {
                        str += '<a href="#" onclick="modifyTypeOrderDown('+ id+ ')" class="btn"><i class=" icon-long-arrow-down" ></i></a>';
                    }
                    return str;
                }
            }, {
                "mDataProp": "first",
                "sTitle": "操作",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    var str = '';
                    return str;

                    if (val) {
                        str += '<a href="#"  onclick="deleteUser(' + full.id + ')">' +
                            '<span  style="color: red"  data-toggle="tooltip"  title="用户已被禁用,点击启用" > ' +
                            '<i class="icon-warning-sign" style="width: 50px;height: 50px"></i></span></a> ';
                    } else {

                        str += '<a href="#"  onclick="deleteUser(' + full.id + ')"><span  style="color: blue"  data-toggle="tooltip"  title="点击禁用" > ' +
                            '<i class="icon-warning-sign" style="width: 50px;height: 50px"></i></span></a> ';
                    }

                    str += '&nbsp&nbsp<span><a href="#userModify" data-toggle="modal"  onclick="modifyUserBut(' +full.id + ')"><i class="icon-edit" style="width: 50px;height: 50px"></i></span></a>';

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

function modifyUserBut(id) {
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        contentType: 'application/json',
        url: "/resource/space/list",
        // data: {},
        success: function (data) {
            var str = '';
            $.each(data.data, function (n, value) {
                str += '<div class="checkbox line">' +
                    '<input type="checkbox" name="spaceIds" value="' + value.value + '"> ' + value.label + '</div>';
            });

            $("#userId").val(id);
            $("#spaces").html(str);
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
            url: $ctx + "/activity/type/modify?id="+ id + "&name=" + name + "&asc=" + asc,
            data: data,
            success: function (data) {
                alert("成功");
                reLoad();
            }
        });

}