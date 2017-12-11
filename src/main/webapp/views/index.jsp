<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%--<c:set var="ua" value="${sessionScope.userAuthorization.authorization}"></c:set>--%>


<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->

<!--[if !IE]><!-->
<html lang="en" class="no-js"> <!--<![endif]-->

<!-- BEGIN HEAD -->
<html lang="zh-CN">

<head>

    <meta charset="utf-8"/>

    <title>E园</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>

    <meta content="" name="description"/>

    <meta content="" name="author"/>

    <!-- BEGIN GLOBAL MANDATORY STYLES -->

    <link href="${ctx }/static/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx }/static/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx }/static/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx }/static/media/css/style-metro.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx }/static/media/css/style.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx }/static/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx }/static/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

    <link href="${ctx }/static/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>

    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN PAGE LEVEL STYLES -->

    <link href="${ctx }/static/media/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx }/static/media/css/daterangepicker.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx }/static/media/css/fullcalendar.css" rel="stylesheet" type="text/css"/>

    <link href="${ctx }/static/media/css/jqvmap.css" rel="stylesheet" type="text/css" media="screen"/>

    <link href="${ctx }/static/media/css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>

    <!-- END PAGE LEVEL STYLES -->

    <link rel="shortcut icon" href="${ctx }/static/media/image/favicon.ico"/>

    <link rel="stylesheet" type="text/css" href="${ctx }/static/media/css/select2_metro.css"/>

    <link rel="stylesheet" href="${ctx }/static/media/css/DT_bootstrap.css"/>

    <!-- END PAGE LEVEL STYLES -->

    <link rel="shortcut icon" href="${ctx }/static/media/image/favicon.ico"/>

    <link rel="stylesheet" type="text/css" href="${ctx }/static/media/css/bootstrap-fileupload.css"/>

</head>
<script type="text/javascript">
    var $ctx = "${ctx }";
</script>
<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

<!-- BEGIN HEADER -->

<div class="header navbar navbar-inverse navbar-fixed-top">

    <!-- BEGIN TOP NAVIGATION BAR -->

    <div class="navbar-inner">

        <div class="container-fluid">

            <!-- BEGIN LOGO -->

            <a class="brand" href="${ctx}/views/index.jsp">

                <span style="font-size: x-large; margin-left: 30px;color: white" >EPARK</span>

            </a>

            <!-- END LOGO -->

            <!-- BEGIN RESPONSIVE MENU TOGGLER -->

            <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">

                <img src="${ctx }/static/media/image/menu-toggler.png" alt=""/>

            </a>

            <!-- END RESPONSIVE MENU TOGGLER -->

            <!-- BEGIN TOP NAVIGATION MENU -->

            <ul class="nav pull-right">

                <li class="dropdown user">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                        <img alt="" src="${ctx }/static/media/image/avatar1_small.jpg"/>

                        <span class="username">${user.name}</span>

                        <i class="icon-angle-down"></i>

                    </a>

                    <ul class="dropdown-menu">

                        <li class="divider"></li>

                        <li><a href="#password-config" data-toggle="modal"><i class="icon-lock"></i>修改密码</a></li>

                        <li><a href="${ctx}/logout"><i class="icon-key"></i> 退出</a></li>

                    </ul>

                </li>

                <!-- END USER LOGIN DROPDOWN -->

            </ul>

            <!-- END TOP NAVIGATION MENU -->

        </div>

    </div>

    <!-- END TOP NAVIGATION BAR -->

</div>


<!-- BEGIN CONTAINER -->

