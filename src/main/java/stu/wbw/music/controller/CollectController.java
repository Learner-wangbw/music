package stu.wbw.music.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stu.wbw.music.pojo.Collect;
import stu.wbw.music.service.CollectService;
import stu.wbw.music.utils.Constants;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    /**
     * 添加收藏
     * @param collect
     * @return
     */
    @PostMapping("/add")
    public Object addCollect(Collect collect){
        JSONObject jsonObject = new JSONObject();
        if (collect.getSongId()==null|| "".equals(collect.getSongId().toString())){
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "该歌曲不存在");
            return jsonObject;
        }
        if (collectService.existSong(collect.getUserId(), collect.getSongId())) {
            jsonObject.put(Constants.CODE, 2);
            jsonObject.put(Constants.MSG, "该歌曲已被收藏");
            return jsonObject;
        }

        boolean flag = collectService.insert(collect);
        if (flag) { //收藏成功
            jsonObject.put(Constants.CODE, 1);
            jsonObject.put(Constants.MSG, "收藏成功");
        } else {
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "收藏失败");
        }
        return jsonObject;
    }


    /**
     * 删除收藏
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public Object deleteCollect(Integer id){
        JSONObject jsonObject = new JSONObject();
        boolean flag = collectService.delete(id);
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
     * 删除收藏
     * @param
     * @return
     */
    @GetMapping("/deleteByUserIdSongId")
    public Object deleteByUserIdSongId(Integer userId,Integer songId){
         return collectService.deleteByUserIdSongId(userId, songId);
    }
    

    /**
     * 查询所有收藏
     */
    @GetMapping("/allCollect")
    public Object allCollect(){
        return collectService.allCollect();
    }

    /**
     * 查询某个用户下的所有收藏
     */
    @GetMapping("/collectOfUserId")
    public Object collectOfUserId(Integer userId){
        return collectService.collectOfUserId(userId);
    }

}
