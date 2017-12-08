jQuery(document).ready(function () {
    $('#recharge_manager').dataTable({
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
                "bVisible": true //此列不显示?
            }, {
                "mDataProp": "money",
                "sTitle": "充值金额",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "price",
                "sTitle": "售价",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "name",
                "sTitle": "操作员",
                "sDefaultContent": "",
                "sClass": "center"
            }, {
                "mDataProp": "lastUpdate",
                "sTitle": "操作时间",
                "sDefaultContent": "",
                "sClass": "center"
            },{
                "mDataProp": "id",
                "sTitle": "操作",
                "sClass": "center",
                "mRender": function (val,data, full) {
                    return '&nbsp&nbsp<a class="edit config" href="#modify-config" data-toggle="modal" onclick="editRechargeConfig('+ full.id +' , '+ full.money+' ,'+full.price+ ')">' +
                        '<span  data-toggle="tooltip"  title="编辑" ><i class="icon-edit"></i></span></a>&nbsp'+
                        '<a href="javascript:;"  onclick="deleteRechargeConfig(' + full.id + ')">' +
                        '<span   data-toggle="tooltip"  title="删除">'+
                        '<i class=" icon-remove" ></i></span></a>';
                }
            }],
        // set the initial value
        "iDisplayLength": 10,
        "aLengthMenu": [
            [5,5, 10, 20, 50],
            [5,5, 10, 20, 50] // change per page values here
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
        "sAjaxSource": $ctx + "/recharge/config/list",
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
function addRechargeConfig() {
    var data = getFormJson("addFrom");
    data = JSON.stringify(data);
    alert(data);
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        contentType: 'application/json',
        url: $ctx +"/recharge/config/add",
        data: data,
        success: function (data) {
            reLoad(data);
        }
    });
}

function deleteRechargeConfig(id) {
    if (window.confirm("确定删除？")) {
        $.ajax({
            dataType: 'json',
            type: "POST",
            async: false,
            contentType: 'application/json',
            url: $ctx + "/recharge/config/delete?id=" + id,
            // data: {},
            success: function (data) {
                reLoad(data);
            }
        });
    }
}

function editRechargeConfig(id,money,price) {
    $("#rechargeId").val(id);
    $("#money").val(money);
    $("#price").val(price);
}

function modifyRechargeConfig() {
    var data = getFormJson("modifyRechargeFrom");
    data = JSON.stringify(data);
    $.ajax({
        dataType: 'json',
        type: "POST",
        async: false,
        contentType: 'application/json',
        url: $ctx +"/recharge/config/update",
        data: data,
        success: function (data) {
            reLoad(data);
        }
    });
}
function reLoad(data) {
    alert(data.message)
    if (data.code == '0') {
        $('.close').click();
        $('#dashboard').load($ctx + "/views/recharge_manager.jsp");
    }
}



function getFormJson(frm) {  //frm：form表单的id
    var o = {};
    var a = $("#"+frm).serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}