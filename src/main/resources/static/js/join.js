/**
 * 
 */

 function joinCheck(){
	 
	 if(document.join_frm.mid.value.length == 0) {
		 alert('아이디는 필수 입력사항입니다. 다시 입력하세요');
		 return;
	 }
	 
	 if(document.join_frm.mpw.value.length == 0) {
		 alert('비밀번호는 필수 입력사항입니다. 다시 입력하세요');
		 return;
	 }
	 
	 if(document.join_frm.mpw_check.value.length == 0) {
		 alert('비번체크는 필수 입력사항입니다. 다시 입력하세요');
		 return;
	 }
	 
	  if(document.join_frm.mpw.value != document.join_frm.mpw_check.value) {
		 alert('비밀번호가와 비번확인이 일치하지 않습니다. 다시 입력하세요');
		 return false;
	 }
	 
	 if(document.join_frm.mname.value.length == 0) {
		 alert('이름은 필수 입력사항입니다. 다시 입력하세요');
		 return;
	 }
	 
	 if(document.join_frm.memail.value.length == 0) {
		 alert('이메일은 필수 입력사항입니다. 다시 입력하세요');
		 return;
	 }
	 
	 document.join_frm.submit();	 
 }