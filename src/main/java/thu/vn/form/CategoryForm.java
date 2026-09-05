package thu.vn.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryForm {

    @NotBlank(message = "Tên danh mục không được để trống")
    @Size(min = 2, max = 100, message = "Tên danh mục phải từ 2 đến 100 ký tự")
    private String categoryname;

    @Min(value = 0, message = "Trạng thái không hợp lệ")
    @Max(value = 1, message = "Trạng thái không hợp lệ")
    private int status;

    /** Link ảnh online (tuỳ chọn). Nếu nhập thì phải là http/https. */
    private String images;

    public String getCategoryname() { return categoryname; }
    public void setCategoryname(String categoryname) { this.categoryname = categoryname; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }
}
