package stu.wbw.music.service;

import stu.wbw.music.pojo.Rank;

/**
 * 评价Service接口
 */
public interface RankService {
    /**
     * 增加
     */
    boolean insert(Rank rank);

    /**
     * 查询总评分
     */
    int selectScoreSum(Integer songListId);

    /**
     * 查总评分的人数
     */
    int selectRankNum(Integer songListId);

    /**
     * 计算平均分
     */
    int rankOfSongListId(Integer songListId);
}
