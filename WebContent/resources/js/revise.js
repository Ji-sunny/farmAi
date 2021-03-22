
const imgChange = () =>{
    console.log("imgchane");

};

function mainInfoDto(){
    $.ajax({
        url:"mainInfoDto",
        method:"get",
        
        success:function(data){
        	console.log("양식 불러오기 성공");
            $("#main_infoForm").html(data);
        }
    });
};

const img = () => {
	$("main_img").attr("src","https://media.magazine.ferrari.com/images/2019/03/25/112037181-ade321bc-2a1b-4a66-952f-d845c44073e8.jpg");
	console.log("change");
};

function setImg(event) { 
	var reader = new FileReader(); 
	reader.onload = function(event) {
		var img = document.createElement("img"); 
		 img.setAttribute("src", event.target.result); 
	document.querySelector("#new_img").appendChild(img);
				
};
$("#main_img").hide();         
reader.readAsDataURL(event.target.files[0]); 
}

const addColor = () =>{
	var pColor = $("#typeColor");
	var colorVal =pColor.val(); //입력된 색상
	const opt = event.target.value; //추가 or 삭제
	if(opt=="추가"){
		if(colorVal==""){
			window.alert("추가 색상을 입력해야 합니다");
		}
		else{
			$("#color_select").append("<option id=" +colorVal+ ">" +colorVal+ "</option>");
			console.log("추가된 옵션의  id:"+ colorVal);
		}
	}
}
const removeColor = () => {
	console.log($("#color_select").value);
}
const addOption = () =>{
	var pOpt = $("#typeOption");
	var optVal =pOpt.val(); //입력된 색상
	const opt = event.target.value; //추가 or 삭제
	if(opt=="추가"){
		if(optVal==""){
		}
		else{
			$("#productOption").append("<option id=" +optVal+ ">" +optVal+ "</option>");
			console.log("추가된 옵션의  id:"+ optVal);
		}
	}
}

const detailText = function(){
				    $.ajax({
				        url:"detailText",
				        method:"get",
				        success: function(data){
				            $(".textDiv").html(data);
				        }
				    })
			}


	const saveConfirm = function() {
	console.log(event);
	/* event.preventDefault(); */

	const brand = $("#brand");
	const productName = $("#product_name");
	const productPrice = $("#product_price");
	const deliveryDate = $("#delivery_date");
	const deliveryFee = $("#delivery_fee");
	const deliveryMethod = $("#delivery_method");
	const productCode = $("#product_code");

	if (brand.val() === "" | productName.val() === "" | productPrice.val() === "" |
		deliveryDate.val() === "" | deliveryFee.val() === "" | deliveryMethod.val() === "" | productCode.val() === "") {
		const uidError = $("#uidError");
		window.alert("모두 입력되어야합니다");
		return;
	}
	else {
		//서버로 데이터 전송
		//const reviseForm = document.reviseForm; //form에 name 저장되어 있으면 바로 가져올 수 있음.
		const result = window.confirm("수정을 완료하시겠습니까?");
		if(result){
		    self.location.href="saveupdate";
		    
		/*$(function(){
		        $.ajax({
		            url:"saveupdate",
		            method: "get"
		        })
		    }) */
		}
	}
}