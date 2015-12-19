package com.you.repository.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.you.repository.entity.SsTask;

public interface SsTaskMapper {

	List<SsTask> selectALL();

}
