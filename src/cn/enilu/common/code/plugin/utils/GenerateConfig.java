package cn.enilu.common.code.plugin.utils;

import com.intellij.openapi.project.Project;

/**
 * 代码生成配置对象<br>
 * </p> Copyright by easecredit.com<br>
 * 作者: zhangtao <br>
 * 创建日期: 16-7-23<br>
 */
public class GenerateConfig {
    private boolean service;
    private boolean conroller;
    private boolean view;
    private String baseUri;
    private String basePackage;

    private String controllerPakName="controllers";
    private String ServicePakName="services";
    private String modelPakName;

    private Project project;

    public boolean isService() {
        return service;
    }

    public void setService(boolean service) {
        this.service = service;
    }

    public boolean isConroller() {
        return conroller;
    }

    public void setConroller(boolean conroller) {
        this.conroller = conroller;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getControllerPakName() {
        return controllerPakName;
    }

    public void setControllerPakName(String controllerPakName) {
        this.controllerPakName = controllerPakName;
    }

    public String getServicePakName() {
        return ServicePakName;
    }

    public void setServicePakName(String servicePakName) {
        ServicePakName = servicePakName;
    }

    public String getModelPakName() {
        return modelPakName;
    }

    public void setModelPakName(String modelPakName) {
        this.modelPakName = modelPakName;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
