package stu.wbw.music.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stu.wbw.music.pojo.Singer;
import stu.wbw.music.pojo.Song;
import stu.wbw.music.service.SingerService;
import stu.wbw.music.service.SongService;
import stu.wbw.music.utils.Constants;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/singer")
public class SingerController {
    @Autowired
    private SingerService singerService;
    @Autowired
    private SongService songService;

    /**
     * 增加歌手
     */
    @PostMapping("/add")
    public Object addSinger(Singer singer) {
        JSONObject jsonObject = new JSONObject();
        boolean flag = singerService.insert(singer);
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
     * 更新歌手
     */
    @PostMapping("/update")
    public Object updateSinger(Singer singer) {
        JSONObject jsonObject = new JSONObject();
        boolean flag = singerService.update(singer);
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
     * 删除歌手
     */
    @GetMapping("/delete")
    public Object deleteSinger(Integer id) {
        Singer singer = singerService.selectByPrimaryKey(id);
        List<Song> songs = songService.songOfSingerId(id);
        //删除该歌手在数据库中歌曲的数据
        songService.deleteOfSingerId(id);
        //删除歌曲的实际文件
        for (Song song : songs) {
            new File(System.getProperty("user.dir")+System.getProperty("file.separator")+song.getUrl()).delete();
        }
        //删除歌手图片
        if (!"/img/singerPic/defaultPic.jpg".equals(singer.getPic())){
            new File(System.getProperty("user.dir")+System.getProperty("file.separator")+singer.getPic()).delete();
        }
        return singerService.delete(id);
    }

    /**
     * 根据主键查询对象
     */
    @GetMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(Integer id) {
        return singerService.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌手
     */
    @GetMapping("/allSinger")
    public Object allSinger() {
        return singerService.allSinger();
    }

    /**
     * 根据姓名查询
     */
    @GetMapping("/singerOfName")
    public Object singerOfName(String name){
        return singerService.singerOfName("%"+name+"%");
    }

    /**
     * 根据性别查询
     */
    @GetMapping("/singerOfSex")
    public Object singerOfSex(Integer sex){
        return singerService.singerOfSex(sex);
    }

    /**
     * 更新歌手图片
     */
    @PostMapping("/updateSingerPic")
    public Object updateSingerPic(@RequestParam("file") MultipartFile file,@RequestParam("id") int id){
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
                System.getProperty("file.separator")+"singerPic";
        //如果文件名不存在，则创建
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的文件地址
        String storePath = "/img/singerPic/" + fileName;

        try {
            file.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storePath);
            boolean flag = singerService.update(singer);
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
