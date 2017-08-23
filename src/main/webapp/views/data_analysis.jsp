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


<div class="page-content">

	<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

	<div id="portlet-config" class="modal hide">

		<div class="modal-header">

			<button data-dismiss="modal" class="close" type="button"></button>

			<h3>修改维度</h3>

		</div>

		<div class="modal-body">

			<form action="#" class="form-horizontal">

				<div class="control-group warning">

					<label class="control-label" for="inputWarning">Input with warning</label>

					<div class="controls">

						<input type="text" class="span6 m-wrap" id="inputWarning" />

						<span class="help-inline">Something may have gone wrong</span>

					</div>

				</div>

				<div class="control-group error">

					<label class="control-label" for="inputError">Input with error</label>

					<div class="controls">

						<input type="text" class="span6 m-wrap" id="inputError" />

						<span class="help-inline">Please correct the error</span>

					</div>

				</div>

				<div class="control-group success">

					<label class="control-label" for="inputSuccess">Input with success</label>

					<div class="controls">

						<input type="text" class="span6 m-wrap" id="inputSuccess" />

						<span class="help-inline ok"></span>

					</div>

				</div>

				<div class="control-group warning">

					<label class="control-label">Input with warning</label>

					<div class="controls input-icon">

						<input type="text" class="span6 m-wrap" />

											<span class="input-warning tooltips" data-original-title="please write a valid email">

											<i class="icon-warning-sign"></i>

											</span>

					</div>

				</div>

				<div class="control-group error">

					<label class="control-label">Input with error</label>

					<div class="controls input-icon">

						<input type="text" class="span6 m-wrap" />

											<span class="input-error tooltips" data-original-title="please write a valid email">

											<i class="icon-exclamation-sign"></i>

											</span>

					</div>

				</div>

				<div class="control-group success">

					<label class="control-label">Input with success</label>

					<div class="controls input-icon">

						<input type="text" class="span6 m-wrap" />

											<span class="input-success tooltips" data-original-title="Success input!">

											<i class="icon-ok"></i>

											</span>

					</div>

				</div>

				<div class="form-actions">

					<button type="submit" class="btn blue">Save</button>

					<button type="button" class="btn">Cancel</button>

				</div>

			</form>

		</div>

	</div>

	<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

	<!-- BEGIN PAGE CONTAINER-->


</div>

<script src="${ctx }/static/js/data_analysis.js"></script>
