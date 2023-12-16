package com.ddzj.studymybatisplugs.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ddzj.studymybatisplugs.dto.QueryUserDto;
import com.ddzj.studymybatisplugs.dto.UserRole;
import com.ddzj.studymybatisplugs.entity.TblUser;
import com.ddzj.studymybatisplugs.mapper.TblUserMapper;
import com.ddzj.studymybatisplugs.service.ITblUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * ; 服务实现类
 * </p>
 *
 * @author yzb
 * @since 2023-12-16
 */
@Service
public class TblUserServiceImpl extends ServiceImpl<TblUserMapper, TblUser> implements ITblUserService {


    @Override
    public List<TblUser> findByName(String name) {
        return this.baseMapper.findByName(name);
    }

    @Override
    public List<UserRole> findByAccount(String account) {
        return this.baseMapper.findByAccount(account);
    }

    @Override
    public List<TblUser> findByQueryUserDto(QueryUserDto queryUserDto) {
        return this.baseMapper.findByQueryUserDto(queryUserDto);
    }

    @Override
    public IPage<TblUser> queryPageByQueryUserDto(IPage<?> page, QueryUserDto queryUserDto) {
        return this.baseMapper.queryPageByQueryUserDto(page, queryUserDto);
    }
}
