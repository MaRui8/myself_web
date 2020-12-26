package com.mr.result;

import com.mr.LanguageEnum;

/**
 * @author Mr
 * @since 2020/12/25
 */
public enum ResultEnum {

	/**
	 * 失败
	 */
	FAILED(-1, "", ""),
	/**
	 * 成功
	 */
	SUCCESS(0, "", "");


	private int resultCode;
	private String chMsg;
	private String enMsg;

	ResultEnum(int resultCode, String chMsg, String enMsg) {
		this.resultCode = resultCode;
		this.chMsg = chMsg;
		this.enMsg = enMsg;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getMsg(LanguageEnum htmlLanguage) {
		if (htmlLanguage == LanguageEnum.ZH) {
			return chMsg;
		} else {
			return enMsg;
		}
	}

}
