package br.com.itwv.cdatasy.base.html.templates.binders;

import br.com.itwv.cdatasy.base.html.elements.HtmlTBody;
import br.com.itwv.cdatasy.base.html.elements.HtmlTHead;
import br.com.itwv.cdatasy.base.html.elements.HtmlTr;
import br.com.itwv.cdatasy.base.html.flows.HtmlTableView;
import br.com.itwv.cdatasy.base.html.flows.ModelBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.Alert;

import java.io.OutputStreamWriter;

public class AlertBinder {

    private static ModelBinder<Alert> binderGetAllergenCode() {
        return new ModelBinder<Alert>() {
            public void bind(OutputStreamWriter out, Alert model)
                    throws Exception {
                out.write(model.getAllergenCode());
            }
        };
    }

    private static ModelBinder<Alert> binderGetAllergenDescription() {
        return new ModelBinder<Alert>() {
            public void bind(OutputStreamWriter out, Alert model)
                    throws Exception {
                out.write(model.getAllergenDescription());
            }
        };
    }

    private static ModelBinder<Alert> binderGetDiagnosisDate() {
        return new ModelBinder<Alert>() {
            public void bind(OutputStreamWriter out, Alert model)
                    throws Exception {
                out.write(model.getDiagnosisDate());
            }
        };
    }

    private static ModelBinder<Alert> binderGetReactions() {
        return new ModelBinder<Alert>() {
            public void bind(OutputStreamWriter out, Alert model)
                    throws Exception {
                out.write(model.getReactions());
            }
        };
    }

    private static ModelBinder<Alert> binderGetStatus() {
        return new ModelBinder<Alert>() {
            public void bind(OutputStreamWriter out, Alert model)
                    throws Exception {
                out.write(model.getStatus());
            }
        };
    }

    private static ModelBinder<Alert> binderGetCategory() {
        return new ModelBinder<Alert>() {
            public void bind(OutputStreamWriter out, Alert model)
                    throws Exception {
                out.write(model.getCategory());
            }
        };
    }

    private static ModelBinder<Alert> binderGetSeverity() {
        return new ModelBinder<Alert>() {
            public void bind(OutputStreamWriter out, Alert model)
                    throws Exception {
                out.write(model.getSeverity());
            }
        };
    }

    private static ModelBinder<Alert> binderGetSource() {
        return new ModelBinder<Alert>() {
            public void bind(OutputStreamWriter out, Alert model)
                    throws Exception {
                out.write(model.getSource());
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static HtmlTableView<Iterable<Alert>> taskListView() {

        HtmlTableView<Iterable<Alert>> taskView = new HtmlTableView<Iterable<Alert>>();

        HtmlTHead<Iterable<Alert>> thead = taskView.thead();
        HtmlTBody<Iterable<Alert>> tbody = taskView.tbody();
        HtmlTr<Iterable<Alert>> trHead = thead.tr();

        trHead.th().text("Código Alergénio CPARA");
        trHead.th().text("Descrição Alergénio");
        trHead.th().text("Data Diagnóstico");
        trHead.th().text("Reacção");
        trHead.th().text("Estado");
        trHead.th().text("Categoria");
        trHead.th().text("Severidade");
        trHead.th().text("Origem");

        tbody.trFromIterable(AlertBinder.binderGetAllergenCode(),
                AlertBinder.binderGetAllergenDescription(),
                AlertBinder.binderGetDiagnosisDate(),
                AlertBinder.binderGetReactions(),
                AlertBinder.binderGetStatus(), AlertBinder.binderGetCategory(),
                AlertBinder.binderGetSeverity(), AlertBinder.binderGetSource());
        return taskView;
    }
}
