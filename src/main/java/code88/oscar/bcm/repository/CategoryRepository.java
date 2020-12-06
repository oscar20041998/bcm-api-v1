package code88.oscar.bcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import code88.oscar.bcm.model.CategoryModel;

/**
 * @FileName: CategoryRepository.java
 * @since: 22/10/2020
 * */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, String> {

}
