package bookshop.controller;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.ValidatorException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@Controller
@RequestMapping("edit_defaults")
public class EditDefaultsController {

	@RenderMapping()
	public ModelAndView edit(RenderRequest req, PortletPreferences pref) {
		ModelAndView mav = new ModelAndView("books/book/edit/bookListEditDeafaults");
		String height = pref.getValue("admin_height", "500");
		String width = pref.getValue("admin_width", "500");
		System.out.println(height);
		mav.addObject("momHeight", height);
		mav.addObject("momWidth", width);
		return mav;
	}

	@ActionMapping(params = "action=savePortletConfig")
	public void storePortletPref(ActionRequest req, PortletPreferences pref) {
		try {
			String momHeight = req.getParameter("momHeight");
			String momWidth = req.getParameter("momWidth");
			if (!"".equals(momWidth)) {
				pref.setValue("admin_width", momWidth);
			}
			if (!"".equals(momHeight)) {
				pref.setValue("admin_height", momHeight);
			}
			pref.store();
		} catch (ReadOnlyException | ValidatorException | IOException e) {
			System.err.println("Preferences could not be set!");
			e.printStackTrace();
		}
	}
}
