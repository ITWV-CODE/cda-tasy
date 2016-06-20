package br.com.itwv.cdatasy.base.html.elements;

import br.com.itwv.cdatasy.base.html.flows.HtmlWriterComposite;

import java.io.OutputStreamWriter;

public class HtmlScriptBlock<T> extends HtmlWriterComposite<T> {

	public HtmlScriptBlock<T> code(String msg) {
		addChild(new TextNode<T>(msg));
		return this;
	}

	@Override
	public void doWriteBefore(OutputStreamWriter out, int depth)
			throws Exception {
		out.write("<script>");
		tabs(++depth);
	}

	@Override
	public void doWriteAfter(OutputStreamWriter out, int depth)
			throws Exception {
		out.write("\n");
		tabs(depth);
		out.write("</script>");
		tabs(depth);
	}
}
