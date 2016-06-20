package br.com.itwv.cdatasy.base.html.elements;


import br.com.itwv.cdatasy.base.html.flows.HtmlWriterComposite;

import java.io.OutputStreamWriter;

public class HtmlTr<T> extends HtmlWriterComposite<T> {

	public HtmlTd<T> td() {
		return addChild(new HtmlTd<T>());
	}

	public HtmlTh<T> th() {
		return addChild(new HtmlTh<T>());
	}

	@Override
	public void doWriteBefore(OutputStreamWriter out, int depth)
			throws Exception {
		out.write("<tr>");
		tabs(++depth);
	}

	@Override
	public void doWriteAfter(OutputStreamWriter out, int depth)
			throws Exception {
		out.write("\n");
		tabs(depth);
		out.write("</tr>");
		tabs(depth);
	}
}
