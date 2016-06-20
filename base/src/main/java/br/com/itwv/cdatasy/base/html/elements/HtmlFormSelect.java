package br.com.itwv.cdatasy.base.html.elements;

import br.com.itwv.cdatasy.base.html.flows.HtmlWriter;

import java.io.OutputStreamWriter;

public class HtmlFormSelect implements HtmlWriter<Object> {
    OutputStreamWriter out;
    final String name;
    final String[] options;

    public HtmlFormSelect(String name, String... options) {
        this.name = name;
        this.options = options;
    }

    public void write(int depth, Object model) throws Exception {
        out.write("<select name=\"" + name + "\">");
        tabs(++depth);
        for (String op : options) {
            out.write("<option>" + op + "</option>");
            tabs(depth);
        }
        tabs(--depth);
        out.write("</select>");
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
