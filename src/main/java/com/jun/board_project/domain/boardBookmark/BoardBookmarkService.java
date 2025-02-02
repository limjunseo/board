package com.jun.board_project.domain.boardBookmark;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardBookmarkService {
    private final BoardBookmarkRepository boardBookmarkRepository;

    //
    public void saveOrBoardBookmark(BoardBookmark boardBookmark) {
        boardBookmarkRepository.insertOrUpdateBoardBookmark(boardBookmark);
    }
}
