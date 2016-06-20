package br.com.itwv.cdatasy.base.html.flows;

import java.io.OutputStreamWriter;

public interface ModelBinder<T> {
	void bind(OutputStreamWriter out, T model) throws Exception;
}
