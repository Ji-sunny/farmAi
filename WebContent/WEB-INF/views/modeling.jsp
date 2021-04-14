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
            <a class="nav-link collapsed" href="${root}/modeling" aria-expanded="true">
                <i class="fas fa-chart-bar"></i>
                <span>Modeling</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="${root}/visualization" aria-expanded="true">
                <i class="fas fa-chart-pie"></i>
                <span>Visualization</span>
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
                    <h1 class="h3 mb-0 text-gray-800">Data Modeling</h1>
                </div>

                <div class="row">
                    <!-- Area Chart -->
                    <div class="col-xl-12 col-lg-">
                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Data Search</h6>
                            </div>
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <form>
                                        <div>모델명:</div>
                                        <select name="dataSearchModelName" id="dataSearchModelName" class="selectpicker"
                                                data-live-search="true">
                                            <option value="">선택</option>
                                            <option value="vif">vif</option>
                                            <option value="describe">describe</option>
                                            <option value="corr_pearson">corr_pearson</option>
                                            <option value="corr_spearman">corr_spearman</option>
                                        </select>
                                        <div>테이블명:</div>
                                        <select name="dataSearch" id="dataSearch" class=""
                                                data-live-search="true" onchange="getDataSearchColumn(this.value,name)">
                                            <option value="">선택</option>
                                        </select>
                                        <input type="button" class="btn-primary btn-sm" value="데이터 검색" onclick="sendDataSearch()">
                                        <div class="dataSearch"></div>
                                    </form>
                                </div>
                            </div>
                            <div>
                                <table id="dataSearchTable" class="table table-bordered dataTable" width="100%"
                                       cellspacing="0" role="grid" aria-describedby="dataTable_info"
                                       style="width: 100%;">
                                    <thead>
                                    <tr>
                                        <th>index</th>
                                        <th>temp</th>
                                        <th>humid</th>
                                        <th>ec</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <!-- Area Chart -->
                    <div class="col-xl-12 col-lg-">
                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Trigger Generation</h6>
                            </div>
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <form>
                                        <div>테이블명:</div>
                                        <select name="trigger" id="marcoColumn1" class=""
                                                data-live-search="true" onchange="getMarcoColumn(this.value,name)">
                                            <option value="">선택</option>
                                        </select>
                                        <div class="trigger"></div>
                                        <div>모델명:</div>
                                        <select name="marcoModelName" id="marcoModelName1" class="selectpicker"
                                                data-live-search="true">
                                            <option value="">선택</option>
                                            <option value="rfe">rfe</option>
                                            <option value="feature_importance">feature_importance</option>
                                            <option value="regression">regression</option>
                                            <option value="scaleregression">scaleregression</option>
                                            <option value="logisticregression">logisticregression</option>
                                            <option value="decisiontree">decisiontree</option>
                                        </select>
                                        <div>트리거 이름 :</div>
                                        <input type="text" name="macroName" id="macroName"/>
                                        <input type="submit" class="btn-primary btn-sm" value="트리거 생성"
                                               onclick="sendParams()">
                                    </form>
                                </div>
                                <hr>
                                <div> 트리거 삭제
                                    <form>
                                        <select name="trigger" id="marcokeys" class="" data-live-search="true">
                                            <option value="">선택</option>
                                        </select>
                                        <input type="submit" class="btn-success btn-sm mt-2" value="트리거 삭제" onclick="deleteMacro()">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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

<!-- selectpicker-->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>
<!-- (Optional) Latest compiled and minified JavaScript translation files -->


