package br.com.itwv.cdatasy.base.html.elements;

import br.com.itwv.cdatasy.base.html.flows.HtmlWriterComposite;
import br.com.itwv.cdatasy.base.html.flows.ModelBinder;

import java.io.OutputStreamWriter;

public class HtmlTable<T> extends HtmlWriterComposite<T> {

    public HtmlTr<T> tr() {
        return addChild(new HtmlTr<T>());
    }

    public <S, I extends Iterable<S>> HtmlTable<T> trFromIterable(
            ModelBinder<S>... binders) {
        addChild(new HtmlTrFromIterable<S, I>(binders));
        return this;
    }

    @Override
    public void doWriteBefore(OutputStreamWriter out, int depth)
            throws Exception {
        out.write("<table>");
        tabs(++depth);
    }

    @Override
    public void doWriteAfter(OutputStreamWriter out, int depth)
            throws Exception {
        out.write("\n");
        tabs(depth);
        out.write("</table>");
        tabs(depth);
    }
}
