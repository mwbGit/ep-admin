<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />



<div class="portlet box blue">

	<div class="portlet-title">

		<div class="caption"><i class="icon-globe"></i>社区经理管理</div>

		<div class="actions">

			<div class="btn-group">

				<a href="#add-config" data-toggle="modal" onclick="addUserBut()"><button class="btn" >
					添加
				</button>
				</a>

				<div id="sample_2_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">

				</div>

			</div>

		</div>

	</div>

	<div class="portlet-body">

		<table class="table table-striped table-bordered table-hover table-full-width" id="community_user_manager">

		</table>
	</div>

</div>

<div id="add-config" class="modal hide">

	<div class="modal-header">

		<button data-dismiss="modal" class="close" type="button"></button>

		<h3>添加社区经理</h3>

	</div>

	<div class="modal-body">

		<div class="control-group">
			<table class="table table-striped table-bordered table-advance table-hover" id="showUsers">
			</table>
		</div>


	</div>

</div>


<script src="${ctx }/static/js/community_user_manager.js"></script>
