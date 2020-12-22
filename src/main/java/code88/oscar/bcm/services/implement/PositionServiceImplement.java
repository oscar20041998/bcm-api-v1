package code88.oscar.bcm.services.implement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code88.oscar.bcm.common.CommonMethod;
import code88.oscar.bcm.common.StatusCommon;
import code88.oscar.bcm.model.PositionModel;
import code88.oscar.bcm.repository.PositionRepository;
import code88.oscar.bcm.services.PositionService;
import code88.oscar.bcm.viewObjects.PositionVO;

/**
 * @FileName: PositionServiceImplement.java
 * @since: 29/10/2020
 */
@Service
public class PositionServiceImplement implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private CommonMethod commonMethod;

    @PersistenceContext
    private EntityManager entityManager;

    public static final String sql_countOpening = ""
	    + "SELECT count(position_status) FROM position WHERE position_status='" + StatusCommon.OPENING + "'";

    public static final String sql_countClosed = ""
	    + "SELECT count(position_status) FROM position WHERE position_status='" + StatusCommon.CLOSED + "'";

    @Override
    public List<PositionVO> getListPosition() {
	List<PositionModel> listModel = positionRepository.findAll();
	return mappingListPosition(listModel);

    }

    List<PositionVO> mappingListPosition(List<PositionModel> listModel) {
	List<PositionVO> listVO = new ArrayList<>();
	for (PositionModel model : listModel) {
	    PositionVO vo = new PositionVO();
	    vo.setPositionId(model.getPositionId());
	    vo.setPositionName(model.getName());
	    vo.setPositionStatus(model.getPositionStatus());
	    vo.setLastUpdateBy(model.getLastUpdateBy());
	    vo.setLastUpdateTime(commonMethod.convertDateTimeToString(model.getLastUpdateTime()));
	    listVO.add(vo);
	}
	return listVO;
    }

    @Override
    public int opening() {
	int result = 0;
	try {
	    Query query = entityManager.createNativeQuery(sql_countOpening);
	    result = ((Number) query.getSingleResult()).intValue();
	} catch (Exception ex) {

	}
	return result;
    }

    @Override
    public int closed() {
	int result = 0;
	try {
	    Query query = entityManager.createNativeQuery(sql_countClosed);
	    result = ((Number) query.getSingleResult()).intValue();
	} catch (Exception ex) {

	}
	return result;
    }

    @Override
    public void moveTableCurrent(String currentTable, String newTable, String createBy) {
	positionRepository.moveCurrentTable(currentTable, newTable);
	positionRepository.closeTableById(currentTable, createBy);
	positionRepository.openTableById(newTable,createBy);
    }

    @Override
    public List<PositionVO> getListPositionOpening() {
	List<PositionModel> listModel = positionRepository.getListPositionOpening();
	List<PositionVO> listVO = mappingListPosition(listModel);
	return listVO;
    }

    @Override
    public List<PositionVO> getListPositionClosed() {
	List<PositionModel> listModel = positionRepository.getListPositionClosed();
	List<PositionVO> listVO = mappingListPosition(listModel);
	return listVO;
    }
}
