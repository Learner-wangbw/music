package stu.wbw.music.service;

import stu.wbw.music.pojo.Singer;

import java.util.List;

/**
 * 歌手service接口
 */
public interface SingerService {
    /**
     * 增加
     */
    boolean insert(Singer singer);

    /**
     * 修改
     */
    boolean update(Singer singer);

    /**
     * 删除
     */
    boolean delete(Integer id);

    /**
     * 根据主键查询
     */
    Singer selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌手
     */
    List<Singer> allSinger();

    /**
     * 根据歌手名字模糊查询列表
     */
    List<Singer> singerOfName(String name);

    /**
     * 根据性别查询
     */
    List<Singer> singerOfSex(Integer sex);
}
