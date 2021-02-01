package kr.or.ddit.user.repository;

import java.util.List;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserDao {

	//����� ���̵�� ����� ��ȸ
	UserVo selectUser(String userid);
	
	List<UserVo> selectPagingUser(PageVo pageVo);
	
	int selectAllCnt();
	
	List<UserVo> selectAllUser();
	
	int insertUser(UserVo userVo);
	
	int deleteUser(String userid);
	
	int modifyUser(UserVo userVo);
}
