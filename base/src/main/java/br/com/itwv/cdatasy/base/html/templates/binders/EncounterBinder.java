package br.com.itwv.cdatasy.base.html.templates.binders;

import br.com.itwv.cdatasy.base.html.elements.HtmlTBody;
import br.com.itwv.cdatasy.base.html.elements.HtmlTHead;
import br.com.itwv.cdatasy.base.html.elements.HtmlTr;
import br.com.itwv.cdatasy.base.html.flows.HtmlTableView;
import br.com.itwv.cdatasy.base.html.flows.ModelBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.Encounter;

import java.io.OutputStreamWriter;

public class EncounterBinder {

    private static ModelBinder<Encounter> binderGetDescription() {
        return new ModelBinder<Encounter>() {
            public void bind(OutputStreamWriter out, Encounter model)
                    throws Exception {
                out.write(model.getDescription());
            }
        };
    }

    private static ModelBinder<Encounter> binderGetDate() {
        return new ModelBinder<Encounter>() {
            public void bind(OutputStreamWriter out, Encounter model)
                    throws Exception {
                out.write(model.getDate());
            }
        };
    }

    private static ModelBinder<Encounter> binderGetLocation() {
        return new ModelBinder<Encounter>() {
            public void bind(OutputStreamWriter out, Encounter model)
                    throws Exception {
                out.write(model.getLocation());
            }
        };
    }

    public static HtmlTableView<Iterable<Encounter>> taskListView() {
        HtmlTableView<Iterable<Encounter>> taskView = new HtmlTableView<Iterable<Encounter>>();

        HtmlTHead<Iterable<Encounter>> thead = taskView.thead();
        HtmlTBody<Iterable<Encounter>> tbody = taskView.tbody();
        HtmlTr<Iterable<Encounter>> trHead = thead.tr();

        trHead.th().text("Descrição");
        trHead.th().text("Local");
        trHead.th().text("Data");

        tbody.trFromIterable(
                EncounterBinder.binderGetDescription(),
                EncounterBinder.binderGetLocation(),
                EncounterBinder.binderGetDate());
        return taskView;
    }
}
