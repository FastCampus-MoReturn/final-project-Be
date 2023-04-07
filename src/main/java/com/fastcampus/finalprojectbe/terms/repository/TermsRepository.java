package com.fastcampus.finalprojectbe.terms.repository;

import com.fastcampus.finalprojectbe.terms.entity.Terms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TermsRepository extends JpaRepository<Terms, Integer> {
    @Query("SELECT t FROM Terms t WHERE t.title LIKE %:keyword% OR t.description LIKE %:keyword%")
    Page<Terms> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    List<Terms> findByTitleStartingWith(String keyword);
}
