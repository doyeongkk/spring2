package kr.or.ddit.user.model;

import java.util.List;

public class UsersVo {
			
	private List<String> userVoList;

	public List<String> userVoList() {
		return userVoList;
	}

	public void setUserVoList(List<String> userVoList) {
		this.userVoList = userVoList;
	}

	@Override
	public String toString() {
		return "UsersVo [userid=" + userVoList + "]";
	}
	
	
}
