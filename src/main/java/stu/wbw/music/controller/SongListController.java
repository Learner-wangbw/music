package stu.wbw.music.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stu.wbw.music.pojo.SongList;
import stu.wbw.music.service.SongListService;
import stu.wbw.music.utils.Constants;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private SongListService songListService;

    /**
     * 增加歌单
     */
    @PostMapping("/add")
    public Object addSongList(SongList songList) {
        JSONObject jsonObject = new JSONObject();

        boolean flag = songListService.insert(songList);
        if (flag) { //保存成功
            jsonObject.put(Constants.CODE, 1);
            jsonObject.put(Constants.MSG, "添加成功");
        } else {
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "添加失败");
        }
        return jsonObject;
    }

    /**
     * 更新歌单
     */
    @PostMapping("/update")
    public Object updateSongList(SongList songList) {
        JSONObject jsonObject = new JSONObject();
        boolean flag = songListService.update(songList);
        if (flag) { //更新成功
            jsonObject.put(Constants.CODE, 1);
            jsonObject.put(Constants.MSG, "修改成功");
        } else { //更新失败
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "修改失败");
        }
        return jsonObject;
    }

    /**
     * 删除歌单
     */
    @GetMapping("/delete")
    public Object deleteSongList(Integer id) {
        SongList songList = songListService.selectByPrimaryKey(id);
        //删除歌单图片
        if (!"/img/songListPic/defaultPic.jpg".equals(songList.getPic())){
            new File(System.getProperty("user.dir")+System.getProperty("file.separator")+songList.getPic()).delete();
        }
        return songListService.delete(id);
    }

    /**
     * 根据主键查询对象
     */
    @GetMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(Integer id) {
        return songListService.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌单
     */
    @GetMapping("/allSongList")
    public Object allSongList() {
        return songListService.allSongList();
    }

    /**
     * 根据姓名查询
     */
    @GetMapping("/songListOfTitle")
    public Object songListOfTitle(String title){
        return songListService.songListOfTitle(title);
    }

    /**
     * 根据标题模糊查询
     */
    @GetMapping("/likeTitle")
    public Object likeTitle(String title){
        return songListService.likeTitle("%"+title+"%");
    }

    /**
     * 根据风格模糊查询
     */
    @GetMapping("/likeStyle")
    public Object likeStyle(String style){
        return songListService.likeStyle("%"+style+"%");
    }


    /**
     * 更新歌单图片
     */
    @PostMapping("/updateSongListPic")
    public Object updateSongListPic(@RequestParam("file") MultipartFile file, @RequestParam("id") int id){
        JSONObject jsonObject = new JSONObject();
        if (file.isEmpty()){
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "文件上传失败");
            return jsonObject;
        }
        //文件名为当前时间精确到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+file.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+
                System.getProperty("file.separator")+"songListPic";
        //如果文件名不存在，则创建
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的文件地址
        String storePath = "/img/songListPic/" + fileName;

        try {
            file.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storePath);
            boolean flag = songListService.update(songList);
            if (flag){
                jsonObject.put(Constants.CODE, 1);
                jsonObject.put(Constants.MSG, "上传成功");
                jsonObject.put("pic",storePath);
            }else {
                jsonObject.put(Constants.CODE, 0);
                jsonObject.put(Constants.MSG, "文件上传失败");
            }
            return jsonObject;
        } catch (IOException e) {
            //io异常，文件夹无权限、磁盘满了等
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "文件上传失败"+e.getMessage());
            return jsonObject;
        }
    }
}
