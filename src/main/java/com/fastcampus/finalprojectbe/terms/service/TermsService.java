package com.fastcampus.finalprojectbe.terms.service;

import com.fastcampus.finalprojectbe.terms.dto.TermsResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TermsService {
    Page<TermsResDTO> searchTerms(String keyword, Pageable pageable);

    List<TermsResDTO> getTermsListByKeyword(String keyword);
}
