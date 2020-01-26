package no.comp.finalproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ViewMessageCleanerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String referer = request.getHeader("Referer");
		
		if (referer != null) {
			for (int i = 0; i < MessageToView.values().length; i++) {
				referer = referer.replaceAll("(" + "\\?" + MessageToView.values()[i].getValue() + ")", "");
				referer = referer.replaceAll("(" + "\\&" + MessageToView.values()[i].getValue() + ")", "");
			}
			request.setAttribute("referer", referer);
		}

		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

}

enum MessageToView {
	ERROR("error"), CREATED("created"), SUCCESS("success"), UPDATED("updated"), CLEARED("cleared"),
	ROLE_CHANGED("role_changed"), DELETED("deleted"), RESTORED("restored"), EXISTS("exists"),
	REGISTERED("registered"), IMAGE_DELETED("image_deleted"), IMAGE_DELETION_ERROR("image-deletion_error");

	private String value;

	private MessageToView(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
