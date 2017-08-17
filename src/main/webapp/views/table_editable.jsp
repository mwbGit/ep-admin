<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css" href="${ctx }/static/media/css/select2_metro.css" />

<link rel="stylesheet" href="${ctx }/static/media/css/DT_bootstrap.css" />

<!-- END PAGE LEVEL STYLES -->

<link rel="shortcut icon" href="${ctx }/static/media/image/favicon.ico" />

<div class="row-fluid">

	<div class="span12">

		<!-- BEGIN EXAMPLE TABLE PORTLET-->

		<div class="portlet box blue">

			<div class="portlet-title">

				<div class="caption"><i class="icon-edit"></i><span >生理需求</span></div>

				<div class="tools">

					<a href="javascript:;" class="collapse"></a>

					<a href="#portlet-config" data-toggle="modal" class="config"></a>

					<%--<a href="javascript:;" >  <i class="icon-plus">+</i></a>--%>

					<a href="javascript:;" class="remove"></a>

				</div>

			</div>

			<div class="portlet-body">

				<div class="clearfix">

					<div class="btn-group">

						<button id="sample_editable_1_new" class="btn green">

							Add New <i class="icon-plus"></i>

						</button>

					</div>

					<div class="btn-group pull-right">

						<button class="btn dropdown-toggle" data-toggle="dropdown">Tools <i class="icon-angle-down"></i>

						</button>

						<ul class="dropdown-menu pull-right">

							<li><a href="#">Print</a></li>

							<li><a href="#">Save as PDF</a></li>

							<li><a href="#">Export to Excel</a></li>

						</ul>

					</div>

				</div>

				<table class="table table-striped table-hover table-bordered" id="sample_editable_1">



				</table>

			</div>

		</div>

		<!-- END EXAMPLE TABLE PORTLET-->

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

<script type="text/javascript" src="${ctx }/static/media/js/select2.min.js"></script>

<script type="text/javascript" src="${ctx }/static/media/js/jquery.dataTables.js"></script>

<script type="text/javascript" src="${ctx }/static/media/js/DT_bootstrap.js"></script>

<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->

<script src="${ctx }/static/media/js/app.js"></script>

<script src="${ctx }/static/js/table-editable.js"></script>
<script>

	jQuery(document).ready(function() {


		TableEditable.init();

	});

</script>