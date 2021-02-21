import React from "react";
import { isValue, emailInfo } from "../../utils/auth";

const EmailInput = ({ email, handleEmail }) => {
  return (
    <p className="field_wrap">
      <label htmlFor="email" className={isValue(email) ? "active" : ""}>
        {emailInfo(email)}
        <span className="req">*</span>
      </label>
      <input
        type="email"
        id="email"
        required
        autoComplete="off"
        value={email}
        onChange={handleEmail}
      />
    </p>
  );
};

export default React.memo(EmailInput);
