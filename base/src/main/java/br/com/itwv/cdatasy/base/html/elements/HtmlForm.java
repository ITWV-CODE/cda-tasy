package br.com.itwv.cdatasy.base.html.elements;

import br.com.itwv.cdatasy.base.html.flows.HtmlWriterComposite;

import java.io.OutputStreamWriter;

public class HtmlForm<T> extends HtmlWriterComposite<T> {
    public HtmlForm<T> text(String msg) {
        addChild(new TextNode<T>(msg));
        return this;
    }

    public HtmlForm<T> br() {
        addChild(new HtmlBr());
        return this;
    }

    public HtmlForm<T> select(String name, String... options) {
        addChild(new HtmlFormSelect(name, options));
        return this;
    }

    public HtmlForm<T> inputText(String name) {
        addChild(new HtmlFormInputText(name));
        return this;
    }

    public HtmlForm<T> inputText(String name, String id) {
        addChild(new HtmlFormInputText(name, id));
        return this;
    }

    public HtmlForm<T> inputSubmit(String value) {
        addChild(new HtmlFormInputSubmit(value));
        return this;
    }

    final private String action;

    public HtmlForm(String action) {
        this.action = action;
    }

    @Override
    public void doWriteBefore(OutputStreamWriter out, int depth)
            throws Exception {
        out.write(String.format(
                "<form action=\"%s\" method=\"%s\" enctype=\"%s\">", action,
                "post", "application/x-www-form-urlencoded"));
        tabs(++depth);
    }

    @Override
    public void doWriteAfter(OutputStreamWriter out, int depth)
            throws Exception {
        out.write("\n");
        tabs(depth);
        out.write("</form>");
        tabs(depth);
    }
}
