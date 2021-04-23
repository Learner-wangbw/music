package stu.wbw.music.mapper;

import org.springframework.stereotype.Repository;
import stu.wbw.music.pojo.Comment;

import java.util.List;

/**
 * Comment的Mapper层
 */
@Repository
public interface CommentMapper {
    /**
     * 增加
     */
    int insert(Comment comment);

    /**
     * 修改
     */
    int update(Comment comment);

    /**
     * 删除
     */
    int delete(Integer id);

    /**
     * 查询
     */
    Comment selectByPrimaryKey(Integer id);

    /**
     * 查询所有评论
     */
    List<Comment> allComment();

    /**
     * 查询某个歌曲下的所有评论
     */
    List<Comment> commentOfSongId(Integer songId);

    /**
     * 查询某个歌单下的全部评论
     */
    List<Comment> commentOfSongListId(Integer songListId);
}
