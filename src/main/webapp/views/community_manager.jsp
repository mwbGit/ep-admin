<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />



<div class="portlet box blue">

	<div class="portlet-title">

		<div class="caption"><i class="icon-globe"></i>社区管理</div>

	</div>

	<div class="portlet-body">

		<table class="table table-striped table-bordered table-hover table-full-width" id="community_manager">

		</table>
	</div>

</div>


<script src="${ctx }/static/js/community_manager.js"></script>
