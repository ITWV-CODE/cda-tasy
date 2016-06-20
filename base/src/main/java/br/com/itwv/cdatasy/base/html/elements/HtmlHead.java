package br.com.itwv.cdatasy.base.html.elements;

import br.com.itwv.cdatasy.base.html.flows.HtmlWriterComposite;

import java.io.OutputStreamWriter;

public class HtmlHead<T> extends HtmlWriterComposite<T> {

	public HtmlHead<T> title(String msg) {
		addChild(new HtmlTitle<T>()).text(msg);
		return this;
	}

	public HtmlHead<T> scriptLink(String src) {
		addChild(new HtmlScriptLink(src));
		return this;
	}

	public HtmlScriptBlock<T> scriptBlock() {
		return addChild(new HtmlScriptBlock<T>());
	}

	public HtmlHead<T> linkCss(String href) {
		addChild(new HtmlLinkCss(href));
		return this;
	}

	@Override
	public void doWriteBefore(OutputStreamWriter out, int depth)
			throws Exception {
		tabs(depth);
		// out.write("<head>"+
		// "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
		out.write("<head>");
		tabs(depth + 1);
	}

	@Override
	public void doWriteAfter(OutputStreamWriter out, int depth)
			throws Exception {
		out.write("\n");
		tabs(depth);
		out.write("</head>");
	}
}
