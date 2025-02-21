package com.jun.board_project.domain.point.pointBase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PointBaseService {
    private final PointBaseRepository pointBaseRepository;
}
