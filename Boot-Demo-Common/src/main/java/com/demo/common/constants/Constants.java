package com.demo.common.constants;

public class Constants {
	
	public class ComConstans {
		// webservice结果状态常量
		public static final String SUCCESS = "调用成功";
		public static final String ERROR = "error";
		public static final String IDCARD_ERROR_DESC="无效的身份证号码";

		//连接超时时间和接收超时时间
		public static final long CONNECT_TIME_OUT = 1000L*15;
		public static final long RECEIVE_TIME_OUT = 1000L*5;

		// 定义0-9 的常量
		public static final String ZERO = "0";
		public static final String ONE = "1";
		public static final String TWO = "2";
		public static final String THREE = "3";
		public static final String FOUR = "4";
		public static final String FIVE = "5";
		public static final String SIX = "6";
		public static final String SEVEN = "7";
		public static final String EIGHT = "8";
		public static final String NINE = "9";

		// 日期格式
		public static final String DATE_TYPE1 = "yyyyMMdd";
		public static final String DATE_TYPE2 = "yyyy-MM-dd";
	}


	// ID Card相关数字常量定义
	public static class IdcardConstants{

		// 身份证号码各部分长度
		public static final int START_INDEX_OF_STRING = 0;
		public static final int STANDARD_IDCARD_LENGTH = 18;
		public static final int STANDARD_IDCARD_NUM_PART_LENGTH = 17;
		public static final int LOCAL_CODE_LEN_OF_IDCARD = 6;
		public static final int BIRTHDAY_CODE_LEN_OF_IDCARD = 8;
		// 区分直辖市和非直辖市
		public static final String AREA_LEVEL_TWO = "2";
		public static final String AREA_LEVEL_THREE = "3";
		// 四个直辖市的身份证前两位编号
		public static final String BEI_JING_CODE="11";
		public static final String SHANG_HAI_CODE="31";
		public static final String TIAN_JIN_CODE="12";
		public static final String CHONG_QING_CODE="50";
		// 身份证校验序列
		public static final int VALID_SERIES[]= {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
		// 罗马数字X 对应阿拉伯数字 10
		public static final int ROMAN_NUM_TEN = 10;
		// 性别
		public static final String GENDER_MALE = "男";
		public static final String GENDER_FEMALE = "女";
	}

	public static class AddressConstants{
		// 返回值为 0 或 1，0 表示请求失败；1 表示请求成功
		public static final String STATUS = "status";
		public static final String COUNT = "count";
		public static final String INFO = "info";
		public static final String DEO_CODES = "geocodes";

		public static final String PROVINCE = "province";
		public static final String CITY = "city";
		public static final String CITY_CODE = "citycode";
		public static final String DISTRICT = "district";
		public static final String TOWNSHIP = "township";
		public static final String STREET = "street";
		public static final String NUMBER = "number";
		public static final String ADCODE = "adcode";

		public static final String LOCATION = "location";
		public static final String LEVEL = "level";
	}

	public static class RedisPrefix{

		public static final String IDCARD = "demo:idcard:";

		public static final String QUESTION_ID = "demo:question:id";
	}

}
