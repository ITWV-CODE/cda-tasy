package br.com.itwv.cdatasy.base.html.elements;


import br.com.itwv.cdatasy.base.html.flows.HtmlWriter;

import java.io.OutputStreamWriter;

public class HtmlScriptLink implements HtmlWriter<Object> {

	OutputStreamWriter out;
	final String src;

	public HtmlScriptLink(String src) {
		this.src = src;
	}

	public void write(int depth, Object model) throws Exception {
		out.write("<script type=\"text/javascript\" src=\"" + src
				+ "\"></script>");
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
