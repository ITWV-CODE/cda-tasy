package br.com.itwv.cdatasy.base.html.templates.binders;

import br.com.itwv.cdatasy.base.html.elements.HtmlTBody;
import br.com.itwv.cdatasy.base.html.elements.HtmlTHead;
import br.com.itwv.cdatasy.base.html.elements.HtmlTr;
import br.com.itwv.cdatasy.base.html.flows.HtmlTableView;
import br.com.itwv.cdatasy.base.html.flows.ModelBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.PlanOfCare;

import java.io.OutputStreamWriter;

public class PlanOfCareBinder {

    private static ModelBinder<PlanOfCare> binderGetDescription() {
        return new ModelBinder<PlanOfCare>() {
            public void bind(OutputStreamWriter out, PlanOfCare model)
                    throws Exception {
                out.write(model.getDescription());
            }
        };
    }

    private static ModelBinder<PlanOfCare> binderGetDate() {
        return new ModelBinder<PlanOfCare>() {
            public void bind(OutputStreamWriter out, PlanOfCare model)
                    throws Exception {
                out.write(model.getDate());
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static HtmlTableView<Iterable<PlanOfCare>> taskListView() {
        HtmlTableView<Iterable<PlanOfCare>> taskView = new HtmlTableView<Iterable<PlanOfCare>>();

        HtmlTHead<Iterable<PlanOfCare>> thead = taskView.thead();
        HtmlTBody<Iterable<PlanOfCare>> tbody = taskView.tbody();
        HtmlTr<Iterable<PlanOfCare>> trHead = thead.tr();

        trHead.th().text("Descrição");
        trHead.th().text("Data");

        tbody.trFromIterable(
                PlanOfCareBinder.binderGetDescription(),
                PlanOfCareBinder.binderGetDate());
        return taskView;
    }
}
