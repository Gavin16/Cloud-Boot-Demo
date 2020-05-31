package com.demo.common.utils;

import com.demo.common.constants.Constants;
import org.apache.commons.lang3.StringUtils;

/**
 * className: DigitUtil
 * @author 伊塔
 * date: 2017-12-9 上午11:10:38
 * Description:
 */
public class DigitUtil {
	
	/**
	 * @Title: isNumberSeries
	 * @param dataInput
	 * @return boolean
	 * date: 2017-12-9 上午11:11:48
	 * @Description: 判断是否是数字串
	 * @throws
	 */
	public static boolean isNumberSeries(String dataInput) {
		if(StringUtils.isNotEmpty(dataInput)) {
			for(int i=0;i<dataInput.length();i++) {
				if(!Character.isDigit(dataInput.charAt(i))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * @Title: isValidIdCard
	 * @param idCardNum
	 * @return boolean
	 * @Description: 判断是否是有效的身份证号码
	 * @throws
	 */
	public static boolean isValidIdCard(String idCardNum) {
		
		try {
			String partToValid = idCardNum.substring(0, idCardNum.length()-1);
			String endNumStr = idCardNum.substring(idCardNum.length()-1);
			// 判断最后的校验位是否在 "0"-"9","X" 这11种取值内
			
			if(isValidEndNum(endNumStr.charAt(0))) { // 最后的校验位合法才解析
				int endNum = "X".equals(endNumStr)? Constants.IdcardConstants.ROMAN_NUM_TEN : Integer.parseInt(endNumStr);
				if(endNum == getValidResult(partToValid)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @Title: getValidResult
	 * @param partToValid
	 * @return int
	 * @Description: 计算输入身份证前17位对应的校验位
	 * @throws
	 */
	public static int getValidResult(String partToValid) {
		int sum = 0;
		int temp;
		for(int k=0;k<partToValid.length();k++) {
			if(Character.isDigit(partToValid.charAt(k))) {
				// char类型直接相减
				int curr = partToValid.charAt(k)-'0';
				sum += curr*Constants.IdcardConstants.VALID_SERIES[k];
			}else {
				return -1;
			}
		}
		temp = sum%11;
		// 身份证前17位的加权和 加上最后一个的值对11求模 要求恒等于 1
		return temp==0?1:12-temp;
	}

	/**
	 * 
	 * @Title: isValidEndNum
	 * @param @param c
	 * @param @return
	 * @return boolean
	 * @Description: 检查最后的校验位是否合法
	 * @throws
	 */
	public static boolean isValidEndNum(char c) {
		switch (c) {
			case '0':case '1':case '2':case '3':case '4':case '5':case '6':
			case '7':case '8':case '9':case 'X':
			return true;
			
			default: return false;
		}
	}
}
