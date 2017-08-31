<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%--<c:set var="ua" value="${sessionScope.userAuthorization.authorization}"></c:set>--%>
<script src="${ctx }/static/js/dimension.js"></script>
<div id="show-content">

</div>
<div class="page-content">

    <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

    <div id="portlet-config" class="modal hide">

        <div class="modal-header">

            <button data-dismiss="modal" class="close" type="button"></button>

            <h3>修改服务项</h3>

        </div>

        <div class="modal-body">

            <form method="post" class="form-horizontal" id="itemModify" >
                <input type="hidden" id="itemId" name="id"/>
                <div class="control-group">
                    <label class="control-label">服务项：</label>
                    <div class="controls">
                        <input type="text" class="m-wrap medium" id="itemName" name="name" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">比例：</label>
                    <div class="controls">
                        <input type="text" class="m-wrap medium" id="ratio" name="ratio" />
                    </div>
                </div>
                <%--<div class="control-group">--%>
                    <%--<label class="control-label">类别：</label>--%>
                    <%--<div class="controls">--%>
                        <%--<select class="itemTypes" name="type">--%>
                        <%--</select>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="form-actions">

                    <button type="button" class="btn blue" onclick="modifyItemSub()"><i class="icon-ok"></i> 提交</button>

                    <button type="reset" class="btn">重置</button>

                </div>
            </form>

        </div>

    </div>

    <div id="dimension-config" class="modal hide">

        <div class="modal-header">

            <button data-dismiss="modal" class="close" type="button"></button>

            <h3>修改维度</h3>

        </div>

        <div class="modal-body">

            <form method="post" class="form-horizontal" id="modifyDimensionFrom">
                <input type="hidden" id="dimensionId" name="id"/>
                <div class="control-group">
                    <label class="control-label">维度：</label>
                    <div class="controls">
                        <input type="text" class="m-wrap medium" id="dimensionName" name="name" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">比例：</label>
                    <div class="controls">
                        <input type="text" class="m-wrap medium" id="dimensionRatio" name="ratio" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">类别：</label>
                    <div class="controls">
                        <select class="dimensionTypes" name="type">
                        </select>
                    </div>
                </div>
                <div class="form-actions">

                    <button type="button" class="btn blue" onclick="modifyDimensionSub()"><i class="icon-ok"></i> 提交</button>

                    <button type="reset" class="btn">重置</button>

                </div>
            </form>

        </div>

    </div>

    <div id="add-config" class="modal hide">

        <div class="modal-header">

            <button data-dismiss="modal" class="close" type="button"></button>

            <h3>添加维度</h3>

        </div>

        <div class="modal-body">

            <form method="post" class="form-horizontal" id="addDimensionFrom">
                <input type="hidden" id="addItemId" name="itemId"/>
                <div class="control-group">
                    <label class="control-label">名称：</label>
                    <div class="controls">
                        <input type="text" class="m-wrap medium" id="addDimensionName" name="name" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">比例：</label>
                    <div class="controls">
                        <input type="text" class="m-wrap medium" id="addDimensionRatio" name="ratio" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">类别：</label>
                    <div class="controls">
                        <select class="dimensionTypes" name="type">
                        </select>
                    </div>
                </div>
                <div class="form-actions">

                    <button type="button" class="btn blue" onclick="addDimensionSub()"><i class="icon-ok"></i> 提交</button>

                    <button type="reset" class="btn">重置</button>

                </div>
            </form>

        </div>

    </div>

    <div id="add-item-config" class="modal hide">

        <div class="modal-header">

            <button data-dismiss="modal" class="close" type="button" ></button>

            <h3>添加服务项</h3>

        </div>

        <div class="modal-body">

            <form method="post" class="form-horizontal" id="addItemFrom">
                <div class="control-group">
                    <label class="control-label">名称：</label>
                    <div class="controls">
                        <input type="text" class="m-wrap medium"  id="addItemName" name="name" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">比例：</label>
                    <div class="controls">
                        <input type="text" class="m-wrap medium" id="addItemRatio" name="ratio" />
                    </div>
                </div>
                <%--<div class="control-group">--%>
                    <%--<label class="control-label">类别：</label>--%>
                    <%--<div class="controls">--%>
                        <%--<select class="itemTypes" name="type">--%>
                        <%--</select>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="form-actions">

                    <button type="button" onclick="addItemSub()" class="btn blue"><i class="icon-ok"></i> 提交</button>

                    <button type="reset" class="btn">重置</button>

                </div>
            </form>

        </div>

    </div>
</div>

