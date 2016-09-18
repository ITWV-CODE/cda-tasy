package br.com.itwv.cdatasy.base.html.templates.collections;

import br.com.itwv.cdatasy.base.encoding.streams.GenericOutputStream;
import br.com.itwv.cdatasy.base.html.templates.binders.FamilyHistoryBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.FamilyHistory;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class FamilyHistoryCollection implements Iterable<FamilyHistory> {

    private ArrayList<FamilyHistory> familyHistorieList;

    public FamilyHistoryCollection() {
        this.familyHistorieList = new ArrayList<FamilyHistory>();
    }

    public Iterator<FamilyHistory> iterator() {
        Iterator<FamilyHistory> iprof = familyHistorieList.iterator();
        return iprof;
    }

    public void add(FamilyHistory p) {
        this.familyHistorieList.add(p);
    }

    public FamilyHistory get(int index) {
        return this.familyHistorieList.get(index);
    }

    public void clear() {
        this.familyHistorieList.clear();
    }

    public int size() {
        return this.familyHistorieList.size();
    }

    public String toString() {
        try {
            GenericOutputStream output = new GenericOutputStream();
            OutputStreamWriter out = new OutputStreamWriter(output);
            FamilyHistoryBinder.taskListView().setOutputStreamWriter(out)
                    .write(1, this.familyHistorieList);

            output.close();

            out.close();
            return output.toString();
        } catch (Exception e) {
            return null;
        }
    }
}