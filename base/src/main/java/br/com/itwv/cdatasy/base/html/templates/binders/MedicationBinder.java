package br.com.itwv.cdatasy.base.html.templates.binders;

import br.com.itwv.cdatasy.base.html.elements.HtmlTable;
import br.com.itwv.cdatasy.base.html.elements.HtmlTr;
import br.com.itwv.cdatasy.base.html.flows.HtmlView;
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
    public static HtmlView<Iterable<Medication>> taskListView() {
        HtmlView<Iterable<Medication>> taskView = new HtmlView<Iterable<Medication>>();
        taskView.head();

        HtmlTable<Iterable<Medication>> t = taskView.body().table();
        HtmlTr<Iterable<Medication>> headerRow = t.tr();
        headerRow.th().text("Substância Activa");
        headerRow.th().text("Código ATC");
        headerRow.th().text("Forma Farmacêutica");
        headerRow.th().text("Dosagem");
        headerRow.th().text("Data Início");
        headerRow.th().text("Data Fim");
        headerRow.th().text("Posologia");
        headerRow.th().text("Descrição");

        t.trFromIterable(MedicationBinder.binderGetActiveSubstance(),
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
