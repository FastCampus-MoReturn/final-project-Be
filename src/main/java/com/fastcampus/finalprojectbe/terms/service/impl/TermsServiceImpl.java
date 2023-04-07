package com.fastcampus.finalprojectbe.terms.service.impl;

import com.fastcampus.finalprojectbe.terms.dto.TermsResDTO;
import com.fastcampus.finalprojectbe.terms.entity.Terms;
import com.fastcampus.finalprojectbe.terms.repository.TermsRepository;
import com.fastcampus.finalprojectbe.terms.service.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class TermsServiceImpl implements TermsService {

    private final TermsRepository termsRepository;

    @Override
    public Page<TermsResDTO> searchTerms(String keyword, Pageable pageable) {
        Page<Terms> termsList = termsRepository.searchByKeyword(keyword, pageable);
        return termsList.map(terms -> TermsResDTO.builder()
                .title(terms.getTitle())
                .description(terms.getDescription())
                .build());
    }

    @Override
    public List<TermsResDTO> getTermsListByKeyword(String keyword) {
       List<Terms> termsList = termsRepository.findByTitleStartingWith(keyword);
        return termsList.stream()
                .map(res -> TermsResDTO.builder()
                        .title(res.getTitle())
                        .description(res.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
