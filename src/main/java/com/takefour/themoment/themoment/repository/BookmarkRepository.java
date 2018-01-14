package com.takefour.themoment.themoment.repository;

import com.takefour.themoment.themoment.model.Bookmark;
import com.takefour.themoment.themoment.model.Moment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
	Bookmark deleteByUserIdAndMomentId(Integer userId, Integer momentId);

	@Query(value = "SELECT m" +
			" FROM Bookmark b, Moment m" +
			" WHERE b.userId = :userId" +
			" AND b.momentId = m.id")
	List<Moment> findMomentsByUserId(@Param("userId") Integer userId);
}
