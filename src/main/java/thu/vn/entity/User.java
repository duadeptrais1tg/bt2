package thu.vn.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "varchar(50) not null", unique = true)
    private String username;

    @Column(columnDefinition = "varchar(50) not null")
    private String password;

    @Column(columnDefinition = "nvarchar(100) null")
    private String fullname;

    @Column(columnDefinition = "varchar(100) null")
    private String email;

    @Column(columnDefinition = "varchar(20) null")
    private String phone;

    private int roleid;

    public User() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public int getRoleid() { return roleid; }
    public void setRoleid(int roleid) { this.roleid = roleid; }
    
    private String images;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
    
}