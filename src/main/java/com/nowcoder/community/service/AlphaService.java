package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class AlphaService {

    @Autowired
    AlphaDAO alphaDAO;

    public AlphaService() {
        System.out.println("实例化AlphaService");
    }

    @PostConstruct
    public void initial() {
        System.out.println("初始化AlphaService");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁AlphaService");
    }

    public String find() {
        return alphaDAO.select();
    }
}
