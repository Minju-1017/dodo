const regex_number = /^[0-9]+$/;
const regex_email = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/; //(알파벳,숫자)@(알파벳,숫자).(알파벳,숫자)
const regex_birth = RegExp(/^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/);

/**
 * 기본 알림 모달
 */
function showModal(modalId, contetnInnerText) {
	modalContent.innerText = contetnInnerText; // 해당 아이디값의 텍스트 변경하기
	
	$(modalId).modal("show");
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