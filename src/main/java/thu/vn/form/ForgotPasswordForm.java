package thu.vn.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ForgotPasswordForm {

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    /** Chỉ bắt buộc ở bước 2 (đổi mật khẩu) - kiểm tra thủ công trong controller. */
    private String otp;

    /** Chỉ bắt buộc ở bước 2 - kiểm tra thủ công trong controller. */
    private String newPassword;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getOtp() { return otp; }
    public void setOtp(String otp) { this.otp = otp; }

    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
}
