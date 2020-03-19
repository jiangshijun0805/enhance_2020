package com.feifei.springboot;

import com.feifei.springboot.beans.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LearningSpringBootDemoApplicationTests {
	@Autowired
	Person person;
	@Test
	void contextLoads() {
		System.out.println("person:*************"+ person);
	}

}
