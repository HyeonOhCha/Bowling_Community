package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";
	
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = new Member(1,"ssar","1234","email");
		System.out.println(TAG+"getter : "+m.getId());
		m.setId(5000);
		System.out.println(TAG+"getter : "+m.getId());
		return "lombok Test 완료";
		
	}
	
	// 사용자가 요청 -> 응답(Data)
	
	//http://localhost:8081/http/get(select)
	@GetMapping("/http/get")
	public String getTest(Member m) {
		System.out.println(TAG+"getter : "+m.getId());
		m.setId(5000);
		System.out.println(TAG+"getter : "+m.getId());
	
		Member m2 = new Member();
		return "get 요청 : "+m.getId() + " , " + m.getUsername();
	}
	
	//http://localhost:8081/http/post(insert)
	@PostMapping("/http/post")
	public String postTest() {
		return "post요청";
	}
	//http://localhost:8081/http/put(update)
	@PutMapping("/http/put")
	public String putTest() {
		return "put요청";
	}
	
	//http://localhost:8081/http/delete(delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete요청";
	}
	


}
