package com.mr.result;

import com.mr.LanguageEnum;

/**
 * @author Mr
 * @since 2020/12/25
 */
public class BaseResult {

	private int resultCode;
	private String resultMsg;

	public BaseResult(ResultEnum resultEnum, LanguageEnum languageEnum) {
		this.resultCode = resultEnum.getResultCode();
		this.resultMsg = resultEnum.getMsg(languageEnum);
	}

	public BaseResult(int resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
}
