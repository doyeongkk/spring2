package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserService {
	
	UserVo selectUser(String userid);
	
	//페이징
	Map<String, Object> selectPagingUser(PageVo pageVo);
	
	//
	int selectAllCnt();
	
	//전체사용자 리스트
	List<UserVo> selectAllUser();
	//사용자 등록
	int insertUser(UserVo userVo);
	//사용자 삭제
	int deleteUser(String userid);
	//사용자 수정
	int modifyUser(UserVo userVo);
	
	
}
