<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>

<html>
   <head>
      <meta charset="UTF-8">
      <title>aiproject</title>
    	<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
       <script src="http://malsup.github.com/jquery.form.js"></script>
   </head>
   <script>
       function checkFileType(filePath) {
           var fileFormat = filePath.split(".");
           if (fileFormat.indexOf("csv") > -1 || fileFormat.indexOf("xlsx") > -1) {
               return true;
           } else {
               return false;
           }
       }

       function check() {
           var file = $("#excelFile").val();
           if (file == "" || file == null) {
               alert("파일을 선택해주세요.");
               return false;
           } else if (!checkFileType(file)) {
               alert("엑셀 파일만 업로드 가능합니다.");
               return false;
           }

           if (confirm("업로드 하시겠습니까?")) {
               var options = {
                   success : function(data) {
                       console.log(data);
                       alert("모든 데이터가 업로드 되었습니다.");
                   },
                   type : "POST"
               };
               $("#excelUploadForm").ajaxSubmit(options);
           }
       }
   </script>
   <body>
        <h2>hello</h2>
        <form id="excelUploadForm" name="excelUploadForm" enctype="multipart/form-data"
              method="post" action= "excelUploadAjax">
            <div class="contents">
                <div>첨부파일은 한개만 등록 가능합니다.</div>
                <dl class="vm_name">
                    <dt class="down w90">첨부 파일</dt>
                    <dd><input id="excelFile" type="file" name="excelFile" /></dd>
                </dl>
            </div>
            <div class="bottom">
                <button type="button" id="addExcelImpoartBtn" class="btn" onclick="check()" ><span>추가</span></button>
            </div>
        </form>
   </body>
</html>