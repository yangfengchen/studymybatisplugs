package com.ddzj.studymybatisplugs;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddzj.studymybatisplugs.dto.QueryUserDto;
import com.ddzj.studymybatisplugs.dto.UserRole;
import com.ddzj.studymybatisplugs.entity.TblUser;
import com.ddzj.studymybatisplugs.service.ITblUserService;
import com.mysql.cj.xdevapi.SessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.rules.Stopwatch;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@MapperScan(basePackages = "com.ddzj.studymybatisplugs.mapper")
class StudymybatisplugsApplicationTests {

	private Logger logger = LoggerFactory.getLogger(StudymybatisplugsApplicationTests.class);

	@Autowired
	private ITblUserService iTblUserService;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Test
	void contextLoads() {
		//iTblUserService.list();
		//queryPageByQueryUserDto();
		insertList();
	}

	/**
	 * 批量插入
	 */
	private void insertList(){
		List<TblUser> tblUsers = buildInsertData();
		// 1992
		/*long startTime = System.currentTimeMillis();
		insertListMapper(tblUsers);
		System.out.println(System.currentTimeMillis() - startTime);*/
		//2154
		long startTime = System.currentTimeMillis();
		insertListJdbc(tblUsers);
		System.out.println(System.currentTimeMillis() - startTime);

	}

	/**
	 * mapper 批量方式插入
	 * @param tblUsers
	 */
	private void insertListMapper(List<TblUser> tblUsers){
		iTblUserService.saveCustomBatch(tblUsers);
	}

	public void insertListJdbc(List<TblUser> tblUsers){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection connection = sqlSession.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			connection.setAutoCommit(false);
			String sql = " insert into tbl_user(id, account, name, email, age, phone, alias_Name) values (?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			// 数量大时需要批量提交
			int count = tblUsers.size();
			for(int i = 0 ; i < count; i++){
				preparedStatement.setString(1,tblUsers.get(i).getId());
				preparedStatement.setString(2,tblUsers.get(i).getAccount());
				preparedStatement.setString(3,tblUsers.get(i).getName());
				preparedStatement.setString(4,tblUsers.get(i).getEmail());
				preparedStatement.setString(5,tblUsers.get(i).getAge());
				preparedStatement.setString(6,tblUsers.get(i).getPhone());
				preparedStatement.setString(7,tblUsers.get(i).getAliasName());
				preparedStatement.addBatch();
				if(i+1 % 1000 == 0){
					preparedStatement.executeBatch();
					connection.commit();
				}
			}
			preparedStatement.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {

			}
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {

				}
			}
			try {
				connection.close();
			} catch (SQLException e) {

			}
			sqlSession.close();
		}

	}

	private List<TblUser> buildInsertData(){
		List<TblUser> tblUsers = Lists.newArrayList();
		for(int i = 0; i < 10000; i++){
			TblUser tblUser = new TblUser();
			tblUser.setId(UUID.randomUUID().toString());
			tblUser.setAccount("zhang"+i);
			tblUser.setName("张"+i);
			tblUser.setAliasName("张"+i);
			tblUser.setAge("");
			tblUser.setEmail("");
			tblUser.setPhone("");
			tblUsers.add(tblUser);
		}
		return tblUsers;
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
