package com.mr.constant;

/**
 * @author Mr
 * @since 2020/12/25
 */
public enum LanguageEnum {

	ZH,
	EN;

	public static LanguageEnum getLanguageType(String type) {
		if (null == type) {
			return ZH;
		}
		if (type.contains("zh")) {
			return ZH;
		}
		return EN;
	}
}
