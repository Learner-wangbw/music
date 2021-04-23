package stu.wbw.music.mapper;

import org.springframework.stereotype.Repository;
import stu.wbw.music.pojo.Rank;

@Repository
public interface RankMapper {

    /**
     * 增加
     */
    int insert(Rank rank);

    /**
     * 查询总评分
     */
    int selectScoreSum(Integer songListId);

    /**
     * 查总评分的人数
     */
    int selectRankNum(Integer songListId);

}
