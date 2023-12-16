package com.ddzj.studymybatisplugs;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddzj.studymybatisplugs.dto.QueryUserDto;
import com.ddzj.studymybatisplugs.dto.UserRole;
import com.ddzj.studymybatisplugs.entity.TblUser;
import com.ddzj.studymybatisplugs.service.ITblUserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class StudymybatisplugsApplicationTests {

	private Logger logger = LoggerFactory.getLogger(StudymybatisplugsApplicationTests.class);

	@Autowired
	private ITblUserService iTblUserService;

	@Test
	void contextLoads() {
		queryPageByQueryUserDto();
	}
	private void queryPageByQueryUserDto(){
		// 分页参数从1开始
		IPage<TblUser> page = new Page<TblUser>(2,2);
		QueryUserDto queryUserDto = new QueryUserDto();
		queryUserDto.setAccount("");
		IPage<TblUser> tblUsers = iTblUserService.queryPageByQueryUserDto(page,queryUserDto);
		logger.debug("{}",tblUsers.getPages());
		logger.debug("{}",tblUsers.getCurrent());
		logger.debug("{}",tblUsers.getSize());
		logger.debug("{}",tblUsers.getTotal());
		for(TblUser tblUser : tblUsers.getRecords()){
			logger.debug("{}",tblUser.getName());
		}
	}

	private void findByQueryUserDto(){
		QueryUserDto queryUserDto = new QueryUserDto();
		queryUserDto.setAccount("1");
		List<TblUser> tblUsers = iTblUserService.findByQueryUserDto(queryUserDto);
		for(TblUser tblUser : tblUsers){
			logger.debug("{}",tblUser.getName());
		}
		logger.debug("{}",tblUsers.size());
	}

	private void findByAccount(){
		List<UserRole> tblUsers = iTblUserService.findByAccount("1");
		logger.debug("{}",tblUsers.size());
		for(UserRole userRole : tblUsers){
			logger.debug("{}",userRole.getRoleCode());
			logger.debug("{}",userRole.getName());
			logger.debug("{}",userRole.getRoleCodeName());
		}
	}

	private void findByName(){
		List<TblUser> tblUsers = iTblUserService.findByName("ceshi1");
		logger.debug("{}",tblUsers.size());
	}

}
