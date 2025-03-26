// 정규식
const REGEX_NUMBER = /^[0-9]+$/; // 정수
const REGEX_REAL_NUMBER = /^[\d]*\.?[\d]{0,2}$/; // 소수점 두자리 실수
const REGEX_BIRTH = RegExp(/^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/); // yyyy-MM-dd
const REGEX_EMAIL = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/; // Email
const REGEX_URL = /^(http|https):\/\/[\w\-]+(\.[\w\-]+)+[/#?]?.*$/;

/**
 * 기본 알림 모달
 */
function showModalAlert(title, body) {
	document.querySelector("#modalAlertTitle").textContent = title;
	document.querySelector("#modalAlertBody").textContent = body;
	$("#modalAlert").modal("show"); 
}

/**
 * 데이터 삭제 알림 모달
 */
function showModalDeleteConfirm(title, body, showBtnUelete, showBtnDelete) {
	document.querySelector("#modalConfirmTitle").textContent = title;
	document.querySelector("#modalConfirmBody").textContent = body;
	document.querySelector("#btnModalUelete").style.display = showBtnUelete;
	document.querySelector("#btnModalDelete").style.display = showBtnDelete;
	$("#modalConfirm").modal("show");      	
}

/**
 * 선택된 체크박스 갯수 세기
 */
function getSelectedElementsCnt(name) {
	return document.querySelectorAll('input[name="' + name + '"]:checked').length;
}

/**
 * 체크박스 전체 선택/선택 해제
 */
function allCheck(name, isChecked) {
	const element = document.getElementsByName(name);
	
	for(let i = 0; i < element.length; i++) {          
		if(!element[i].disabled) {
			element[i].checked = isChecked;	
		}
	}
}