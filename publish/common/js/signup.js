window.onload = function () {

  const form = document.getElementById('signup');
  const id = document.getElementById('sign_email');
  const pw = document.getElementById('sign_pw');
  const pw2 = document.getElementById('sign_pw_confirm');
  const userName = document.getElementById('sign_name');
  const phone = document.getElementById('sign_phone');

  form.addEventListener('submit', (e) => {
    e.preventDefault();
    checkInputs();
  });

  function checkInputs() {

    const idValue = id.value.trim();
    const pwValue = pw.value.trim();
    const pw2Value = pw2.value.trim();
    const userNameValue = userName.value.trim();
    const phoneValue = phone.value.trim();

    if (idValue === '') {
      setErrorFor(id, '필수 정보입니다');
    } else if (!isId(idValue)) {
      setErrorFor(id, '이메일을 다시 확인해 주세요');
    } else {
      setSuccessFor(id);
    }

    if (pwValue === '') {
      setErrorFor(pw, '필수 정보입니다');
    } else if (!isPassword(pwValue)) {
      setErrorFor(pw, '영문,숫자,특수문자 포함 6-15자를 입력 해주세요.');
    } else {
      setSuccessFor(pw);
    }

    if (pw2Value === '') {
      setErrorFor(pw2, '필수 정보입니다.');
    } else if (pwValue !== pw2Value) {
      setErrorFor(pw2, '비밀번호가 일치하지 않습니다.');
    } else {
      setSuccessFor(pw2);
    }

    if (userNameValue === '') {
      setErrorFor(userName, '필수 정보입니다.');
    } else {
      setSuccessFor(userName);
    }

    if (phoneValue === '') {
      setErrorFor(phone, '필수 정보입니다.');
    } else if (!isPhone(phoneValue)) {
      setErrorFor(phone, '형식에 맞지 않는 번호입니다.');
    } else {
      setSuccessFor(phone);
    }
  }

  function setErrorFor(input, message) {
    const fieldWrap = input.parentElement;
    const small = fieldWrap.querySelector('small');
    fieldWrap.className = 'field_wrap error';
    small.innerText = message;
  }

  function setSuccessFor(input) {
    const fieldWrap = input.parentElement;
    fieldWrap.className = 'field_wrap success';
  }

  function isId(id) {
    return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(
        id);
  };

  function isPassword(pw) {
    return /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{6,15}/.test(pw);
  };

  function isPhone(phone) {
    return /^[0-9]{11}$/.test(phone);
  };

}




