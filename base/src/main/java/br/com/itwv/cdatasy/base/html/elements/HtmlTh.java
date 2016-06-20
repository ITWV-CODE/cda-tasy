package br.com.itwv.cdatasy.base.html.elements;

public class HtmlTh<T> extends HtmlTextElement<T>{
	
	public HtmlA<T> a(String href){return addChild(new HtmlA<T>(href));}

	public HtmlTh() {
		super("th");
	}
}
