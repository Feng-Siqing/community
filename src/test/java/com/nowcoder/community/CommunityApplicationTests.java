package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDAO;
import com.nowcoder.community.dao.AlphaDAOImpl;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testSelectUser() {
		User user1 = userMapper.getUserById(150);
		System.out.println(user1);
		System.out.println(userMapper.getUserByName("Liubei"));
		System.out.println(userMapper.getUserByEmail("nowcoder11@sina.com"));
	}

	@Test
	public void testInsertUser() {
		User user = new User();
		user.setUsername("test_name1");
		user.setPassword("123456");
		user.setSalt("abc");
		user.setEmail("test1@sina.com");
		user.setType(0);
		user.setStatus(1);
		user.setHeaderUrl("http://www.nowcoder.com/101.png");
		user.setCreateTime(new Date());

		int rows = userMapper.insertUser(user);
		System.out.println(rows);
		System.out.println(user.getId());
	}

	@Test
	public void testUpdateUser() {
		int r1 = userMapper.updateStatus(150, 0);
		System.out.println(r1);
		int r2 = userMapper.updateHeader(150, "http://www.nowcoder.com/102.png");
		System.out.println(r2);
		int r3 = userMapper.updatePassword(150, "654321");
		System.out.println(r3);
	}

	@Test
	void contextLoads() {
		System.out.println(applicationContext);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

		AlphaDAO b1 = applicationContext.getBean(AlphaDAO.class);
		System.out.println(b1.select());

		AlphaDAO b2 = applicationContext.getBean("alphaImpl1", AlphaDAO.class);
		System.out.println(b2.select());
	}

	@Test
	public void testBeanManagement() {
		AlphaService as1 = applicationContext.getBean(AlphaService.class);
		AlphaService as2 = applicationContext.getBean(AlphaService.class);
		System.out.println(as1 == as2);
	}

	@Autowired
	private AlphaDAO alphaDAO;

	@Test
	public void testDependencyInjection() {
		System.out.println(alphaDAO);
	}
}
