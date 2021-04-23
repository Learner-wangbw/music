package stu.wbw.music.mapper;

import org.springframework.stereotype.Repository;
import stu.wbw.music.pojo.Consumer;

import java.util.List;

@Repository
public interface ConsumerMapper {
    /**
     * 增加
     */
    int insert(Consumer consumer);

    /**
     * 修改
     */
    int update(Consumer consumer);

    /**
     * 删除
     */
    int delete(Integer id);

    /**
     * 查询
     */
    Consumer selectByPrimaryKey(Integer id);

    /**
     * 查询所有用户
     */
    List<Consumer> allConsumer();

    /**
     * 验证密码
     */
    int verifyPassword(String username,String password);

    /**
     * 根据账号名查询
     */
    Consumer getByUsername(String username);
}
