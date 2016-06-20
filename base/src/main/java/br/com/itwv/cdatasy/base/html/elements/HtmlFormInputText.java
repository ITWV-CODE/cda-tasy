package br.com.itwv.cdatasy.base.html.elements;

import br.com.itwv.cdatasy.base.html.flows.HtmlWriter;

import java.io.OutputStreamWriter;

public class HtmlFormInputText implements HtmlWriter<Object> {

    OutputStreamWriter out;
    final String name;
    final String id;

    public HtmlFormInputText(String name) {
        this.name = name;
        this.id = null;
    }

    public HtmlFormInputText(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public void write(int depth, Object model) throws Exception {
        out.write("<input type=\"text\" name=\"" + name + "\"");
        if (id != null)
            out.write(" id = \"" + id + "\"");
        out.write("/>");
    }

    public HtmlWriter<Object> setOutputStreamWriter(OutputStreamWriter out) {
        this.out = out;
        return this;
    }

}
