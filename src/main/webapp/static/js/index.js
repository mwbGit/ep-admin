
$(function () {

});


function loadJsp(item) {
    $('#showAddItem').hide();

    if (item == "analysis") {
        // $('#analysis').attr("class", "active");

        $('#dashboard').load("/views/data_analysis.jsp");
        $('#menuTitle').html("数据分析");
    } else if (item == "dimension") {
        $('#dashboard').load("/views/table_basic.jsp");
        $('#menuTitle').html("吐槽管理");
        $('#showAddItem').show();
    } else if (item == "user") {
        $('#dashboard').load("/views/user_manager.jsp");
        $('#menuTitle').html("用户列表");
    }

}