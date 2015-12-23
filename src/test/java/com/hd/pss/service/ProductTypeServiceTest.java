package com.hd.pss.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.hd.pss.domain. ProductType;
import com.hd.pss.service.IProductTypeService;

public class ProductTypeServiceTest extends BaseServiceTest {
	@Autowired
	IProductTypeService productTypeService;

	@Test
	public void testSave() throws Exception {
		ProductType productType = new ProductType();
		productTypeService.save(productType);
	}
}
