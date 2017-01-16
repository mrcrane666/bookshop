package bookshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@RequestMapping("view")
@Controller
public class Home {

	@RenderMapping()
	public ModelAndView showHome(){
		ModelAndView mav = new ModelAndView("home/home");
		return mav;
	}
	@RenderMapping(params="view=commingSoon")
	public ModelAndView commingSoon(){
		ModelAndView mav = new ModelAndView("home/commingSoon");
		return mav;
	}
}
