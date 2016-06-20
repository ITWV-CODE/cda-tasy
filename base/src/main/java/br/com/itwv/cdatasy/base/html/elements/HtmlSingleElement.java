package br.com.itwv.cdatasy.base.html.elements;

import br.com.itwv.cdatasy.base.html.flows.HtmlWriter;

import java.io.OutputStreamWriter;

public abstract class HtmlSingleElement implements HtmlWriter<Object> {
    private OutputStreamWriter out;
    private final String element;

    public HtmlSingleElement(String element) {
        this.element = element;
    }

    public final void write(int depth, Object model) throws Exception {
        out.write("\n");
        tabs(depth);
        out.write("<" + element + "/>");
        tabs(depth);
    }

    public HtmlWriter<Object> setOutputStreamWriter(OutputStreamWriter out) {
        this.out = out;
        return this;
    }

	/* ========================================================================= */
    /*-------------------- auxiliar Methods ----------------------------*/
	/* ========================================================================= */

    public final void tabs(int depth) throws Exception {
        for (int i = 0; i < depth; i++)
            out.write("\t");
    }
}
