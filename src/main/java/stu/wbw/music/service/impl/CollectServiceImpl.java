package stu.wbw.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stu.wbw.music.mapper.CollectMapper;
import stu.wbw.music.pojo.Collect;
import stu.wbw.music.service.CollectService;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;
    /**
     * 增加
     *
     * @param collect
     */
    @Override
    public boolean insert(Collect collect) {
        return collectMapper.insert(collect)>0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return collectMapper.delete(id)>0;
    }

    /**
     * 根据用户id和歌曲id删除
     *
     * @param userId
     * @param songId
     */
    @Override
    public boolean deleteByUserIdSongId(Integer userId, Integer songId) {
        return collectMapper.deleteByUserIdSongId(userId, songId)>0;
    }

    /**
     * 查询所有收藏
     */
    @Override
    public List<Collect> allCollect() {
        return collectMapper.allCollect();
    }

    /**
     * 查询某个用户下的所有收藏
     *
     * @param userId
     */
    @Override
    public List<Collect> collectOfUserId(Integer userId) {
        return collectMapper.collectOfUserId(userId);
    }

    /**
     * 查询某个用户是否收藏该歌曲
     *
     * @param userId
     * @param songId
     */
    @Override
    public boolean existSong(Integer userId, Integer songId) {
        return collectMapper.existSong(userId, songId)>0;
    }

}
