package com.fastcampus.finalprojectbe.terms.service;

import com.fastcampus.finalprojectbe.terms.dto.TermsResDTO;

import java.util.List;

public interface TermsService {
    List<TermsResDTO> selectTermsList();

}
