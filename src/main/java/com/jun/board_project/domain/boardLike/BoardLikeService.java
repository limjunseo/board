package com.jun.board_project.domain.boardLike;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardLikeService {
    private final BoardLikeRepository boardLikeRepository;

    public String findLikeYn(int boardId, String memberId) {
        return boardLikeRepository.findLikeYn(boardId, memberId);
    }
}
