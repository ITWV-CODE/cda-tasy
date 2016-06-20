package br.com.itwv.cdatasy.base.html.elements;

import br.com.itwv.cdatasy.base.html.flows.HtmlWriter;

import java.io.OutputStreamWriter;

public class HtmlLinkCss implements HtmlWriter<Object> {
    OutputStreamWriter out;
    final String href;

    public HtmlLinkCss(String href) {
        this.href = href;
    }

    public void write(int depth, Object model) throws Exception {
        out.write("<link rel=\"Stylesheet\" type=\"text/css\" href=\"" + href
                + "\"/>");
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
