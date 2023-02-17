package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.service.GalleryBoardService;

@Controller
public class GalleryBoardController {
	
	@Autowired
	private GalleryBoardService galleryBoardService;
	
	@GetMapping({"/galleryBoard/galleryMain"})
	public String index(Model model, @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("boards",galleryBoardService.글목록(pageable));
	//  /WEB-INF/views/index.jsp
		return "galleryBoard/galleryMain";
	}
	
	@GetMapping("/galleryBoard/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board",galleryBoardService.글상세보기(id));
		return "galleryBoard/galleryDetail";
		
	}
	
	// USER 권한이 필요
	@GetMapping("/galleryBoard/gallerySaveForm")
	public String saveForm() {
		return "galleryBoard/gallerySaveForm";
	}
	
	@GetMapping("/galleryBoard/{id}/galleryUpdateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", galleryBoardService.글상세보기(id));
		return "galleryBoard/galleryUpdateForm";
		
	}
}
