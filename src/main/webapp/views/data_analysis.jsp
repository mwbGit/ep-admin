<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />



<div class="portlet box blue">

	<div class="portlet-title">

		<div class="caption"><i class="icon-globe"></i>吐槽列表</div>

		<div class="actions">

			<div class="btn-group">

				<a href="${ctx}/complain/queryComplainForExcel" ><button class="btn">
					导出
				</button>
				</a>

				<div id="sample_2_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">

				</div>

			</div>

		</div>

	</div>

	<div class="portlet-body">

		<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">

		</table>
	</div>

</div>



<script src="${ctx }/static/js/data_analysis.js"></script>
