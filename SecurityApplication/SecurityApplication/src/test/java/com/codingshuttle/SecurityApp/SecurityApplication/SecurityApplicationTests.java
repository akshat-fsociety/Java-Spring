package com.codingshuttle.SecurityApp.SecurityApplication;

import com.codingshuttle.SecurityApp.SecurityApplication.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {

	@Autowired
	private JwtService jwtService;

//	@Test
//	void contextLoads() {
//		User user = new User(4L, "akshat@gmail.com", "1234");
//		String token = jwtService.generateToken(user);
//		System.out.println(token);
//		Long id = jwtService.getUserIdFromToken(token);
//		System.out.println(id);
//	}

}
