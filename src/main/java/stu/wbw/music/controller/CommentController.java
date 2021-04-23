package stu.wbw.music.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stu.wbw.music.pojo.Comment;
import stu.wbw.music.service.CommentService;
import stu.wbw.music.utils.Constants;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     * @param comment
     * @return
     */
    @PostMapping("/add")
    public Object addComment(Comment comment){
        JSONObject jsonObject = new JSONObject();
        if (comment.getSongId()!=null&& !"".equals(comment.getSongId().toString())){
            comment.setType(0);
        }else if(comment.getSongListId()!=null&& !"".equals(comment.getSongListId().toString())){
            comment.setType(1);
        }
        boolean flag = commentService.insert(comment);
        if (flag) { //保存成功
            jsonObject.put(Constants.CODE, 1);
            jsonObject.put(Constants.MSG, "评论成功");
        } else {
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "评论失败");
        }
        return jsonObject;
    }

    /**
     * 修改评论
     * @param comment
     * @return
     */
    @PostMapping("/update")
    public Object updateComment(Comment comment){
        JSONObject jsonObject = new JSONObject();
        boolean flag = commentService.update(comment);
        if (flag) {
            jsonObject.put(Constants.CODE, 1);
            jsonObject.put(Constants.MSG, "修改成功");
        } else {
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "修改失败");
        }
        return jsonObject;
    }

    @GetMapping("/delete")
    public Object deleteComment(Integer id){
        JSONObject jsonObject = new JSONObject();
        boolean flag = commentService.delete(id);
        if (flag) {
            jsonObject.put(Constants.CODE, 1);
            jsonObject.put(Constants.MSG, "删除成功");
        } else {
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "删除失败");
        }
        return jsonObject;
    }

    /**
     * 根据主键查询对象
     */
    @GetMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(Integer id){
        return commentService.selectByPrimaryKey(id);
    }

    /**
     * 查询所有评论
     */
    @GetMapping("/allComment")
    public Object allComment(){
        return commentService.allComment();
    }

    /**
     * 查询某个歌曲下的所有评论
     */
    @GetMapping("/commentOfSongId")
    public Object commentOfSongId(Integer songId){
        return commentService.commentOfSongId(songId);
    }

    /**
     * 查询某个歌单下的全部评论
     */
    @GetMapping("/commentOfSongListId")
    public Object commentOfSongListId(Integer songListId){
        return commentService.commentOfSongListId(songListId);
    }

    /**
     * 给某个评论点赞
     * @param id
     * @return
     */
    @PostMapping("/like")
    public Object like(Integer id,Integer up){
        JSONObject jsonObject = new JSONObject();
        Comment comment = new Comment();
        comment.setId(id);
        comment.setUp(up);

        boolean flag = commentService.update(comment);
        if (flag) {
            jsonObject.put(Constants.CODE, 1);
            jsonObject.put(Constants.MSG, "点赞成功");
        } else {
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "点赞失败");
        }
        return jsonObject;
    }
}
