package code88.oscar.bcm.viewObjects;

import java.util.List;


/**
 * @FileName: OrderProductVO.java
 * @since: 31/10/2020
 * */
public class OrderProductVO {

    private String tableName;
    private List<OrderedVO> listOrder;
    private String totalPrice;
    public OrderProductVO() {
	super();
	// TODO Auto-generated constructor stub
    }
    public OrderProductVO(String tableName, List<OrderedVO> listOrder, String totalPrice, String creatBy,
	    String createOn) {
	super();
	this.tableName = tableName;
	this.listOrder = listOrder;
	this.totalPrice = totalPrice;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public List<OrderedVO> getListOrder() {
        return listOrder;
    }
    public void setListOrder(List<OrderedVO> listOrder) {
        this.listOrder = listOrder;
    }
    public String getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
    @Override
    public String toString() {
	return "OrderProductVO [tableName=" + tableName + ", listOrder=" + listOrder + ", totalPrice=" + totalPrice
		+ "]";
    }
    
}
