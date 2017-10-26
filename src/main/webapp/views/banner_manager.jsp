<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<div class="row-fluid profile">

    <div class="span12">

        <!--BEGIN TABS-->

        <div class="tabbable tabbable-custom tabbable-full-width">

            <ul class="nav nav-tabs">

                <li class="active"><a href="#tab_1_1" data-toggle="tab">首页轮播</a></li>

                <li><a href="#tab_1_2" data-toggle="tab">E卡管理轮播</a></li>

                <li style="float: right">
                    <a href="#add-config" data-toggle="modal">
                        <button type="button" class="btn green" >添加</button>
                    </a>
                </li>
            </ul>
            <div class="tab-content">

                <div class="tab-pane row-fluid active" id="tab_1_1">

                    <table class="table table-striped table-bordered table-hover table-full-width"
                           id="HOME_PAGE">
                    </table>

                </div>
                <div class="tab-pane row-fluid" id="tab_1_2">
                    <div class="portlet-body">

                        <table class="table table-striped table-bordered table-hover table-full-width"
                               id="E_CARD">

                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<div class="page-content">

    <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

    <div id="add-config" class="modal hide" style="height: 600px">

        <div class="modal-header">

            <button data-dismiss="modal" class="close" type="button" onclick="add()"></button>

            <h3>编辑</h3>

        </div>

        <div class="modal-body" style="height: 550px;max-height:650px;">

            <form method="post" class="form-horizontal" id="bannerForm" enctype="multipart/form-data">
                <input type="hidden" id="bannerId" name="id"/>

                <div class="control-group" id="positionGroup">

                    <label class="control-label">轮播位置：</label>

                    <div class="controls">

                        <label class="radio">

                            <input checked class="m-wrap" type="radio" name="positionCode" value="HOME_PAGE"  />
                            首页轮播
                        </label>
                        <label class="radio">
                            <input type="radio" class="m-wrap" name="positionCode" value="E_CARD"  />
                            E卡管理轮播
                        </label>

                    </div>

                </div>
                <div class="control-group"  >
                    <label class="control-label">标题：</label>
                    <div class="controls">
                        <input type="text" class="m-wrap medium"  id="title" name="title"/>
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

                    <label class="control-label">跳转详情：</label>

                    <div class="controls">

                        <select class="small m-wrap" tabindex="1" id="typeCode" name="typeCode">

                        </select>
                        <select class="small m-wrap" tabindex="1" name="activityId" id="activityId">

                        </select>
                    </div>
                </div>
                <div class="control-group" id="linkUrlGroup" style="display: none;">
                    <label class="control-label">链接地址：</label>
                    <div class="controls">
                        <input type="text" class="m-wrap medium"  name="linkUrl" id="linkUrl"/>
                    </div>
                </div>

                <div class="control-group">

                    <label class="control-label">是否显示：</label>

                    <div class="controls">

                        <label class="radio">

                            <input class="m-wrap " type="radio" name="online" value="true" checked/>
                            是
                        </label>
                        <label class="radio">
                            <input type="radio" class="m-wrap " name="online" value="false"  />
                            否
                        </label>

                    </div>

                </div>

                <div class="form-actions">

                    <button type="button" class="btn blue" onclick="bannerSub()"><i class="icon-ok"></i> 提交</button>

                    <button type="reset" class="btn" id="reset">重置</button>

                </div>
            </form>

        </div>

    </div>
</div>


<script src="${ctx }/static/js/banner_manager.js"></script>