<div class="page-container">

    <!-- BEGIN SIDEBAR -->

    <div class="page-sidebar nav-collapse collapse">

        <!-- BEGIN SIDEBAR MENU -->

        <ul class="page-sidebar-menu">

            <li>

                <!-- BEGIN SIDEBAR TOGGLER BUTTON -->

                <div class="sidebar-toggler hidden-phone"></div>

                <!-- BEGIN SIDEBAR TOGGLER BUTTON -->

            </li>


            <li class="start ">

                <a href="${ctx}/views/index.jsp">

                    <i class="icon-home"></i>

                    <span class="title">主页</span>

                    <span class="selected"></span>

                </a>

            </li>


            <li>

                <a href="javascript:;">

                    <i class="icon-th"></i>

                    <span class="title">吐槽管理</span>

                    <span class="arrow "></span>

                </a>

                <ul class="sub-menu">

                    <li id="analysis">

                        <a href="#" onclick="loadJsp('analysis')">

                            数据分析</a>

                    </li>

                    <li id="dimension">

                        <a href="#" onclick="loadJsp('dimension')">

                            维度管理</a>

                    </li>

                </ul>

            </li>


            <li>
                <a href="javascript:;">

                    <i class="icon-bullhorn"></i>

                    <span class="title">活动管理</span>

                    <span class="arrow "></span>

                </a>

                <ul class="sub-menu">

                    <li id="activity_add-li">

                        <a href="#" onclick="loadJsp('activity_add')">

                            添加活动</a>
                    </li>

                    <li id="activity_manager-li">

                        <a href="#" onclick="loadJsp('activity_manager')">

                            活动内容管理</a>
                    </li>
                    <li id="activity-type-li">

                        <a href="#" onclick="loadJsp('activity_type')">

                            活动分类管理</a>

                    </li>
                </ul>

            </li>

            <li>
                <a href="javascript:;">

                    <i class="icon-bullhorn"></i>

                    <span class="title">社区管理</span>

                    <span class="arrow "></span>

                </a>

                <ul class="sub-menu">

                    <li id="community_manager-li">

                        <a href="#" onclick="loadJsp('community_manager')">

                            社区经理管理</a>
                    </li>
                </ul>

            </li>



            <li>
                <a href="javascript:;">

                    <i class="icon-bar-chart"></i>

                    <span class="title">E卡管理</span>

                    <span class="arrow "></span>

                </a>

                <ul class="sub-menu">

                    <li id="recharge_manager-li">

                        <a href="#" onclick="loadJsp('recharge_manager')">

                            充值管理</a>
                    </li>

                    <li id="recharge_detail-li">

                        <a href="#" onclick="loadJsp('recharge_detail')">

                            充值记录</a>
                    </li>
                </ul>

            </li>

            <li>

                <a href="javascript:;">

                    <i class="icon-expand"></i>

                    <span class="title">轮播图管理</span>

                    <span class="arrow "></span>

                </a>

                <ul class="sub-menu">

                    <li id="banner-li">

                        <a href="#" onclick="loadJsp('banner_manager')">

                            轮播图管理</a>

                    </li>

                </ul>

            </li>
            <li>

                <a href="javascript:;">

                    <i class="icon-group"></i>

                    <span class="title">用户管理</span>

                    <span class="arrow "></span>

                </a>

                <ul class="sub-menu">

                    <li id="user-li">

                        <a href="#" onclick="loadJsp('user_manager')">

                            用户列表</a>

                    </li>

                </ul>

            </li>
            <li>

                <a href="javascript:;">

                    <i class="icon-user"></i>

                    <span class="title">资讯管理</span>

                    <span class="arrow "></span>

                </a>

                <ul class="sub-menu">

                    <li id="adviceAdd">

                        <a href="#" onclick="loadJsp('adviceAdd')">

                            添加资讯</a>

                    </li>

                    <li id="adviceManage">

                        <a href="#" onclick="loadJsp('advice_manager')">

                            内容管理</a>

                    </li>

                    <li id="adviceClass">

                        <a href="#" onclick="loadJsp('advice_type')">

                            分类管理</a>

                    </li>

                </ul>
            </li>
        </ul>

        <!-- END SIDEBAR MENU -->

    </div>

    <!-- END SIDEBAR -->

    <!-- BEGIN PAGE -->

    <div class="page-content">


        <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

        <!-- BEGIN PAGE CONTAINER-->

        <div class="container-fluid">

            <!-- BEGIN PAGE HEADER-->
            <div class="row-fluid">
                <div class="span12">

                    <h3 class="page-title">

                        &nbsp;

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="${ctx}/views/index.jsp">主页</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#" id="menuTitle"></a></li>

                        <li class="pull-right no-text-shadow">

                            <div id="dashboard-report-range"
                                 class="dashboard-date-range tooltips no-tooltip-on-touch-device responsive"
                                 data-tablet="" data-desktop="tooltips" data-placement="top"
                                 data-original-title="Change dashboard date range">

                                <i class="icon-calendar"></i>

                                <span></span>

                                <i class="icon-angle-down"></i>

                            </div>

                        </li>
                        <li style="float: right">
                            <div>
                                <div class="actions">
                                    <div class="btn-group" id="showAddItem" style="display: none">

                                        <a href="#add-item-config" data-toggle="modal">
                                            <button class="btn green">
                                                添加服务项
                                            </button>
                                        </a>

                                        <div id="sample_2_column_toggler"
                                             class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">

                                        </div>

                                    </div>
                                </div>
                            </div>
                        </li>



                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <%--todo includ--%>
            <div id="dashboard">


            </div>

        </div>

        <!-- END PAGE CONTAINER-->

        <div id="password-config" class="modal hide">

            <div class="modal-header">

                <button data-dismiss="modal" class="close" type="button"></button>

                <h3>修改密码</h3>

            </div>

            <div class="modal-body">

                <form method="post" class="form-horizontal" id="modifyPassword">

                    <div class="control-group">

                        <label class="control-label">密码<span class="required">*</span></label>

                        <div class="controls">

                            <input id="password" type="password" class="medium m-wrap" name="password"/>

                            <span class="help-inline" style="color: red;display: none" id="pass">至少六位密码</span>

                        </div>

                    </div>

                    <div class="control-group">

                        <label class="control-label">再次输入<span class="required">*</span></label>

                        <div class="controls">

                            <input type="password" class="medium m-wrap" name="rpassword" id="rpassword"/>

                            <span class="help-inline" style="color: red;display: none" id="rpass">两次输入不一致</span>

                        </div>

                    </div>
                    <div class="form-actions">

                        <button type="button" class="btn blue" onclick="subPassword()"><i class="icon-ok"></i> 提交
                        </button>

                    </div>
                </form>

            </div>

        </div>

    </div>

    <!-- END PAGE -->

