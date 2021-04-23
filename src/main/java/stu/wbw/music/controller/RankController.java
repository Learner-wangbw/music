package stu.wbw.music.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import stu.wbw.music.pojo.Rank;
import stu.wbw.music.service.RankService;
import stu.wbw.music.utils.Constants;

@RestController
public class RankController {

    @Autowired
    private RankService rankService;

    /**
     * 新增评价
     * @param rank
     * @return
     */
    @PostMapping("/rank/add")
    public Object add(Rank rank){
        JSONObject jsonObject = new JSONObject();

        boolean flag = rankService.insert(rank);

        if (flag) { //保存成功
            jsonObject.put(Constants.CODE, 1);
            jsonObject.put(Constants.MSG, "评价成功");
        } else {
            jsonObject.put(Constants.CODE, 0);
            jsonObject.put(Constants.MSG, "评价失败");
        }
        return jsonObject;
    }

    /**
     * 计算平均分
     */
    @GetMapping("/rank")
    public Object rankOfSongListId(Integer songListId){
        return rankService.rankOfSongListId(songListId);
    }

}
