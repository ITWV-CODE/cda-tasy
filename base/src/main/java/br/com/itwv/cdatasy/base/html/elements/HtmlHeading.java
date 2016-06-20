package br.com.itwv.cdatasy.base.html.elements;

public class HtmlHeading<T> extends HtmlTextElement<T> {
	public HtmlHeading(int level) {
		super("h" + level);
	}
}