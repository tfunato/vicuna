package jp.canetrash.vicuna.logic;

import static org.junit.Assert.*;

import org.junit.Test;

public class OAuthLogicTest {

	@Test
	public void test() {
		OAuthLogic target = new OAuthLogic();
		System.out.println(target.isAuthorized());
		System.out.println(target.getAuthPage());
		System.out.println(target.getGmailService());
	}

}
