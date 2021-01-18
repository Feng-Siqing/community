package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

@Repository("alphaImpl1")
public class AlphaDAOImpl implements AlphaDAO{
    @Override
    public String select() {
        return "查询完成";
    }
}
