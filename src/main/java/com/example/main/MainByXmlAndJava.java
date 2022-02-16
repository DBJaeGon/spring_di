package com.example.main;

import com.example.AuthException;
import com.example.AuthenticationService;
import com.example.PasswordChangeService;
import com.example.UserNotFoundException;
import com.example.conf.ConfigXmlJava;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainByXmlAndJava {
    
    public static void main(String[] args) {

		// GenericXmlApplicationContext ctx = 
		// 		new GenericXmlApplicationContext("classpath:config-with-java.xml");

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigXmlJava.class);
        
		AuthenticationService authSvc = 
				ctx.getBean("authenticationService", AuthenticationService.class);
		runAuthAndCatchAuthEx(authSvc, "jgKim", "1111");
		runAuthAndCatchAuthEx(authSvc, "jgKim", "11111");
		runAuthAndCatchAuthEx(authSvc, "jgKim", "111111");
		try {
			authSvc.authenticate("jgKim2", "1111");
		} catch (UserNotFoundException ex) {
		}
		authSvc.authenticate("jgKim", "1234");
		PasswordChangeService pwChgSvc = ctx.getBean(PasswordChangeService.class);
		pwChgSvc.changePassword("jgKim", "1234", "5678");
		runAuthAndCatchAuthEx(authSvc, "jgKim", "1234");
		authSvc.authenticate("jgKim", "5678");
		ctx.close();
	}

	private static void runAuthAndCatchAuthEx(
			AuthenticationService authSvc, String userId, String password) {
		try {
			authSvc.authenticate(userId, password);
		} catch (AuthException ex) {
		}
	}

}
