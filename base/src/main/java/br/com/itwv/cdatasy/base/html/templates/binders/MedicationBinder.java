package br.com.itwv.cdatasy.base.html.templates.binders;

import br.com.itwv.cdatasy.base.html.elements.HtmlTBody;
import br.com.itwv.cdatasy.base.html.elements.HtmlTHead;
import br.com.itwv.cdatasy.base.html.elements.HtmlTr;
import br.com.itwv.cdatasy.base.html.flows.HtmlTableView;
import br.com.itwv.cdatasy.base.html.flows.ModelBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.Medication;

import java.io.OutputStreamWriter;

public class MedicationBinder {

    private static ModelBinder<Medication> binderGetDate() {
        return new ModelBinder<Medication>() {
            public void bind(OutputStreamWriter out, Medication model)
                    throws Exception {
                out.write(model.getDate());
            }
        };
    }

    private static ModelBinder<Medication> binderGetDosage() {
        return new ModelBinder<Medication>() {
            public void bind(OutputStreamWriter out, Medication model)
                    throws Exception {
                out.write(model.getDosage());
            }
        };
    }

    private static ModelBinder<Medication> binderGetSubstance() {
        return new ModelBinder<Medication>() {
            public void bind(OutputStreamWriter out, Medication model)
                    throws Exception {
                out.write(model.getSubstance());
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static HtmlTableView<Iterable<Medication>> taskListView() {

        HtmlTableView<Iterable<Medication>> taskView = new HtmlTableView<Iterable<Medication>>();

        HtmlTHead<Iterable<Medication>> thead = taskView.thead();
        HtmlTBody<Iterable<Medication>> tbody = taskView.tbody();
        HtmlTr<Iterable<Medication>> trHead = thead.tr();

        trHead.th().text("Substância");
        trHead.th().text("Dosagem");
        trHead.th().text("Data");

        tbody.trFromIterable(MedicationBinder.binderGetSubstance(),
                MedicationBinder.binderGetDosage(),
                MedicationBinder.binderGetDate());
        return taskView;
    }
}
