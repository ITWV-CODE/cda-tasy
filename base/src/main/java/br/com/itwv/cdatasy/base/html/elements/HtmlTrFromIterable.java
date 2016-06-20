package br.com.itwv.cdatasy.base.html.elements;

import br.com.itwv.cdatasy.base.html.flows.HtmlWriter;
import br.com.itwv.cdatasy.base.html.flows.ModelBinder;

import java.io.OutputStreamWriter;

public class HtmlTrFromIterable<S, T extends Iterable<S>> implements
        HtmlWriter<T> {

    private final HtmlTr<S> tr;

    public HtmlTrFromIterable(ModelBinder<S>[] binders) {
        tr = new HtmlTr<S>();
        for (ModelBinder<S> b : binders) {
            tr.td().text(b);
        }
    }

    public void write(int depth, T model) throws Exception {
        for (S item : model) {
            tr.write(depth, item);
        }
    }

    public HtmlWriter<T> setOutputStreamWriter(OutputStreamWriter out) {
        tr.setOutputStreamWriter(out);
        return this;
    }
}
