package br.com.itwv.cdatasy.base.html.templates.collections;

import br.com.itwv.cdatasy.base.encoding.streams.GenericOutputStream;
import br.com.itwv.cdatasy.base.html.templates.binders.ImmunizationBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.Immunization;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class ImmunizationCollection implements Iterable<Immunization> {

    private ArrayList<Immunization> immunizations;

    public ImmunizationCollection() {
        this.immunizations = new ArrayList<Immunization>();
    }

    public Iterator<Immunization> iterator() {
        Iterator<Immunization> iprof = immunizations.iterator();
        return iprof;
    }

    public void add(Immunization m) {
        this.immunizations.add(m);
    }

    public Immunization get(int index) {
        return this.immunizations.get(index);
    }

    public void clear() {
        this.immunizations.clear();
    }

    public int size() {
        return this.immunizations.size();
    }

    public String toString() {
        try {
            GenericOutputStream output = new GenericOutputStream();
            OutputStreamWriter out = new OutputStreamWriter(output);
            ImmunizationBinder.taskListView().setOutputStreamWriter(out)
                    .write(1, this.immunizations);

            output.close();
            out.close();
            return output.toString();
        } catch (Exception e) {
            return null;
        }
    }
}