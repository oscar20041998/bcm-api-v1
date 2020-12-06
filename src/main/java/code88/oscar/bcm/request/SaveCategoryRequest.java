package code88.oscar.bcm.request;

/**
 * @FileName: SaveCategoryRequest.java
 * @since: 24/10/2020
 * */
public class SaveCategoryRequest {

    private String categoryId;
    
    private String categoryName;

    public SaveCategoryRequest() {
	super();
	// TODO Auto-generated constructor stub
    }

    public SaveCategoryRequest(String categoryId, String categoryName) {
	super();
	this.categoryId = categoryId;
	this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
