package com.soy.springcommunity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentsEditRequest {
    @NotBlank
    private String newCommentContent;
    public CommentsEditRequest() {}
    public CommentsEditRequest(String newCommentContent) {
        this.newCommentContent = newCommentContent;
    }
}

