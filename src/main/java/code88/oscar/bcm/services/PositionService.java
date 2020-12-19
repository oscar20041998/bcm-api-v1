package code88.oscar.bcm.services;

import java.util.List;

import code88.oscar.bcm.viewObjects.PositionVO;

/**
 * @FileName: PositionService.java
 * @since: 29/10/2020
 * */
public interface PositionService {

    List<PositionVO> getListPosition();
    
    int opening();
    
    int closed();
    
    void moveTableCurrent(String currentTable, String newTable);
}
