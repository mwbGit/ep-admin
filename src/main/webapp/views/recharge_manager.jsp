<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />



<div class="portlet box blue">

	<div class="portlet-title">

		<div class="caption"><i class="icon-globe"></i>充值管理</div>
		<div class="actions">

			<div class="btn-group">

				<a href="#add-config" data-toggle="modal">
					<button class="btn">
						添加
					</button>
				</a>

				<div id="sample_2_column_toggler"
					 class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">

				</div>

			</div>
		</div>
	</div>

	<div class="portlet-body">

		<table class="table table-striped table-bordered table-hover table-full-width" id="recharge_manager">

		</table>
	</div>

</div>

<div id="modify-config" class="modal hide">

	<div class="modal-header">

		<button data-dismiss="modal" class="close" type="button"></button>

		<h3>修改分类</h3>

	</div>

	<div class="modal-body">

		<form method="post" class="form-horizontal" id="modifyRechargeFrom">
			<input type="hidden" id="rechargeId" name="id"/>

			<div class="control-group">
				<label class="control-label">充值金额：</label>
				<div class="controls">
					<input type="text" class="m-wrap medium" id="money" name="money" />
				</div>
				<label class="control-label">售价：</label>
				<div class="controls">
					<input type="text" class="m-wrap medium" id="price" name="price" />
				</div>
			</div>
			<div class="form-actions">

				<button type="button" class="btn blue" onclick="modifyRechargeConfig()"><i class="icon-ok"></i> 提交</button>

				<button type="reset" class="btn">重置</button>

			</div>
		</form>

	</div>

</div>


<div id="add-config" class="modal hide">

	<div class="modal-header">

		<button data-dismiss="modal" class="close" type="button"></button>

		<h3>添加</h3>

	</div>

	<div class="modal-body">

		<form method="post" class="form-horizontal" id="addFrom">

			<div class="control-group">
				<label class="control-label">充值金额：</label>
				<div class="controls">
					<input type="text" class="m-wrap medium" name="money"/>

				</div>
				<label class="control-label">售价：</label>
				<div class="controls">
					<input type="text" class="m-wrap medium" name="price"/>

				</div>
			</div>
			<div class="form-actions">

				<button type="button" class="btn blue" onclick="addRechargeConfig()"><i class="icon-ok"></i> 提交
				</button>

				<button type="reset" class="btn">重置</button>

			</div>
		</form>

	</div>

</div>

<script src="${ctx }/static/js/recharge_manager.js"></script>
