package stu.wbw.music.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import stu.wbw.music.pojo.Collect;

import java.util.List;

/**
 * Collect的Mapper层
 */
@Repository
public interface CollectMapper {
    /**
     * 增加
     */
    int insert(Collect collect);

    /**
     * 删除
     */
    int delete(Integer id);

    /**
     * 根据用户id和歌曲id删除
     */
    int deleteByUserIdSongId(@Param("userId")Integer userId,@Param("songId")Integer songId);

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
    int existSong(@Param("userId") Integer userId, @Param("songId") Integer songId);


}
