package kr.or.ddit.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class FileDownloadView extends AbstractView {
    
	
	
	// controller에서 개발자가 model 객체에 realfilename = 실제 디스크에 저장된 경로와 파일명을,
	// 									filename = 업로드 당시의 파일명을 속성을 저장 
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// d:\\upload\\sally.png
		
		String realfilename = (String)model.get("realFilename");
		String filename = (String)model.get("filename");
		
		response.setHeader("Content-Disposition", "attachment" + filename);
		
		
		ServletOutputStream sos = response.getOutputStream();
		FileInputStream fis  = new FileInputStream(new File(realfilename));
		byte[]buf = new byte[512];
		
		while(fis.read(null) != -1) {
			
		}
		fis.close();
		sos.close();
	}
    
}













