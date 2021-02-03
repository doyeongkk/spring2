package kr.or.ddit.hello;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.config.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

/*
kava - gui , swing , awt , java , fx , swt

*/
//설정파일이 지금 1개만 필요한 것인지 그 안에 속하는 설정파일도 필요한 것인지 잘 생각할 것 -> 지금은 classpath 를 추가해서 userservice 와 userdao 도 추가해줘야 한다. 

@ContextConfiguration( locations = {"classpath:/kr/or/ddit/config/spring/application-context.xml" , "classpath:/kr/or/ddit/config/spring/root-context.xml" })
@WebAppConfiguration //스프링 환경을 WEB 기반의 application Context 로 생성 
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloControllerTest extends WebTestConfig {

   
   /* @Resource(name="helloController") */
   //type 으로 찾아간다. -> 호환될 수 있는 타입이 2개 이상일 때는? 그럼 ?
   //스프링 빈중에 대입 가능한 타입의 스프링 빈을 주입한다.
   //만약 동일한 타입의 스프링 빈이 여러개 있을 경우 @Qulifier 어노테이션을 통해
   //특정 스프링 빈의 이름을 지칭 할 수 있따. 
   // ==> @Resource 어노테이션 하나를 사용했을 때와 동일하다. 
   
   /* 이부분은 공통으로 WebTestConfig 에 넣고 이 클래스를 상속받을 것임. 
    * @Autowired private WebApplicationContext context;
    * 
    * private MockMvc mockMvc;
    * 
    * 
    * //mockupmvc객체 를 만들기 위해서. 모든 테스트에 적용하기 위해서.
    * 
    * @Before public void setup() { //mockMvc 객체가 만들어진다. mockMvc =
    * MockMvcBuilders.webAppContextSetup(context).build(); //매번 mockMvc 가 만들어진다. }
    */
   
   @Test
   public void viewTest() throws Exception {
      //한코드에 들어가있어서 gwt 안쓰고 builder 패턴
      //초록동그라미 : public 빨간 s : static 
      //get 방식으로 요청을 보내겠다. 인자는 url 
      //localhost/hello/view 
      //내부라서 localhost 는 빼고 url 쓰기
      //상태가 응답이 정상적으로 왔다 200 을 확인(status().isOk())
      //build up 패턴 
      MvcResult mvcResult = mockMvc.perform(get("/hello/view"))
				    		  .andExpect(status().isOk())
				    		  .andExpect(view().name("hello"))
				    		  .andExpect(model().attributeExists("userVo"))
				    		  .andDo(print())
				    		  .andReturn();
      
      ModelAndView mav = mvcResult.getModelAndView();
      
      assertEquals("hello", mav.getViewName());
      
      UserVo userVo = (UserVo)mav.getModel().get("userVo");
      assertEquals("브라운", userVo.getUsernm());
      
      
      
      //우리가 수행되어 있는게 아니라서 import static org.junit.Assert.*; 이렇게 static 으로 import 가 되어 있다. 
      //앞에 클래스명을 안쓴 이유는 static import가 되어 있다. 
      //assertEquals(0, 0);
      
      //늘 매번 controller 를 하려고 할때 위에 코드들이 반복. -> class 만들고ㅓ 상속시켜버리기로 해보자. 
      
      
      
   }
   
   @Test
   public void PathVariableTest() throws Exception {
	   MvcResult mvcResult = mockMvc.perform(get("/hello/path/conny"))
	    		  .andExpect(status().isOk())
	    		  .andExpect(model().attributeExists("subpath"))
	    		  .andDo(print())
	    		  .andReturn();


   }
   
   
   

}















