package br.com.itwv.cdatasy.base.html.elements;

import br.com.itwv.cdatasy.base.html.flows.HtmlWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class HRef<T> implements HtmlWriter<T> {
    /* ========================================================================= */
	/*-------------------------     FIELDS    ---------------------------------*/
	/* ========================================================================= */

    private final URL url;
    private OutputStreamWriter out;

	/* ========================================================================= */
	/*-------------------------  CONSTRUCTOR  ---------------------------------*/
	/* ========================================================================= */

    public HRef(String href) {
        try {
            url = new URL(href);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

	/* ========================================================================= */
	/*--------------------- HtmlPrinter interface -----------------------------*/
	/* ========================================================================= */

    public void write(int depth, T model) throws IOException {
        out.write(url.toExternalForm());
    }

    public HtmlWriter<T> setOutputStreamWriter(OutputStreamWriter out) {
        this.out = out;
        return this;
    }
}