</div>

<!-- END CONTAINER -->

<!-- BEGIN FOOTER -->

<div class="footer">

    <div class="footer-inner" style="text-align: center;position: fixed;left: 45%;">
        Copyright&copy;2016-2020 北京意园创新办公服务股份有限公司
    </div>

    <div class="footer-tools">

			<span class="go-top">

			<i class="icon-angle-up"></i>

			</span>

    </div>

</div>

<!-- END FOOTER -->

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

<!-- BEGIN CORE PLUGINS -->

<script src="${ctx }/static/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="${ctx }/static/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

<script src="${ctx }/static/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/bootstrap.min.js" type="text/javascript"></script>

<!--[if lt IE 9]>

<script src="${ctx }/static/media/js/excanvas.min.js"></script>

<script src="${ctx }/static/media/js/respond.min.js"></script>

<![endif]-->

<script src="${ctx }/static/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery.blockui.min.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery.cookie.min.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery.uniform.min.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL PLUGINS -->

<script src="${ctx }/static/media/js/jquery.vmap.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery.vmap.russia.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery.vmap.world.js" type="text/javascript"></script>

<script src="${ctx}/static/media/js/jquery.vmap.europe.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery.vmap.germany.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery.vmap.usa.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery.vmap.sampledata.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery.flot.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery.flot.resize.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery.pulsate.min.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/date.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/daterangepicker.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery.gritter.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/fullcalendar.min.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery.easy-pie-chart.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/jquery.sparkline.min.js" type="text/javascript"></script>

<!-- END PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="${ctx }/static/media/js/select2.min.js"></script>

<script type="text/javascript" src="${ctx }/static/media/js/jquery.dataTables.min.js"></script>

<script type="text/javascript" src="${ctx }/static/media/js/DT_bootstrap.js"></script>

<!-- BEGIN PAGE LEVEL SCRIPTS -->

<script src="${ctx }/static/media/js/app.js" type="text/javascript"></script>

<script src="${ctx }/static/media/js/index.js" type="text/javascript"></script>
<script src="${ctx }/static/media/js/index.js" type="text/javascript"></script>
<script src="${ctx }/static/js/index.js" type="text/javascript"></script>

<script type="text/javascript" src="${ctx }/static/media/js/jquery.dataTables.js"></script>

<script type="text/javascript" src="${ctx }/static/media/js/bootstrap-fileupload.js"></script>

<!-- END PAGE LEVEL SCRIPTS -->

<script>

    jQuery(document).ready(function () {
        App.init(); // initlayout and core plugins

    });

</script>

<!-- END JAVASCRIPTS -->

</body>

<!-- END BODY -->

</html>

