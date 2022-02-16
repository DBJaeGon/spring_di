package com.example.main;

import com.example.AuthException;
import com.example.AuthenticationService;
import com.example.PasswordChangeService;
import com.example.UserNotFoundException;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainByXml {
    public static void main(String[] args) {
        // namespace 적용 설정 파일
        // GenericXmlApplicationContext ctx = 
		// 		new GenericXmlApplicationContext("classpath:config-with-namespace.xml");

		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:config.xml");
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
