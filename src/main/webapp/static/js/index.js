
$(function () {

});


function loadJsp() {
    //$('#_zscq').attr("class", "active");
    $('#dashboard').load("/views/table_basic.jsp");
    $('#menuTitle').html("吐槽管理");
    // $('#showAddItem').html("添加服务项");
    $('#showAddItem').show();

}function loadJsp1() {
    $('#showAddItem').hide();
    $('#dashboard').load("/views/data_analysis.jsp");
    $('#menuTitle').html("数据分析");
}