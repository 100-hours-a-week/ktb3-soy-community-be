package com.soy.springcommunity.service;

import com.soy.springcommunity.dto.LikesSimpleResponse;
import com.soy.springcommunity.entity.CustomUserDetails;

public interface LikesService {
    LikesSimpleResponse like(CustomUserDetails userDetails, Long contentId);
    LikesSimpleResponse unlike(CustomUserDetails userDetails, Long contentId);
}

