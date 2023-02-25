package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cos.blog.model.JoinBoard;
import com.cos.blog.service.JoinBoardService;

@Controller
public class JoinBoardController {

	@Autowired
	private JoinBoardService joinboard_Service;

	@GetMapping("/joinBoard/joinMain")
	public String joinForm(Model model,
			@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String searchText) {
	
		Page<JoinBoard> list = joinboard_Service.joinBoard_list(pageable);
		
		String sort = pageable.getSort().toString().replaceAll(" ", "").replace(':', ',');
		
		if(field.equals("title")) {
			list = joinboard_Service.TitleSearch(searchText, pageable);
		}
		else if(field.equals("content")){
			list = joinboard_Service.ContentSearch(searchText, pageable);
		}
	
//		else if(field.equals("TitleOrContent")){
//			list = joinboard_Service.TitleOrContent_Search(searchText, pageable);
//		}
		
		int pageNumber=list.getPageable().getPageNumber(); //현재페이지
		int totalPages=list.getTotalPages(); //총 페이지 수. 검색에따라 10개면 10개..
		int pageBlock = 5; //블럭의 수 1, 2, 3, 4, 5	
		int startBlockPage = ((pageNumber)/pageBlock)*pageBlock+1; //현재 페이지가 7이라면 1*5+1=6
		int endBlockPage = startBlockPage+pageBlock-1; //6+5-1=10. 6,7,8,9,10해서 10.
		endBlockPage= totalPages<endBlockPage? totalPages:endBlockPage;
		
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("sort",  sort);

		model.addAttribute("joinboards", list);
		
		return "joinBoard/joinMain";
	}



	// USER 권한이 필요
	@GetMapping("/joinBoard/joinSaveForm")
	public String joinSaveForm() {
		return "joinBoard/joinSaveForm";
	}

	@GetMapping("/joinBoard/{id}")
	public String findById(@PathVariable int id, Model model) {
		joinboard_Service.CountUp(id);
		model.addAttribute("board", joinboard_Service.joinBoard_detail(id));
		return "joinBoard/joinDetail";
	}

	@GetMapping("/joinBoard/{id}/joinUpdateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", joinboard_Service.joinBoard_detail(id));
		return "joinBoard/joinUpdateForm";

	}
}
