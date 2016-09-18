package br.com.itwv.cdatasy.base.html.templates.collections;

import br.com.itwv.cdatasy.base.encoding.streams.GenericOutputStream;
import br.com.itwv.cdatasy.base.html.templates.binders.EncounterBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.Encounter;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class EncounterCollection implements Iterable<Encounter> {

    private ArrayList<Encounter> encounterList;

    public EncounterCollection() {
        this.encounterList = new ArrayList<Encounter>();
    }

    public Iterator<Encounter> iterator() {
        Iterator<Encounter> iprof = encounterList.iterator();
        return iprof;
    }

    public void add(Encounter p) {
        this.encounterList.add(p);
    }

    public Encounter get(int index) {
        return this.encounterList.get(index);
    }

    public void clear() {
        this.encounterList.clear();
    }

    public int size() {
        return this.encounterList.size();
    }

    public String toString() {
        try {
            GenericOutputStream output = new GenericOutputStream();
            OutputStreamWriter out = new OutputStreamWriter(output);
            EncounterBinder.taskListView().setOutputStreamWriter(out)
                    .write(1, this.encounterList);

            output.close();

            out.close();
            return output.toString();
        } catch (Exception e) {
            return null;
        }
    }
}