<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link href="${ctx }/static/js/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">

<script type="text/javascript">
    var activity_id = "${param.id}";
</script>

<div class="row-fluid">

    <div class="span12">

        <!-- BEGIN SAMPLE FORM PORTLET-->

        <div class="portlet box blue tabbable">

            <div class="portlet-title">

                <div class="caption">

                    <i class="icon-reorder"></i>

                    <span class="hidden-480" id="titleSpan">添加社区</span>

                </div>

            </div>

            <div class="portlet-body form">

                <form id="form_activity_add"
                      action="" class="form-horizontal" method="post" enctype="multipart/form-data" >
                    <br>
                    <div class="control-group">
                        <input type="hidden" id="communityId" name="id">
                        <label class="control-label">社区名称：</label>

                        <div class="controls">

                            <input type="text" class="m-wrap medium" name="name" id="name"/>

                        </div>

                    </div>


                    <div class="control-group">

                        <label class="control-label">标签：</label>

                        <div class="controls">

                            <select class="medium m-wrap" tabindex="1" name="tag" id="tag">

                            </select>

                        </div>
                    </div>


                    <div class="control-group">

                        <label class="control-label">封面图片：</label>

                        <div class="controls">

                            <div class="fileupload fileupload-new" data-provides="fileupload" style="float: left">

                                <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
                                    <img src="" id="imgShow1" style="width: 200px; height: 150px;">
                                </div>

                                <div class="fileupload-preview fileupload-exists thumbnail"
                                     style="max-width: 200px; max-height: 150px; line-height: 20px;" >
                                </div>
                                <div>

									<span class="btn btn-file"><span
                                            class="fileupload-new">选择</span>

									<span class="fileupload-exists">更改</span>

									<input type="file" class="default" name="imgUpload" id="imgUpload1"/></span>

                                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload">移除</a>

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

									<input type="file" class="default" name="imgUpload" id="imgUpload2"/></span>

                                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload">移除</a>

                                </div>

                            </div>

                        </div>

                    </div>

                    <div class="control-group">

                        <div class="controls">
                            <textarea name="content" type="text/plain"
                                      id="myEditor" style="height: 240px;width:724px"></textarea>

                        </div>

                    </div>


                    <div class="form-actions">

                        <button type="button" class="btn blue" onclick="validateFromSub()"><i class="icon-ok"></i> 提交</button>

                        <button type="button" class="btn blue" onclick="ret()">返回</button>

                    </div>

                </form>


            </div>

        </div>

        <!-- END SAMPLE FORM PORTLET-->

    </div>

</div>



<script type="text/javascript" src="${ctx }/static/js/umeditor/third-party/template.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/static/js/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/static/js/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="${ctx }/static/js/umeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="media/js/jquery.multi-select.js"></script>

<script type="text/javascript" src="${ctx }/static/js/activity_add.js"></script>

<script type="text/javascript">
    //实例化编辑器
    var um = UM.getEditor('myEditor');
</script>
