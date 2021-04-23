package stu.wbw.music.mapper;

import org.springframework.stereotype.Repository;
import stu.wbw.music.pojo.Singer;

import java.util.List;

/**
 * Singer的Mapper层
 */
@Repository
public interface SingerMapper {
    /**
     * 增加
     */
    int insert(Singer singer);

    /**
     * 修改
     */
    int update(Singer singer);

    /**
     * 删除
     */
    int delete(Integer id);

    /**
     * 查询
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
