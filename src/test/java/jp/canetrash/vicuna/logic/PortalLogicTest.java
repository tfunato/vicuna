package jp.canetrash.vicuna.logic;

import static org.junit.Assert.assertNotNull;
import jp.canetrash.vicuna.Application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class PortalLogicTest {

	@Autowired
	private PortalLogic portalLogic;

	@Test
	public void test() {
		assertNotNull(portalLogic);
	}

}
