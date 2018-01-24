package com.takefour.themoment.service;

import com.takefour.themoment.model.Bookmark;
import com.takefour.themoment.model.Moment;
import com.takefour.themoment.repository.BookmarkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {
	@Autowired
	private BookmarkRepository bookmarkRepository;

	public List<Moment> findMomentsByAccountId(String accountId){
		return bookmarkRepository.findMomentsByAccountId(accountId);
	}
//	POST: /apis/moments/{momentId}/heart

	public Bookmark save(String accountId, Integer momentId) {
		Bookmark bookmark = new Bookmark();
		bookmark.setAccountId(accountId);
		bookmark.setMomentId(momentId);
		return bookmarkRepository.save(bookmark);
	}
//	DELETE: /apis/moments/{momentId}/heart

	public void deleteByAccountIdAndMomentId(String accountId, Integer momentId) {
		bookmarkRepository.deleteByAccountIdAndMomentId(accountId, momentId);
	}
}
