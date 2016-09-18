package br.com.itwv.cdatasy.base.html.templates.collections;

import br.com.itwv.cdatasy.base.encoding.streams.GenericOutputStream;
import br.com.itwv.cdatasy.base.html.templates.binders.PlanOfCareBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.PlanOfCare;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class PlanOfCareCollection implements Iterable<PlanOfCare> {

    private ArrayList<PlanOfCare> planOfCareList;

    public PlanOfCareCollection() {
        this.planOfCareList = new ArrayList<PlanOfCare>();
    }

    public Iterator<PlanOfCare> iterator() {
        Iterator<PlanOfCare> iprof = planOfCareList.iterator();
        return iprof;
    }

    public void add(PlanOfCare p) {
        this.planOfCareList.add(p);
    }

    public PlanOfCare get(int index) {
        return this.planOfCareList.get(index);
    }

    public void clear() {
        this.planOfCareList.clear();
    }

    public int size() {
        return this.planOfCareList.size();
    }

    public String toString() {
        try {
            GenericOutputStream output = new GenericOutputStream();
            OutputStreamWriter out = new OutputStreamWriter(output);
            PlanOfCareBinder.taskListView().setOutputStreamWriter(out)
                    .write(1, this.planOfCareList);

            output.close();

            out.close();
            return output.toString();
        } catch (Exception e) {
            return null;
        }
    }
}