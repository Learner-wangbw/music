package stu.wbw.music.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stu.wbw.music.pojo.Consumer;
import stu.wbw.music.service.ConsumerService;
import stu.wbw.music.utils.Constants;
import stu.wbw.music.utils.JWTUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;

    /**
     * 增加前端用户
     */
    @PostMapping("/add")
    public Object addConsumer(Consumer consumer) {
        JSONObject jsonObject = new JSONObject();
        if (consumer.getUsername()==null || "".equals(consumer.getUsername())){
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "账号不能为空");
            return jsonObject;
        }

        Consumer user = consumerService.getByUsername(consumer.getUsername());
        if (user!=null){
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG,"账号名已存在,请更换!");
            return jsonObject;
        }
        if (consumer.getPassword()==null || "".equals(consumer.getPassword())){
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "密码不能为空");
            return jsonObject;
        }
        boolean flag = consumerService.insert(consumer);
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
     * 更新前端用户
     */
    @PostMapping("/update")
    public Object updateConsumer(Consumer consumer) {
        JSONObject jsonObject = new JSONObject();
        if (consumer.getUsername()==null || "".equals(consumer.getUsername())){
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "账号不能为空");
            return jsonObject;
        }
        if (consumer.getPassword()==null || "".equals(consumer.getPassword())){
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "密码不能为空");
            return jsonObject;
        }
        System.out.println(consumer);
        boolean flag = consumerService.update(consumer);
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
     * 删除前端用户
     */
    @GetMapping("/delete")
    public Object deleteConsumer(Integer id) {
        //删除用户的头像的实际文件
        Consumer consumer = consumerService.selectByPrimaryKey(id);
        if (!"/avatarImages/defaultPic.jpg".equals(consumer.getAvator())){
            new File(System.getProperty("user.dir")+System.getProperty("file.separator")+consumer.getAvator()).delete();
        }
        return consumerService.delete(id);
    }

    /**
     * 根据主键查询对象
     */
    @GetMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(Integer id) {
        return consumerService.selectByPrimaryKey(id);
    }

    /**
     * 查询所有前端用户
     */
    @GetMapping("/allConsumer")
    public Object allConsumer() {
        return consumerService.allConsumer();
    }

    /**
     * 根据账号名查询前端用户
     */
    @GetMapping("/getByUsername")
    public Object getByUsername(String username){
        return consumerService.getByUsername("%"+username+"%");
    }

    @PostMapping("/updateConsumerPic")
    public Object updateConsumerPic(@RequestParam("file") MultipartFile file, @RequestParam("id") int id){
        JSONObject jsonObject = new JSONObject();
        if (file.isEmpty()){
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "文件上传失败");
            return jsonObject;
        }
        //文件名为当前时间精确到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+file.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"avatarImages";

        //如果文件名不存在，则创建
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的文件地址
        String storePath = "/avatarImages/" + fileName;

        try {
            file.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storePath);
            boolean flag = consumerService.update(consumer);
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

    @PostMapping("/login")
    public Object login(String username,String password){
        JSONObject jsonObject = new JSONObject();
        if (username==null || "".equals(username)){
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "账号不能为空");
            return jsonObject;
        }
        if (password==null || "".equals(password)){
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "密码不能为空");
            return jsonObject;
        }

        boolean flag = consumerService.verifyPassword(username, password);
//        Map<String, String> payload = new HashMap<>();
//        payload.put("username",username);
//        String token = JWTUtil.getToken(payload);

        if (flag) { //保存成功
            jsonObject.put(Constants.CODE, 1);
            jsonObject.put(Constants.MSG, "登录成功");
            jsonObject.put("userMsg",consumerService.getByUsername(username));
//            jsonObject.put("token",token);
        } else {
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "登录失败");
        }
        return jsonObject;
    }
}
