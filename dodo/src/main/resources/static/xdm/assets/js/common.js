/**
 * Validation
 */

// 정규식
const REGEX_NUMBER = /^[0-9]+$/; // 정수
const REGEX_REAL_NUMBER = /^[\d]*\.?[\d]{0,2}$/; // 소수점 두자리 실수
const REGEX_yyyyMMdd = RegExp(/^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/);
const REGEX_EMAIL = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/; // Email
const REGEX_URL = /^(http|https):\/\/[\w\-]+(\.[\w\-]+)+[/#?]?.*$/;

// Validation 초기화
function resetValidation(obj) {
	obj.classList.remove("is-valid");
	obj.classList.remove("is-invalid");
}

// 문자열 체크 - 빈값, null
function strValidation(obj) {
	var value = obj.value.trim();
	
	if (value == null || value == "") return false;
	
	return true;
}

// URL 체크
function urlValidation(obj) {
	var value = obj.value.trim();
	
	return REGEX_URL.test(value);
}

// Email 체크
function emailValidation(obj) {
	var value = obj.value.trim();
	
	if (value == null || value == "" || !REGEX_EMAIL.test(value)) return false;
	
	return true;
}

// 날짜 체크(yyyy-MM-dd)
function dateValidation_yyyyMMdd(obj) {
	var value = obj.value.trim();
	
	if (value == null || value == "" || !REGEX_yyyyMMdd.test(value)) return false;
	
	return true;
}

// 숫자 체크 - 정수, 빈값, null, 0이하(양의 정수만 가능)
function positiveNumberValidation(obj) {
	var value = obj.value.trim();
							
	if (value == null || value == ""
			|| !REGEX_NUMBER.test(value) || parseInt(value) < 1 || isNaN(value)) return false;
	
	return true;
}

// 숫자 체크 - 실수, 빈값, null
function realNumberValidation(obj) {
	var value = obj.value.trim();
						
	if (value == null || value == ""
			|| !REGEX_REAL_NUMBER.test(value) || isNaN(value)) return false;
	
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
function ueleteCheckedElements(goUrl) {
	var checkArr = new Array;
					
	$('input:checkbox[name=check]:checked').each(function () {
		checkArr.push($(this).val());
    })
	  
	// TODO: 체크한 리스트 삭제(업데이트) 처리
	// submit(goUrl);
}

// 삭제
function deleteCheckedElements(goUrl) {
	var checkArr = new Array;
					
	$('input:checkbox[name=check]:checked').each(function () {
		checkArr.push($(this).val());
    })
    
	// TODO: 체크한 리스트 삭제 처리
	// submit(goUrl);		
}