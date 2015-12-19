package com.you.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.you.repository.entity.SsTask;
import com.you.service.SsTaskService;
@Controller
public class SampleController {

	@Autowired
	private SsTaskService ssTaskService;
	 @RequestMapping("/")
	  String home() {
		 List<SsTask>list = ssTaskService.selectALL();
	        return "test/test.jsp";
	    }

}
