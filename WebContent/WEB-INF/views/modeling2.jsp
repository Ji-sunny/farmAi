<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
            <a class="nav-link collapsed" href="${root}/modeling1" aria-expanded="true">
                <i class="fas fa-chart-bar"></i>
                <span>Modeling1</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="${root}/modeling2" aria-expanded="true">
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

                                <div class="col-sm-12 col-md-2">
                                    <select name="evaluation" id="evaluation"
                                            class="custom-select custom-select-sm form-control form-control-sm"
                                            data-live-search="true">
                                        <option value="">선택</option>
                                    </select>

                                </div>
                                <div id="evaluationScore"></div>
                                <%--                                <div id="evaluationTable"></div>--%>
                                <div id="evaluationTablediv">
                                    <table id="evaluationTable" class="table table-bordered dataTable" width="100%"
                                           cellspacing="0" role="grid" aria-describedby="dataTable_info"
                                           style="width: 100%;">
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
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">시각화</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                                <div class="row">
                                    <div class="col-sm-12 col-md-8">
                                        <div class="dataTables_length">
                                            <form>
                                                <label>시각화 검색:
                                                    <select id="visualization" name="visualization"
                                                            class="custom-select custom-select-sm form-control form-control-sm"
                                                            data-live-search="true" onchange="getModelName()">
                                                        <option value="">선택</option>
                                                    </select>
                                                    <div class="macroCols"></div>
                                                </label>
                                                <input type="button" class="btn-primary btn-sm" value="시각화 버튼"
                                                       onclick="getVisualStyle()">
                                            </form>
                                        </div>
                                    </div>
                                </div>


                                <div id="image">

                                </div>
                                <div id="bardiv">

                                </div>
                                <div id="chart">

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

<!--chart-->
<script src="https://cdn.amcharts.com/lib/4/core.js"></script>
<script src="https://cdn.amcharts.com/lib/4/charts.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/frozen.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/animated.js"></script>


