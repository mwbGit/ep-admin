<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="row-fluid">

<div class="span6">

	<div class="portlet box light-grey">

		<div class="portlet-title">

			<div class="caption"><i class="icon-globe"></i>分类管理</div>

		</div>

		<div class="portlet-body">
			<table class="table table-bordered table-hover" id="type_table">

			</table>
		</div>

	</div>
</div>
</div>

<div class="page-content">
	<div id="userModify" class="modal hide">

		<div class="modal-header">

			<button data-dismiss="modal" class="close" type="button"></button>

			<h3>修改入住空间</h3>

		</div>

		<div class="modal-body">

			<form method="post" class="form-horizontal" id="modifyUserFrom">
				<input type="hidden" id="userId" name="id"/>

				<div class="controls" id="spaces">

				</div>
				<div class="form-actions">

					<button type="button" class="btn blue" onclick="modifyUser()"><i class="icon-ok"></i> 提交</button>

					<button type="reset" class="btn">重置</button>

				</div>
			</form>

		</div>

	</div>


</div>

<script src="${ctx }/static/js/activity_type_table.js"></script>
