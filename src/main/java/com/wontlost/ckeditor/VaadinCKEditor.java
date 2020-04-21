package com.wontlost.ckeditor;

import com.google.gson.Gson;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.dependency.JsModule;
import elemental.json.JsonArray;
import elemental.json.impl.JreJsonFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Used in @VaadinCKEditorBuilder.
 */
@Tag("vaadin-ckeditor")
@JsModule("./vaadin-ckeditor.js")
public class VaadinCKEditor extends CustomField<String> {

    private String editorData="";

    public Toolbar[] toolbar = new Toolbar[]{Toolbar.heading, Toolbar.pipe, Toolbar.bold, Toolbar.italic,
           Toolbar.underline, Toolbar.strikethrough, Toolbar.subscript, Toolbar.superscript, Toolbar.highlight,
           Toolbar.removeFormat, Toolbar.pipe, Toolbar.horizontalLine, Toolbar.pageBreak, Toolbar.link,
           Toolbar.bulletedList, Toolbar.numberedList, Toolbar.alignment, Toolbar.todoList, Toolbar.indent,
           Toolbar.outdent, Toolbar.code, Toolbar.codeBlock, Toolbar.pipe, Toolbar.specialCharacters,
           Toolbar.imageUpload, Toolbar.blockQuote, Toolbar.insertTable, Toolbar.mediaEmbed, Toolbar.undo, Toolbar.redo};

    /**
     * Constructor of VaadinCKEditor.
     * @param editorType  Type of Editor, refer to enum @EditorType.
     * @param toolbar   Toolbar of Editor, refer to enum @Toolbar.
     * @param editorData Content of editor.
     * @param width   Width of editor, default value is 'auto'.
     * @param height  Height of editor, default value is 'auto'.
     * @param isReadOnly Make editor readonly
     */
    VaadinCKEditor(EditorType editorType, Toolbar[] toolbar, Theme theme, String editorData, String width, String height, Boolean isReadOnly) {
        getElement().setProperty("editorType", editorType.toString());
        getElement().setPropertyJson("toolBar", toJson(toolbar));
        getElement().setProperty("editorData", editorData==null?"":editorData);
        getElement().setProperty("themeStyles", theme.getStyles());
        getElement().setProperty("editorWidth", width==null?"auto":width);
        getElement().setProperty("editorHeight", height==null?"auto":height);
        getElement().setProperty("isReadOnly", isReadOnly==null?false:isReadOnly);
        getElement().getStyle().set("width", "100%");
        getElement().getStyle().set("width", "-webkit-fill-available");//Chrome
        getElement().getStyle().set("width", "-moz-available");//Firefox
        getElement().getStyle().set("margin", "20px");
        getElement().addPropertyChangeListener("change:data",
                event -> System.out.println("change:data: " + getValue()));
    }

    /**
     * @param toolbar Toolbar of Editor, refer to enum @Toolbar
     * @return JsonArray
     */
    private JsonArray toJson(Toolbar[] toolbar) {
        List<String> values = new ArrayList<>();
        if(toolbar == null || toolbar.length==0) {
            toolbar = this.toolbar;
        }
        Arrays.stream(toolbar).forEach(item -> values.add(item.getValue()));
        String toolbarJson = new Gson().toJson(values);
        return new JreJsonFactory().parse(toolbarJson);
    }

    protected String generateModelValue() {
        return editorData;
    }

    protected void setPresentationValue(String newPresentationValue) {
        this.editorData = newPresentationValue;
    }

    /**
     * Get content of editor.
     * @return Data in editor text area.
     */
    public String getValue() {
        return editorData;
    }

    /**
     * Set content of editor.
     * @param value  Data in editor text area.
     */

    public void setValue(String value) {
        this.editorData = value;
    }

    @ClientCallable
    private void setEditorData(String editorData) {
        this.editorData = editorData;
        setValue(editorData);
    }

}
