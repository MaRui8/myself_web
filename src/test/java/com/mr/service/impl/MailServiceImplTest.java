package com.mr.service.impl;

import com.mr.service.MailService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Mr
 * @since 2020/12/25
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MailServiceImplTest {

	@Resource
	private MailService mailService;

	@Mock
	private HttpServletRequest request;

	@Test
	public void sendSimpleMail() {
		System.out.println(mailService.sendSimpleMail("18761690550@163.com", "哈哈", "你是猪", request));
	}
}