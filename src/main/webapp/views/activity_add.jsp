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

                    <span class="hidden-480" id="titleSpan">添加活动</span>

                </div>

            </div>

            <div class="portlet-body form">

                <form id="form_activity_add"
                      action="${ctx }/activity/add" class="form-horizontal" method="post" enctype="multipart/form-data" >
                    <br>
                    <div class="control-group">
                        <input type="hidden" id="activityId" name="id">
                        <label class="control-label">活动标题：</label>

                        <div class="controls">

                            <input type="text" class="m-wrap medium" name="title" id="title"/>

                            <span class="help-inline">0/32</span>

                        </div>

                    </div>


                    <div class="control-group">

                        <label class="control-label">活动分类：</label>

                        <div class="controls">

                            <select class="medium m-wrap" tabindex="1" name="typeId" id="activityType">


                            </select>

                        </div>
                    </div>

                    <div class="control-group">

                        <label class="control-label">开始时间：</label>

                        <div class="controls">

                            <input id="d4311" name="startTime" class="Wdate" type="text" onclick="WdatePicker({
							dateFmt:'yyyy-MM-dd HH:mm', minDate:'%y-%M-{%d}', maxDate:'#F{$dp.$D(\'d4312\')||\'2030-10-01\'}'})"/>
                        </div>
                    </div>
                    <div class="control-group">

                        <label class="control-label">结束时间：</label>

                        <div class="controls">
                            <input id="d4312" name="endTime"  class="Wdate" type="text" onclick="WdatePicker({
							dateFmt:'yyyy-MM-dd HH:mm', minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2030-10-01'})"/>

                        </div>
                    </div>

                    <div class="control-group">

                        <label class="control-label">活动地点：</label>

                        <div class="controls">

                            <select class="small m-wrap" tabindex="1" id="address">

                            </select>
                            <select class="small m-wrap" tabindex="1" name="addressId" id="addressDetail">

                            </select>
                        </div>
                    </div>

                    <div class="control-group">

                        <label class="control-label">是否收费：</label>

                        <div class="controls">

                            <select class="small m-wrap" tabindex="1" id="fee">

                            </select>

                            <input type="text" class="m-wrap small" name="price" id="price"/>

                            <span class="help-inline">元</span>
                        </div>

                    </div>

                    <div class="control-group">

                        <label class="control-label">封面图片：</label>

                        <div class="controls">

                            <div class="fileupload fileupload-new" data-provides="fileupload">

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



<script type="text/javascript" src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/static/js/umeditor/third-party/template.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/static/js/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/static/js/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="${ctx }/static/js/umeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="${ctx }/static/js/activity_add.js"></script>

<script type="text/javascript">
    //实例化编辑器
    var um = UM.getEditor('myEditor');
</script>
