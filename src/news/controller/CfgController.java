package news.controller;

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
@RequestMapping("config")
public class CfgController {

	@RenderMapping()
	public ModelAndView showNewsCfg(PortletPreferences pref){
		ModelAndView mav = new ModelAndView("news/edit/newsCfg");
		mav.addObject("fontFamily", pref.getValue("fontFamily", "arial"));
		return mav;
	}
	
	@ActionMapping(params="action=savePortletCfg")
	public void storeNewsCfg(ActionRequest req, PortletPreferences pref){
		String font = req.getParameter("fontFamily");
		if(!"".equals(font)){
			try {
				pref.setValue("fontFamily", font);
				pref.store();
			} catch (ReadOnlyException | ValidatorException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}