</body>
<script>
    $(document).ready(function () {
        $.get("${root}/process/selectnew/list",
            function (data, status) {
                // console.log(data)
                $.each(data.list, function (index, vo) {
                    // console.log("1", index)
                    // console.log("2", vo)
                    $("#marcoColumn, #marcoColumn1").append(
                        "<option value='" + vo.tablesName + "'>" + vo.filesName + "</option>");
                    $("#dataSearch").append(
                        "<option value='" + vo.tablesName + "'>" + vo.tablesName + "</option>");
                });
                $('#marcoColumn, #marcoColumn1, #dataSearch').selectpicker();
            }, "json");
        $.get("${root}/trigger/select/list",
            function (data) {
                $.each(data.list, function (index, vo) {
                    $("#marcokeys").append(
                        "<option value='" + vo.macroName + "'>" + vo.macroName + "</option>");
                });
                $('#marcokeys').selectpicker();
            }, "json");
        $('#dataSearchTable').hide();
    });

    //marcoColumn
    function getMarcoColumn(tableName, divName) {
        $("." + divName).empty();
        $.get("${root}/process/table_new/list/" + tableName,
            function (data, status) {
                $("." + divName).append("cols_X : ");
                $.each(data.list, function (index, vo) {
                    $("." + divName).append("<label for=\""+index+"q"+"\"><input type=\"checkbox\" name=\"checkX\" class=\"\"id="+index+"q"+ +" value=" + vo.COLUMN_NAME + ">" + "   " + vo.COLUMN_NAME + "<label>  |  ");
                });
                $("." + divName).append("<input type=\"hidden\" id=\"checkTable\" name=\"checkTable\" class=\"\" value=" + tableName + ">");
                $("." + divName).append("</br>");
                $("." + divName).append("col_y : ");
                $.each(data.list, function (index, vo) {
                    $("." + divName).append("<label for=\""+index+"w"+"\"><input type=\"checkbox\" name=\"checkY\" class=\"\"id="+index+"w" +" value=" + vo.COLUMN_NAME + ">" + "   " + vo.COLUMN_NAME + "<label>  |  ");
                });
            }, "json");
    }

    function getDataSearchColumn(tableName, divName) {
        $("." + divName).empty();
        $.get("${root}/process/table_new/list/" + tableName,
            function (data, status) {
                $("." + divName).append("cols_X : ");
                $.each(data.list, function (index, vo) {
                    $("." + divName).append("<label for=\""+index+"e"+"\"><input type=\"checkbox\" name=\"checkDataX\" class=\"\" id="+index+"e" +" value=" + vo.COLUMN_NAME + ">" + "   " + vo.COLUMN_NAME + "<label>  |  ");
                });
                $("." + divName).append("<input type=\"hidden\" id=\"checkTableData\" name=\"checkTableData\" class=\"\" value=" + tableName + ">");
                $("." + divName).append("</br>");
            }, "json");
    }

    function sendDataSearch() {
        $('#dataSearchTable').show();
        var checkTableData = $("#checkTableData").val();
        var dataSearchModelName = $("#dataSearchModelName").val();

        var checkXvalues = new Array();
        $("input:checkbox[name='checkDataX']:checked").each(function () {
            checkXvalues.push($(this).val());
        });

        var chkArray = {
            "checkTable": checkTableData,
            "checkX": checkXvalues,
            "dataSearchModelName": dataSearchModelName
        };
        $("#dataSearchTable").DataTable({
            destroy: true,
            ajax: {url: "${root}/trigger/dataSearch", type: 'get', data: chkArray, dataSrc: 'lists'},
            columns: [
                {data: " "},
                {data: "temp"},
                {data: 'humid'},
                {data: 'ec'}
            ]
        });

    }

    function sendParams() {
        var checkTable = $("#checkTable").val();
        var macroName = $("#macroName").val();
        var marcoModelName1 = $("#marcoModelName1").val();

        var checkXvalues = new Array();
        $("input:checkbox[name='checkX']:checked").each(function () {
            checkXvalues.push($(this).val());
        });
        var checkYvalue = new Array();
        $("input:checkbox[name='checkY']:checked").each(function () {
            checkYvalue.push($(this).val());
        });
        var chkArray = {
            "macroName": macroName,
            "checkTable": checkTable,
            "checkX": checkXvalues,
            "checkY": checkYvalue,
            "marcoModelName": marcoModelName1
        };

        $.ajax({
            url: "${root}/trigger/create",
            type: 'post',
            data: chkArray,
            success: function (data) {
                alert("완료!");
                window.opener.location.reload();
                self.close();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
                self.close();
            }
        });

    }

    //지선
    function deleteMacro() {
        var macroname = $("#marcokeys").val();
        // console.log(macroname);
        $.ajax({
            url: "${root}/trigger/delete/" + macroname,
            type: 'get',
            success: function (data) {
                alert("완료!");
                window.opener.location.reload();
                self.close();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
                self.close();
            }
        });
    }

    $("#sidebarToggle").on('click', function (e) {
        $("body").toggleClass("sidebar-toggled");
        $(".sidebar").toggleClass("toggled");
    });
</script>
</html>