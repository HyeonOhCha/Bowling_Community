package com.cos.blog.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class UserController {
	

	@Value("${cos.key}")
	private String cosKey;
	
	@Autowired
	UserService userService;
	
	@Autowired(required=false)
	private AuthenticationManager authenticationManager;

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}

	@GetMapping("/auth/kakao/callback")
	@JsonIgnoreProperties(ignoreUnknown=true)
	public String kakaoCallback(String code) throws Exception{ // Data??? ??????????????? ???????????? ??????

		// Post ???????????? key=value ???????????? ?????? (??????????????????)

		RestTemplate rt = new RestTemplate();
		
		// HttpHeader ???????????? ??????
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// HttpBody ???????????? ??????
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "1806ffac320379da55f20ce05554df7b");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		
		// HttpHeader??? HttpBody??? ????????? ??????????????? ??????
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params, headers);
		
		// Http ???????????? - Post???????????? - ????????? response ????????? ?????? ??????.
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
		);
		
		// Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("????????? ????????? ?????? : "+oauthToken.getAccess_token());
		
		RestTemplate rt2 = new RestTemplate();
		
		// HttpHeader ???????????? ??????
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// HttpHeader??? HttpBody??? ????????? ??????????????? ??????
		
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = 
				new HttpEntity<>(headers2);
		
		// Http ???????????? - Post???????????? - ????????? response ????????? ?????? ??????.
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest2,
				String.class
		);
		System.out.println(response2.getBody());
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		// User ???????????? : username, password, email
		System.out.println("????????? ?????????(??????) : "+kakaoProfile.getId());
		System.out.println("????????? ????????? : "+kakaoProfile.getKakao_account().getEmail());
		
		System.out.println("??????????????? ???????????? : "+kakaoProfile.getKakao_account().getEmail());
		System.out.println("??????????????? ????????? : "+kakaoProfile.getKakao_account().getEmail());
		// UUID??? -> ???????????? ?????? ?????? ?????? ?????? ??????????????? ????????????
		UUID garbagePassword = UUID.randomUUID();
		System.out.println("??????????????? ???????????? : "+garbagePassword);
		
		User kakaoUser = User.builder()
				.username(kakaoProfile.getKakao_account().getEmail())
				.password(cosKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.build();
		
		// ????????? ?????? ???????????? ?????? ?????? ??????
		User originUser = userService.????????????(kakaoUser.getUsername());

		if(originUser.getUsername() == null) {
			System.out.println("?????? ????????? ???????????? ?????? ??????????????? ???????????????");
			userService.????????????(kakaoUser);
		}
		System.out.println("?????? ???????????? ???????????????.");
		// ????????? ??????
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return  "redirect:/";
	}
	
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}

	
	// ?????? ?????? ??????
	@GetMapping("/user/userList")
	public String userList(Model model,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String searchText) {
		
		Page<User> list = userService.userList(pageable);
		
		
		String sort = pageable.getSort().toString().replaceAll(" ", "").replace(':', ',');
		
		if(field.equals("username")) {
			list = userService.UsernameSearch(searchText, pageable);
		}
		else if(field.equals("email")) {
			list = userService.EmailSearch(searchText, pageable);
		}
		
		int pageNumber=list.getPageable().getPageNumber(); //???????????????
		int totalPages=list.getTotalPages(); //??? ????????? ???. ??????????????? 10?????? 10???..
		int pageBlock = 5; //????????? ??? 1, 2, 3, 4, 5	
		int startBlockPage = ((pageNumber)/pageBlock)*pageBlock+1; //?????? ???????????? 7????????? 1*5+1=6
		int endBlockPage = startBlockPage+pageBlock-1; //6+5-1=10. 6,7,8,9,10?????? 10.
		endBlockPage= totalPages<endBlockPage? totalPages:endBlockPage;
		
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("sort",  sort);

		model.addAttribute("userList",list);
		return "user/userList";
		
	}
	

}
