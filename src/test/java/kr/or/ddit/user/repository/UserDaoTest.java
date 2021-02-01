package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;

//������ ȯ�濡�� junit �ڵ带 ���� ==> junit �ڵ嵵 ������ ������ ���


public class UserDaoTest extends ModelTestConfig{
	
	@Resource(name="userDao")
	private UserDao userDao;
	
//	@Before
//	public void setup() {
//		userDao = new UserDaoImpl();
//		
//		//테스트에서 사용할 신규 사용자 추가
//		UserVo userVo = new UserVo("testUser", "테스트사용자", "testUserPass", 
//									new Date(), "대덕", "대전 중구 중앙로 76", "4층", 
//									"34940", "brown.png", "uuid-generated");
//		
//		userDao.insertUser(userVo);
//		
//		//신규 입력 테스트를 위해 테스트 과정에서 입력된 데이터를 삭제
//		userDao.deleteUser("ddit_n");
//	}
	
//	@After
//	public void tearDown() {
//		userDao.deleteUser("testUser");
//	}
	
	
	@Test
	public void selectUserTest() {
		/***Given***/
		String userid = "brown";
		
		/***When***/
		UserVo userVo = userDao.selectUser(userid);

		/***Then***/
		assertEquals("브라운", userVo.getUsernm());
	}
	
	@Test
	public void selectPagingTest() {
		/***Given***/
		PageVo pageVo = new PageVo(2, 5);
		
		/***When***/
		List<UserVo> userList = userDao.selectPagingUser(pageVo);
		
		/***Then***/
		assertEquals(5, userList.size());
	}
	
	@Test
	public void selectAllUserTest() {
		/***Given***/
		

		/***When***/
		List<UserVo> userList = userDao.selectAllUser();
		/***Then***/
		assertEquals(26, userList.size());
	}
	
	@Test
	public void modifyUserTest() {
		/***Given***/
		UserVo userVo = new UserVo("ddit", "대덕인재", "dditpass", new Date(), "개발원 m", "대전시 중구 중앙로76", "4층 대덕인재개발원", "34940", "brown.png", "uuid-generated");
	
		/***When***/
		int updateCnt = userDao.modifyUser(userVo);
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void insertUserTest() {
		/***Given***/
		userDao.deleteUser("ddit_n");

		UserVo userVo = new UserVo("ddit_n", "대덕인", "dditpass", new Date(), "개발원 i", "대전시 중구 중앙로76", "4층 대덕인재개발원", "34940", "brown.png", "uuid-generated");
		/***When***/
		int insertCnt = userDao.insertUser(userVo);
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void deleteUserTest() {
		/***Given***/
		UserVo userVo = new UserVo("testUser", "테스트사용자", "testUserPass", 
				new Date(), "대덕", "대전 중구 중앙로 76", "4층", 
				"34940", "brown.png", "uuid-generated");
		userDao.insertUser(userVo);
		String userid = "testUser";

		/***When***/
		int deleteCnt = userDao.deleteUser(userid);

		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	
	
	
	
	

}
























