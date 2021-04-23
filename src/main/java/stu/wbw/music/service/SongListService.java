package stu.wbw.music.service;

import stu.wbw.music.pojo.SongList;

import java.util.List;

/**
 * 歌单Service接口
 */
public interface SongListService {
    /**
     * 增加
     */
    boolean insert(SongList songList);

    /**
     * 修改
     */
    boolean update(SongList songList);

    /**
     * 删除
     */
    boolean delete(Integer id);

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
     * 根据歌单风格糊查询列表
     */
    List<SongList> likeStyle(String style);
}
