package br.com.itwv.cdatasy.base.html.templates.binders;

import br.com.itwv.cdatasy.base.html.elements.HtmlTable;
import br.com.itwv.cdatasy.base.html.elements.HtmlTr;
import br.com.itwv.cdatasy.base.html.flows.HtmlView;
import br.com.itwv.cdatasy.base.html.flows.ModelBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.Procedure;

import java.io.OutputStreamWriter;

public class ProcedureBinder {

    private static ModelBinder<Procedure> binderGetClassificationCode() {
        return new ModelBinder<Procedure>() {
            public void bind(OutputStreamWriter out, Procedure model)
                    throws Exception {
                out.write(model.getClassificationCode());
            }
        };
    }

    private static ModelBinder<Procedure> binderGetCode() {
        return new ModelBinder<Procedure>() {
            public void bind(OutputStreamWriter out, Procedure model)
                    throws Exception {
                out.write(model.getCode());
            }
        };
    }

    private static ModelBinder<Procedure> binderGetDescription() {
        return new ModelBinder<Procedure>() {
            public void bind(OutputStreamWriter out, Procedure model)
                    throws Exception {
                out.write(model.getDescription());
            }
        };
    }

    private static ModelBinder<Procedure> binderGetInitialDate() {
        return new ModelBinder<Procedure>() {
            public void bind(OutputStreamWriter out, Procedure model)
                    throws Exception {
                out.write(model.getInitialDate());
            }
        };
    }

    private static ModelBinder<Procedure> binderGetFinalDate() {
        return new ModelBinder<Procedure>() {
            public void bind(OutputStreamWriter out, Procedure model)
                    throws Exception {
                out.write(model.getFinalDate());
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static HtmlView<Iterable<Procedure>> taskListView() {
        HtmlView<Iterable<Procedure>> taskView = new HtmlView<Iterable<Procedure>>();
        taskView.head();

        HtmlTable<Iterable<Procedure>> t = taskView.body().table();
        HtmlTr<Iterable<Procedure>> headerRow = t.tr();
        headerRow.th().text("Classificação");
        headerRow.th().text("Código");
        headerRow.th().text("Descrição");
        headerRow.th().text("Data Início");
        headerRow.th().text("Data Fim");

        t.trFromIterable(ProcedureBinder.binderGetClassificationCode(),
                ProcedureBinder.binderGetCode(),
                ProcedureBinder.binderGetDescription(),
                ProcedureBinder.binderGetInitialDate(),
                ProcedureBinder.binderGetFinalDate());
        return taskView;
    }
}
