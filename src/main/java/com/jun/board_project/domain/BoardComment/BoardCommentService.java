package com.jun.board_project.domain.BoardComment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardCommentService {
    private final BoardCommentRepository boardCommentRepository;

    @Transactional
    public int save(BoardComment boardComment) {
        return boardCommentRepository.saveComment(boardComment);
    }

}
