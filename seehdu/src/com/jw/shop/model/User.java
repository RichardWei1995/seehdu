package com.jw.shop.model;

import java.io.Serializable;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 用户实体类
 */
public class User extends BmobUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String TAG = "User";

	// 父类中已经存在的属性
	// private String id;
	// private String username;
	// private String password;
	// private String email;
	// private String regTime;

	private String sex; // 性别
	private String phone; // 电话
	private String qq; // QQ
	private String school = "杭州电子科技大学"; // 学校
	private String cademy; // 学院
	private String dorPart; // 校区
	private String dorNum; // 寝室号
	private String stunum;
	public String name;
	private String state = "未登陆"; // 登录状态
	private String type = "普通用户"; // 用户类型(普通用户、黑名单、中奖者)
	private BmobFile avatar;
	private UnionSignUp unionSignUp;
	private Integer booleanticket;
	private MessegeCenter messege;

	public MessegeCenter getMessege() {
		return messege;
	}

	public void setMessege(MessegeCenter messege) {
		this.messege = messege;
	}

	public static String getTag() {
		return TAG;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQQ() {
		return qq;
	}

	public void setQQ(String qq) {
		this.qq = qq;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCademy() {
		return cademy;
	}

	public void setCademy(String cademy) {
		this.cademy = cademy;
	}

	public String getDorPart() {
		return dorPart;
	}

	public void setDorPart(String dorPart) {
		this.dorPart = dorPart;
	}

	public String getDorNum() {
		return dorNum;
	}

	public void setDorNum(String dorNum) {
		this.dorNum = dorNum;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getStunum() {
		return stunum;
	}

	public void setStunum(String stunum) {
		this.stunum = stunum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BmobFile getAvatar() {
		return avatar;
	}

	public void setAvatar(BmobFile avatar) {
		this.avatar = avatar;
	}

	public UnionSignUp getUnionSignUp() {
		return unionSignUp;
	}

	public void setUnionSignUp(UnionSignUp unionSignUp) {
		this.unionSignUp = unionSignUp;
	}

	public Integer getBooleanticket() {
		return booleanticket;
	}

	public void setBooleanticket(Integer booleanticket) {
		this.booleanticket = booleanticket;
	}

}
