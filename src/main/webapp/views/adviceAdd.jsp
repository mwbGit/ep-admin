<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css" href="${ctx }/static/media/css/bootstrap-fileupload.css"/>

<link href="${ctx }/static/js/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">

<script type="text/javascript">
    var activity_id = "${param.id}";
</script>
<div class="row-fluid">

	<div class="span12">

			<div class="portlet box blue tabbable">

				<div class="portlet-title">

					<div class="caption">

						<i class="icon-reorder"></i>

						<span class="hidden-480" id="titleSpan">添加资讯</span>

					</div>

				</div>

				<div class="portlet-body form">


								<!-- BEGIN FORM-->

								<form class="form-horizontal" id="editForm" method="post">
									<br>
									<br>
									<div class="control-group">

										<label class="control-label">资讯标题：</label>
										<input type="hidden" id="id" name="id">
										<div class="controls">

											<input class="m-wrap large"  id="title" name="title" type="text" >

										</div>

									</div>

									<div class="control-group">

										<label class="control-label">选择分类：</label>

										<div class="controls">

											<select class="medium m-wrap" tabindex="1" name="typeId" id="activityType">


											</select>

										</div>

									</div>

									<div class="control-group">

										<label class="control-label">资讯摘要：</label>

										<div class="controls">

											<textarea id="miniText" name="miniText" class="large m-wrap" rows="3"></textarea>

											<span id="a" class="help-inline"></span>

										</div>

									</div>

									<div class="control-group">

										<label class="control-label">封面图片：</label>
										<div class="controls">

											<div class="fileupload fileupload-new" data-provides="fileupload" style="float: left">

												<div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
													<img src="" id="imgShow" style="width: 200px; height: 150px;">
												</div>

												<div class="fileupload-preview fileupload-exists thumbnail"
													 style="max-width: 200px; max-height: 150px; line-height: 20px;" >
												</div>
												<div>

												<span class="btn btn-file"><span
														class="fileupload-new">选择</span>

												<span class="fileupload-exists">更改</span>

												<input type="file" class="default" name="imgUpload" id="imgUpload"/></span>


												</div>

											</div>

										</div>

										<div class="controls">

											<div class="fileupload fileupload-new" data-provides="fileupload" style="float: left">

												<div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
													<img src="" id="imgShow2" style="width: 200px; height: 150px;">
												</div>

												<div class="fileupload-preview fileupload-exists thumbnail"
													 style="max-width: 200px; max-height: 150px; line-height: 20px;" >
												</div>
												<div>

												<span class="btn btn-file"><span
														class="fileupload-new">选择</span>

												<span class="fileupload-exists">更改</span>

												<input type="file" class="default" name="imgUpload2" id="imgUpload2"/></span>


												</div>

											</div>

										</div>

										<div class="controls">

											<div class="fileupload fileupload-new" data-provides="fileupload" style="float: left">

												<div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
													<img src="" id="imgShow3" style="width: 200px; height: 150px;">
												</div>

												<div class="fileupload-preview fileupload-exists thumbnail"
													 style="max-width: 200px; max-height: 150px; line-height: 20px;" >
												</div>
												<div>

												<span class="btn btn-file"><span
														class="fileupload-new">选择</span>

												<span class="fileupload-exists">更改</span>

												<input type="file" class="default" name="imgUpload3" id="imgUpload3"/></span>


												</div>

											</div>

										</div>

									</div>

									<div class="control-group">

										<label class="control-label">资讯内容：</label>

										<div class="controls">

											 <textarea name="content" type="text/plain"
													   id="myEditor" style="height: 240px;width:724px"></textarea>

										</div>

									</div>

									<div class="form-actions">

										<button type="button" class="btn blue"  type="submit" onclick="validateFromSub()"><i class="icon-ok"></i> 保存</button>
										<button class="btn" type="button" onclick="ret()">取消</button>

									</div>

								</form>

								<!-- END FORM-->



				</div>

			</div>

	</div>

</div>


<script type="text/javascript" src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/static/js/umeditor/third-party/template.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/static/js/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/static/js/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="${ctx }/static/js/umeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="${ctx }/static/js/advice_add.js"></script>

<script type="text/javascript">
    //实例化编辑器
    var um2 = UM.getEditor('myEditor');
</script>
<%--
<script>

  $("#saveId").click(function () {
      var data = $("#editForm").serialize();
      $.ajax({
          dataType: 'json',
          type: "POST",
          async: false,
          // contentType:'application/json',
          url: $ctx + "/advice/list",
          data: data,
          success: function (data) {
              alert("成功");
              reLoad();
          }
      });
  })

	  function reLoad() {
		  $('.close').click();
		  $('#dashboard').load("/views/user_manager.jsp");
	  }
</script>--%>
