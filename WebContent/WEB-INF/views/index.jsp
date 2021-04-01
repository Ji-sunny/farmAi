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
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">FarmAI <sup> v.1</sup></div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href="index.jsp">
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
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
               aria-expanded="true" aria-controls="collapseTwo">
                <i class="fas fa-fw fa-cog"></i>
                <span>Components</span>
            </a>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Custom Components:</h6>
                    <a class="collapse-item" href="buttons.html">Buttons</a>
                    <a class="collapse-item" href="cards.html">Cards</a>
                </div>
            </div>
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
                    <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
                    <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                </div>

                <!-- Content Row -->
                <div class="row">

                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">CSV
                                            파일
                                            저장
                                        </div>
                                        <form id="excelUploadForm" name="excelUploadForm"
                                              enctype="multipart/form-data"
                                              method="post" action="${root}/csvUpload">
                                            <div class="contents">
                                                <dl class="vm_name">
                                                    <dt class="down w90">첨부파일(only one)</dt>
                                                    <dd><input id="csvFile" type="file" name="csvFile"/></dd>
                                                </dl>
                                            </div>
                                            <%--                                                <div class="col-auto">--%>
                                            <button type="button" id="addExcelImpoartBtn" class="btn-primary btn-sm"
                                                    onclick="check()">추가
                                            </button>
                                            <%--                                                </div>--%>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                            저장된 엑셀 파일 삭제
                                        </div>
                                        <form method="get" action="${root}/delete">
                                            <dt class="down w90"><label>엑셀선택</label></dt>
                                            <select name="csvexcel" id="delexcel" class="selectpicker"
                                                    data-live-search="true">
                                                <option value="">선택</option>
                                            </select>
                                            <br/>
                                            <input type="submit" class="btn-success btn-sm mt-2" name="btn"
                                                   value="파일삭제">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Pending Requests Card Example -->
                    <%--                    <div class="col-xl-3 col-md-6 mb-4">--%>
                    <%--                        <div class="card border-left-warning shadow h-100 py-2">--%>
                    <%--                            <div class="card-body">--%>
                    <%--                                <div class="row no-gutters align-items-center">--%>
                    <%--                                    <div class="col mr-2">--%>
                    <%--                                        <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">--%>
                    <%--                                            매크로 리스트--%>
                    <%--                                        </div>--%>
                    <%--                                        <div class="row no-gutters align-items-center">--%>
                    <%--                                            <form method="post" action="${root}/macro/excute">--%>
                    <%--                                                <select id="selectMacro" name="macroName" class="selectpicker" data-live-search="true">--%>
                    <%--                                                    <option value="">선택</option>--%>
                    <%--                                                </select>--%>
                    <%--                                                <input type="submit" class="btn-primary btn-sm" name="btn" value="전처리 실행">--%>
                    <%--                                            </form>--%>
                    <%--                                        </div>--%>
                    <%--                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">--%>
                    <%--                                            매크로 삭제--%>
                    <%--                                        </div>--%>
                    <%--                                        <form method="get" action="${root}/macro/delete">--%>
                    <%--                                            <dt class="down w90"><label>매크로 선택</label></dt>--%>
                    <%--                                            <select name="macro" id="macrodel" class="selectpicker" data-live-search="true">--%>
                    <%--                                                <option value="">선택</option>--%>
                    <%--                                            </select>--%>
                    <%--                                            <br/>--%>
                    <%--                                            <input type="submit" class="btn-success btn-sm mt-2" name="btn"--%>
                    <%--                                                   value="매크로 삭제">--%>
                    <%--                                        </form>--%>
                    <%--                                    </div>--%>
                    <%--                                </div>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </div>--%>
                </div>

                <div class="row">
                    <!-- Area Chart -->
                    <div class="col-xl-12 col-lg-">
                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">_new 파일 선택</h6>
                            </div>

                            <div class="card-body">

                                <div class="row no-gutters align-items-center">
                                    <form method="get" action="${root}/modeling">
                                        <select name="macro1" id="macroselect1" class="selectpicker"
                                                data-live-search="true" onchange="getColumn(this.value,name)">
                                            <option value="">선택</option>
                                        </select>
                                        <div class="macro1"></div>

                                        <select name="macro2" id="macroselect2" class="selectpicker"
                                                data-live-search="true" onchange="getColumn(this.value,name)">
                                            <option value="">선택</option>
                                        </select>
                                        <div class="macro2"></div>

                                        <select name="macro3" id="macroselect3" class="selectpicker"
                                                data-live-search="true" onchange="getColumn(this.value,name)">
                                            <option value="">선택</option>
                                        </select>
                                        <div class="macro3"></div>

                                        <select name="macro4" id="macroselect4" class="selectpicker"
                                                data-live-search="true" onchange="getColumn(this.value,name)">
                                            <option value="">선택</option>
                                        </select>
                                        <div class="macro4"></div>

                                        <select name="macro5" id="macroselect5" class="selectpicker"
                                                data-live-search="true" onchange="getColumn(this.value,name)">
                                            <option value="">선택</option>
                                        </select>
                                        <div class="macro5"></div>

                                        <input type="text" name="macroName">
                                        <input type="submit" class="btn-primary btn-sm" name="btn" value="모델링 실행">
                                    </form>
                                </div>


                            </div>
                        </div>
                    </div>

                </div>

                <!-- Content Row -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">테이블 </h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                                <div class="row">
                                    <div class="col-sm-12 col-md-2">
                                        <div class="dataTables_length" id="dataTable_length">
                                            <label>Show
                                                <select id="airowper" name="dataTable_length" aria-controls="dataTable"
                                                        class="custom-select custom-select-sm form-control form-control-sm">
                                                    <option value="10">10</option>
                                                    <option value="25">25</option>
                                                    <option value="50">50</option>
                                                    <option value="100">100</option>
                                                </select>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-10">
                                        <div class="dataTables_length">
                                            <label>테이블 검색:
                                                <select id="aidatatable" name="aidatatable"
                                                        aria-controls="dataTable"
                                                        class="custom-select custom-select-sm form-control form-control-sm">
                                                    <option value="">선택</option>
                                                </select>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div id="boardList">

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
</body>
<script>
    $(document).ready(function () {
        $.get("${root}/selectori/list",
            function (data, status) {
                // console.log(data)
                $.each(data.list, function (index, vo) {
                    // console.log("1", index)
                    // console.log("2", vo)
                    $("#delexcel").append(
                        "<option value='" + vo.filesName + "'>" + vo.filesName + "</option>");
                });
            }, "json");
        $.get("${root}/select/list",
            function (data, status) {
                // console.log(data)
                $.each(data.list, function (index, vo) {
                    // console.log("1", index)
                    // console.log("2", vo)
                    $("#aidatatable").append(
                        "<option value='" + vo + "'>" + vo + "</option>");
                });
            }, "json");
        $.get("${root}/selectnew/list",
            function (data, status) {
                // console.log(data)
                $.each(data.list, function (index, vo) {
                    // console.log("1", index)
                    // console.log("2", vo)
                    $("#macroselect1, #macroselect2, #macroselect3, #macroselect4, #macroselect5").append(
                        "<option value='" + vo.tablesName + "'>" + vo.filesName + "</option>");
                });
            }, "json");
        $.get("${root}/macro/list",
            function (data, status) {
                $.each(data.list, function (index, vo) {
                    $("#selectMacro, #macrodel").append(
                        "<option value='" + vo.macroName + "'>" + vo.macroName + "</option>");
                });
            }, "json");
    });

    function getColumn(tableName, divName) {
        // alert(tableName+" "+divName);
        $("." + divName).empty();
        $.get("${root}/table_new/list/" + tableName,
            function (data, status) {
                if (divName == 'macro1'){
                    $("." + divName).append("받을 열 : ");
                    $.each(data.list, function (index, vo) {
                        $("." + divName).append("<input type=\"checkbox\" name=\"check1\" class=\"check\" value=\"${vo.COLUMN_NAME}\">" + "   " + vo.COLUMN_NAME + "  |  ");
                    });
                    $("." + divName).append("</br>");
                    $("." + divName).append("기준 열 : ");
                    $.each(data.list, function (index, vo) {
                        $("." + divName).append("<input type=\"checkbox\" name=\"check2\" class=\"check\" value=\"${vo.COLUMN_NAME}\">" + "   " + vo.COLUMN_NAME + "  |  ");
                    });
                }
                if (divName == 'macro2'){
                    $("." + divName).append("받을 열 : ");
                    $.each(data.list, function (index, vo) {
                        $("." + divName).append("<input type=\"checkbox\" name=\"check3\" class=\"check\" value=\"${vo.COLUMN_NAME}\">" + "   " + vo.COLUMN_NAME + "  |  ");
                    });
                    $("." + divName).append("</br>");
                    $("." + divName).append("기준 열 : ");
                    $.each(data.list, function (index, vo) {
                        $("." + divName).append("<input type=\"checkbox\" name=\"check4\" class=\"check\" value=\"${vo.COLUMN_NAME}\">" + "   " + vo.COLUMN_NAME + "  |  ");
                    });
                }
                if (divName == 'macro3'){
                    $("." + divName).append("받을 열 : ");
                    $.each(data.list, function (index, vo) {
                        $("." + divName).append("<input type=\"checkbox\" name=\"check5\" class=\"check\" value=\"${vo.COLUMN_NAME}\">" + "   " + vo.COLUMN_NAME + "  |  ");
                    });
                    $("." + divName).append("</br>");
                    $("." + divName).append("기준 열 : ");
                    $.each(data.list, function (index, vo) {
                        $("." + divName).append("<input type=\"checkbox\" name=\"check6\" class=\"check\" value=\"${vo.COLUMN_NAME}\">" + "   " + vo.COLUMN_NAME + "  |  ");
                    });
                }
                if (divName == 'macro4'){
                    $("." + divName).append("받을 열 : ");
                    $.each(data.list, function (index, vo) {
                        $("." + divName).append("<input type=\"checkbox\" name=\"check7\" class=\"check\" value=\"${vo.COLUMN_NAME}\">" + "   " + vo.COLUMN_NAME + "  |  ");
                    });
                    $("." + divName).append("</br>");
                    $("." + divName).append("기준 열 : ");
                    $.each(data.list, function (index, vo) {
                        $("." + divName).append("<input type=\"checkbox\" name=\"check8\" class=\"check\" value=\"${vo.COLUMN_NAME}\">" + "   " + vo.COLUMN_NAME + "  |  ");
                    });
                }
                if (divName == 'macro5'){
                    $("." + divName).append("받을 열 : ");
                    $.each(data.list, function (index, vo) {
                        $("." + divName).append("<input type=\"checkbox\" name=\"check9\" class=\"check\" value=\"${vo.COLUMN_NAME}\">" + "   " + vo.COLUMN_NAME + "  |  ");
                    });
                    $("." + divName).append("</br>");
                    $("." + divName).append("기준 열 : ");
                    $.each(data.list, function (index, vo) {
                        $("." + divName).append("<input type=\"checkbox\" name=\"check10\" class=\"check\" value=\"${vo.COLUMN_NAME}\">" + "   " + vo.COLUMN_NAME + "  |  ");
                    });
                }
            }, "json");
    }

    function checkFileType(filePath) {
        var fileFormat = filePath.split(".");
        if (fileFormat.indexOf("csv") > -1) {
            return true;
        } else {
            return false;
        }
    }

    function check() {
        var file = $("#csvFile").val();
        if (file == "" || file == null) {
            alert("파일을 선택해주세요.");
            return false;
        } else if (!checkFileType(file)) {
            alert("엑셀 파일만 업로드 가능합니다.");
            return false;
        }
        if (confirm("업로드 하시겠습니까?")) {
            var options = {
                success: function (data) {
                    console.log(data);
                    alert("모든 데이터가 업로드 되었습니다.");
                    javascript:history.go(0);
                },
                beforeSend: function () {
                    // console.log("start");
                    $('.wrap-loading').removeClass('display-none');
                },
                complete: function () {
                    console.log("complete");
                    $('.wrap-loading').addClass('display-none');
                    javascript:history.go(0);
                },
                type: "POST"
            };
            $("#excelUploadForm").ajaxSubmit(options);
        }
        // window.location.reload()
    }



    $('#aidatatable').change(function () {
        var tname = $('#aidatatable option:selected').val();
        var rper = $('#airowper option:selected').val();
        console.log(tname)
        $.ajax({
            url: "${root}/table/list?tableName=" + tname+"&rowsPer="+rper,
            type: "get"
        }).done(function (result) {
            $("#boardList").html(result);

        }).fail(function (jqXHR, textStatus, errorThrown) {
            console.log("에러");
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        });
    });
    function pagingBoard(no) {
        //@RequestParam(defaultValue = "1") int pageNo, @RequestParam String tableName, @RequestParam (defaultValue = "10")int rowsPer, Model model) throws IOException {
        var tname = $('#aidatatable option:selected').val();
        var rper = $('#airowper option:selected').val();
        $.ajax({
            url: "${root}/table/list?pageNo="+no+"&tableName="+tname+"&rowsPer="+rper,
            type: "get"
        }).done(function (result) {
            console.log("결과확인");
            $("#boardList").html(result);

        }).fail(function (jqXHR, textStatus, errorThrown) {
            console.log("에러");
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        });

    }

    $("#sidebarToggle").on('click', function(e) {
        $("body").toggleClass("sidebar-toggled");
        $(".sidebar").toggleClass("toggled");
    });
</script>
</html>