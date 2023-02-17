package com.cos.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.model.FreeBoard;
import com.cos.blog.service.FreeBoardService;

@Controller
public class FreeBoardController {

	@Autowired
	private FreeBoardService freeboard_Service;
	

// 페이징 원본
//	@GetMapping("/freeBoard/freeMain")
//	public String freeForm(Model model,
//			@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//
//		model.addAttribute("freeboards", freeboard_Service.freeBoard_list(pageable));
//		return "freeBoard/freeMain";
//	}
	
	@GetMapping("/freeBoard/freeMain")
	public String freeForm(Model model,
			@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

		Page<FreeBoard> list = freeboard_Service.freeBoard_list(pageable);
		
		int pageNumber=list.getPageable().getPageNumber(); //현재페이지
		int totalPages=list.getTotalPages(); //총 페이지 수. 검색에따라 10개면 10개..
		int pageBlock = 5; //블럭의 수 1, 2, 3, 4, 5	
		int startBlockPage = ((pageNumber)/pageBlock)*pageBlock+1; //현재 페이지가 7이라면 1*5+1=6
		int endBlockPage = startBlockPage+pageBlock-1; //6+5-1=10. 6,7,8,9,10해서 10.
		endBlockPage= totalPages<endBlockPage? totalPages:endBlockPage;
		
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);

		model.addAttribute("freeboards", list);
		
		return "freeBoard/freeMain";
	}
	
	// 검색
	@GetMapping("/freeBoard/search")
    public String search(String searchText, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, 
    		Model model ) {

        Page<FreeBoard> searchList = freeboard_Service.search(searchText, pageable);

       model.addAttribute("searchText", searchText);
       
       model.addAttribute("freeboards", searchList);

        return "freeBoard/freeMainSearch";
	}

	
	// USER 권한이 필요
	@GetMapping("/freeBoard/freeSaveForm")
	public String freeSaveForm() {
		return "freeBoard/freeSaveForm";
	}


	@GetMapping("/freeBoard/{id}")
	public String findById(@PathVariable int id, Model model) {
		freeboard_Service.CountUp(id);
		model.addAttribute("board", freeboard_Service.freeBoard_detail(id));
		return "freeBoard/freeDetail";
	}
		

	@GetMapping("/freeBoard/{id}/freeUpdateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", freeboard_Service.freeBoard_detail(id));
		return "freeBoard/freeUpdateForm";

	}
}
