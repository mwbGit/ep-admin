<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />



<div class="portlet box light-grey">

	<div class="portlet-title">

		<div class="caption"><i class="icon-globe"></i>活动管理</div>

	</div>

	<div class="portlet-body">

		<table class="table table-striped table-bordered table-hover table-full-width" id="activity_manager">

		</table>
	</div>

</div>


<script src="${ctx }/static/js/activity_manager.js"></script>
