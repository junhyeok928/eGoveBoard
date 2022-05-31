package egovframework.user.cop.bbs.web;

import java.io.File;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import egovframework.user.cop.bbs.service.SmarteditorVO;

@Controller
public class editorFileUpload {
	@RequestMapping("/user/cop/bbs/editorFileUpload")
	public String fileUpload(HttpServletRequest req, SmarteditorVO smarteditorVO)throws Exception {
		String callback = smarteditorVO.getCallback();
		String callback_func = smarteditorVO.getCallback_func();
		String file_result = "";
		String result = "";
		MultipartFile multiFile = smarteditorVO.getFiledata();
		try {
			if(multiFile != null && multiFile.getSize() > 0 && StringUtils.isNotBlank(multiFile.getName())) {
				if(multiFile.getContentType().toLowerCase().startsWith("image/")) {
					String oriName = multiFile.getName();
					String uploadPath = req.getServletContext().getRealPath("/img");
					String path = uploadPath + "/smarteditor/";
					File file = new File(path);
					if(!file.exists()) {
						file.mkdirs();
					}
					String fileName = UUID.randomUUID().toString();
					smarteditorVO.getFiledata().transferTo(new File(path + fileName));
					file_result += "&bNewLine=true&sFileName=" + oriName + "&sFileURL=/img/smarteditor/" + fileName;
				}else {
					file_result += "&error=error";
				}
			}else {
				file_result += "&error=error";
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		result = "redirect:" + callback + "?callback_func=" + URLEncoder.encode(callback_func, "UTF-8") + file_result;
		return result;
	}

}
