package br.com.itwv.cdatasy.base.html.templates.binders;

import br.com.itwv.cdatasy.base.html.elements.HtmlTBody;
import br.com.itwv.cdatasy.base.html.elements.HtmlTHead;
import br.com.itwv.cdatasy.base.html.elements.HtmlTr;
import br.com.itwv.cdatasy.base.html.flows.HtmlTableView;
import br.com.itwv.cdatasy.base.html.flows.ModelBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.Problem;

import java.io.OutputStreamWriter;

public class ProblemBinder {

    private static ModelBinder<Problem> binderGetProblem() {
        return new ModelBinder<Problem>() {
            public void bind(OutputStreamWriter out, Problem model)
                    throws Exception {
                out.write(model.getProblem());
            }
        };
    }

    private static ModelBinder<Problem> binderGetStatus() {
        return new ModelBinder<Problem>() {
            public void bind(OutputStreamWriter out, Problem model)
                    throws Exception {
                out.write(model.getStatus());
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static HtmlTableView<Iterable<Problem>> taskListView() {

        HtmlTableView<Iterable<Problem>> taskView = new HtmlTableView<Iterable<Problem>>();

        HtmlTHead<Iterable<Problem>> thead = taskView.thead();
        HtmlTBody<Iterable<Problem>> tbody = taskView.tbody();
        HtmlTr<Iterable<Problem>> trHead = thead.tr();

        trHead.th().text("Problema");
        trHead.th().text("Estado");

        tbody.trFromIterable(
                ProblemBinder.binderGetProblem(),
                ProblemBinder.binderGetStatus());
        return taskView;
    }
}
