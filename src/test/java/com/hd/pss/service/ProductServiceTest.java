package com.hd.pss.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.hd.pss.domain. Product;
import com.hd.pss.service.IProductService;

public class ProductServiceTest extends BaseServiceTest {
	@Autowired
	IProductService productService;

	@Test
	public void testSave() throws Exception {
		Product product = new Product();
		productService.save(product);
	}
}
