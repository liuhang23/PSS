package com.hd.pss.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.hd.pss.domain. Supplier;
import com.hd.pss.service.ISupplierService;

public class SupplierServiceTest extends BaseServiceTest {
	@Autowired
	ISupplierService supplierService;

	@Test
	public void testSave() throws Exception {
		Supplier supplier = new Supplier("湖北","湖北供应商");
		supplierService.save(supplier);
		
		supplier = new Supplier("北京","北京供应商");
		supplierService.save(supplier);
	}
}
