package code88.oscar.bcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import code88.oscar.bcm.model.UserLogModel;

/**
 * @FileName: UserLogRepository.java
 * @since: 22/10/2020
 * */
@Repository
public interface UserLogRepository extends JpaRepository<UserLogModel, Integer> {

}
