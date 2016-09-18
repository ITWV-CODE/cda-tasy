package br.com.itwv.cdatasy.base.html.templates.binders;

import br.com.itwv.cdatasy.base.html.elements.HtmlTBody;
import br.com.itwv.cdatasy.base.html.elements.HtmlTHead;
import br.com.itwv.cdatasy.base.html.elements.HtmlTr;
import br.com.itwv.cdatasy.base.html.flows.HtmlTableView;
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
    public static HtmlTableView<Iterable<Immunization>> taskListView() {
        HtmlTableView<Iterable<Immunization>> taskView = new HtmlTableView<Iterable<Immunization>>();

        HtmlTHead<Iterable<Immunization>> thead = taskView.thead();
        HtmlTBody<Iterable<Immunization>> tbody = taskView.tbody();
        HtmlTr<Iterable<Immunization>> trHead = thead.tr();

        trHead.th().text("Data Administração");
        trHead.th().text("Código");
        trHead.th().text("Nome");
        trHead.th().text("Dosagem");
        trHead.th().text("Lote");
        trHead.th().text("Nome Comercial");

        tbody.trFromIterable(ImmunizationBinder.binderGetInitialDate(), ImmunizationBinder.binderGetCode(), ImmunizationBinder.binderGetName(),
                ImmunizationBinder.binderGetDosage(), ImmunizationBinder.binderGetLot(), ImmunizationBinder.binderGetComercialCode());
        return taskView;
    }
}
