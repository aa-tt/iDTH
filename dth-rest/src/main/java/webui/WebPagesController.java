package webui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import daos.CustomerDAO;

@Controller
public class WebPagesController {
	
	@Autowired CustomerDAO customerDAO;
	
	@RequestMapping(value="/newfile", method=RequestMethod.GET)
	public ModelAndView getNewFileJsp() {
		return new ModelAndView("NewFile");
	}
}
