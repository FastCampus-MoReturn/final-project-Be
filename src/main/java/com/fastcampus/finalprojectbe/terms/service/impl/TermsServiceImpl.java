package com.fastcampus.finalprojectbe.terms.service.impl;


import com.fastcampus.finalprojectbe.terms.dto.TermsResDTO;
import com.fastcampus.finalprojectbe.terms.repository.TermsRepository;
import com.fastcampus.finalprojectbe.terms.service.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TermsServiceImpl implements TermsService {
    private final TermsRepository termsRepository;

    @Override
    public List<TermsResDTO> selectTermsList() {
        return termsRepository.findAll()
                .stream()
                .map(res -> TermsResDTO.builder()
                        .title(res.getTitle())
                        .description(res.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
