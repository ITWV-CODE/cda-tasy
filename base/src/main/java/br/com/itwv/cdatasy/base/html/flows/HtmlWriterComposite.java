package br.com.itwv.cdatasy.base.html.flows;

import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

public abstract class HtmlWriterComposite<T> implements HtmlWriter<T> {

	/* ========================================================================= */
	/*------------------------- STATIC FIELDS ---------------------------------*/
	/* ========================================================================= */
	public static final String NEWLINE = System.getProperty("line.separator");

	/* ========================================================================= */
	/*-------------------------     FIELDS    ---------------------------------*/
	/* ========================================================================= */

	private final List<HtmlWriter<T>> children;
	protected OutputStreamWriter out;

	/* ========================================================================= */
	/*-------------------------  CONSTRUCTOR  ---------------------------------*/
	/* ========================================================================= */
	public HtmlWriterComposite() {
		children = new LinkedList<HtmlWriter<T>>();
	}

	/* ========================================================================= */
	/*--------------------- HtmlPrinter interface -----------------------------*/
	/* ========================================================================= */
	public HtmlWriter<T> setOutputStreamWriter(OutputStreamWriter out) {
		this.out = out;
		for (HtmlWriter<?> elem : children) {
			elem.setOutputStreamWriter(out);
		}
		return this;
	}

	public final void write(int depth, T model) throws Exception {
		doWriteBefore(out, depth++);
		for (HtmlWriter<T> elem : children) {
			elem.write(depth, model);
		}
		doWriteAfter(out, --depth);
	}

	/* ========================================================================= */
	/*----------------------- Instance Methods --------------------------------*/
	/* ========================================================================= */

	@SuppressWarnings("unchecked")
	public <S extends HtmlWriter<?>> S addChild(S child) {
		children.add((HtmlWriter<T>) child);
		return child;
	}

	public abstract void doWriteBefore(OutputStreamWriter out, int depth)
			throws Exception;

	public abstract void doWriteAfter(OutputStreamWriter out, int depth)
			throws Exception;

	/* ========================================================================= */
	/*-------------------- auxiliar Methods ----------------------------*/
	/* ========================================================================= */

	public final void tabs(int depth) throws Exception {
		for (int i = 0; i < depth; i++)
			out.write("\t");
	}
}