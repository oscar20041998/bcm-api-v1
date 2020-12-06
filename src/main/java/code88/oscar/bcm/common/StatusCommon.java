package code88.oscar.bcm.common;

import org.springframework.stereotype.Component;

@Component
public class StatusCommon {
	
	// For service
	public static final String SUCCESS = "Success";
	public static final String FAILED = "Failed";
	public static final String DUPLICATED = "Dublicated";
	
	// For Account status
	public static final String BLOCKED = "BLOCKED";
	public static final String ACTIVE = "ACTIVE";
	
	// For Order status
	public static final String PENDING = "PENDING";
	public static final String DONE = "DONE";
	public static final String OUTOFFTABLE = "Out off table !!!";
	
	// For position
	public static final String OPENING = "OPENING";
	public static final String CLOSED = "CLOSED";
	
	// For transaction 
	public static final String RECIEVED = "RECIEVED";
	
}
