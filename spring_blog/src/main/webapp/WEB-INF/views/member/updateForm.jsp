<%@ page contentType="text/html; charset=UTF-8" %>

 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('sample6_address').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('sample6_address2').focus();
            }
        }).open();
    }
</script>
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 20px; 
} 
.td1{
	text-align: center;
	width: 100px;
}
</style> 
<script type="text/javascript">
function inputCheck(f){
	
	if(f.email.value==""){
		alert("이메일을 입력하세요.");
		f.email.focus();
		return false;
	}
	if(f.job.value=="0"){
		alert("직업을 선택해 주세요.");
		f.job.focus();
		return false;
	}
	
}

function emailCheck2(i){
	alert("이메일을 변경하시려면 이메일 중복확인\n버튼을 클릭하세요");
	i.blur();
	
}
function emailCheck(email){
	if(email==""){
		alert("이메일을 입력하세요.");
		document.frm.email.focus();
	}else{
		var url = "${pageContext.request.contextPath}/member/email_proc";
		url +="?email="+email;
		wr = window.open(url,"이메일 검색","width=350,height=360");
		wr.moveTo((window.screen.width-350)/2,(window.screen.height-360)/2)
	}
	
}
</script>

<link href="${pageContext.request.contextPath}/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>

 
<DIV class="title">회원수정</DIV>
 
<FORM name='frm' method='POST' action='./update' 
	 onsubmit="return inputCheck(this)">
	 <input type="hidden" name="id" value="${id}">
	 <input type="hidden" name="col" value="${param.col}">
	 <input type="hidden" name="word" value="${param.word}">
	 <input type="hidden" name="nowPage" value="${param.nowPage}">
	 
  <TABLE style="width: 30%">
    <TR>
      <TD colspan="3" class="td1">
      <img style="width: 250px; height: 250px;" src="./storage/${dto.fname}">
      </TD>
    </TR>
    <TR>
      <TH>*아이디</TH>
      <TD>${dto.id} </TD>
    </TR>
    <TR>
      <TH>*이름</TH>
      <TD>${dto.mname}</TD>
    </TR>
    <TR>
      <TH>전화번호</TH>
      <TD><input type="text" name="tel" size="15" value="${dto.tel}"></TD>
    </TR>
    <TR>
      <TH>*email</TH>
      <TD colspan="2"><input type="text" name="email" size="20" value="${dto.email}" onclick="emailCheck2()">
      <input type="button" value="Email중복확인" onclick="emailCheck(document.frm.email.value)"></td>
    </TR>
    <TR>
      <TH>우편번호</TH>
      <TD><input type="text" name="zipcode" size="7" id="sample6_postcode" value="${dto.zipcode}" placeholder="우편번호">
      <input type="button" value="우편번호찾기" onclick="sample6_execDaumPostcode()"></td>
    </TR>
    <TR>
      <TH>주소</TH>
      <TD><input type="text" name="address1" size="25" id="sample6_address" value="${dto.address1}" placeholder="주소"><br>
      				  <input type="text" name="address2" size="25" id="sample6_address2" value="${dto.address2}" placeholder="상세주소"></td>
    </TR>
    <TR>
      <TH>*직업</TH>
      <TD>
      <select name="job">
      <option value="0">선택하세요</option>
      <option value="A01">회사원</option>
      <option value="A02" selected>전산관련직</option>
      <option value="A03">연구전문직</option>
      <option value="A04">각종학교학생</option>
      <option value="A05">일반자영업</option>
      <option value="A06">공무원</option>
      <option value="A07">의료인</option>
      <option value="A08">법조인</option>
      <option value="A09">종교/언론/예술인</option>
      <option value="A010">기타</option>

      </select>
      <script type="text/javascript">
      document.frm.job.value='${dto.job}'
      </script>
      </TD> 
    </TR>
    
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='회원수정'>
    <input type='button' value='돌아가기' onclick="history.back()">
  </DIV>
</FORM>
 

</body>
<!-- *********************************************** -->
</html> 