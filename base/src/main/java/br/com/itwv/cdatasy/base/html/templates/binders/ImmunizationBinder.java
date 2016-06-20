package br.com.itwv.cdatasy.base.html.templates.binders;

import br.com.itwv.cdatasy.base.html.elements.HtmlTable;
import br.com.itwv.cdatasy.base.html.elements.HtmlTr;
import br.com.itwv.cdatasy.base.html.flows.HtmlView;
import br.com.itwv.cdatasy.base.html.flows.ModelBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.Immunization;

import java.io.OutputStreamWriter;

public class ImmunizationBinder {

    private static ModelBinder<Immunization> binderGetInitialDate() {
        return new ModelBinder<Immunization>() {
            public void bind(OutputStreamWriter out, Immunization model) throws Exception {
                out.write(model.getInitialDate());
            }
        };
    }

    private static ModelBinder<Immunization> binderGetDosage() {
        return new ModelBinder<Immunization>() {
            public void bind(OutputStreamWriter out, Immunization model) throws Exception {
                out.write(model.getDosage());
            }
        };
    }

    private static ModelBinder<Immunization> binderGetCode() {
        return new ModelBinder<Immunization>() {
            public void bind(OutputStreamWriter out, Immunization model) throws Exception {
                out.write(model.getCode());
            }
        };
    }

    private static ModelBinder<Immunization> binderGetName() {
        return new ModelBinder<Immunization>() {
            public void bind(OutputStreamWriter out, Immunization model) throws Exception {
                out.write(model.getName());
            }
        };
    }

    private static ModelBinder<Immunization> binderGetComercialCode() {
        return new ModelBinder<Immunization>() {
            public void bind(OutputStreamWriter out, Immunization model) throws Exception {
                out.write(model.getComercialCode());
            }
        };
    }

    private static ModelBinder<Immunization> binderGetLot() {
        return new ModelBinder<Immunization>() {
            public void bind(OutputStreamWriter out, Immunization model) throws Exception {
                out.write(model.getLot());
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static HtmlView<Iterable<Immunization>> taskListView() {
        HtmlView<Iterable<Immunization>> taskView = new HtmlView<Iterable<Immunization>>();
        taskView.head();

        HtmlTable<Iterable<Immunization>> t = taskView.body().table();
        HtmlTr<Iterable<Immunization>> headerRow = t.tr();
        headerRow.th().text("Data Administração");
        headerRow.th().text("Código");
        headerRow.th().text("Nome");
        headerRow.th().text("Dosagem");
        headerRow.th().text("Lote");
        headerRow.th().text("Nome Comercial");

        t.trFromIterable(ImmunizationBinder.binderGetInitialDate(), ImmunizationBinder.binderGetCode(), ImmunizationBinder.binderGetName(),
                ImmunizationBinder.binderGetDosage(), ImmunizationBinder.binderGetLot(), ImmunizationBinder.binderGetComercialCode());
        return taskView;
    }
}
