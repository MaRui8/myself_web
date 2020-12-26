package com.mr.service.impl;

import com.mr.constant.LanguageEnum;
import com.mr.result.BaseResult;
import com.mr.result.ResultEnum;
import com.mr.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author Mr
 * @since 2020/12/25
 */
@Service("mailService")
public class MailServiceImpl implements MailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Spring Boot 提供了一个发送邮件的简单抽象，使用的是下面这个接口，这里直接注入即可使用
	 */
	@Resource
	private JavaMailSender mailSender;

	/**
	 * 配置文件中我的qq邮箱
	 */
	@Value("${spring.mail.from}")
	private String from;

	/**
	 * 简单文本邮件
	 *
	 * @param to      收件人
	 * @param subject 主题
	 * @param content 内容
	 */
	@Override
	public BaseResult sendSimpleMail(String to, String subject, String content, HttpServletRequest request) {
		try {
			//创建SimpleMailMessage对象
			SimpleMailMessage message = new SimpleMailMessage();
			//邮件发送人
			message.setFrom(from);
			//邮件接收人
			message.setTo(to);
			//邮件主题
			message.setSubject(subject);
			//邮件内容
			message.setText(content);
			//发送邮件
			mailSender.send(message);
			return new BaseResult(ResultEnum.SUCCESS, LanguageEnum.getLanguageType(request.getHeader("accept-language")));
		} catch (MailException e) {
			logger.error("send content mail to:[{}] failed", to, e);
			return new BaseResult(ResultEnum.FAILED, LanguageEnum.getLanguageType(request.getHeader("accept-language")));
		}
	}

	/**
	 * html邮件
	 *
	 * @param to      收件人
	 * @param subject 主题
	 * @param content 内容
	 */
	@Override
	public BaseResult sendHtmlMail(String to, String subject, String content, HttpServletRequest request) {
		//获取MimeMessage对象
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(message, true);
			//邮件发送人
			messageHelper.setFrom(from);
			//邮件接收人
			messageHelper.setTo(subject);
			//邮件主题
			message.setSubject(subject);
			//邮件内容，html格式
			messageHelper.setText(content, true);
			//发送
			mailSender.send(message);
			//日志信息
			logger.info("send html mail to:[{}] success", to);
			return new BaseResult(ResultEnum.SUCCESS, LanguageEnum.getLanguageType(request.getHeader("accept-language")));
		} catch (MessagingException e) {
			logger.error("send html mail to:[{}] failed", to, e);
			return new BaseResult(ResultEnum.FAILED, LanguageEnum.getLanguageType(request.getHeader("accept-language")));
		}
	}

	/**
	 * 带附件的邮件
	 *
	 * @param to       收件人
	 * @param subject  主题
	 * @param content  内容
	 * @param filePath 附件
	 */
	@Override
	public BaseResult sendAttachmentsMail(String to, String subject, String content, String filePath, HttpServletRequest request) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			helper.addAttachment(fileName, file);
			mailSender.send(message);
			//日志信息
			logger.info("send Attachment mail to:[{}] success", to);
			return new BaseResult(ResultEnum.SUCCESS, LanguageEnum.getLanguageType(request.getHeader("accept-language")));
		} catch (MessagingException e) {
			logger.error("send Attachment mail to:[{}]  failed", to, e);
			return new BaseResult(ResultEnum.FAILED, LanguageEnum.getLanguageType(request.getHeader("accept-language")));
		}
	}
}
