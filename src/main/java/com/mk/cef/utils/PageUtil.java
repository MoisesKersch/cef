package com.mk.cef.utils;

import org.springframework.web.servlet.ModelAndView;

public class PageUtil {
    private ModelAndView model;

    public PageUtil(String view) {
        this.model = new ModelAndView(view);
    }

    public void setPageTitle(String title) {
        this.model.addObject("page", title);
    }

    public void setTableId(String tableId) {
        this.model.addObject("tableId", tableId);
    }

    public void setTitle(String title) {
        this.model.addObject("title", title);
    }

    public void setURI(String uri) {
        this.model.addObject("uri", uri);
    }

    public void setSubTitle(String subTitle) {
        this.model.addObject("subTitle", subTitle);
    }

    public void setJs(String js) {
        this.model.addObject("js", js);
    }

    public void setInnerTitle(String innerTitle) {
        this.model.addObject("innerTitle", innerTitle);
    }

    public void setText(String text) {
        this.model.addObject("text", text);
    }

    public void setFormId(String formId) {
        this.model.addObject("formId", formId);
    }

    public void setAttr(String attr, Object value) {
        this.model.addObject(attr, value);
    }

    public void setCommandName(String commandName) {
        this.model.addObject("commandName", commandName);
    }

    public ModelAndView getModel() {
        return this.model;
    }
}
