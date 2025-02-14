package com.jun.board_project.domain.point;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PointService {
    private final PointRepository pointRepository;

    public void savePoint(Point point) {
        int seq = pointRepository.findSeqByMemberId(point.getMemberId());
        point.setSeq(seq);
        pointRepository.savePoint(point);
    }

}
