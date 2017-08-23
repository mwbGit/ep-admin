jQuery(document).ready(function () {
    // initiate layout and plugins
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        contentType: 'application/json',
        url: $ctx + "/complain/item/list",
        // data: data,
        success: function (data) {
            if (data.empty) {
                return;
            }
            $.each(data.data, function (n, value) {
                init(n, value.id, value.name, value.ratio);
            });
        }
    });

});

function testNumber(number) {
    var re = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
    if (number== null || number =='' || !re.test(number)) {
        alert("非法数字！");
        return false;
    }

    return true;
}

function testStr(itemName) {
    if (itemName== null || itemName ==''){
        alert("输入不正确！");
        return;
    }
    return true;
}

function modifyItem(id) {
    var itemId = $('#item-id' + id).val();
    var ratio = $('#service-ratio' + id).text();
    var item = $('#service-item' + id).text();
    $('#itemId').val(itemId);
    $('#itemName').val(item);
    $('#ratio').val(ratio);
    addItem();
}
function reLoad() {
    $('.close').click();
    $('#dashboard').load("/views/table_basic.jsp");
}
function modifyItemSub() {
    var  ratio = $("#ratio").val();
    var  itemName = $("#itemName").val().trim();
    if (!testNumber(ratio) || !testStr(itemName)){
        return;
    }

    var data = $("#itemModify").serialize();
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        // contentType:'application/json',
        url: $ctx + "/complain/item/modify",
        data: data,
        success: function (data) {
            alert("成功")
            reLoad();
        }
    });
}

function deleteDimension(id) {

    if (window.confirm("确定删除？")) {
        $.ajax({
            dataType: 'json',
            type: "POST",
            async: false,
            // contentType:'application/json',
            url: $ctx + "/complain/dimension/delete?id=" + id,
            // data: data,
            success: function (data) {
                alert("成功");
                reLoad();
            }
        });
    } else {
        return false;
    }
}

function deleteItem(id) {
    // return false;
    if (window.confirm("确定删除此服务项？")) {
        $.ajax({
            dataType: 'json',
            type: "POST",
            async: false,
            // contentType:'application/json',
            url: $ctx + "/complain/item/delete?id=" + id,
            // data: data,
            success: function (data) {
                alert("成功");
                reLoad();
            }
        });
    } else {
        return false;
    }
}

function modifyDimension(id, name, ratio) {
    $('#dimensionId').val(id);
    $('#dimensionName').val(name);
    $('#dimensionRatio').val(ratio);
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        contentType: 'application/json',
        url: "/resource/list",
        // data: {},
        success: function (data) {
            var trs = "";
            $.each(data.dimensions, function (n, value) {
                trs += '<option value=' + value.value + '>' + value.label + '</option>';
            });

            $(".dimensionTypes").html(trs);
        }
    });
}

function addDimension(itemId) {
    $('#addItemId').val(itemId);
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        contentType: 'application/json',
        url: "/resource/list",
        // data: {},
        success: function (data) {
            var trs = "";
            $.each(data.dimensions, function (n, value) {
                trs += '<option value=' + value.value + '>' + value.label + '</option>';
            });

            $(".dimensionTypes").html(trs);
        }
    });
}
function addItemSub() {
    var  ratio = $("#addItemRatio").val();
    var  itemName = $("#addItemName").val().trim();
    if (!testNumber(ratio) || !testStr(itemName)){
        return;
    }

    var data = $("#addItemFrom").serialize();
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        // contentType:'application/json',
        url: $ctx + "/complain/item/add",
        data: data,
        success: function (data) {
            alert("成功")
            reLoad();
        }
    });
}

function addDimensionSub() {
    var  ratio = $("#addDimensionRatio").val();
    var  itemName = $("#addDimensionName").val().trim();
    if (!testNumber(ratio) || !testStr(itemName)){
        return;
    }
    var data = $("#addDimensionFrom").serialize();
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        // contentType:'application/json',
        url: $ctx + "/complain/dimension/add",
        data: data,
        success: function (data) {
            alert("成功")
            reLoad();
        }
    });
}

function modifyDimensionSub() {
    var  ratio = $("#dimensionRatio").val();
    var  itemName = $("#dimensionName").val().trim();
    if (!testNumber(ratio) || !testStr(itemName)){
        return;
    }
    var data = $("#modifyDimensionFrom").serialize();
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        // contentType:'application/json',
        url: $ctx + "/complain/dimension/modify",
        data: data,
        success: function (data) {
            alert("成功");
            reLoad();
        }
    });
}


function addItem() {
    return;
    // $.ajax({
    //     dataType: 'json',
    //     type: "POST",
    //     async: false,
    //     contentType: 'application/json',
    //     url: "/resource/list",
    //     // data: {},
    //     success: function (data) {
    //         var trs = "";
    //         $.each(data.items, function (n, value) {
    //             trs += '<option value=' + value.value + '>' + value.label + '</option>';
    //         });
    //
    //         $(".itemTypes").html(trs);
    //     }
    // });
}

