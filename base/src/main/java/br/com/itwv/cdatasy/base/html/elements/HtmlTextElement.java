package br.com.itwv.cdatasy.base.html.elements;

import br.com.itwv.cdatasy.base.html.flows.HtmlWriterComposite;
import br.com.itwv.cdatasy.base.html.flows.ModelBinder;

import java.io.OutputStreamWriter;

public abstract class HtmlTextElement<T> extends HtmlWriterComposite<T> {

    public final void text(String msg) {
        addChild(new TextNode<T>(msg));
    }

    public final void text(ModelBinder<T> binder) {
        addChild(new TextNode<T>(binder));
    }

    private final String element;

    public HtmlTextElement(String element) {
        this.element = element;
    }

    @Override
    public final void doWriteBefore(OutputStreamWriter out, int depth)
            throws Exception {
        out.write("<" + element + ">");
    }

    @Override
    public final void doWriteAfter(OutputStreamWriter out, int depth)
            throws Exception {
        out.write("</" + element + ">");
    }
}
