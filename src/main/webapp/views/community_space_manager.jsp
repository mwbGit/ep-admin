<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
    var community_id = "${param.id}";
</script>

<div class="row-fluid profile">

    <div class="span12">

        <!--BEGIN TABS-->

        <div class="tabbable tabbable-custom tabbable-full-width">

            <ul class="nav nav-tabs" >

                <li class="active"><a href="#tab_1_1" data-toggle="tab" onclick="typeChange('1')">会议室</a></li>

                <li><a href="#tab_1_2" data-toggle="tab" onclick="typeChange('0')">活动场所</a></li>

                <li style="float: right">
                        <button type="button" onclick="add()" class="btn blue" ><span id="addType">添加会议室</span></button>
                </li>
            </ul>
            <div class="tab-content">

                <div class="tab-pane row-fluid active" id="tab_1_1">

                    <table class="table table-striped table-bordered table-hover table-full-width"
                           id="Meeting">
                    </table>

                </div>
                <div class="tab-pane row-fluid" id="tab_1_2">
                    <div class="portlet-body">

                        <table class="table table-striped table-bordered table-hover table-full-width"
                               id="Activity">

                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
    <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

<script src="${ctx }/static/js/community_space_manager.js"></script>
