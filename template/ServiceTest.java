package com.hd.pss.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.hd.pss.domain. ${domain};
import com.hd.pss.service.I${domain}Service;

public class ${domain}ServiceTest extends BaseServiceTest {
	@Autowired
	I${domain}Service ${lowerDomain}Service;

	@Test
	public void testSave() throws Exception {
		${domain} ${lowerDomain} = new ${domain}();
//		${lowerDomain}Service.save(${lowerDomain});
	}
}
