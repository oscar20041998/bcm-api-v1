package code88.oscar.bcm.services;

import java.util.List;
import code88.oscar.bcm.model.PersonalLogModel;
import code88.oscar.bcm.request.SearchLogRequest;

/**
 * @FileName: PersonalLogService.java
 * @since: 11/10/2020
 * */
public interface PersonalLogService{
    
    PersonalLogModel insertSystemLog(PersonalLogModel systemLog);
    
    List<PersonalLogModel> getSystemLogByAccountUser (String userName);
    
    List <PersonalLogModel> getAllSystemLog ();
    
    List<PersonalLogModel> searchSystemLogByDateAndAcountId(SearchLogRequest request); 

}
