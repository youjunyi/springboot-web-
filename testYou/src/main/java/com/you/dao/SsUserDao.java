package com.you.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.you.entity.User;
@Transactional
public interface SsUserDao extends CrudRepository<User, Long>{

}
