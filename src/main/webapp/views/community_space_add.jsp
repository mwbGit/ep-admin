<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link rel="stylesheet" type="text/css" href="${ctx }/static/media/css/multi-select-metro.css"/>

<script type="text/javascript">
    var community_id = "${param.id}";
    var type = "${param.type}";
    var space_id = "${param.spaceId}";
</script>

<div class="row-fluid">

    <div class="span12">

        <!-- BEGIN SAMPLE FORM PORTLET-->

        <div class="portlet box blue tabbable">

            <div class="portlet-title">

                <div class="caption">

                    <i class="icon-reorder"></i>

                    <span class="hidden-480" id="titleSpan">添加</span>

                </div>

            </div>

            <div class="portlet-body form">

                <form id="form_community_space_add"
                      action="" class="form-horizontal" method="post" enctype="multipart/form-data">
                    <br>
                    <div class="control-group">
                        <label class="control-label">名称：</label>

                        <div class="controls">

                            <input type="hidden" class="m-wrap medium" name="id" id="id"/>
                            <input type="hidden" class="m-wrap medium" name="type" id="typeId"/>
                            <input type="hidden" class="m-wrap medium" name="communityId" id="communityId"/>
                            <input type="text" class="m-wrap medium" name="name" id="name"/>

                        </div>

                    </div>


                    <div class="control-group">

                        <label class="control-label">位置：</label>

                        <div class="controls">

                            <input type="text" class="m-wrap medium" name="position" id="position"/>
                        </div>
                    </div>

                    <div class="control-group">

                        <label class="control-label">容纳人数：</label>

                        <div class="controls">

                            <input type="text" class="m-wrap medium" name="capacity" id="capacity"/>
                            <span class="help-inline">(人)</span>
                        </div>
                    </div>

                    <div class="control-group">

                        <label class="control-label">计费标准：</label>

                        <div class="controls">

                            <input type="text" class="m-wrap medium" name="amount" id="amount"/>
                            <span class="help-inline">(元/小时)</span>
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
                                     style="max-width: 200px; max-height: 150px; line-height: 20px;">
                                </div>
                                <div>

									<span class="btn btn-file"><span
                                            class="fileupload-new">选择</span>

									<span class="fileupload-exists">更改</span>

									<input type="file" class="default" name="imgUpload" id="imgUpload"/></span>

                                </div>

                            </div>

                        </div>

                    </div>


                    <div class="control-group">

                        <label class="control-label">设施：</label>

                        <div class="controls">

                            <select multiple="multiple" id="device_list" name="devices">
                            </select>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button type="button" class="btn blue"  onclick="preservation()">保存</button>
                        <button type="button" class="btn blue"  onclick="ret()">返回</button>
                    </div>
                </form>
            </div>
        </div>

        <!-- END SAMPLE FORM PORTLET-->

    </div>
</div>


<script type="text/javascript" src="${ctx }/static/media/js/bootstrap-timepicker.js"></script>

<script type="text/javascript" src="${ctx }/static/media/js/jquery.inputmask.bundle.min.js"></script>

<script type="text/javascript" src="${ctx }/static/media/js/jquery.input-ip-address-control-1.0.min.js"></script>
<script type="text/javascript" src="${ctx }/static/media/js/jquery.multi-select.js"></script>
<script type="text/javascript" src="${ctx }/static/js/community_space_add.js"></script>

