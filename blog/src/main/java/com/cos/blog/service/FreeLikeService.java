package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.repository.FreeLikeRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class FreeLikeService {

	@Autowired
	private final FreeLikeRepository freeLikeRepository;

	@Transactional
	public boolean addLike(int userId, int boardId) {

		// 중복 좋아요 방지
		if (isNotAlreadyLike(userId, boardId)) {

			freeLikeRepository.mSave(userId, boardId);

			return true;
		}
		return false;

	}

	// 사용자가 이미 좋아요 한 게시물인지 체크
	@Transactional
	private boolean isNotAlreadyLike(int userId, int boardId) {
		return freeLikeRepository.findByUserIdAndBoardId(userId, boardId).isEmpty();
	}
}
