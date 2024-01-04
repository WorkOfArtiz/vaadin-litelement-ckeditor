package com.wontlost.ckeditor;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class ExampleView extends VerticalLayout {
    public ExampleView() {
        add(new H1("Editor tester"));

        var builder = new VaadinCKEditorBuilder();


        add(builder.createVaadinCKEditor());
    }
}
