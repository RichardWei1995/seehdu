package com.jw.shop.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Hr extends BmobObject{
          private String hrname;
          private String hrdescribe;
          private BmobFile hrpic;
          private String hrobj;
          private String hrrank;
          private String hrneed;
          private String hrbeizhu;
          private String hrtime;
          private String hrplace;
          private String permission;
          private String introduction;
		public String getIntroduction() {
			return introduction;
		}
		public void setIntroduction(String introduction) {
			this.introduction = introduction;
		}
		public String getPermission() {
			return permission;
		}
		public void setPermission(String permission) {
			this.permission = permission;
		}
		public String getHrname() {
			return hrname;
		}
		public void setHrname(String hrname) {
			this.hrname = hrname;
		}
		public String getHrdescribe() {
			return hrdescribe;
		}
		public void setHrdescribe(String hrdescribe) {
			this.hrdescribe = hrdescribe;
		}
		public BmobFile getHrpic() {
			return hrpic;
		}
		public void setHrpic(BmobFile hrpic) {
			this.hrpic = hrpic;
		}
		public String getHrobj() {
			return hrobj;
		}
		public void setHrobj(String hrobj) {
			this.hrobj = hrobj;
		}
		public String getHrrank() {
			return hrrank;
		}
		public void setHrrank(String hrrak) {
			this.hrrank = hrrak;
		}
		public String getHrneed() {
			return hrneed;
		}
		public void setHrneed(String hrneed) {
			this.hrneed = hrneed;
		}
		public String getHrbeizhu() {
			return hrbeizhu;
		}
		public void setHrbeizhu(String hrbeizhu) {
			this.hrbeizhu = hrbeizhu;
		}
		public String getHrtime() {
			return hrtime;
		}
		public void setHrtime(String hrtime) {
			this.hrtime = hrtime;
		}
		public String getHrplace() {
			return hrplace;
		}
		public void setHrplace(String hrplace) {
			this.hrplace = hrplace;
		}
}
