package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDAO;
import com.nowcoder.community.dao.AlphaDAOImpl;
import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
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
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {

    @Autowired
    DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectPosts(){
        List<DiscussPost> discussPostsList = discussPostMapper.selectDiscussPosts(0, 0, 10);
        for(DiscussPost p: discussPostsList) {
            System.out.println(p);
        }

        int rows = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);
    }
}
