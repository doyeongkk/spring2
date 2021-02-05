package kr.or.ddit.user.repository;

import java.util.List;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserDao {

	//사용자 아이디로 사용자 조회 
	UserVo selectUser(String userid);
	//페이징
	List<UserVo> selectPagingUser(PageVo pageVo);
	
	int selectAllCnt();
	//전체 사용자
	List<UserVo> selectAllUser();
	//사용자 등록
	int insertUser(UserVo userVo);
	//사용자 삭제
	int deleteUser(String userid);
	//사용자 수정
	int modifyUser(UserVo userVo);
}
