package kr.mvc.controller;

// 호출 방식과 view 파일명을 기억
public class ModelAndView {
	private boolean isRedirect = false; // Spring은 forwarding이 디폴트
	private String viewName = "";
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
}
