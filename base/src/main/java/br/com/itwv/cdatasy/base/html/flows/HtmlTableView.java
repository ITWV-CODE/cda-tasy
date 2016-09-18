package br.com.itwv.cdatasy.base.html.flows;


import br.com.itwv.cdatasy.base.html.elements.HtmlTBody;
import br.com.itwv.cdatasy.base.html.elements.HtmlTHead;

import java.io.OutputStreamWriter;

public class HtmlTableView<T> extends HtmlWriterComposite<T> {

    public HtmlTHead<T> thead() {
        return addChild(new HtmlTHead<T>());
    }

    public HtmlTBody<T> tbody() {
        return addChild(new HtmlTBody<T>());
    }

    @Override
    public void doWriteBefore(OutputStreamWriter out, int depth)
            throws Exception {
        // out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        // out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" >");
        out.write("<table>");
    }

    @Override
    public void doWriteAfter(OutputStreamWriter out, int depth)
            throws Exception {
        out.write("</table>");
    }
}
