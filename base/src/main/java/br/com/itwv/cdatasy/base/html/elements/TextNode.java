package br.com.itwv.cdatasy.base.html.elements;

import br.com.itwv.cdatasy.base.html.flows.HtmlWriter;
import br.com.itwv.cdatasy.base.html.flows.ModelBinder;

import java.io.OutputStreamWriter;

public class TextNode<T> implements HtmlWriter<T> {

    OutputStreamWriter out;
    private final String msg;
    private final ModelBinder<T> binder;

    public TextNode(String msg) {
        this.msg = msg;
        this.binder = null;
    }

    public TextNode(ModelBinder<T> binder) {
        this.msg = null;
        this.binder = binder;
    }

    public void write(int depth, T model) throws Exception {
        if (binder == null) {
            out.write(msg);
        } else {
            assert (binder != null);
            binder.bind(out, model);
        }
    }

    public HtmlWriter<T> setOutputStreamWriter(OutputStreamWriter out) {
        this.out = out;
        return this;
    }
}
