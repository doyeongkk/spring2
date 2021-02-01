package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserService {
	
	UserVo selectUser(String userid);
	
	Map<String, Object> selectPagingUser(PageVo pageVo);
	
	int selectAllCnt();
	
	List<UserVo> selectAllUser();
	
	int insertUser(UserVo userVo);
	
	int deleteUser(String userid);
	
	int modifyUser(UserVo userVo);
	
	
}
