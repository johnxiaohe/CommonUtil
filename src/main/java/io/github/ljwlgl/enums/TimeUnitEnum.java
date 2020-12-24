package io.github.ljwlgl.enums;

/**
 * @ClassName TimeUnitEnum
 * @Description TODO: 时间单位枚举类
 * @Date 2020/6/1 14:49
 * @Author johnxiaohe
 **/
public enum TimeUnitEnum {
	DAY(1) , WEEK(2) , MONTH(3) , YEAR(4);

	private Integer index ;

	TimeUnitEnum(Integer index) {
		this.index = index;
	}

	public Integer getIndex(){
		return this.index;
	}



}
