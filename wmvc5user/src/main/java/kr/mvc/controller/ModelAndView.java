package kr.mvc.controller;

public class ModelAndView {		// 호출 방식과 view 파일명 기억
	private boolean isRedirect = false;
	private String viewName = "";
	
	public boolean isRedirect() {	// Redirect의 getter
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {	// Redirect의 setter
		this.isRedirect = isRedirect;
	}
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	
}
