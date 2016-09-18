package br.com.itwv.cdatasy.base.html.templates.binders;

import br.com.itwv.cdatasy.base.html.elements.HtmlTBody;
import br.com.itwv.cdatasy.base.html.elements.HtmlTHead;
import br.com.itwv.cdatasy.base.html.elements.HtmlTr;
import br.com.itwv.cdatasy.base.html.flows.HtmlTableView;
import br.com.itwv.cdatasy.base.html.flows.ModelBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.Medication;

import java.io.OutputStreamWriter;

public class MedicationBinder {

    private static ModelBinder<Medication> binderGetInitialDate() {
        return new ModelBinder<Medication>() {
            public void bind(OutputStreamWriter out, Medication model)
                    throws Exception {
                out.write(model.getInitialDate());
            }
        };
    }

    private static ModelBinder<Medication> binderGetFinalDate() {
        return new ModelBinder<Medication>() {
            public void bind(OutputStreamWriter out, Medication model)
                    throws Exception {
                out.write(model.getFinalDate());
            }
        };
    }

    private static ModelBinder<Medication> binderGetCodeATC() {
        return new ModelBinder<Medication>() {
            public void bind(OutputStreamWriter out, Medication model)
                    throws Exception {
                out.write(model.getCodeATC());
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

    private static ModelBinder<Medication> binderGetActiveSubstance() {
        return new ModelBinder<Medication>() {
            public void bind(OutputStreamWriter out, Medication model)
                    throws Exception {
                out.write(model.getActiveSubstance());
            }
        };
    }

    private static ModelBinder<Medication> binderGetPharmaceuticalForm() {
        return new ModelBinder<Medication>() {
            public void bind(OutputStreamWriter out, Medication model)
                    throws Exception {
                out.write(model.getPharmaceuticalForm());
            }
        };
    }

    private static ModelBinder<Medication> binderGetApplicationRate() {
        return new ModelBinder<Medication>() {
            public void bind(OutputStreamWriter out, Medication model)
                    throws Exception {
                out.write(model.getApplicationRate());
            }
        };
    }

    private static ModelBinder<Medication> binderGetDescription() {
        return new ModelBinder<Medication>() {
            public void bind(OutputStreamWriter out, Medication model)
                    throws Exception {
                out.write(model.getDescription());
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static HtmlTableView<Iterable<Medication>> taskListView() {

        HtmlTableView<Iterable<Medication>> taskView = new HtmlTableView<Iterable<Medication>>();

        HtmlTHead<Iterable<Medication>> thead = taskView.thead();
        HtmlTBody<Iterable<Medication>> tbody = taskView.tbody();
        HtmlTr<Iterable<Medication>> trHead = thead.tr();

        trHead.th().text("Substância Activa");
        trHead.th().text("Código ATC");
        trHead.th().text("Forma Farmacêutica");
        trHead.th().text("Dosagem");
        trHead.th().text("Data Início");
        trHead.th().text("Data Fim");
        trHead.th().text("Posologia");
        trHead.th().text("Descrição");

        tbody.trFromIterable(MedicationBinder.binderGetActiveSubstance(),
                MedicationBinder.binderGetCodeATC(),
                MedicationBinder.binderGetPharmaceuticalForm(),
                MedicationBinder.binderGetDosage(),
                MedicationBinder.binderGetInitialDate(),
                MedicationBinder.binderGetFinalDate(),
                MedicationBinder.binderGetApplicationRate(),
                MedicationBinder.binderGetDescription());
        return taskView;
    }
}
