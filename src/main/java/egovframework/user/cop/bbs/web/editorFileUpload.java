package egovframework.user.cop.bbs.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import egovframework.user.cop.bbs.service.SmarteditorVO;

@Controller
public class editorFileUpload {
	@RequestMapping("/user/cop/bbs/editorFileUpload")
	public String fileUpload(HttpServletRequest req, SmarteditorVO smarteditorVO) throws Exception {
		String callback = smarteditorVO.getCallback();
		String callback_func = smarteditorVO.getCallback_func();
		String file_result = "";
		String result = "";
		MultipartFile multiFile = smarteditorVO.getFiledata();
		try {
			if (multiFile != null && multiFile.getSize() > 0 && StringUtils.isNotBlank(multiFile.getName())) {
				if (multiFile.getContentType().toLowerCase().startsWith("image/")) {
					String oriName = multiFile.getName();
					String uploadPath = req.getServletContext().getRealPath("/img");
					String path = uploadPath + "/smarteditor/";
					File file = new File(path);
					if (!file.exists()) {
						file.mkdirs();
					}
					String fileName = UUID.randomUUID().toString();
					smarteditorVO.getFiledata().transferTo(new File(path + fileName));
					file_result += "&bNewLine=true&sFileName=" + oriName + "&sFileURL=/img/smarteditor/" + fileName;
				} else {
					file_result += "&error=error";
				}
			} else {
				file_result += "&error=error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = "redirect:" + callback + "?callback_func=" + URLEncoder.encode(callback_func, "UTF-8") + file_result;
		return result;
	}
	@RequestMapping({"/cmmn/file/editorFileUpload.do","/com/file/editorFileUpload.do"})
	   public String editorFileUpload(ModelMap model, HttpServletRequest request, HttpServletResponse response,SmarteditorVO smarteditorVO) throws Exception {
//	      System.out.println("##### /cmmn/file/editorFileUpload.do : 업로드 들어옴");
		String callback = smarteditorVO.getCallback();
		String callback_func = smarteditorVO.getCallback_func();
		String file_result = "";
	      String sFileInfo = "";
	      String returnStr = "";
	      
	      //파일명 - 싱글파일업로드와 다르게 멀티파일업로드는 HEADER로 넘어옴
	      String name = request.getHeader("file-name");
	      String ext = name.substring(name.lastIndexOf(".")+1);

	      long fileSize = new Long(request.getContentLength());
	      //System.out.println("ext::" + ext.toLowerCase());
	      //[보안취약점]-2019-01-25 수정-start
	      if (!"jpg".equals(ext.toLowerCase()) && !"gif".equals(ext.toLowerCase()) && !"png".equals(ext.toLowerCase()) && !"bmp".equals(ext.toLowerCase())){
//	         System.out.println("NOTALLOW_" + name);
	         returnStr="NOTALLOW_" + name;
	         //System.out.println("등록하실 수 없는 확장자입니다.");
	         model.addAttribute("result", returnStr);
	      }
	      //3MB 보다 큰지 체크
	      else if (fileSize > 3000000) {
//	         System.out.println("LIMITFILESIZE_" + "3MB");
	         returnStr="LIMITFILESIZE_" + "3MB";
	         model.addAttribute("result", returnStr);
	      }
	      //[보안취약점]-2019-01-25 수정-end

	      //파일 기본경로
	      //String defaultPath = request.getServletContext().getRealPath("/");
	      //String defaultPath = request.getSession().getServletContext().getRealPath("/");
	      String defaultPath = "D:";
//	      System.out.println("defaultPath="+defaultPath);
	      //파일 기본경로 _ 상세경로
	      String path = defaultPath + "/editorUpload" + File.separator;
//	      System.out.println("path="+path);

	      
	      
	      File file = new File(path);
	      if(!file.exists()) {
	        file.mkdirs();
	      }
	      String realname = UUID.randomUUID().toString() + "." + ext;
	      InputStream is = request.getInputStream();
	      OutputStream os=new FileOutputStream(path + realname);
	      
	      String fileStreCours = "/editorUpload";
	      String streFileNm = realname;
	      
	      String imageSrc = "/com/file/imageSrc.do";
	      
	      try{
	      
	         
	         int numRead;
	         //파일쓰기
	         byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
	         while((numRead = is.read(b,0,b.length)) != -1){
	           os.write(b,0,numRead);
	         }
	         if(is != null) {
	           is.close();
	         }
	         os.flush();
	         os.close();
	         //sFileInfo += "&bNewLine=true&sFileName="+ name+"&sFileURL="+"/editorUpload/"+realname;
	         sFileInfo += "&bNewLine=true";
	         sFileInfo += "&sFileName=" + name; 
	         sFileInfo += "&sFileURL=" + imageSrc;
	         sFileInfo += "&fileStreCours=" + fileStreCours;
	         sFileInfo += "&streFileNm=" + streFileNm;
	         
//	         System.out.println(sFileInfo);
	         returnStr=sFileInfo;
	         model.addAttribute("result", returnStr);
	      } catch (Exception e){
//	         System.out.println(e.toString());
	      } finally {
	         if (is!=null){ try { is.close();} catch (Exception e) {   e.printStackTrace();}}
	         if (os!=null){ try { os.flush(); os.close();} catch (Exception e) {   e.printStackTrace();}}
	      }
	      
	      String result = "redirect:" + callback + "?callback_func=" + URLEncoder.encode(callback_func, "UTF-8") + file_result;
	      return result;
	      
	   }
}
