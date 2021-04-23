package stu.wbw.music.service;

import stu.wbw.music.pojo.Song;

import java.util.List;

public interface SongService {
    /**
     * 增加
     */
    boolean insert(Song song);

    /**
     * 修改
     */
    boolean update(Song song);

    /**
     * 删除
     */
    boolean delete(Integer id);

    /**
     * 根据歌手id删除歌曲
     */
    boolean deleteOfSingerId(Integer singerId);

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
     * 根据歌手id查询
     */
    List<Song> songOfSingerId(Integer singerId);
}
