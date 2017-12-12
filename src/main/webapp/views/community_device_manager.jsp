<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />



<div class="portlet box blue">

	<div class="portlet-title">

		<div class="caption"><i class="icon-globe"></i>社区设备管理</div>

		<div class="actions">

			<div class="btn-group">

				<a href="#add-config" data-toggle="modal"><button class="btn" >
					添加
				</button>
				</a>

				<div id="sample_2_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">

				</div>

			</div>

		</div>

	</div>

	<div class="portlet-body">

		<table class="table table-striped table-bordered table-hover table-full-width" id="community_device_manager">

		</table>
	</div>

</div>

<div id="add-config" class="modal hide">

	<div class="modal-header">

		<button data-dismiss="modal" class="close" type="button"></button>

		<h3>添加设备</h3>

	</div>

	<div class="modal-body">
		<form method="post" class="form-horizontal" id="addDeviceFrom">
			<div class="control-group">

				<label class="control-label">名称：</label>
				<div class="controls">
					<input type="text" class="m-wrap medium" name="name" id="addDeviceImgName"/>
				</div>
			</div>

			<div class="control-group">

				<label class="control-label">Icon：</label>

				<div class="controls">

					<div class="fileupload fileupload-new" data-provides="fileupload">

						<div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
						</div>

						<div class="fileupload-preview fileupload-exists thumbnail"
							 style="max-width: 200px; max-height: 150px; line-height: 20px;" >
						</div>
						<div>

							<span class="btn btn-file"><span
									class="fileupload-new">选择</span>

							<span class="fileupload-exists">更改</span>

							<input type="file" class="default" name="imgUpload" id="imgUpload"/></span>

							<a href="#" class="btn fileupload-exists" data-dismiss="fileupload">移除</a>

						</div>

					</div>

				</div>

			</div>

			<div class="form-actions">

				<button type="button" class="btn blue" onclick="addDeviceBut()"><i class="icon-ok"></i> 提交</button>

				<button type="reset" class="btn">重置</button>

			</div>
		</form>

	</div>

</div>


<div id="modify-config" class="modal hide">

	<div class="modal-header">

		<button data-dismiss="modal" class="close" type="button"></button>

		<h3>编辑设备</h3>

	</div>

	<div class="modal-body">
		<form method="post" class="form-horizontal" id="modifyDeviceFrom">
			<div class="control-group">
				<input type="hidden" id="deviceId" name="id"/>

				<label class="control-label">名称：</label>
				<div class="controls">
					<input type="text" class="m-wrap medium" id="deviceName" name="name"/>
				</div>
			</div>

			<div class="control-group">

				<label class="control-label">Icon：</label>

				<div class="controls">

					<div class="fileupload fileupload-new" data-provides="fileupload">

						<div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
						</div>

						<div class="fileupload-preview fileupload-exists thumbnail"
							 style="max-width: 200px; max-height: 150px; line-height: 20px;" >
						</div>
						<div>

							<span class="btn btn-file"><span
									class="fileupload-new">选择</span>

							<span class="fileupload-exists">更改</span>

							<input type="file" class="default" name="imgUpload"  /></span>

							<a href="#" class="btn fileupload-exists" data-dismiss="fileupload">移除</a>

						</div>

					</div>

				</div>

			</div>

			<div class="form-actions">

				<button type="button" class="btn blue" onclick="modifyDeviceBut()"><i class="icon-ok"></i> 提交</button>

				<button type="reset" class="btn">重置</button>

			</div>
		</form>

	</div>

</div>

<script src="${ctx }/static/js/community_device_manager.js"></script>
