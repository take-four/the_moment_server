package com.takefour.themoment.themoment.service;

import com.takefour.themoment.themoment.model.Bookmark;
import com.takefour.themoment.themoment.model.Moment;
import com.takefour.themoment.themoment.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {
	@Autowired
	private BookmarkRepository bookmarkRepository;

	public List<Moment> findMomentsByUserId(Integer userId){
		return bookmarkRepository.findMomentsByUserId(userId);
	}
//	POST: /apis/moments/{momentId}/heart

	public Bookmark save(Integer userId, Integer momentId) {
		Bookmark bookmark = new Bookmark();
		bookmark.setUserId(userId);
		bookmark.setMomentId(momentId);
		return bookmarkRepository.save(bookmark);
	}
//	DELETE: /apis/moments/{momentId}/heart

	public void deleteByUserIdAndMomentId(Integer userId, Integer momentId) {
		bookmarkRepository.deleteByUserIdAndMomentId(userId,momentId);
	}
}
