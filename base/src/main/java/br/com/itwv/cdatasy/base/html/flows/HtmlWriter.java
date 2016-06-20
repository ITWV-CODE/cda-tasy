package br.com.itwv.cdatasy.base.html.flows;

import java.io.OutputStreamWriter;

public interface HtmlWriter<T> {

	void write(int depth, T model) throws Exception;

	HtmlWriter<T> setOutputStreamWriter(OutputStreamWriter out);
}
