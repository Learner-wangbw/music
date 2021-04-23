package stu.wbw.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stu.wbw.music.mapper.RankMapper;
import stu.wbw.music.pojo.Rank;
import stu.wbw.music.service.RankService;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;

    /**
     * 增加
     *
     * @param rank
     */
    @Override
    public boolean insert(Rank rank) {
        return rankMapper.insert(rank)>0;
    }

    /**
     * 查询总评分
     *
     * @param songListId
     */
    @Override
    public int selectScoreSum(Integer songListId) {
        return rankMapper.selectScoreSum(songListId);
    }

    /**
     * 查总评分的人数
     *
     * @param songListId
     */
    @Override
    public int selectRankNum(Integer songListId) {
        return rankMapper.selectRankNum(songListId);
    }

    /**
     * 计算平均分
     *
     * @param songListId
     */
    @Override
    public int rankOfSongListId(Integer songListId) {
        int rankNum = rankMapper.selectRankNum(songListId);
        if (rankNum==0){
            return 5;
        }
        return rankMapper.selectScoreSum(songListId)/rankNum;
    }
}
