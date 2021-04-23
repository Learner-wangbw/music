package stu.wbw.music.mapper;

import org.springframework.stereotype.Repository;
import stu.wbw.music.pojo.SongList;

import java.util.List;
@Repository
public interface SongListMapper {
    /**
     * 增加
     */
    int insert(SongList songList);

    /**
     * 修改
     */
    int update(SongList songList);

    /**
     * 删除
     */
    int delete(Integer id);

    /**
     * 查询
     */
    SongList selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌单
     */
    List<SongList> allSongList();

    /**
     * 根据歌单名精确查询列表
     */
    List<SongList> songListOfTitle(String title);

    /**
     * 根据歌单名模糊查询列表
     */
    List<SongList> likeTitle(String title);

    /**
     * 根据歌单名模糊查询列表
     */
    List<SongList> likeStyle(String style);
}
