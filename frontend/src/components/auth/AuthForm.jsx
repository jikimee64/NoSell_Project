import React from "react";
import LoginTail from "./LoginTail";
import RegisterTail from "./RegisterTail";
import {emailInfo, pwInfo} from "../../utils/auth";

const AuthForm = ({type, form, onChangeForm, onSubmit}) => {
  return (
<<<<<<< HEAD
      <form
          action="#"
          accept-charset="utf-8"
          name="login_form"
          method="post"
          id="login"
          onSubmit={onSubmit}
      >
        <h2>{type}</h2>
        <fieldset>
          <div className="field_wrap">
            <label for="email" className={!!form.email ? "active" : ""}>
              {emailInfo(form.email)}
              <span className="req">*</span>
            </label>
            <input
                type="email"
                id="email"
                name="email"
                required
                autocomplete="off"
                value={form.email}
                onChange={onChangeForm}
            />
          </div>
          <div className="field_wrap">
            <label for="pw" className={!!form.password ? "active" : ""}>
              {pwInfo(form.password)}
              <span className="req">*</span>
            </label>
            <input
                type="password"
                id="pw"
                name="password"
                required
                autocomplete="off"
                minlength="6"
                maxlength="15"
                value={form.password}
                onChange={onChangeForm}
            />
          </div>
          {type === "register" && <RegisterTail {...{form, onChangeForm}} />}
          <div className="field_wrap test">
            <input type="checkbox"/>
            <span>I'm not a robot</span>
          </div>
          <button type="submit" className="button button_block">
            {type}
          </button>
          {type === "login" && <LoginTail/>}
        </fieldset>
      </form>
=======
    <form
      action="#"
      acceptCharset="utf-8"
      name="login_form"
      method="post"
      id="login"
      onSubmit={onSubmit}
    >
      <h2>{type}</h2>
      <fieldset>
        <div className="field_wrap">
          <label htmlFor="email" className={!!form.email ? "active" : ""}>
            {emailInfo(form.email)}
            <span className="req">*</span>
          </label>
          <input
            type="email"
            id="email"
            name="email"
            required
            autoComplete="off"
            value={form.email}
            onChange={onChangeForm}
          />
          {type === "register" && <button className="com_btn">중복확인</button>}
        </div>
        <div className="field_wrap">
          <label htmlFor="pw" className={!!form.password ? "active" : ""}>
            {pwInfo(form.password)}
            <span className="req">*</span>
          </label>
          <input
            type="password"
            id="pw"
            name="password"
            required
            autoComplete="off"
            minLength="4"
            maxLength="15"
            value={form.password}
            onChange={onChangeForm}
          />
        </div>
        {type === "register" && <RegisterTail {...{ form, onChangeForm }} />}

        <button type="submit" className="button button_block">
          {type}
        </button>
        {type === "login" && <LoginTail />}
      </fieldset>
    </form>
>>>>>>> 2e8e26f9b3f739b8c1815878baa3ee16a931aecf
  );
};

export default AuthForm;
