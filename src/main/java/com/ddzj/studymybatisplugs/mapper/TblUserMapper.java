package com.ddzj.studymybatisplugs.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ddzj.studymybatisplugs.dto.QueryUserDto;
import com.ddzj.studymybatisplugs.dto.UserRole;
import com.ddzj.studymybatisplugs.entity.TblUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * ; Mapper 接口
 * </p>
 *
 * @author yzb
 * @since 2023-12-16
 */
public interface TblUserMapper extends BaseMapper<TblUser> {

    /**
     * 根据名称查询用户信息
     * @param name
     * @return
     */
    List<TblUser> findByName(String name);

    /**
     * 根据账号查询用户及角色
     * @param account
     * @return
     */
    List<UserRole> findByAccount(String account);

    /**
     * 根据用户查询对象查询用户信息
     * @param queryUserDto
     * @return
     */
    List<TblUser> findByQueryUserDto(QueryUserDto queryUserDto);

    /**
     * 根据用户查询对象分页查询用户信息
     * @param queryUserDto
     * @return
     */
    IPage<TblUser> queryPageByQueryUserDto(IPage<?> page, QueryUserDto queryUserDto);

    /**
     * 批量保存数据
     */
    void saveCustomBatch(List<TblUser> list);
}
