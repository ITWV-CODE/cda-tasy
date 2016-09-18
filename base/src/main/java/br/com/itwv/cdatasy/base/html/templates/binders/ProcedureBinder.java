package br.com.itwv.cdatasy.base.html.templates.binders;

import br.com.itwv.cdatasy.base.html.elements.HtmlTBody;
import br.com.itwv.cdatasy.base.html.elements.HtmlTHead;
import br.com.itwv.cdatasy.base.html.elements.HtmlTr;
import br.com.itwv.cdatasy.base.html.flows.HtmlTableView;
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
    public static HtmlTableView<Iterable<Procedure>> taskListView() {

        HtmlTableView<Iterable<Procedure>> taskView = new HtmlTableView<Iterable<Procedure>>();

        HtmlTHead<Iterable<Procedure>> thead = taskView.thead();
        HtmlTBody<Iterable<Procedure>> tbody = taskView.tbody();
        HtmlTr<Iterable<Procedure>> trHead = thead.tr();

        trHead.th().text("Classificação");
        trHead.th().text("Código");
        trHead.th().text("Descrição");
        trHead.th().text("Data Início");
        trHead.th().text("Data Fim");

        tbody.trFromIterable(ProcedureBinder.binderGetClassificationCode(),
                ProcedureBinder.binderGetCode(),
                ProcedureBinder.binderGetDescription(),
                ProcedureBinder.binderGetInitialDate(),
                ProcedureBinder.binderGetFinalDate());
        return taskView;
    }
}
