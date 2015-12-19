package com.you.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.you.repository.entity.SsTask;
import com.you.repository.mapper.SsTaskMapper;

@Service
public class SsTaskService {
	@Autowired
	private SsTaskMapper ssTaskMapper;
	
	public List<SsTask> selectALL() {
		// TODO Auto-generated method stub
		return ssTaskMapper.selectALL();
	}

//	public List<SsTask> querySsTask() {
		// TODO Auto-generated method stub
	//	return ssTaskMapper.selectALL();
//	}
	//@Autowired
	//private SsTaskMapper ssTaskMapper;

	//public List<SsTask> querySsTask() {
		// TODO Auto-generated method stub
	//	return ssTaskMapper.selectALL();
	//}
}
