package com.you.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.you.dao.SsUserDao;
import com.you.entity.User;

@Service
public class SsUserService {
	@Autowired
	private SsUserDao ssUserDao;
	public List<User> findAll() {
		
		return (List<User>) ssUserDao.findAll();
	}

}
