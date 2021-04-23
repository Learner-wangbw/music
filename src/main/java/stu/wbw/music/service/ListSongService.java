package stu.wbw.music.service;

import stu.wbw.music.pojo.ListSong;

import java.util.List;

/**
 * 歌单里面的歌曲ListSongService接口
 */
public interface ListSongService {
    /**
     * 增加
     */
    boolean insert(ListSong listSong);

    /**
     * 修改
     */
    boolean update(ListSong listSong);

    /**
     * 删除
     */
    boolean delete(Integer id);

    /**
     *根据歌曲id和歌单id删除
     */
    boolean deleteBySongIdAndSongListId(Integer songId,Integer songListId);

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
