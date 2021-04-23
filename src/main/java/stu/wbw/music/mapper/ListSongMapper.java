package stu.wbw.music.mapper;

import org.springframework.stereotype.Repository;
import stu.wbw.music.pojo.ListSong;

import java.util.List;

/**
 * ListSong的Mapper层
 */
@Repository
public interface ListSongMapper {
    /**
     * 增加
     */
    int insert(ListSong listSong);

    /**
     * 修改
     */
    int update(ListSong listSong);

    /**
     * 删除
     */
    int delete(Integer id);
    /**
     * 根据歌曲id和歌单id删除
     */
    int deleteBySongIdAndSongListId(Integer songId,Integer songListId);


    /**
     * 查询
     */
    ListSong selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌曲
     */
    List<ListSong> allListSong();


    /**
     * 根据歌单id查询所有歌曲
     */
    List<ListSong> listSongOfSongListId(Integer songListId);
}
