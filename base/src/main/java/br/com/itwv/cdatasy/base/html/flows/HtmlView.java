package br.com.itwv.cdatasy.base.html.flows;


import br.com.itwv.cdatasy.base.html.elements.HtmlBody;
import br.com.itwv.cdatasy.base.html.elements.HtmlHead;

import java.io.OutputStreamWriter;

public class HtmlView<T> extends HtmlWriterComposite<T> {

    public HtmlHead<T> head() {
        return addChild(new HtmlHead<T>());
    }

    public HtmlBody<T> body() {
        return addChild(new HtmlBody<T>());
    }

    @Override
    public void doWriteBefore(OutputStreamWriter out, int depth)
            throws Exception {
        // out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        // out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" >");
        out.write("<html>");
    }

    @Override
    public void doWriteAfter(OutputStreamWriter out, int depth)
            throws Exception {
        out.write("</html>");
    }
}
