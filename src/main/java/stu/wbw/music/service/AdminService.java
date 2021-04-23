package stu.wbw.music.service;


/**
 * 管理员service接口
 */

public interface AdminService {
    /**
     * 验证密码是否正确
     */
    boolean verifyPassword(String name,String password);
}
