package com.takefour.themoment.repository;

import com.takefour.themoment.model.Bookmark;
import com.takefour.themoment.model.Moment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
	Bookmark deleteByAccountIdAndMomentId(String accountId, Integer momentId);

	@Query(value = "SELECT m" +
			" FROM Bookmark b, Moment m" +
			" WHERE b.accountId = :accountId" +
			" AND b.momentId = m.id")
	List<Moment> findMomentsByAccountId(@Param("accountId") String accountId);
}
