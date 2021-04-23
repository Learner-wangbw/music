package stu.wbw.music.mapper;

import org.springframework.stereotype.Repository;
import stu.wbw.music.pojo.Song;

import java.util.List;

/**
 * Song的Mapper层
 */
@Repository
public interface SongMapper {
    /**
     * 增加
     */
    int insert(Song song);

    /**
     * 修改
     */
    int update(Song song);

    /**
     * 删除
     */
    int delete(Integer id);

    /**
     * 根据歌手id删除歌曲
     */
    int deleteOfSingerId(Integer singerId);

    /**
     * 查询
     */
    Song selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌曲
     */
    List<Song> allSong();

    /**
     * 根据歌名查询列表
     */
    List<Song> songOfName(String name);

    /**
     * 根据歌名模糊查询列表
     */
    List<Song> likeSongOfName(String name);


    /**
     * 根据歌手id查询歌曲
     */
    List<Song> songOfSingerId(Integer singerId);
}