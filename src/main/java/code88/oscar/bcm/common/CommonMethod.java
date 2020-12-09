package code88.oscar.bcm.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import code88.oscar.bcm.model.PersonalLogModel;
import code88.oscar.bcm.services.PersonalLogService;

/**
 * @FileName: CommonMethod.java
 * @since: 10/10/2020
 */
@Service
public class CommonMethod {

    @Autowired
    private PersonalLogService personalLogService;

    public String convertDateTimeNowToString() {
	String result;
	LocalDateTime nowDate = LocalDateTime.now();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	result = dtf.format(nowDate);
	return result;
    }

    public String convertDateTimeToString(LocalDateTime request) {
	String result;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	result = dtf.format(request);
	return result;
    }

    public String convertDateToString(LocalDateTime request) {
	String result;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	result = dtf.format(request);
	return result;
    }

    public LocalDateTime getDateTimeNow() {
	LocalDateTime date = LocalDateTime.now();
	return date;
    }

    public String encryptString(byte[] value) {
	return DigestUtils.md5DigestAsHex(value);
    }

    public void insertSystemLog(String accountId, String action, String actionStatus) {
	PersonalLogModel model = mappingSystemLog(accountId, action, actionStatus);
	personalLogService.insertSystemLog(model);
    }

    /**
     * @Function :mappingSystemLog(...)
     * @param: AccountUserModel - Object
     */
    PersonalLogModel mappingSystemLog(String accountId, String action, String actionStatus) {
	PersonalLogModel model = new PersonalLogModel();
	model.setId(convertDateTimeNowToString());
	model.setAccountId(accountId);
	model.setAction(action);
	model.setActionStatus(actionStatus);
	model.setActionDate(getDateTimeNow());
	return model;
    }

    /**
     * @Function: autoCreatePassword
     * @param: dateOfBrith - Date
     */
    public String autoCreatePassword(Date dateOfBrith) {
	String result = "";
	SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
	result = formatter.format(dateOfBrith);
	return result;
    }

    /**
     * @Function: convertCurrencyToString(...)
     * @param: BigDecimal - value
     */
    public String convertCurrencyToString(BigDecimal value) {
	String result = "";
	DecimalFormat formatter = new DecimalFormat("###,###,###,###,###");
	result = formatter.format(value);
	return result;
    }

    /**
     * @Function: maskCardumber(...)
     * @param: cardNumber - String
     */
    public String maskCardNumber(String cardNumber) {
	String result = "";
	String fourFristNumber = cardNumber.substring(0, 4).trim();
	String fourEndNumber = cardNumber.substring(cardNumber.length() - 4, cardNumber.length()).trim();
	String otherNumber = "";
	for (int i = 5; i <= cardNumber.length() - 4; i++) {
	    otherNumber = otherNumber.concat("*");
	}
	return result.concat(fourFristNumber).concat(otherNumber).concat(fourEndNumber).trim();
    }

    // compress the image bytes before storing it in the database
    public byte[] compressBytes(byte[] data) {
	Deflater deflater = new Deflater();
	deflater.setInput(data);
	deflater.finish();
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
	byte[] buffer = new byte[1024];
	while (!deflater.finished()) {
	    int count = deflater.deflate(buffer);
	    outputStream.write(buffer, 0, count);
	}
	try {
	    outputStream.close();

	} catch (IOException e) {

	}
	System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
	return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public byte[] decompressBytes(byte[] data) {
	Inflater inflater = new Inflater();
	inflater.setInput(data);
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
	byte[] buffer = new byte[1024];
	try {
	    while (!inflater.finished()) {
		int count = inflater.inflate(buffer);
		outputStream.write(buffer, 0, count);
	    }
	    outputStream.close();
	} catch (IOException ioe) {
	} catch (DataFormatException e) {

	}
	return outputStream.toByteArray();
    }
}