</body>
<script>
    $(document).ready(function () {
        $.get("${root}/visual/evaluationList",
            function (data, status) {
                $.each(data.list, function (index, vo) {
                    $("#evaluation").append(
                        "<option value='" + vo.macroName + "'>" + vo.macroName + "</option>");
                });
                $.each(data.list, function (index, vo) {
                    $("#visualization").append(
                        "<option value='" + vo.kind + "' mName ='" + vo.macroName + "'>" + vo.macroName + "</option>");
                });
            }, "json");
        $("#evaluationTablediv").hide();
    });
    $('#evaluation').change(function () {
        var tname = $('#evaluation option:selected').val();
        if (tname == "") {
            $("#evaluationTablediv, #evaluationScore").hide();
        } else {
            $("#evaluationTablediv, #evaluationScore").show();
        }

        $("#evaluationScore").empty();
        $("#evaluationTable").DataTable({
            destroy: true,
            searching: false,
            ajax: {url: "${root}/visual/evaluationList/" + tname, dataSrc: 'lists'},
            columns: [
                {data: " "},
                {data: "precision"},
                {data: 'recall'},
                {data: 'f1-score'},
                {data: 'support'}
            ]
        });
        $.ajax({
            url: "${root}/visual/evaluationList/" + tname,
            type: "get",
            success: function (res) {
                $("#evaluationScore").append("score = ").append(res.list.score);
                var results = res.list.report;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
                self.close();
            }
        });
    });

    function getModelName() {
        const name = $('#visualization option:selected').text();
        $(".macroCols").empty();
        $.get("${root}/macro/modelName/" + name,
            function (res, status) {
                const modelName = res.list.modelName;
                const colsX = res.list.colsX;
                if (modelName === 'logisticregression') {
                    const words = colsX.split(',');
                    $.each(words, function (index, vo) {
                        $(".macroCols").append(vo + " : " + "<input type=\"number\" step=\"0.0000000001\" name=\"predCols\" class=\"\" value=\"\">");
                        $(".macroCols").append("</br>");
                    });
                }
            }, "json");
    }

    function getVisualStyle() {
        $("#image").empty();
        $("#bardiv").empty();
        $("#chart").empty();
        const visualType = $('#visualization option:selected').val();
        const mName = $('#visualization option:selected').text();
        const visualArray = {
            "visualMname": mName,
            "predCols": [],
        };
        if (visualType === 'img') {
            $.ajax({
                url: "${root}/visual/img",
                type: 'get',
                data: visualArray,
                success: function (res) {
                    console.log("img : ", res);
                    const imgNames = res.list.split('/');
                    const imgName = "image/" + imgNames[imgNames.length - 1];
                    $("#image").append("<img src=\"${root}" + imgName + "\">");
                    self.close();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
                    self.close();
                }
            });
        }

        if (visualType === 'chart') {
            // const html = " <table id = \"dataSearchTable\" class=\"table table-bordered dataTable\" width=\"100%\" cellspacing=\"0\" role=\"grid\" aria-describedby=\"dataTable_info\" style=\"width: 100%;\">" +
            //     "<thead>" +
            //     "<tr>" +
            //     "<th>index</th>" +
            //     "<th>temp</th>" +
            //     "<th>humid</th>" +
            //     "<th>ec</th>" +
            //     "</tr>" +
            //     "</thead>" +
            //     "</table>";
            // $("#chart").append(html);

            $.ajax({
                url: "${root}/visual/chart",
                type: 'get',
                data: visualArray,
                success: function (res) {
                    console.log(res.list.keys());
                    console.log(Object.keys(res.list));

                    for (prop in res.list){
                        console.log(prop)
                    }




















                    // $("#chart").empty();
                    // $("#chart").append('<table id="displayTable" class="display" cellspacing="0" width="100%"><thead><tr>' + tableHeaders + '</tr></thead></table>');
                    // //$("#tableDiv").find("table thead tr").append(tableHeaders);  
                    //
                    // $('#displayTable').dataTable(res.list);


                    self.close();
                },
                "dataType": "json",
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
                    self.close();
                }
            });




            <%--$("#dataSearchTable").DataTable({--%>
            <%--    destroy: true,--%>
            <%--    searching: false,--%>
            <%--    ajax: {url: "${root}/visual/chart", type: 'get', data: visualArray, dataSrc: 'list'},--%>
            <%--    columns: [--%>
            <%--        {data: "0"},--%>
            <%--        {data: "1"}--%>
            <%--    ]--%>
            <%--});--%>
        }

        if (visualType === 'bar') {
            const xValues = new Array();
            $("input[name='predCols']").each(function () {
                xValues.push($(this).val());
            });
            visualArray.predCols = xValues;
            $.ajax({
                url: "${root}/visual/bar",
                type: 'get',
                data: visualArray,
                success: function (res) {
                    barJson(res.list);
                    self.close();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
                    self.close();
                }
            });
        }
    }


    function barJson(dataSet) {
        am4core.ready(function () {
            am4core.useTheme(am4themes_animated);

            var chart = am4core.create("bardiv", am4charts.XYChart);
            chart.scrollbarX = new am4core.Scrollbar();

            chart.data = dataSet;
            var topContainer = chart.chartContainer.createChild(am4core.Container);
            topContainer.layout = "absolute";
            topContainer.toBack();
            topContainer.paddingBottom = 2;
            topContainer.width = am4core.percent(100);

            let axisTitle = topContainer.createChild(am4core.Label);
            axisTitle.text = "predict";
            axisTitle.fontWeight = 600;
            axisTitle.align = "left";
            axisTitle.paddingLeft = 10;

            var label = chart.chartContainer.createChild(am4core.Label);
            label.text = "box_num";
            label.fontWeight = 600;
            label.align = "center";

            var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
            categoryAxis.dataFields.category = "box_num";
            categoryAxis.renderer.grid.template.location = 0;
            categoryAxis.renderer.minGridDistance = 30;
            categoryAxis.renderer.labels.template.horizontalCenter = "right";
            categoryAxis.renderer.labels.template.verticalCenter = "middle";
            // categoryAxis.renderer.labels.template.rotation = 270;
            categoryAxis.tooltip.disabled = true;
            categoryAxis.renderer.minHeight = 50;

            var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
            valueAxis.renderer.minWidth = 50;


            var series = chart.series.push(new am4charts.ColumnSeries());
            series.sequencedInterpolation = true;
            series.dataFields.valueY = "predict";
            series.dataFields.categoryX = "box_num";
            series.tooltipText = "[{categoryX}: bold]{valueY}[/]";
            series.columns.template.strokeWidth = 0;

            series.tooltip.pointerOrientation = "vertical";

            series.columns.template.column.cornerRadiusTopLeft = 10;
            series.columns.template.column.cornerRadiusTopRight = 10;
            series.columns.template.column.fillOpacity = 0.8;


            var hoverState = series.columns.template.column.states.create("hover");
            hoverState.properties.cornerRadiusTopLeft = 0;
            hoverState.properties.cornerRadiusTopRight = 0;
            hoverState.properties.fillOpacity = 1;

            series.columns.template.adapter.add("fill", function (fill, target) {
                return chart.colors.getIndex(target.dataItem.index);
            });


            chart.cursor = new am4charts.XYCursor();

        }); // end am4core.ready()
    }


    $("#sidebarToggle").on('click', function (e) {
        $("body").toggleClass("sidebar-toggled");
        $(".sidebar").toggleClass("toggled");
    });
</script>
<style>
    #bardiv {
        width: 100%;
        height: 500px;
    }
</style>
</html>