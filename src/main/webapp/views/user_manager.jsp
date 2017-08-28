<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />



<div class="portlet box blue">

	<div class="portlet-title">

		<div class="caption"><i class="icon-globe"></i>用户列表</div>

	</div>

	<div class="portlet-body">

		<table class="table table-striped table-bordered table-hover table-full-width" id="user_manager">

		</table>
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

<script src="${ctx }/static/js/user-manager.js"></script>
