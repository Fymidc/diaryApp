package com.example.mydaily.dtos;

import com.example.mydaily.entities.Dislike;


import lombok.Data;

@Data
public class DislikeResponse {

	private Long id;
    private Long userId;
    private Long postId;

    public DislikeResponse(Dislike entity) {
        this.id =entity.getId();
        this.userId = entity.getUser().getId();
        this.postId = entity.getPost().getId();
    }
}
