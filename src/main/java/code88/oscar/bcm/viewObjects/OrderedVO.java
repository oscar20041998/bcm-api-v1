package code88.oscar.bcm.viewObjects;

/**
 * @FileName: OrderedVO.java
 * @since:1/11/2020
 */
public class OrderedVO {

    private String productId;
    private String productName;
    private String priceConvert;
    private int quantity;
    private String statusProduct;

    public OrderedVO() {
	super();
	// TODO Auto-generated constructor stub
    }

    public OrderedVO(String productName, String priceConvert, int quantity, String statusProduct, String productId) {
	super();
	this.productName = productName;
	this.priceConvert = priceConvert;
	this.quantity = quantity;
	this.productId = productId;
	this.statusProduct = statusProduct;
    }

    public String getProductName() {
	return productName;
    }

    public void setProductName(String productName) {
	this.productName = productName;
    }

    public String getPriceConvert() {
	return priceConvert;
    }

    public void setPriceConvert(String priceConvert) {
	this.priceConvert = priceConvert;
    }

    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    public String getStatusProdct() {
	return statusProduct;
    }

    public void setStatusProdct(String statusProdct) {
	this.statusProduct = statusProdct;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStatusProduct() {
        return statusProduct;
    }

    public void setStatusProduct(String statusProduct) {
        this.statusProduct = statusProduct;
    }

    @Override
    public String toString() {
	return "OrderedVO [productId=" + productId + ", productName=" + productName + ", priceConvert=" + priceConvert
		+ ", quantity=" + quantity + ", statusProduct=" + statusProduct + "]";
    }
}
