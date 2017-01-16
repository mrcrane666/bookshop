package bookshop.controller;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@Controller
@RequestMapping("edit")
public class EditController {

	@RenderMapping()
	public ModelAndView edit(PortletPreferences pref){
		ModelAndView mav = new ModelAndView("books/book/edit/bookListEdit");
		mav.addObject("user_color", pref.getValue("user_color", "black"));
		return mav;
	}
	
	@ActionMapping(params="action=saveUserConfig")
	public void storeuserConfig(ActionRequest req, PortletPreferences pref) {
		String color = req.getParameter("user_color");
		if(!"".equals(color)){
			try {
				pref.setValue("user_color", color);
				pref.store();
			} catch (ReadOnlyException | ValidatorException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
