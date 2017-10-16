<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
    var activity_id = "${param.id}";
</script>


<div class="portlet box light-grey">

	<div class="portlet-title">

		<div class="caption"><i class="icon-globe"></i>报名列表</div>
		<div class="actions">

			<div class="btn-group">

				<a href="#" id="user_excel_id"><button class="btn" onclick="userExcel()">
					导出
				</button>
				</a>

				<div id="sample_2_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">

				</div>

			</div>

		</div>

	</div>

	<div class="portlet-body">

		<table class="table table-striped table-bordered table-hover table-full-width" id="activity_user_table">

		</table>
	</div>

</div>


<script src="${ctx }/static/js/activity_user_table.js"></script>
