jQuery(document).ready(function () {
    // initiate layout and plugins


    init("1", true, 1, "生理需求", 20);
    init("2", false, 2, "安全需求", 40);
    init("12", true, 2, "安全需求", 40);

});

function modifyItem(id) {
    var itemId = $('#item-id' + id).val();
    var ratio = $('#service-ratio' + id).text();
    var item = $('#service-item' + id).text();
    $('#itemId').val(itemId);
    $('#itemName').val(item);
    $('#ratio').val(ratio);
    addItem();
}

function deleteDimension(id) {

    if (window.confirm("确定删除？")) {
        alert("成功")
    }
}

function deleteItem(id) {

    if (window.confirm("确定删除此服务项？")) {
        alert("成功")
    }
}

function modifyDimension(id, name, ratio) {
    $('#dimensionId').val(id);
    $('#dimensionName').val(name);
    $('#dimensionRatio').val(ratio);
    $.ajax({
        dataType: 'json',
        type: "POST",
        contentType:'application/json',
        url: "/resource/list",
        // data: {},
        success: function (data) {
            var trs = "";
            $.each(data.dimensions, function (n, value) {
                trs += '<option value='+value.value +'>' +value.label + '</option>';
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
        contentType:'application/json',
        url: "/resource/list",
        // data: {},
        success: function (data) {
            var trs = "";
            $.each(data.dimensions, function (n, value) {
                trs += '<option value='+value.value +'>' +value.label + '</option>';
            });

            $(".dimensionTypes").html(trs);
        }
    });
}
function addItem() {
    $.ajax({
        dataType: 'json',
        type: "POST",
        contentType:'application/json',
        url: "/resource/list",
        // data: {},
        success: function (data) {
            var trs = "";
            $.each(data.items, function (n, value) {
                trs += '<option value='+value.value +'>' +value.label + '</option>';
            });

            $(".itemTypes").html(trs);
        }
    });
}

function init(id, two, itemId, itemName, itemRatio) {
    var initContent = '';
    if (two) {
        initContent += '<div class="row-fluid" id="row-fluid' + id + '">';
    }
    initContent += '<div class="span6"><div class="portlet box red"><div class="portlet-title">' +
        '<div class="caption"><i class="icon-cogs"></i><input type="hidden" id="item-id' + id + '" value="' + itemId + '">' +
        '<span id="service-item' + id + '"> ' + itemName + '</span>(<span id="service-ratio' + id + '">' + itemRatio + '</span>%)' +
        '</div><div class="tools"><input type="hidden" value="true" id="collapse_hidden' + id + '">' +
        '<a href="javascript:;" class="collapse" onclick="collapse1(' + id + ')"></a>' +
        '<a href="#add-config" data-toggle="modal"  onclick="addDimension(' + itemId + ')"><i class="icon-plus"></i></a>' +
        '&nbsp<i class="icon-plus" onclick="addDimension(' + itemId + ')"></i>' +
        '<a href="#portlet-config" data-toggle="modal" class="config" onclick="modifyItem(' + id + ')"></a>' +
        '<a href="javascript:;" class="remove" onclick="deleteItem(' + itemId + ')"></a></div></div>' +
        '<div id="tablesDiv' + id + '"></div></div></div>';

    if (two) {
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
                "mDataProp": "id",
                "sTitle": "序号",
                "sDefaultContent": "",
                "sClass": "center",
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
                "sClass": "center"
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
        "sAjaxSource": "/complain/dimension/list",
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
        "fnServerData": function (sSource, aDataSet, fnCallback, oSettings) {
            $.ajax({
                dataType: 'json',
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


