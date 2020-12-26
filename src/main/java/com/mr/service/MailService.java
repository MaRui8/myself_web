package com.mr.service;

import com.mr.result.BaseResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mr
 * @since 2020/12/25
 */
public interface MailService {

	/**
	 * 发送文本邮件
	 *
	 * @param to      收件人
	 * @param subject 主题
	 * @param content 内容
	 */
	BaseResult sendSimpleMail(String to, String subject, String content, HttpServletRequest request);

	/**
	 * 发送HTML邮件
	 *
	 * @param to      收件人
	 * @param subject 主题
	 * @param content 内容
	 */
	BaseResult sendHtmlMail(String to, String subject, String content, HttpServletRequest request);


	/**
	 * 发送带附件的邮件
	 *
	 * @param to       收件人
	 * @param subject  主题
	 * @param content  内容
	 * @param filePath 附件
	 */
	BaseResult sendAttachmentsMail(String to, String subject, String content, String filePath, HttpServletRequest request);

}
