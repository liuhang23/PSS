package com.hd.pss.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hd.pss.domain. SystemDictionaryDetail;
import com.hd.pss.domain.SystemDictionaryType;
import com.hd.pss.service.ISystemDictionaryDetailService;

public class SystemDictionaryDetailServiceTest extends BaseServiceTest {
	@Autowired
	ISystemDictionaryDetailService systemDictionaryDetailService;
	
//	Id    name      types_id [数据字典类型的id]
//	1     网络          1	
//	2     朋友推荐  	   1
//	3     广告          1
	
//	4     七匹狼    	   2
//	5     耐克     	   2
	
//	6     条           3
//	7     斤           3

	// 添加产品明细 ： 对应类型
	@Test
	public void testSave() throws Exception {
		// type 1L : 来源网络
		SystemDictionaryType types = new SystemDictionaryType(1L);
		SystemDictionaryDetail systemDictionaryDetail = new SystemDictionaryDetail("网络",types);
		systemDictionaryDetailService.save(systemDictionaryDetail);
		
		systemDictionaryDetail = new SystemDictionaryDetail("朋友推荐",types);
		systemDictionaryDetailService.save(systemDictionaryDetail);
		
		systemDictionaryDetail = new SystemDictionaryDetail("广告",types);
		systemDictionaryDetailService.save(systemDictionaryDetail);
		
		// type 2L 产品来源
		types = new SystemDictionaryType(2L);
		systemDictionaryDetail = new SystemDictionaryDetail("七匹狼",types);
		systemDictionaryDetailService.save(systemDictionaryDetail);
		
		systemDictionaryDetail = new SystemDictionaryDetail("耐克 ",types);
		systemDictionaryDetailService.save(systemDictionaryDetail);
		
		// type 3L 产品[单位]
		types = new SystemDictionaryType(3L);
		systemDictionaryDetail = new SystemDictionaryDetail("条",types);
		systemDictionaryDetailService.save(systemDictionaryDetail);
		
		systemDictionaryDetail = new SystemDictionaryDetail("斤",types);
		systemDictionaryDetailService.save(systemDictionaryDetail);
		
	}
}
