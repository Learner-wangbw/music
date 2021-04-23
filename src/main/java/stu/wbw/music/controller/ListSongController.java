package stu.wbw.music.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stu.wbw.music.pojo.ListSong;
import stu.wbw.music.service.ListSongService;
import stu.wbw.music.utils.Constants;


@RestController
@RequestMapping("/listSong")
public class ListSongController {
    @Autowired
    private ListSongService listSongService;

    /**
     * 给歌单添加歌曲
     */
    @PostMapping("/add")
    public Object addSong(ListSong listSong){
        JSONObject jsonObject = new JSONObject();
        boolean flag = listSongService.insert(listSong);
        if (flag){
            jsonObject.put(Constants.CODE, 1);
            jsonObject.put(Constants.MSG, "保存成功");
        }else {
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "保存失败");
        }
        return jsonObject;
    }

    /**
     * 根据歌单id查询歌曲
     */
    @GetMapping("/detail")
    public Object songOfSingerId(@RequestParam("songListId") int songListId){
        return listSongService.listSongOfSongListId(songListId);
    }


    /**
     * 删除歌曲
     */
    @GetMapping("/delete")
    public Object delete(@RequestParam("songId") Integer songId,@RequestParam("songListId") Integer songListId) {
        //删除歌曲的实际文件
        //1.先查询数据库对应文件地址
        //2.删除该文件
        return listSongService.deleteBySongIdAndSongListId(songId,songListId);
    }

}
