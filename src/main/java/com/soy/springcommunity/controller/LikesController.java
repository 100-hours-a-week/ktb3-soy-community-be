package com.soy.springcommunity.controller;

import com.soy.springcommunity.dto.LikesSimpleResponse;
import com.soy.springcommunity.dto.SimpleResponse;
import com.soy.springcommunity.entity.CustomUserDetails;
import com.soy.springcommunity.service.LikesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LikesController {
    private Map<String, LikesService> likeServiceMap;
    @Autowired
    public LikesController(Map<String, LikesService> likeServiceMap) {
        this.likeServiceMap = likeServiceMap;
    }

    public LikesService getLikeService(String contentType) {
        LikesService likeService = likeServiceMap.get(contentType + "LikeService");
        if (likeService == null) {
            throw new IllegalArgumentException("지원하지 않는 contentType: " + contentType);
        }
        return likeService;
    }


    @PostMapping("/{contentType}/{contentId}/likes")
    @Operation(summary = "컨텐츠 좋아요")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "컨텐츠 좋아요 성공")
    })
    public ResponseEntity<LikesSimpleResponse> like(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable("contentType") String contentType,
            @PathVariable("contentId") Long contentId){
        LikesService likeService = getLikeService(contentType);
        LikesSimpleResponse simpleResponse = likeService.like(userDetails, contentId);
        return ResponseEntity.ok(simpleResponse);
    }

    @DeleteMapping("/{contentType}/{contentId}/likes")
    @Operation(summary = "컨텐츠 좋아요 취소")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "컨텐츠 좋아요 취소 성공")
    })
    public ResponseEntity<LikesSimpleResponse> unlike(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable("contentType") String contentType,
            @PathVariable("contentId") Long contentId){
        LikesService likeService = getLikeService(contentType);
        LikesSimpleResponse simpleResponse = likeService.unlike(userDetails, contentId);
        return ResponseEntity.ok(simpleResponse);
    }
}

