package kr.or.ddit.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.util.FileUtil;
import kr.or.ddit.validator.UserVoValidator;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
	
	@RequestMapping("user")
	@Controller
	public class UserController {
	  private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("allUser")
	public String allUser(Model model) {
		
		List<UserVo> userList = userService.selectAllUser();
		
		model.addAttribute("userList", userList);
		return "/user/allUser";
	}
	
	//전체사용자 타일즈 
	@RequestMapping("allUserTiles")
	public String allUserTiles(Model model) {
		
		List<UserVo> userList = userService.selectAllUser();
		
		model.addAttribute("userList", userList);
		return ".tiles.user.allUser";
	}
	
	
	
	
	@RequestMapping("pagingUser")
	public String pagingUser ( 
			                    @RequestParam(defaultValue = "1") int page,
								@RequestParam(defaultValue = "5" )	int pageSize,
								Model model) {
								
	
		
		PageVo pageVo = new PageVo(page, pageSize);
		
	    model.addAllAttributes(userService.selectPagingUser(pageVo));
	    
	    
		return "user/pagingUser";
	}
	
	//페이징 타일즈
	@RequestMapping("pagingUserTiles")
	public String pagingUserTiles ( @RequestParam(defaultValue = "1") int page,
									@RequestParam(defaultValue = "5" )	int pageSize,
									Model model) {

		
		PageVo pageVo = new PageVo(page, pageSize);
		
	    model.addAllAttributes(userService.selectPagingUser(pageVo));
	    
	    //tiles-definition에 설정한 name
		return "tiles.user.pagingUser";
	}
	
	
	
	
	//@RequestMapping("pagingUser")
	public String pagingUser(PageVo pageVo) {
		
		
		
	logger.debug("pageVo: {}", pageVo);
		
  return "";
	}
	//사용자 상세 조회 
	
	@RequestMapping("user")
	public String userDetail( String userid,Model model) {
		UserVo user = userService.selectUser(userid);
		model.addAttribute("user", user);	
		 
		return "user/user";
	}
	//사용자 상세조회 타일즈
//	@RequestMapping("userDetailTiles")
//	public String userDetailTiles( String userid,Model model) {
//		UserVo user = userService.selectUser(userid);
//		model.addAttribute("user", user);	
//		 
//		return "user/user";
//	}

	
	

	    
	//사용자 삭제 기능
	@RequestMapping("deleteUser")
	public String deleteUser(String userid) {
		userService.deleteUser(userid);	
		return "user/pagingUser";
	}
	
	
	
	  //사용자 수정 페이지  
	  @RequestMapping(path="userModify", method= RequestMethod.POST) 
	  public String modifyUser(Model model, MultipartFile profile, String userid, UserVo userVo) {
	   
		if(profile != null) {
			String filename = profile.getOriginalFilename();
			String fileExtension = FileUtil.getFileExtension(filename);
			String realfilename = UUID.randomUUID().toString()+fileExtension;
			userVo.setFilename(filename);  
			userVo.setRealfilename(realfilename); 
			try {
				profile.transferTo(new File("d:\\upload\\" +realfilename));
			}catch (IllegalStateException | IOException e){
				e.printStackTrace();
			}
		}
		int updateCnt = userService.modifyUser(userVo);
		
		model.addAttribute("user",userService.selectUser(userid));
		if(updateCnt == 1) {
			return "redirect:/user/user?userid=" + userid;
		}else {
			return "user/userModify";
		}
	  }
		
		@RequestMapping(path="modifyUser", method = RequestMethod.GET)
		public String modifyUser(Model model, String userid) {
			model.addAttribute("user", userService.selectUser(userid));
			return "user/userModify";
		}
		
   
		 //사용자 등록 페이지  
//    @RequestMapping(path="registUser",  method= RequestMethod.POST)
//    public String resistUser(Model model, MultipartFile profile, UserVo userVo) {
//    	if(profile != null) {
//    		
//			String filename = profile.getOriginalFilename();
//			String fileExtension = FileUtil.getFileExtension(filename);
//			String realfilename = UUID.randomUUID().toString()+fileExtension;
//			userVo.setFilename(filename);  
//			userVo.setRealfilename(realfilename); 
//		
//			try {
//				profile.transferTo(new File("d:\\upload\\" +realfilename));
//			}catch (IllegalStateException | IOException e){
//				e.printStackTrace();
//			}
//		}
		 //bindingResult 객체는 command 객체 바로 뒤에 인자로 기술해야 한다. 
//	     @RequestMapping(path="regist", method =RequestMethod.POST)
//	     public String regist(@Valid UserVo userVo, BindingResult result, MultipartFile profile, Model model) {
//	     
//	     new UserVoValidator().validate(userVo, result);
//	     if(result.hasErrors()) {
//	    	 logger.debug("result has error");
//	    	 return "user/registUser";
//	     }
		
		
		
		
//		int insertCnt = 0;
//		try {
//			insertCnt = userService.insertUser(userVo);
//		}catch (Exception e){
//			insertCnt = 0;
//		}
//	      if(insertCnt ==1) {
//	    	  return "redirect:/user/pagingUser";
//	      }else {
//			return "user/registUser";
//		}
//    		
//    }
//
//	@RequestMapping(path="registUser", method = RequestMethod.GET)
//	public String registUser() {
//		return "user/registUser";
//	}
    
    @RequestMapping("excelDownload")
    public String excelDownload(Model model) {
        List<String> header = new ArrayList<String>();
        header.add("사용자 아이디");
        header.add("사용자 이름");
        header.add("사용자 별명");
        
        model.addAttribute("header", header);
        model.addAttribute("data", userService.selectAllUser());
        
    	return "userExcelDownloadView";	
    }
    
    
    //localhost/user/profile
    @RequestMapping("profile")
		public void profile(HttpServletResponse resp, String userid, HttpServletRequest req) {
    	 resp.setContentType("image");
   	  
   	  //userid 파라미터를 이용하여
   	  // userService 객체를 통해 사용자의 사진 파일 이름을 획득
   	  // 파일 입출력을 통해 사진을 읽어들여 resp객체의 outputStream으로 응답 생성
   	
   	  UserVo userVo = userService.selectUser(userid);
   		 String path ="";
   	 if( userVo.getRealfilename() == null) {
   		path = req.getServletContext().getRealPath("/image/unknown.png");
   	 }else {
   		path= userVo.getRealfilename();
   		 
   	 }
   	  
   	  logger.debug("path : {}", path);
   	  
   	  try {
   		  
   		  FileInputStream fis = new FileInputStream(path);
   		  ServletOutputStream sos = resp.getOutputStream();
   		  
   		  byte[] buff = new byte[512];
   		  while(fis.read(buff) != -1) {
   			  sos.write(buff);
   		  }
   		  fis.close();
   		  sos.close();
   	  }catch (Exception e){
   		  e.printStackTrace();
   	  }
   }

    }
	
     
	
	
	

	

	




