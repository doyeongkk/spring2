package kr.or.ddit.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.service.UserService;

public class IocMain {
	
	private static final Logger logger = LoggerFactory.getLogger(IocMain.class);

	public static void main(String[] args) {
		//1. ������ ���� ������ �̿��Ͽ� ������ �����̳ʸ� ����(kr/or/ddit/ioc/ioc.xml)
		//		������ �����̳� Ÿ�� : ApplicationContext
		//2. ������ �����̳ʿ��� ������� ������ ���� ��û
		//		DL(Dependency Lookup) : ������ �����̳ʿ��� ������ ���� ��û�ϴ� ����
		//3. ������ �����̳ʿ��� �����ǰ� �ִ� ���� �� ����� ������ Ȯ��
		
		ApplicationContext context 
			= new ClassPathXmlApplicationContext("classpath:/kr/or/ddit/ioc/ioc.xml");
		
		UserDao userDao = (UserDao)context.getBean("userDao");
		
		UserVo userVo = userDao.selectUser("brown");
		logger.debug("userVo : {}", userVo);
		
		//������ ���״ϳʷ� ���� userService ������ ���� DL�� ���� ������
		//getUser �޼ҵ带 call, ��ȯ�� ��(userVo)�� logger�� ���� ���
		UserService userService = (UserService)context.getBean("userService");
		
		UserVo userVo1 = userService.selectUser("brown");
		logger.debug("userVo1 : {}", userVo1);
		
		
		for(String beanName : context.getBeanDefinitionNames()) {
			logger.debug("beanName : {}", beanName);
		}
	}

}

























