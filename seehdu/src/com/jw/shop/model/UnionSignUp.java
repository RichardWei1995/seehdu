package com.jw.shop.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobPointer;

public class UnionSignUp extends BmobObject{
         private String signunion;
         private String signname;
         private String signnum;
         private String signxueyuan;
         private String signzhuanye;
         private String signphone;
         private User manager;
         private BmobPointer pointeruser;
		public String getSignunion() {
			return signunion;
		}
		public void setSignunion(String signunion) {
			this.signunion = signunion;
		}
		public String getSignname() {
			return signname;
		}
		public void setSignname(String signname) {
			this.signname = signname;
		}
		public String getSignnum() {
			return signnum;
		}
		public void setSignnum(String signnum) {
			this.signnum = signnum;
		}
		public String getSignxueyuan() {
			return signxueyuan;
		}
		public void setSignxueyuan(String signxueyuan) {
			this.signxueyuan = signxueyuan;
		}
		public String getSignzhuanye() {
			return signzhuanye;
		}
		public void setSignzhuanye(String signzhuanye) {
			this.signzhuanye = signzhuanye;
		}
		public String getSignphone() {
			return signphone;
		}
		public void setSignphone(String signphone) {
			this.signphone = signphone;
		}
		public User getUser() {
			return manager;
		}
		public void setUser(User user) {
			this.manager = user;
		}
		public BmobPointer getPointeruser() {
			return pointeruser;
		}
		public void setPointeruser(BmobPointer pointeruser) {
			this.pointeruser = pointeruser;
		}
}
