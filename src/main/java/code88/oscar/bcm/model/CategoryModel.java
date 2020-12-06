package code88.oscar.bcm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @FileName: CategoryModel.java
 * @since: 22/10/2020
 * */
@Entity
@Table(name = "category")
public class CategoryModel {

    @Id
    @Column(name = "category_id")
    private String categoryId;
    
    @Column(name = "category_name")
    private String categoryName;

    public CategoryModel(String idCategory, String categoryName) {
	super();
	this.categoryId = idCategory;
	this.categoryName = categoryName;
    }

    public CategoryModel() {
	super();
	// TODO Auto-generated constructor stub
    }

    public String getIdCategory() {
        return categoryId;
    }

    public void setIdCategory(String idCategory) {
        this.categoryId = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
	return "CategoryModel [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
    }
}
