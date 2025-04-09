/**
 * Validation
 */

// 정규식
const REGEX_KOR_ENG_NUMBER = /^[a-z|A-Z|0-9|ㄱ-ㅎ|가-힣|\s]+$/;
const REGEX_ENG_NUMBER = /^[a-z|A-Z|0-9|\s]+$/;
const REGEX_NUMBER = /^[0-9]+$/; // 정수
const REGEX_REAL_NUMBER = /^[\d]*\.?[\d]{0,2}$/; // 소수점 두자리 실수
const REGEX_yyyyMMdd = RegExp(/^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/);
const REGEX_EMAIL = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/; // Email
const REGEX_URL = /^(http|https):\/\/[\w\-]+(\.[\w\-]+)+[/#?]?.*$/;
const REGEX_IMG_FILE = /(.*?)\.(jpg|jpeg|png|gif|JPG|JPEG|PNG|GIF)$/;

// 이미지 파일 최대 사이즈
const IMG_MAX_SIZE = 2 * 1024 * 1024; // 2MB

// Validation 초기화
function resetValidation(obj) {
	obj.classList.remove("is-valid");
	obj.classList.remove("is-invalid");
}

// 문자열 체크 - 빈값, null
function strValidation(obj) {
	if (obj == null || (obj != null && obj.value == null)) return false;
	
	var value = obj.value.trim();
	if (value == "") return false;
	
	return true;
}

// 문자열 체크 - 빈값, null, 한글/영어/숫자만
function strKorEngNumberValidation(obj) {
	if (obj == null || (obj != null && obj.value == null)) return false;
	
	var value = obj.value.trim();
	if (value == "" || !REGEX_KOR_ENG_NUMBER.test(value)) return false;
	
	return true;
}

// 문자열 체크 - 빈값, null, 영어/숫자만
function strEngNumberValidation(obj) {
	if (obj == null || (obj != null && obj.value == null)) return false;
	
	var value = obj.value.trim();
	if (value == "" || !REGEX_ENG_NUMBER.test(value)) return false;
	
	return true;
}

// URL 체크 - null, 빈값 허용
function urlValidation(obj) {
	if (obj == null || (obj != null && obj.value == null)) return true;
	
	var value = obj.value.trim();
	return REGEX_URL.test(value);
}

// Email 체크 - null, 빈값
function emailValidation(obj) {
	if (obj == null || (obj != null && obj.value == null)) return false;
	
	var value = obj.value.trim();
	if (value == "" || !REGEX_EMAIL.test(value)) return false;
	
	return true;
}

// 날짜 체크(yyyy-MM-dd) - null, 빈값
function dateValidation_yyyyMMdd(obj) {
	if (obj == null || (obj != null && obj.value == null)) return false;
	
	var value = obj.value.trim();
	if (value == "" || !REGEX_yyyyMMdd.test(value)) return false;
	
	return true;
}

// 숫자 체크 - 정수, 빈값, null, 0이하(양의 정수만 가능)
function positiveNumberValidation(obj) {
	if (obj == null || (obj != null && obj.value == null)) return false;
	
	var value = obj.value.trim();				
	if (value == ""
			|| !REGEX_NUMBER.test(value) || parseInt(value) < 1 || isNaN(value)) return false;
	
	return true;
}

// 숫자 체크 - 실수, 빈값, null
function realNumberValidation(obj) {
	if (obj == null || (obj != null && obj.value == null)) return false;
	
	var value = obj.value.trim();		
	if (value == ""
			|| !REGEX_REAL_NUMBER.test(value) || isNaN(value)) return false;
	
	return true;
}

// 이미지 파일 체크 - 이미지 파일 형식(jpg, jpeg, gif, png), 사이즈(2MB), null/빈값 허용
// Object null, "" 체크는 호출전에 함
function imgFileValidation(fileObj, fileNameInputObj) {
	var value = fileNameInputObj.value.trim();
	if(!value.match(REGEX_IMG_FILE) || fileObj.size >= IMG_MAX_SIZE) return false;
	
	return true;
}
///////////////////////////////////////////////////////////////////////////////////////

/**
 * 모달
 */

// 기본 알림 모달
function showModalAlert(title, body) {
	document.querySelector("#modalAlertTitle").textContent = title;
	document.querySelector("#modalAlertBody").textContent = body;
	$("#modalAlert").modal("show"); 
}

// 데이터 삭제 알림 모달
function showModalDeleteConfirm(title, body, showBtnUelete, showBtnDelete) {
	document.querySelector("#modalConfirmTitle").textContent = title;
	document.querySelector("#modalConfirmBody").textContent = body;
	document.querySelector("#btnModalUelete").style.display = showBtnUelete;
	document.querySelector("#btnModalDelete").style.display = showBtnDelete;
	document.querySelector("#btnModalWithdrawal").style.display = 'none';
	$("#modalConfirm").modal("show");      	
}

// 데이터 삭제 알림 모달(회원탈퇴)
function showModalWithdrawalConfirm(title, body) {
	document.querySelector("#modalConfirmTitle").textContent = title;
	document.querySelector("#modalConfirmBody").textContent = body;
	document.querySelector("#btnModalUelete").style.display = 'none';
	document.querySelector("#btnModalDelete").style.display = 'none';
	document.querySelector("#btnModalWithdrawal").style.display = '';
	$("#modalConfirm").modal("show");      	
}

///////////////////////////////////////////////////////////////////////////////////////

/**
 * 테이블 체크박스 처리
 */

// 선택된 체크박스 갯수 세기
function getCheckedElementsCnt(name) {
	return document.querySelectorAll('input[name="' + name + '"]:checked').length;
}

// 체크박스 전체 선택/선택 해제
function allCheck(name, isChecked) {
	var element = document.getElementsByName(name);
	
	for(let i = 0; i < element.length; i++) {          
		if(!element[i].disabled) {
			element[i].checked = isChecked;	
		}
	}
}

// 체크한 데이터 수정
function modifyCheckedElement(str) {
	const selectedElementsCnt = getCheckedElementsCnt("check");

	if (selectedElementsCnt == 0) return;
	
	if (selectedElementsCnt > 1) {
		showModalAlert("확인", str + "만 선택해 주세요.");
		return;
	}
	
	$('input:checkbox[name=check]:checked').each(function () {
        goForm($(this).val());
    })
}

// 삭제(업데이트) - 체크한 데이터 리스트 모달창 띄우기
function ueleteCheckedElementsModal(str) {
	const selectedElementsCnt = getCheckedElementsCnt("check");
					
	if (selectedElementsCnt == 0) return;
	
	showModalDeleteConfirm(
			"확인", 
			"선택한 " + str + " 전부 삭제(업데이트)하시겠습니까?", 
			'', 'none'
	);
}

// 삭제 - 체크한 데이터 리스트 모달창 띄우기
function deleteCheckedElementsModal(str) {
	const selectedElementsCnt = getCheckedElementsCnt("check");
					
	if (selectedElementsCnt == 0) return;
	
	showModalDeleteConfirm(
			"확인", 
			"선택한 " + str + " 전부 삭제하시겠습니까?", 
			'none', ''
	); 
}

// 삭제(업데이트)
function ueleteCheckedElements(goUrl, successUrl) {
	var checkArr = new Array;
					
	$('input:checkbox[name=check]:checked').each(function () {
		checkArr.push($(this).val());
    })
	  
	$.ajax({
		async: true 
		,cache: false
		,type : "post"
		,url : goUrl
		,traditional: true
		,data : { chbox : checkArr }
		,success: function(response) {
			if(response.rt == "success") {
				location.href = successUrl;
			} else {
				showModalAlert("확인", "삭제(업데이트) 실패하였습니다.");
			}
		}
		,error : function(jqXHR, textStatus, errorThrown){
			alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
		}
	});	
}

// 삭제
function deleteCheckedElements(goUrl, successUrl) {
	var checkArr = new Array;
						
	$('input:checkbox[name=check]:checked').each(function () {
		checkArr.push($(this).val());
    })
	  
	$.ajax({
		async: true 
		,cache: false
		,type : "post"
		,url : goUrl
		,traditional: true
		,data : { chbox : checkArr }
		,success: function(response) {
			if(response.rt == "success") {
				location.href = successUrl;
			} else {
				showModalAlert("확인", "삭제 실패하였습니다.");
			}
		}
		,error : function(jqXHR, textStatus, errorThrown){
			alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
		}
	});	
}

///////////////////////////////////////////////////////////////////////////////////////

/**
 * Date Time Picker 처리
 */
jQuery.datetimepicker.setLocale('ko');

jQuery(function() {
	jQuery('#shDateStart').datetimepicker({
		format:'Y-m-d',
		timepicker:false,
		onShow:function(ct) {
			this.setOptions({
				maxDate: jQuery('#shDateEnd').val() ? jQuery('#shDateEnd').val() : false
			})
		}
	 });
	
	jQuery('#shDateEnd').datetimepicker({
		/*datepicker:false,
		format:'H:i',*/
		format:'Y-m-d',
		timepicker:false,
		onShow:function(ct) {
			this.setOptions({
				minDate: jQuery('#shDateStart').val() ? jQuery('#shDateStart').val() : false
			})
		}
	});
	
	jQuery('#mBirth').datetimepicker({
		format:'Y-m-d',
		timepicker:false,
		//minDate:'-1970/01/02',//yesterday is minimum date(for today use 0 or -1970/01/01)
		maxDate:'+1970/01/01', //tomorrow is maximum date calendar
		onChangeDateTime:function(dp, $input) {
			if ($input.val() != null && $input.val() != '' 
				&& !mBirth.classList.contains("is-valid")) {
				mBirth.classList.add("is-valid");
			}
		}
	});
});