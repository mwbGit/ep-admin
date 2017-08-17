var TableEditable = function () {

    return {

        //main function to initiate the module
        init: function () {
            function restoreRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);

                for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                    oTable.fnUpdate(aData[i], nRow, i, true);
                }

                oTable.fnDraw();
            }

            function editRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                debugger;
                //jqTds[0].innerHTML = '<input type="text" class="m-wrap small" value="' + aData[0] + '">';
                jqTds[1].innerHTML = '<input type="text" class="m-wrap small" value="' + aData.name + '">';
                jqTds[2].innerHTML = '<input type="text" class="m-wrap small" value="' + aData.ratio + '"> %';
                jqTds[3].innerHTML = '<input type="text" class="m-wrap small" value="' + aData.type + '">';
                jqTds[4].innerHTML = '<a class="edit" href="">保存</a> <a class="cancel" href="">取消</a>';
                //jqTds[5].innerHTML = '<a class="cancel" href="">Cancel</a>';
            }

            function saveRow(oTable, nRow) {
                var jqInputs = $('input', nRow);
                debugger
                //oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[0].value, nRow, 1, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 2, false);
                oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
                oTable.fnUpdate('<a class="edit" href="">编辑</a><a class="delete" href="">删除</a>', nRow, 4, false);
                //oTable.fnUpdate('<a class="delete" href="">删除</a>', nRow, 5, false);
                oTable.fnDraw();
            }

            function cancelEditRow(oTable, nRow) {
                var jqInputs = $('input', nRow);
                oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
                oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
                oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 4, false);
                oTable.fnDraw();
            }

            var oTable = $('#sample_editable_1').dataTable({
                "bScrollInfinite": true,
                "bScrollCollapse": true,
                "sScrollY": "200px",//长200像素,
                "bPaginate": false, //是否显示（应用）分页器

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
                        "sClass": "center"
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
                        "sTitle": "编辑",
                        "sDefaultContent": "",
                        "sClass": "center",
                        "mRender": function (val, data, full) {
                            return '<a class="edit" href="javascript:;">编辑  </a><a class="delete" href="javascript:;">  删除</a>';
                        }
                    }],
                // set the initial value
                "iDisplayLength": 5,
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                "bInfo": false, //是否显示页脚信息，DataTables插件左下角显示记录数
                "bFilter": false, //是否启动过滤、搜索功能

                "oLanguage": {
                    "sInfo": "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
                    "sLengthMenu": "显示 _MENU_ 条",
                    "sInfoEmpty": "记录数为0",
                    "oPaginate": {
                        "sPrevious": "Prev",
                        "sNext": "Next"
                    }
                },
                "aoColumnDefs": [{
                    'bSortable': false,
                    'aTargets': [0,1,2,3,4]
                }
                ],
                "bProcessing": true,
                "bServerSide": true,
                "sAjaxSource": "/dimension/list",
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
                    $.ajax({
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

            jQuery('#sample_editable_1_wrapper .dataTables_filter input').addClass("m-wrap medium"); // modify table search input
            jQuery('#sample_editable_1_wrapper .dataTables_length select').addClass("m-wrap small"); // modify table per page dropdown
            jQuery('#sample_editable_1_wrapper .dataTables_length select').select2({
                showSearchInput: false //hide search box with special css class
            }); // initialzie select2 dropdown

            var nEditing = null;

            $('#sample_editable_1_new').click(function (e) {
                e.preventDefault();
                var aiNew = oTable.fnAddData(['', '', '', '',
                    '<a class="edit" href="">Edit</a>', '<a class="cancel" data-mode="new" href="">Cancel</a>'
                ]);
                var nRow = oTable.fnGetNodes(aiNew[0]);
                editRow(oTable, nRow);
                nEditing = nRow;
            });

            $('#sample_editable_1 a.delete').live('click', function (e) {
                e.preventDefault();

                if (confirm("确定删除?<a href='index.js'> sdddddddddddd</a>>") == false) {

                    return;
                }

                $(this).parents('tr')[0].remove();
                //oTable.fnDeleteRow(nRow);
                alert("Deleted! Do not forget to do some ajax to sync with backend :)");
            });

            $('#sample_editable_1 a.cancel').live('click', function (e) {
                e.preventDefault();
                if ($(this).attr("data-mode") == "new") {
                    var nRow = $(this).parents('tr')[0];
                    oTable.fnDeleteRow(nRow);
                } else {
                    restoreRow(oTable, nEditing);
                    nEditing = null;
                }
            });

            $('#sample_editable_1 a.edit').live('click', function (e) {
                e.preventDefault();

                /* Get the row as a parent of the link that was clicked on */
                var nRow = $(this).parents('tr')[0];

                debugger;
                if (nEditing !== null && nEditing != nRow) {
                    /* Currently editing - but not this row - restore the old before continuing to edit mode */
                    restoreRow(oTable, nEditing);
                    editRow(oTable, nRow);
                    nEditing = nRow;
                } else if (nEditing == nRow && this.innerHTML == "保存") {
                    /* Editing this row and want to save it */
                    saveRow(oTable, nEditing);
                    nEditing = null;
                    alert("Updated! Do not forget to do some ajax to sync with backend :)");
                } else {
                    /* No edit in progress - let's start one */
                    editRow(oTable, nRow);
                    nEditing = nRow;
                }
            });
        }

    };

}();