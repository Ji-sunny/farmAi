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
<div id="gettable">
    <div class="row">
        <div class="col-sm-12">
            <a class="btn btn-primary btn-sm"  href="javascript:jsonresponse1()">객체 받기</a>
            <!-- controller에 있는 {}를 받음 -->
            <script type="text/javascript">
                const jsonresponse1 = function() {
                    $.ajax({
                        url: "${root}/modeling/table",
                        method:"post",
                        success: function(data) {
                            console.log(data);
                            $("#rec").html(data.rec);
                            $("#accuracy").html(data.accuracy);
                        }
                    });
                };
            </script>
            <div id="result2">
                <div>recall: <span id="rec"></span> </div>
                <div>accuracy: <span id="accuracy"></span></div>
            </div>
        </div>
    </div>
</div>


