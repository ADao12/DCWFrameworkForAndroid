package com.dcw.app.dcwdianping.ui.adapter;


import com.dcw.app.dcwdianping.ModuleController;
import com.dcw.framework.uiframework.basic.IFrameworkManifest;

public class FrameworkManifest implements IFrameworkManifest {
    @Override
    public String[] getControllers()
    {
        String[] controllers = new String[]{
                ModuleController.class.getName(),
        };
        return  controllers;
    }

    @Override
    public String[] getDynamicContorllers() {
        return null;
    }
}
