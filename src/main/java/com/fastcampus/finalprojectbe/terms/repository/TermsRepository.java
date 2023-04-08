package com.fastcampus.finalprojectbe.terms.repository;

import com.fastcampus.finalprojectbe.terms.entity.Terms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TermsRepository extends JpaRepository<Terms, Integer> {
    @Query("SELECT t FROM Terms t WHERE t.title LIKE %:keyword% OR t.description LIKE %:keyword%")
    Page<Terms> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);


    @Query("SELECT t FROM Terms t " +
            "WHERE CASE WHEN t.title < '가' THEN SUBSTRING(t.title, 1, 1) " +
            "WHEN '가' <= t.title AND t.title <= 'ㅎ' THEN t.title " +
            "WHEN t.title < '나' THEN '가' " +
            "WHEN t.title < '다' THEN '나' " +
            "WHEN t.title < '라' THEN '다' " +
            "WHEN t.title < '마' THEN '라' " +
            "WHEN t.title < '바' THEN '마' " +
            "WHEN t.title < '사' THEN '바' " +
            "WHEN t.title < '아' THEN '사' " +
            "WHEN t.title < '자' THEN '아' " +
            "WHEN t.title < '차' THEN '자' " +
            "WHEN t.title < '카' THEN '차' " +
            "WHEN t.title < '타' THEN '카' " +
            "WHEN t.title < '파' THEN '타' " +
            "WHEN t.title < '하' THEN '파' " +
            "ELSE '하' " +
            "END = :keyword")
    Page<Terms> findByTitleStartingWith(@Param("keyword") String keyword, Pageable pageable);


}
