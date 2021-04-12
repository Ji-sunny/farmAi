<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <title>FarmAI</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


    <!-- Custom fonts for this template-->
    <link href="${root}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${root}/resources/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="${root}/resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
</head>
<style type="text/css">
    .wrap-loading { /*화면 전체를 어둡게 합니다.*/
        position: fixed;
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
        background: rgba(0, 0, 0, 0.2); /*not in ie */
        z-index: 99999999;
        filter: progid:DXImageTransform.Microsoft.Gradient(startColorstr='#20000000', endColorstr='#20000000'); /* ie */
    }

    .wrap-loading div { /*로딩 이미지*/
        position: fixed;
        top: 50%;
        left: 50%;
        margin-left: -21px;
        margin-top: -21px;
    }

    .display-none { /*감추기*/
        display: none;
    }
</style>

<body id="page-top">
<!-- Page Wrapper -->
<div id="wrapper">
    <div class="wrap-loading display-none">
        <div><img src="${root}/resources/img/loading.gif" alt="파일이 업로드 중입니다. 잠시만 기다려주세요."/></div>
    </div>
    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${root}/">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">FarmAI <sup> v.1</sup></div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href="${root}/">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Dashboard</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Interface
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="${root}/modeling1" aria-expanded="true" >
                <i class="fas fa-chart-bar"></i>
                <span>Modeling1</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="${root}/modeling2" aria-expanded="true" >
                <i class="fas fa-chart-pie"></i>
                <span>Modeling2</span>
            </a>
        </li>


        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>


    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>

                <!-- Topbar Search -->
                <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                    <div class="input-group">
                        <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                               aria-label="Search" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="button">
                                <i class="fas fa-search fa-sm"></i>
                            </button>
                        </div>
                    </div>
                </form>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <li class="nav-item dropdown no-arrow d-sm-none">
                        <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-search fa-fw"></i>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                             aria-labelledby="searchDropdown">
                            <form class="form-inline mr-auto w-100 navbar-search">
                                <div class="input-group">
                                    <input type="text" class="form-control bg-light border-0 small"
                                           placeholder="Search for..." aria-label="Search"
                                           aria-describedby="basic-addon2">
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button">
                                            <i class="fas fa-search fa-sm"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </li>
                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">시각화</h1>
                </div>

                <!-- Content Row -->

                <div class="row">
                    <!-- Area Chart -->
                    <div class="col-xl-12 col-lg-">
                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">평가</h6>
                            </div>

                            <div class="card-body">

                                <div class="row no-gutters align-items-center">
                                    <select name="evaluation" id="evaluation" class="" data-live-search="true">
                                        <option value="">선택</option>
                                    </select>

                                </div>
                                <div id="evaluationScore" ></div>
<%--                                <div id="evaluationTable"></div>--%>
                                <div>
                                    <table id = "evaluationTable" class="table table-bordered dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                                        <thead>
                                            <tr>
                                                <th>index</th>
                                                <th>precision</th>
                                                <th>recall</th>
                                                <th>f1-score</th>
                                                <th>support</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>

                <!-- Content Row -->
<%--                <div class="card shadow mb-4">--%>
<%--                    <div class="card-header py-3">--%>
<%--                        <h6 class="m-0 font-weight-bold text-primary">시각화</h6>--%>
<%--                    </div>--%>
<%--                    <div class="card-body">--%>
<%--                        <div class="table-responsive">--%>
<%--                            <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">--%>
<%--                                <div class="row">--%>

<%--                                    <div class="col-sm-12 col-md-8">--%>
<%--                                        <div class="dataTables_length">--%>
<%--                                            <label>시각화 검색:--%>
<%--                                                <select id="aidatatable" name="aidatatable"--%>
<%--                                                        aria-controls="dataTable"--%>
<%--                                                        class="custom-select custom-select-sm form-control form-control-sm">--%>
<%--                                                    <option value="">선택</option>--%>
<%--                                                </select>--%>
<%--                                            </label>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>


<%--                                <div id="image">--%>
<%--                                </div>--%>
<%--                                <div id="graph">--%>
<%--                                </div>--%>
<%--                                <div id="chart">--%>
<%--                                </div>--%>

<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <%--                <<div><table id = "boardList" border = "1"></table></div>--%>
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2020</span>
                </div>
                <div class="card-body">

                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="login.html">Logout</a>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Bootstrap core JavaScript-->
<script src="${root}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${root}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="${root}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="${root}/resources/js/sb-admin-2.min.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>

<script src="${root}/resources/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="${root}/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>
<script src="${root}/resources/js/sb-admin-2.min.js"></script>
<!-- Page level custom scripts -->
<script src="${root}/resources/js/demo/datatables-demo.js"></script>


</body>
<script>
    $(document).ready(function () {
        $.get("${root}/visual/evaluationList",
            function (data, status) {
                $.each(data.list, function (index, vo) {
                    $("#evaluation").append(
                        "<option value='" + vo.macroName + "'>" + vo.macroName + "</option>");
                });
            }, "json");
    });
    $('#evaluation').change(function () {
        var tname = $('#evaluation option:selected').val();
        $("#evaluationScore").empty();
        $("#evaluationTable").DataTable({
            destroy: true,
            searching: false,
            ajax: {  url: "${root}/visual/evaluationList/" + tname, dataSrc: 'lists' },
            columns: [
                        { data: " " },
                        { data: "precision" },
                        { data: 'recall' },
                        { data: 'f1-score' },
                        { data: 'support' }
            ]
        });
        $.ajax({
            url: "${root}/visual/evaluationList/" + tname,
            type: "get",
            success:function(res){
                $("#evaluationScore").append("score = ").append(res.list.score);
                var results = res.list.report;
                console.log("results : ",results);
                console.log("results : ",results.length);
                console.log(res.lists)

            },
            error:function(jqXHR, textStatus, errorThrown){
                alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
                self.close();
            }
        });
    });
    $("#sidebarToggle").on('click', function(e) {
        $("body").toggleClass("sidebar-toggled");
        $(".sidebar").toggleClass("toggled");
    });
</script>
</html>