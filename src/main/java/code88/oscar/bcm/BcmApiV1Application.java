package code88.oscar.bcm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import code88.oscar.bcm.repository.ElectronicWalletRepository;

@SpringBootApplication
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
public class BcmApiV1Application {
    
    @Autowired
    ElectronicWalletRepository e;
	public static void main(String[] args) {
		SpringApplication.run(BcmApiV1Application.class, args);
	}

	@RequestMapping(value = "/test-connection", method = RequestMethod.GET)
	public String connection() {
	    return "CONNECTED";
	}
}
