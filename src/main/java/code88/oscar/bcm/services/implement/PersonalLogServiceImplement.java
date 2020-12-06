package code88.oscar.bcm.services.implement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code88.oscar.bcm.model.PersonalLogModel;
import code88.oscar.bcm.repository.PersonalLogRepository;
import code88.oscar.bcm.request.SearchLogRequest;
import code88.oscar.bcm.services.PersonalLogService;

@Service
public class PersonalLogServiceImplement implements PersonalLogService {

    @Autowired
    private PersonalLogRepository personalLogRepository;

    @Override
    public PersonalLogModel insertSystemLog(PersonalLogModel systemLog) {
	return personalLogRepository.save(systemLog);
    }

    @Override
    public List<PersonalLogModel> getSystemLogByAccountUser(String userName) {
	return personalLogRepository.getListSystemLogByAccount(userName);
    }

    @Override
    public List<PersonalLogModel> getAllSystemLog() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<PersonalLogModel> searchSystemLogByDateAndAcountId(SearchLogRequest request) {
	return personalLogRepository.getListSystemLogByAccounAndDate(request.getUserName(), request.getDate());
    }

}
