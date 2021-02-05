<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/common/common_lib.jsp" %>
<title>아짜스</title>
<script>
$(function(){
$('#makeBtn').on('click',function(){
$('#reqData').empty();
if($('#type').val() == "form"){
//$('#reqData').text($('#frm').serialize());
$('#reqData').text("userid="+$('input[name=userid]').val()+ "&usernm="+$('input[name=usernm]').val());
}else{

}
})

$('#send').on('click', function(){
$.ajax({
url : '/ajax/form',
type : 'post',
data : $('#frm').serialize(),
dataType : 'json',
success : function(res){
console.log(res);
}
})
})
})
</script>
</head>
<body>
<form id="frm">
사용자 아이디 : <input type="text" name="userid" value="brown"/><br>
사용자 이름 : <input type="text" name="usernm" value="브라운"/><br>
<select id="type">
<option value="form">form전송</option>
<option value="json">json전송</option>
</select> <input type="button" id="makeBtn" value="전송데이터생성"/><br>
</form>
<h4>전송데이터</h4>
<span id="reqData"></span><br>

<h4>응답데이터</h4>
<span id="respData"></span><br>

<input type="button" id="send" value="전송"/>
</body>
</html>
