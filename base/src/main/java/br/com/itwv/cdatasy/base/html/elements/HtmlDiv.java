package br.com.itwv.cdatasy.base.html.elements;

import br.com.itwv.cdatasy.base.html.flows.HtmlWriterComposite;
import br.com.itwv.cdatasy.base.html.flows.ModelBinder;

import java.io.OutputStreamWriter;

public class HtmlDiv<T> extends HtmlWriterComposite<T> {

    public HtmlTable<T> table() {
        return addChild(new HtmlTable<T>());
    }

    public HtmlDiv<T> text(String msg) {
        addChild(new TextNode<T>(msg));
        return this;
    }

    public HtmlDiv<T> text(ModelBinder<T> binder) {
        addChild(new TextNode<T>(binder));
        return this;
    }

    public HtmlDiv<T> br() {
        addChild(new HtmlBr());
        return this;
    }

    public HtmlDiv<T> hr() {
        addChild(new HtmlHr());
        return this;
    }

    public HtmlDiv<T> div() {
        return addChild(new HtmlDiv<T>());
    }

    public HtmlForm<T> form(String action) {
        return addChild(new HtmlForm<T>(action));
    }

    @Override
    public void doWriteBefore(OutputStreamWriter out, int depth)
            throws Exception {
        out.write("<div>");
        tabs(depth + 1);
    }

    @Override
    public void doWriteAfter(OutputStreamWriter out, int depth)
            throws Exception {
        out.write("\n");
        tabs(depth);
        out.write("</div>");
    }
}
