package com.ecarinfo.traffic.protocol.meta;

public class StaticType {
	
	public static final String DEFAULT_ORG_CODE = "-1";// 默认机构
	
	public static class QueryType {
		public static final int REAL_TIME = 1;// 实时查询
		public static final int BAT = 2;// 非时实查询(按包月之类的)
	}

	public static class ErrorCode {
		//API模块相关
		public static final Integer SUCCESS = 0;// 调用成功
		public static final Integer ORG_ERROR = -1001;// 机构编码错误
		public static final Integer FAIL = -1002;// 调用失败,服务器错误（超时，数据获取异常等）
		public static final Integer ERROR_RESULT = -1003;// 返回错误结果
		public static final Integer SOCKET_ERROR = -1004;// 网络异常
		public static final Integer SIGN_ERROR = -1005;// 未被授权访问该服务或用户名密码不正确
		public static final Integer QUERY_TIME_OUT_ERROR = -1006;// 查询时间超时
		public static final Integer TACK_NOT_FOUND = -1007;// 没有找到策略
		public static final Integer SPI_NOT_FOUND = -1008;// 没有找到SPI信息
		public static final Integer SPI_TRAFFIC_URL_NOT_FOUND = -1009;// 没有找到SPI.TRAFFIC_URL信息
		public static final Integer SPI_KEY_NOT_FOUND = -1010;// 没有找到SPI.SPI_KEY信息
		public static final Integer SPI_USERNAME_OR_PASSWORD_NOT_FOUND = -1011;// 用户名或密码为空
		public static final Integer SPI_MONEY_NOT_FOUND = -1012;// 没有找到SPI价钱信息
		public static final Integer API_MONEY_NOT_FOUND = -1013;// 没有找到API价钱信息
		public static final Integer UNKNO_ERROR = -1014;// 未和错误
		public static final Integer ERROR_NO_CAR_COUNT_LEFT = -1015;// 查询车辆次数已经用完
		public static final Integer ERROR_NO_CAR_QUERY_LEFT = -1016;// 车辆查询次数已经用完
		
		//违章相关
		public static final Integer ERROR_PARAM = -2001;//缺少必要的参数或找不到车牌前缀所匹配的城市
		public static final Integer ERROR_UNSUPPORT_AREA = -2003;//本系统暂不提供该城市违章查询请求	
		public static final Integer ERROR_SERVER_INTERVAL = -2005;// 服务器错误（超时，数据获取异常等）
		public static final Integer ERROR_SIGN = -2010;//未被授权访问该服务或用户名密码不正确
		public static final Integer ERROR_UNKNOWN = -2020;//未和错误
		public static final Integer ERROR_AUTHOR = -2040;//未被授权查询此车牌信息	
		public static final Integer ERROR_DATASOURCE = -2042;//数据源暂不可用	
		public static final Integer ERROR_LIMIT_COUNT = -2043;// 当日查询数已达到授权数标准，无法继续查询
		public static final Integer ERROR_INPUT = -2006;// 错误：您输入信息有误  
		public static final Integer ERROR_CAR_NO = -2061;//输入车牌号有误	
		public static final Integer ERROR_CAR_FRAME = -2062;//输入车架号有误	
		public static final Integer ERROR_CAR_ENGINE = -2063;//输入发动机号有误
		public static final Integer ERROR_CAR_TYPE = -2066;//不支持的车辆类型  
		public static final Integer ERROR_DIFF_AREA = -2067;//该省份（城市）不支持异地车牌
		
	}

	public static class Status {
		public static final Integer VALID = 1;// 有效的
		public static final Integer IN_VALID = 0;// 无效的
		public static final Integer DEL_VALID = -1;// 删除的
		public static final Integer DEFAULT = 0;
	}

	public static class CarType {
		/**
		 * 大型车
		 */
		public static final Integer LARGE = 1;// 大型
		/**
		 * 小型车
		 */
		public static final Integer SMALL = 2;// 小型
	}
	
	/**
	 * 计费类型
	 */
	public static class MoneyType {
		/**
		 * 按请求计
		 */
		public static final Integer PER_REQUEST = 1;// 按请求计
		/**
		 * 按年计
		 */
		public static final Integer PER_YEAR = 2;// 按年计
		/**
		 * 按月计
		 */
		public static final Integer PER_MONTH = 3;// 按月计
		/**
		 * 按天计
		 */
		public static final Integer PER_DAY = 4;// 按天计
	}
	
}
