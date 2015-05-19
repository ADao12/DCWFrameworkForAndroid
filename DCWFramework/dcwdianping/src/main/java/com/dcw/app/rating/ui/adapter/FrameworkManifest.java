package com.dcw.app.rating.ui.adapter;


import com.dcw.app.rating.ModuleController;
import com.dcw.framework.pac.basic.IFrameworkManifest;

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
