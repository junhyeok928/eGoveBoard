package egovframework.user.cop.bbs.web;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.user.cop.bbs.service.BoardExService;

@Controller
public class BoardMasterExController {
	@Resource(name="BoardExService")
	private BoardExService boardExService;

	@Autowired
    private DefaultBeanValidator beanValidator;
	
	
//	@RequestMapping("/user/cop/bbs/insertBBSMasterView.do")
//	public String insertBBSMasterView
	
}
