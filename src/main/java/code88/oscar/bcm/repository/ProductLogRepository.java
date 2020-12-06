package code88.oscar.bcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import code88.oscar.bcm.model.ProductLogModel;

/**
 * @FileName: ProductLogRepository.java
 * @since:28/10/2020
 * */
@Repository
public interface ProductLogRepository extends JpaRepository<ProductLogModel, Integer> {

}
