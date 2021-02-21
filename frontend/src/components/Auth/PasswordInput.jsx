import React from "react";
import { isValue, passwordInfo } from "../../utils/auth";

const PasswordInput = ({ password, handlePassword }) => {
  return (
    <p className="field_wrap">
      <label htmlFor="pw" className={isValue(password) ? "active" : ""}>
        {passwordInfo(password)}
        <span className="req">*</span>
      </label>
      <input
        type="password"
        id="pw"
        required
        autoComplete="off"
        minLength="6"
        maxLength="15"
        value={password}
        onChange={handlePassword}
      />
    </p>
  );
};

export default React.memo(PasswordInput);
