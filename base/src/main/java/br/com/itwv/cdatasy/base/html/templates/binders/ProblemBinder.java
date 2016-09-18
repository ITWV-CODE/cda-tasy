package br.com.itwv.cdatasy.base.html.templates.binders;

import br.com.itwv.cdatasy.base.html.elements.HtmlTBody;
import br.com.itwv.cdatasy.base.html.elements.HtmlTHead;
import br.com.itwv.cdatasy.base.html.elements.HtmlTr;
import br.com.itwv.cdatasy.base.html.flows.HtmlTableView;
import br.com.itwv.cdatasy.base.html.flows.ModelBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.Problem;

import java.io.OutputStreamWriter;

public class ProblemBinder {

    private static ModelBinder<Problem> binderGetClassificationCode() {
        return new ModelBinder<Problem>() {
            public void bind(OutputStreamWriter out, Problem model)
                    throws Exception {
                out.write(model.getClassificationCode());
            }
        };
    }

    private static ModelBinder<Problem> binderGetCode() {
        return new ModelBinder<Problem>() {
            public void bind(OutputStreamWriter out, Problem model)
                    throws Exception {
                out.write(model.getCode());
            }
        };
    }

    private static ModelBinder<Problem> binderGetDescription() {
        return new ModelBinder<Problem>() {
            public void bind(OutputStreamWriter out, Problem model)
                    throws Exception {
                out.write(model.getDescription());
            }
        };
    }

    private static ModelBinder<Problem> binderGetInitialDate() {
        return new ModelBinder<Problem>() {
            public void bind(OutputStreamWriter out, Problem model)
                    throws Exception {
                out.write(model.getInitialDate());
            }
        };
    }

    private static ModelBinder<Problem> binderGetFinalDate() {
        return new ModelBinder<Problem>() {
            public void bind(OutputStreamWriter out, Problem model)
                    throws Exception {
                out.write(model.getFinalDate());
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static HtmlTableView<Iterable<Problem>> taskListView() {

        HtmlTableView<Iterable<Problem>> taskView = new HtmlTableView<Iterable<Problem>>();

        HtmlTHead<Iterable<Problem>> thead = taskView.thead();
        HtmlTBody<Iterable<Problem>> tbody = taskView.tbody();
        HtmlTr<Iterable<Problem>> trHead = thead.tr();

        trHead.th().text("Classificação");
        trHead.th().text("Código");
        trHead.th().text("Descrição");
        trHead.th().text("Data Início");
        trHead.th().text("Data Fim");

        tbody.trFromIterable(ProblemBinder.binderGetClassificationCode(),
                ProblemBinder.binderGetCode(),
                ProblemBinder.binderGetDescription(),
                ProblemBinder.binderGetInitialDate(),
                ProblemBinder.binderGetFinalDate());
        return taskView;
    }
}
