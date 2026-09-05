package thu.vn.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "NVARCHAR(200)", nullable = false, unique = true)
    private String username;

    @Column(columnDefinition = "NVARCHAR(200)", nullable = false)
    private String password;

    @Column(columnDefinition = "NVARCHAR(200)")
    private String email;

    @Column(columnDefinition = "NVARCHAR(200)")
    private String fullname;

    @Column(columnDefinition = "NVARCHAR(20)")
    private String phone;

    @Column(columnDefinition = "NVARCHAR(500)")
    private String images;

    private int roleid = 2; // 1: Admin, 2: User

    @Column(name = "status")
    private int status = 0; // 0: Chưa kích hoạt OTP, 1: Đã kích hoạt

    @Column(name = "otp")
    private String otp;

    @Column(name = "otp_expiry")
    private LocalDateTime otpExpiry;

    public User() {}

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }

    public int getRoleid() { return roleid; }
    public void setRoleid(int roleid) { this.roleid = roleid; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getOtp() { return otp; }
    public void setOtp(String otp) { this.otp = otp; }

    public LocalDateTime getOtpExpiry() { return otpExpiry; }
    public void setOtpExpiry(LocalDateTime otpExpiry) { this.otpExpiry = otpExpiry; }
}