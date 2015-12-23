package com.hd.pss.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.hd.pss.domain. SystemDictionaryType;
import com.hd.pss.service.ISystemDictionaryTypeService;

public class SystemDictionaryTypeServiceTest extends BaseServiceTest {
	@Autowired
	ISystemDictionaryTypeService systemDictionaryTypeService;

//	Id   sn唯一,不能修改    name 来自模型的名称
//	1    clientResouce    客户来源
//	2    productBrand     产品品牌
//	3    productUnit      产品单位
//	唯一,不能修改:unique="true" update="false"

	
	@Test
	public void testSave() throws Exception {
		SystemDictionaryType systemDictionaryType = new SystemDictionaryType(SystemDictionaryType.CLIENT_RESOUCE,"客户来源");
		systemDictionaryTypeService.save(systemDictionaryType);
		
		systemDictionaryType = new SystemDictionaryType(SystemDictionaryType.PRODUCT_BRAND,"产品品牌");
		systemDictionaryTypeService.save(systemDictionaryType);
		
		systemDictionaryType = new SystemDictionaryType(SystemDictionaryType.PRODUCT_UNIT,"产品单位");
		systemDictionaryTypeService.save(systemDictionaryType);
		
	}
}
