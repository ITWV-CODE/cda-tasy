package br.com.itwv.cdatasy.base.html.elements;

import br.com.itwv.cdatasy.base.html.flows.HtmlWriter;

import java.io.OutputStreamWriter;

public class HtmlFormInputSubmit implements HtmlWriter<Object> {

    OutputStreamWriter out;
    final String value;

    public HtmlFormInputSubmit(String value) {
        this.value = value;
    }

    public void write(int depth, Object model) throws Exception {
        out.write("<input type=\"submit\" value=\"" + value + "\"/>");
    }

    public HtmlWriter<Object> setOutputStreamWriter(OutputStreamWriter out) {
        this.out = out;
        return this;
    }

}
