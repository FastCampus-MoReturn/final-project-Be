package com.fastcampus.finalprojectbe.terms.repository;

import com.fastcampus.finalprojectbe.terms.entity.Terms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsRepository extends JpaRepository<Terms, Integer> {
}
