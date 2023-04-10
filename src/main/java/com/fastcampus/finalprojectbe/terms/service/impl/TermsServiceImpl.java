package com.fastcampus.finalprojectbe.terms.service.impl;

import com.fastcampus.finalprojectbe.global.exception.DataNotFoundException;
import com.fastcampus.finalprojectbe.global.exception.KeywordValidationException;
import com.fastcampus.finalprojectbe.terms.dto.TermsResDTO;
import com.fastcampus.finalprojectbe.terms.entity.Terms;
import com.fastcampus.finalprojectbe.terms.repository.TermsRepository;
import com.fastcampus.finalprojectbe.terms.service.TermsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TermsServiceImpl implements TermsService {
    private final TermsRepository termsRepository;

    @Override
    public Page<TermsResDTO> searchTerms(String keyword, Pageable pageable){

        if (keyword == null || keyword.trim().isEmpty()) {
            throw new KeywordValidationException();
        }
        Page<Terms> termsList;
        termsList = termsRepository.searchByKeyword(keyword, pageable);

        if (termsList.isEmpty()) {
            try {
                throw new DataNotFoundException();
            } catch (DataNotFoundException e) {
                log.error("해당 키워드의 데이터가 없습니다.", e);
            }
        }

        return mapToTermsResDTO(termsList);

    }

    @Override
    public Page<TermsResDTO> getTermsListByKeyword(String keyword, Pageable pageable){

        if (keyword == null || keyword.trim().isEmpty()) {
            throw new KeywordValidationException();
        }
        Page<Terms> termsList;
        termsList = termsRepository.findByTitleStartingWith(keyword, pageable);

        if (termsList.isEmpty()) {
            try {
                throw new DataNotFoundException();
            } catch (DataNotFoundException e) {
                log.error("해당 키워드의 데이터가 없습니다.", e);
            }
        }

        return mapToTermsResDTO(termsList);

    }

    private Page<TermsResDTO> mapToTermsResDTO(Page<Terms> termsList) {
        return termsList.map(terms -> TermsResDTO.builder()
                .title(terms.getTitle())
                .build());
    }
}
