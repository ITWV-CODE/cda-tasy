package br.com.itwv.cdatasy.base.html.elements;


import br.com.itwv.cdatasy.base.html.flows.HtmlWriterComposite;

import java.io.OutputStreamWriter;

public class HtmlA<T> extends HtmlWriterComposite<T> {
    private final String href;

    public HtmlA<T> text(String msg) {
        addChild(new TextNode<T>(msg));
        return this;
    }

    public HtmlA(String href) {
        this.href = href;
    }

    @Override
    public void doWriteBefore(OutputStreamWriter out, int depth)
            throws Exception {
        out.write("<a href=\"" + href + "\">");
    }

    @Override
    public void doWriteAfter(OutputStreamWriter out, int depth)
            throws Exception {
        out.write("</a>");
    }
}