function init(id, itemId, itemName, itemRatio) {
    var initContent = '';
    if ((id % 2) == 0) {
        initContent += '<div class="row-fluid" id="row-fluid' + id + '">';
    }
    initContent += '<div class="span6" id="aa"><div class="portlet box red"><div class="portlet-title">' +
        '<div class="caption"><i class="icon-cogs"></i><input type="hidden" id="item-id' + id + '" value="' + itemId + '">' +
        '<span id="service-item' + id + '"> ' + itemName + '</span>(<span id="service-ratio' + id + '">' + itemRatio + '</span>%)' +
        '</div><div class="tools"><input type="hidden" value="true" id="collapse_hidden' + id + '">' +
        '<a href="javascript:;" class="collapse" onclick="collapse1(' + id + ')"></a>' +
        '<a href="#add-config" style="color: white" data-toggle="modal"  onclick="addDimension(' + itemId + ')"><i style="color: white" class="icon-plus"></i></a>' +
        '<a href="#portlet-config" data-toggle="modal" class="config" onclick="modifyItem(' + id + ')"></a>' +
        '<a href="javascript:;" class="remove" onclick="deleteItem(' + itemId + ')"></a></div></div>' +
        '<div id="tablesDiv' + id + '"></div    ></div></div>';

    if ((id % 2) == 0) {
        initContent += '</div>';
        $('#show-content').append(initContent);
    } else {
        $('#row-fluid' + (id - 1)).append(initContent);
    }

    $('#tablesDiv' + id).html('<div class="portlet-body"><table class="table table-hover" id="table' + id + '"></table> </div>');

    $('#table' + id).dataTable({
        "bScrollInfinite": true,
        "bScrollCollapse": true,
        "bProcessing": false,
        "sScrollY": "200px",//长200像素,
        //"sScrollX": "2000px",
        "bPaginate": false, //是否显示（应用）分页器
        "bFilter": false, //是否启动过滤、搜索功能
        "bInfo": false, //是否显示页脚信息，DataTables插件左下角显示记录数
        //"bJQueryUI": true, //是否显示页脚信息，DataTables插件左下角显示记录数

        "aoColumns": [
            //    {
            //    "mDataProp": "code",
            //    "sDefaultContent": "", //此列默认值为""，以防数据中没有此值，DataTables加载数据的时候报错
            //    "bVisible": false //此列不显示
            //},
            {
                "mDataProp": "orderId",
                "sTitle": "序号",
                "sDefaultContent": "",
                "sClass": "center"
                //"sWidth": "20%"
            }, {
                "mDataProp": "name",
                "sTitle": "维度名称",
                "sDefaultContent": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    return full.name;
                }
            }, {
                "mDataProp": "ratio",
                "sTitle": "所占为例",
                "sDefaultContent": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    return full.ratio + "%";
                }
            }, {
                "mDataProp": "type",
                "sTitle": "所属类别",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "projectType",
                "sTitle": "操作",
                "sDefaultContent": "",
                "sClass": "center",
                "mRender": function (val, data, full) {
                    return '<a class="edit config" href="#dimension-config"' +
                        'data-toggle="modal" onclick="modifyDimension(' + full.id + ',\'' + full.name + '\',' + full.ratio + ')"> ' +
                        '<i class="icon-edit"></i>' +
                        '</a><span>&nbsp&nbsp </span><a class="delete" onclick="deleteDimension(' + full.id + ')"' +
                        ' href="javascript:;" class="btn mini black"><i class="icon-trash"></i></a>';
                }
            }],
        // set the initial value
        "sDom": '<"top"ifl<"clear">>rt<"bottom"ilp<"clear">><"span6"pi>',
        "bServerSide": true,
        "sAjaxSource": $ctx + "/complain/dimension/list?itemId=" + itemId,
        //"fnRowCallback": function (nRow, aData, iDisplayIndex) {
        //    ///* 用来改写用户权限的 */
        //    //if (aData.ISADMIN == '1')
        //    //    $('td:eq(5)', nRow).html('管理员');
        //    //if (aData.ISADMIN == '2')
        //    //    $('td:eq(5)', nRow).html('资料下载');
        //    //if (aData.ISADMIN == '3')
        //    //    $('td:eq(5)', nRow).html('一般用户');
        //
        //    //alert(aData);
        //    return nRow;
        //},
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
        "fnServerData": function (sSource, aDataSet, fnCallback, oSettings) {
            $.ajax({
                dataType: 'json',
                async: false,
                type: "POST",
                //contentType:'application/json',
                url: sSource,
                data: aDataSet,
                success: fnCallback
            });
        }
    });

}

function collapse1(id) {
    var bool = $('#collapse_hidden' + id).val();
    if (bool == 'true') {
        $('#collapse_hidden' + id).val(false);
        $('#tablesDiv' + id).hide();
    } else {
        $('#collapse_hidden' + id).val(true);
        $('#tablesDiv' + id).show();
    }
}


