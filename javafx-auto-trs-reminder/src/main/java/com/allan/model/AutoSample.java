package com.allan.model;

public class AutoSample {
	private String name;
	private String browserType;
	private boolean isOpenBrowser;
	private String gocs;
	private String template;
	private String ignores;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrowserType() {
		return browserType;
	}

	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}
	
	

	public boolean isOpenBrowser() {
		return isOpenBrowser;
	}

	public void setOpenBrowser(boolean isOpenBrowser) {
		this.isOpenBrowser = isOpenBrowser;
	}

	public String getGocs() {
		return gocs;
	}

	public void setGocs(String gocs) {
		this.gocs = gocs;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getIgnores() {
		return ignores;
	}

	public void setIgnores(String ignores) {
		this.ignores = ignores;
	}

}
