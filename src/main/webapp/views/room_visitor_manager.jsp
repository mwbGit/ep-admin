<%--
  Created by IntelliJ IDEA.
  User: 吴晓海
  Date: 2018/1/4
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />



<div class="portlet box blue">

    <div class="portlet-title">

        <div class="caption"><i class="icon-globe"></i>空间访客管理</div>

    </div>

    <div class="portlet-body">

        <table class="table table-striped table-bordered table-hover table-full-width" id="room_visitor_manager">

        </table>
    </div>

</div>


<script src="${ctx }/static/js/room_visitor_manager.js"></script>

