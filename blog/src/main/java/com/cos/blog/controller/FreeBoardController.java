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
	
//	@GetMapping("/freeBoard/freeMain")
//	public String freeForm(Model model,
//			@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//
//		Page<FreeBoard> list = freeboard_Service.freeBoard_list(pageable);
//		
//		
//				
//		int nowPage = list.getPageable().getPageNumber() + 1;
//		int startPage = Math.max(nowPage - 4, 1);
//		int endPage = Math.min(nowPage+ 5, list.getTotalPages());
//		
//		model.addAttribute("nowPage", nowPage);
//		model.addAttribute("startPage", startPage);
//		model.addAttribute("endPage", endPage);
//		model.addAttribute("freeboards", list);
//		
//		return "freeBoard/freeMain";
//	}
	
	// 검색
	@GetMapping("/freeBoard/freeMain")
    public String search(String searchText, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, 
    		Model model ) {

        Page<FreeBoard> searchList = freeboard_Service.search(searchText, pageable);

       model.addAttribute("freeboards", searchList);

        return "freeBoard/freeMain";
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
