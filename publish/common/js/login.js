window.onload = function () {

  const form = document.getElementById('login');
  const id = document.getElementById('email');
  const pw = document.getElementById('pw');

  form.addEventListener('submit', (e) => {
    e.preventDefault();
    checkInputs();
  });

  function checkInputs() {

    const idValue = id.value.trim();
    const pwValue = pw.value.trim();

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
}




