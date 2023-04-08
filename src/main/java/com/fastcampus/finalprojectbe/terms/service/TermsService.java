package com.fastcampus.finalprojectbe.terms.service;

import com.fastcampus.finalprojectbe.terms.dto.TermsResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TermsService {
    Page<TermsResDTO> searchTerms(String keyword, Pageable pageable);

    Page<TermsResDTO> getTermsListByKeyword(String keyword, Pageable pageable);
}
