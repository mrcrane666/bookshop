package news.controller;

import java.io.IOException;
import java.io.StringWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceResponse;

import news.service.NewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.ibm.json.java.JSONObject;
import com.ibm.lang.management.SysinfoCpuTime;
import com.ibm.portal.portlet.service.PortletServiceHome;
import com.ibm.portal.portlet.service.PortletServiceUnavailableException;
import com.ibm.portal.state.EngineURL;
import com.ibm.portal.state.PortletStateManager;
import com.ibm.portal.state.URLFactory;
import com.ibm.portal.state.accessors.action.engine.logout.LogoutActionAccessorController;
import com.ibm.portal.state.accessors.exceptions.StateNotInRequestException;
import com.ibm.portal.state.exceptions.CannotCloneDocumentModelException;
import com.ibm.portal.state.exceptions.CannotCreateDocumentException;
import com.ibm.portal.state.exceptions.CannotInstantiateAccessorException;
import com.ibm.portal.state.exceptions.CannotInstantiateURLFactoryException;
import com.ibm.portal.state.exceptions.InvalidConstantException;
import com.ibm.portal.state.exceptions.OutputMediatorException;
import com.ibm.portal.state.exceptions.PostProcessorException;
import com.ibm.portal.state.exceptions.StateManagerException;
import com.ibm.portal.state.exceptions.UnknownAccessorTypeException;
import com.ibm.portal.state.service.PortletStateManagerService;
import com.ibm.wps.state.accessors.action.engine.logout.LogoutActionAccessorFactory;

@Controller()
@RequestMapping("view")
public class NewsController {

	private NewsService news;

	@Autowired
	public NewsController(NewsService newsServ) {
		news = newsServ;
	}

	@RenderMapping()
	public ModelAndView showLatestNews(PortletPreferences pref, RenderRequest req, RenderResponse res) {

		ModelAndView mav = new ModelAndView("news/newsflash");
		String[] newsText = news.getOneTopic();
		mav.addObject("news", newsText[0]);
		mav.addObject("link", newsText[1]);
		mav.addObject("fontFamily", pref.getValue("fontFamily", "arial"));
//		try {
//			mav.addObject("logoutUrl", generateLogoutUrl(req, res));
//		} catch (CannotCloneDocumentModelException
//				| CannotCreateDocumentException | StateNotInRequestException
//				| UnknownAccessorTypeException
//				| CannotInstantiateAccessorException
//				| PortletServiceUnavailableException | StateManagerException
//				| CannotInstantiateURLFactoryException | PostProcessorException
//				| OutputMediatorException | NamingException | IOException e) {
//			e.printStackTrace();
//		}
		return mav;
	}

	@ResourceMapping(value = "getNews")
	public void getNews(ResourceResponse res) throws IOException {
		String[] newsText = news.getOneTopic();
		JSONObject obj = new JSONObject();
		obj.put("news", newsText[0]);
		obj.put("link", newsText[1]);
		res.getWriter().println(obj);
	}
//
//	public String generateLogoutUrl(RenderRequest req, RenderResponse res)
//			throws NamingException, PortletServiceUnavailableException,
//			StateManagerException, CannotInstantiateURLFactoryException,
//			InvalidConstantException, CannotCloneDocumentModelException,
//			CannotCreateDocumentException, StateNotInRequestException,
//			UnknownAccessorTypeException, CannotInstantiateAccessorException,
//			PostProcessorException, OutputMediatorException, IOException {
//		String logoutUrl = "";
//		Context ctx = new InitialContext();
//		PortletServiceHome stateMgrServiceHome = (PortletServiceHome) ctx
//				.lookup("portletservice/com.ibm.portal.state.service.PortletStateManagerService");
//		PortletStateManagerService service = (PortletStateManagerService) stateMgrServiceHome
//				.getPortletService(PortletStateManagerService.class);
//		PortletStateManager stateMgr = service.getPortletStateManager(req, res);
//		final URLFactory urlFactory = stateMgr.getURLFactory();
//		EngineURL url = urlFactory.newURL(null);
//		LogoutActionAccessorFactory logoutFactory = (LogoutActionAccessorFactory) stateMgr
//				.getAccessorFactory(LogoutActionAccessorFactory.class);
//		logoutUrl = url.writeDispose(new StringWriter()).toString();
//		stateMgr.dispose();
//		return logoutUrl;
//	}
}


