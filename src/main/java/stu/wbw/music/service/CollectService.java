package stu.wbw.music.service;

import org.apache.ibatis.annotations.Param;
import stu.wbw.music.pojo.Collect;

import java.util.List;

/**
 * 收藏Service接口
 */
public interface CollectService {
    /**
     * 增加
     */
    boolean insert(Collect collect);

    /**
     * 删除
     */
    boolean delete(Integer id);

    /**
     * 根据用户id和歌曲id删除
     */
    boolean deleteByUserIdSongId(@Param("userId")Integer userId,@Param("songId")Integer songId);

    /**
     * 查询所有收藏
     */
    List<Collect> allCollect();

    /**
     * 查询某个用户下的所有收藏
     */
    List<Collect> collectOfUserId(Integer userId);

    /**
     * 查询某个用户是否收藏该歌曲
     */
    boolean existSong(@Param("userId") Integer userId, @Param("songId") Integer songId);


}
