window.onload = function () {

// 변수선언

  var userName = document.querySelector('#pf_name');
  var pw = document.querySelector('#new_password');
  var phone = document.querySelector('#phone_input1');
  var error = document.querySelectorAll('.error');

// 이벤트 핸들러

  userName.addEventListener("focusout", checkName);
  pw.addEventListener("focusout", checkPw);
  phone.addEventListener("focusout", checkPhoneNum);

// 콜백 함수

  function checkName() {
    var namePattern = /^[a-zA-Z0-9가-힣]{2,15}$/;
    if (!namePattern.test(userName.value) || userName.value.indexOf(" ") > -1) {
      error[0].innerHTML = "최소 두글자 이상 입력 해주세요."
      error[0].style.display = "block";
      userName.style.borderColor = "#e74c3c";
    } else {
      error[0].style.display = "none";
      userName.style.borderColor = "#4147e1";
    }
  }

  function checkPhoneNum() {
    var isPhoneNum = /^[0-9]{11}$/;
    if (!isPhoneNum.test(phone.value)) {
      error[1].innerHTML = "형식에 맞는 번호를 입력 해주세요."
      error[1].style.display = "block";
      phone.style.borderColor = "#e74c3c";
    } else {
      error[1].style.display = "none";
      phone.style.borderColor = "#4147e1";
    }
  }

  function checkPw() {
    var pwPattern = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{6,15}/;
    if (!pwPattern.test(pw.value)) {
      error[2].innerHTML = "영문,숫자,특수문자 포함 6-15자를 입력 해주세요."
      error[2].style.display = "block";
      pw.style.borderColor = "#e74c3c";
    } else {
      error[2].style.display = "none";
      pw.style.borderColor = "#4147e1";

    }
  }

}; 



