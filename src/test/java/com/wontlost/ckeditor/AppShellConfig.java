package com.wontlost.ckeditor;


import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.AppShellSettings;

public class AppShellConfig implements AppShellConfigurator {

    @Override
    public void configurePage(AppShellSettings settings) {
        settings.addFavIcon("icon", "icons/vaadin-ckeditor.png", "256x256");
        settings.addLink("shortcut icon", "icons/vaadin-ckeditor.ico");
    }
}