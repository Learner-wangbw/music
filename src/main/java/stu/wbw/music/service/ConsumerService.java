package stu.wbw.music.service;

import stu.wbw.music.pojo.Consumer;

import java.util.List;

/**
 * 前端用户Service接口
 */
public interface ConsumerService {
    /**
     * 增加
     */
    boolean insert(Consumer consumer);

    /**
     * 修改
     */
    boolean update(Consumer consumer);

    /**
     * 删除
     */
    boolean delete(Integer id);

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
    boolean verifyPassword(String username,String password);

    /**
     * 根据账号名查询
     */
    Consumer getByUsername(String username);
}
