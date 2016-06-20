package br.com.itwv.cdatasy.base.html.templates.binders;

import br.com.itwv.cdatasy.base.html.elements.HtmlTable;
import br.com.itwv.cdatasy.base.html.elements.HtmlTr;
import br.com.itwv.cdatasy.base.html.flows.HtmlView;
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
    public static HtmlView<Iterable<Problem>> taskListView() {
        HtmlView<Iterable<Problem>> taskView = new HtmlView<Iterable<Problem>>();
        taskView.head();

        HtmlTable<Iterable<Problem>> t = taskView.body().table();
        HtmlTr<Iterable<Problem>> headerRow = t.tr();
        headerRow.th().text("Classificação");
        headerRow.th().text("Código");
        headerRow.th().text("Descrição");
        headerRow.th().text("Data Início");
        headerRow.th().text("Data Fim");

        t.trFromIterable(ProblemBinder.binderGetClassificationCode(),
                ProblemBinder.binderGetCode(),
                ProblemBinder.binderGetDescription(),
                ProblemBinder.binderGetInitialDate(),
                ProblemBinder.binderGetFinalDate());
        return taskView;
    }
}
