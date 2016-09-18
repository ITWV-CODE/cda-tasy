package br.com.itwv.cdatasy.base.html.templates.binders;

import br.com.itwv.cdatasy.base.html.elements.HtmlTBody;
import br.com.itwv.cdatasy.base.html.elements.HtmlTHead;
import br.com.itwv.cdatasy.base.html.elements.HtmlTr;
import br.com.itwv.cdatasy.base.html.flows.HtmlTableView;
import br.com.itwv.cdatasy.base.html.flows.ModelBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.FamilyHistory;

import java.io.OutputStreamWriter;

public class FamilyHistoryBinder {

    private static ModelBinder<FamilyHistory> binderGetRelationship() {
        return new ModelBinder<FamilyHistory>() {
            public void bind(OutputStreamWriter out, FamilyHistory model)
                    throws Exception {
                out.write(model.getRelationship());
            }
        };
    }

    private static ModelBinder<FamilyHistory> binderGetProblem() {
        return new ModelBinder<FamilyHistory>() {
            public void bind(OutputStreamWriter out, FamilyHistory model)
                    throws Exception {
                out.write(model.getProblem());
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static HtmlTableView<Iterable<FamilyHistory>> taskListView() {
        HtmlTableView<Iterable<FamilyHistory>> taskView = new HtmlTableView<Iterable<FamilyHistory>>();

        HtmlTHead<Iterable<FamilyHistory>> thead = taskView.thead();
        HtmlTBody<Iterable<FamilyHistory>> tbody = taskView.tbody();
        HtmlTr<Iterable<FamilyHistory>> trHead = thead.tr();

        trHead.th().text("Parentesco");
        trHead.th().text("Problema");

        tbody.trFromIterable(
                FamilyHistoryBinder.binderGetRelationship(),
                FamilyHistoryBinder.binderGetProblem());
        return taskView;
    }
}
