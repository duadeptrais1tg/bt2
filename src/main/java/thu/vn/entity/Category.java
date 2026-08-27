package thu.vn.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private int categoryid;

    @Column(name = "categoryname", columnDefinition = "nvarchar(255) not null")
    private String categoryname;

    @Column(name = "images", columnDefinition = "nvarchar(255) null")
    private String images;

    @Column(name = "status")
    private int status;

    @OneToMany(mappedBy = "categories")
    private List<Video> videos;

    public Category() {}

    public int getCategoryid() { return categoryid; }
    public void setCategoryid(int categoryid) { this.categoryid = categoryid; }

    public String getCategoryname() { return categoryname; }
    public void setCategoryname(String categoryname) { this.categoryname = categoryname; }

    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public List<Video> getVideos() { return videos; }
    public void setVideos(List<Video> videos) { this.videos = videos; }
}