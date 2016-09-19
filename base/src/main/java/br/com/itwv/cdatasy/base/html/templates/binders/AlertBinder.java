package br.com.itwv.cdatasy.base.html.templates.binders;

import br.com.itwv.cdatasy.base.html.elements.HtmlTBody;
import br.com.itwv.cdatasy.base.html.elements.HtmlTHead;
import br.com.itwv.cdatasy.base.html.elements.HtmlTr;
import br.com.itwv.cdatasy.base.html.flows.HtmlTableView;
import br.com.itwv.cdatasy.base.html.flows.ModelBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.Alert;

import java.io.OutputStreamWriter;

public class AlertBinder {

    private static ModelBinder<Alert> binderGetSubstance() {
        return new ModelBinder<Alert>() {
            public void bind(OutputStreamWriter out, Alert model)
                    throws Exception {
                out.write(model.getSubstance());
            }
        };
    }

    private static ModelBinder<Alert> binderGetReaction() {
        return new ModelBinder<Alert>() {
            public void bind(OutputStreamWriter out, Alert model)
                    throws Exception {
                out.write(model.getReaction());
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

    @SuppressWarnings("unchecked")
    public static HtmlTableView<Iterable<Alert>> taskListView() {

        HtmlTableView<Iterable<Alert>> taskView = new HtmlTableView<Iterable<Alert>>();

        HtmlTHead<Iterable<Alert>> thead = taskView.thead();
        HtmlTBody<Iterable<Alert>> tbody = taskView.tbody();
        HtmlTr<Iterable<Alert>> trHead = thead.tr();

        trHead.th().text("Substância");
        trHead.th().text("Reacção");
        trHead.th().text("Estado");

        tbody.trFromIterable(AlertBinder.binderGetSubstance(),
                AlertBinder.binderGetReaction(),
                AlertBinder.binderGetStatus());
        return taskView;
    }
}
