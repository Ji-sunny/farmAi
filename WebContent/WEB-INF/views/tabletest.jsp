<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2021-04-08
  Time: 오후 8:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.amcharts.com/lib/4/core.js"></script>
<script src="https://cdn.amcharts.com/lib/4/charts.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/frozen.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/animated.js"></script>
<div id="gettable">
    <div class="row">
        <div class="col-sm-12">
            <a class="btn btn-primary btn-sm"  href="javascript:jsonresponse1()">객체 받기</a>
            <select name="marcodoneName" id="marcodoneName1" class=""
                    data-live-search="true" >
                <option value="">선택</option>

            </select>
            <a class="btn btn-primary btn-sm"  href="javascript:barchart()"> bar html</a>
            <script type="text/javascript">
                $(document).ready(function () {
                    console.log("시작");
                    $.get("${root}/macro/test",
                        function (data, status) {
                            console.log(data)
                            // $.each(data.list, function (index, vo) {
                            //     // console.log("1", index)
                            //     // console.log("2", vo)
                            //     $("#marcodoneName1").append(
                            //         "<option value='" + vo.tablesName + "'>" + vo.filesName + "</option>");
                            // });
                        }, "json");
                });
                const jsonresponse1 = function() {
                    $.ajax({
                        url: "${root}/modeling/table",
                        method:"post",
                        success: function(data) {
                            // console.log(data);
                            $("#rec").html(data.rec);
                            $("#accuracy").html(data.accuracy);

                        }
                    });
                };
                const barchart = function () {
                    $.ajax({
                        url: "${root}/modeling/bar",
                        method: "get",
                        success: function (data) {
                            alert("성공");
                            $("#result").html(data);
                        }
                    })
                }
            </script>
            <div>
                <div id="name">recall: <span id="rec"></span></div>
                <div id="age">accuarcy: <span id="accuracy"></span></div>
            </div>

            <div id="result"> bar</div>
        </div>
    </div>
</div>


