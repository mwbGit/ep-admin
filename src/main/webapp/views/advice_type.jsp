<%--
  Created by IntelliJ IDEA.
  User: 吴晓海
  Date: 2017/10/27
  Time: 1:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="row-fluid">

    <div class="span12">

        <div class="portlet box light-grey">

            <div class="portlet-title">

                <div class="caption"><i class="icon-globe"></i>分类管理</div>
                <div class="actions">

                    <div class="btn-group">

                        <a href="#add-config" data-toggle="modal" ><button class="btn">
                            添加
                        </button>
                        </a>

                        <div id="sample_2_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">

                        </div>

                    </div>
                </div>
            </div>

            <div class="portlet-body">
                <table class="table table-bordered table-hover" id="type_table">

                </table>
            </div>

        </div>
    </div>
</div>

<div class="page-content">
    <div id="modify-config" class="modal hide">

        <div class="modal-header">

            <button data-dismiss="modal" class="close" type="button"></button>

            <h3>修改分类</h3>

        </div>

        <div class="modal-body">

            <form method="post" class="form-horizontal" id="modifyTypeFrom">
                <input type="hidden" id="typeId" name="id"/>

                <div class="control-group">
                    <label class="control-label">名称：</label>
                    <div class="controls">
                        <input type="text" class="m-wrap medium" id="typeName" name="name" />
                    </div>
                </div>
                <div class="form-actions">

                    <button type="button" class="btn blue" onclick="modifyTypeNameBut()"><i class="icon-ok"></i> 提交</button>

                    <button type="reset" class="btn">重置</button>

                </div>
            </form>

        </div>

    </div>

    <div id="add-config" class="modal hide">

        <div class="modal-header">

            <button data-dismiss="modal" class="close" type="button"></button>

            <h3>添加分类</h3>

        </div>

        <div class="modal-body">

            <form method="post" class="form-horizontal" id="addFrom">

                <div class="control-group">
                    <label class="control-label">名称：</label>
                    <div class="controls">
                        <input type="text" class="m-wrap medium" name="name" />
                    </div>
                </div>
                <div class="form-actions">

                    <button type="button" class="btn blue" onclick="addTypeNameBut()"><i class="icon-ok"></i> 提交</button>

                    <button type="reset" class="btn">重置</button>

                </div>
            </form>

        </div>

    </div>

</div>

<script src="${ctx }/static/js/advice_type_table.js"></script>

