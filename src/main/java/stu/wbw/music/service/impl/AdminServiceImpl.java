package stu.wbw.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stu.wbw.music.mapper.AdminMapper;
import stu.wbw.music.service.AdminService;

/**
 * 管理员service实现类
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 验证密码是否正确
     */
    @Override
    public boolean verifyPassword(String name, String password) {
        return adminMapper.verifyPassword(name, password)>0;
    }
}
