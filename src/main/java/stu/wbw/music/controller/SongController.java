package stu.wbw.music.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stu.wbw.music.pojo.Song;
import stu.wbw.music.service.SongService;
import stu.wbw.music.utils.Constants;

import java.io.File;
import java.io.IOException;

/**
 * 歌曲管理Controller
 */
@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    /**
     * 添加歌曲
     */
    @PostMapping("/add")
    public Object addSong(Song song, @RequestParam("file")MultipartFile file){
        JSONObject jsonObject = new JSONObject();

        if (file.isEmpty()){
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "歌曲上传失败");
            return jsonObject;
        }
        //文件名为当前时间精确到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+file.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        //如果文件名不存在，则创建
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的文件地址
        String storeUrlPath = "/song/" + fileName;
        try {
            file.transferTo(dest);
            song.setUrl(storeUrlPath);
            boolean flag = songService.insert(song);
            if (flag){
                jsonObject.put(Constants.CODE, 1);
                jsonObject.put(Constants.MSG, "歌曲上传成功");
                jsonObject.put("avator",storeUrlPath);
            }else {
                jsonObject.put(Constants.CODE, 0);
                jsonObject.put(Constants.MSG, "歌曲上传失败");
            }
            return jsonObject;
        } catch (IOException e) {
            //io异常，文件夹无权限、磁盘满了等
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "歌曲上传失败"+e.getMessage());
            return jsonObject;
        }
    }

    /**
     * 根据歌手id查询歌曲
     */
    @GetMapping("/singer/detail")
    public Object songOfSingerId(@RequestParam("singerId") int singerId){
        return songService.songOfSingerId(singerId);
    }

    /**
     * 根据歌曲id查询歌曲
     */
    @GetMapping("/detail")
    public Object songOfSongId(int songId){
        return songService.selectByPrimaryKey(songId);
    }

    /**
     * 根据歌曲名查询歌曲
     */
    @GetMapping("/songOfSongName")
    public Object songOfSongName(String songName){
        return songService.songOfName(songName);
    }

    /**
     * 查询所有歌曲
     */
    @GetMapping("/allSong")
    public Object allSong(){
        return songService.allSong();
    }

    /**
     * 根据歌名模糊查询列表
     */
    @GetMapping("/likeSongOfName")
    public Object likeSongOfName(String songName){
        return songService.likeSongOfName(songName);
    }

    /**
     * 更新歌曲
     */
    @PostMapping("/update")
    public Object updateSingerPic(Song song){
        JSONObject jsonObject = new JSONObject();
        boolean flag = songService.update(song);
        if (flag){ //更新成功
            jsonObject.put(Constants.CODE, 1);
            jsonObject.put(Constants.MSG, "修改成功");
        } else { //更新失败
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "修改失败");
        }
        return jsonObject;
    }

    /**
     * 删除歌曲
     */
    @GetMapping("/delete")
    public Object deleteSinger(Integer id) {
        //删除歌曲以及图片的实际文件
        //1.先查询数据库对应文件地址,
        //2.从而删除实际文件
        Song song = songService.selectByPrimaryKey(id);
        new File(System.getProperty("user.dir")+System.getProperty("file.separator")+song.getPic()).delete();
        new File(System.getProperty("user.dir")+System.getProperty("file.separator")+song.getUrl()).delete();
        return songService.delete(id);
    }

    /**
     * 更新歌曲图片
     */
    @PostMapping("/updateSongPic")
    public Object updateSongPic(@RequestParam("file") MultipartFile file,@RequestParam("id") int id){
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
                System.getProperty("file.separator")+"songPic";
        //如果文件名不存在，则创建
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的文件地址
        String storePath = "/img/songPic/" + fileName;

        try {
            file.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storePath);
            boolean flag = songService.update(song);
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

    /**
     * 更新歌曲
     */
    @PostMapping("/updateSong")
    public Object updateSong(@RequestParam("file") MultipartFile file,@RequestParam("id") int id){
        JSONObject jsonObject = new JSONObject();
        if (file.isEmpty()){
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "文件上传失败");
            return jsonObject;
        }
        //文件名为当前时间精确到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+file.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        //如果文件名不存在，则创建
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的文件地址
        String storeAvatorPath = "/song/" + fileName;

        try {
            file.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeAvatorPath);
            boolean flag = songService.update(song);
            if (flag){
                jsonObject.put(Constants.CODE, 1);
                jsonObject.put(Constants.MSG, "上传成功");
                jsonObject.put("avator",storeAvatorPath);
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
