package code88.oscar.bcm.services;
import java.util.List;

import code88.oscar.bcm.model.CategoryModel;
import code88.oscar.bcm.request.SaveCategoryRequest;

/**
 * @FileName: CategoryService.java
 * @since: 24/10/2020
 * */
public interface CategoryService {

    CategoryModel saveCategory(SaveCategoryRequest request);
    
    List<CategoryModel> getListCategory();
    
    void deleteCategoryById(String categoryId);
}
