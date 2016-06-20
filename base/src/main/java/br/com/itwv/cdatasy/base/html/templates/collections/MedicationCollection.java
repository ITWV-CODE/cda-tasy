package br.com.itwv.cdatasy.base.html.templates.collections;

import br.com.itwv.cdatasy.base.encoding.streams.GenericOutputStream;
import br.com.itwv.cdatasy.base.html.templates.binders.MedicationBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.Medication;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class MedicationCollection implements Iterable<Medication> {

    private ArrayList<Medication> medications;

    public MedicationCollection() {
        this.medications = new ArrayList<Medication>();
    }

    public Iterator<Medication> iterator() {
        Iterator<Medication> iprof = medications.iterator();
        return iprof;
    }

    public void add(Medication m) {
        this.medications.add(m);
    }

    public Medication get(int index) {
        return this.medications.get(index);
    }

    public void clear() {
        this.medications.clear();
    }

    public int size() {
        return this.medications.size();
    }

    public String toString() {
        try {
            GenericOutputStream output = new GenericOutputStream();
            OutputStreamWriter out = new OutputStreamWriter(output);
            MedicationBinder.taskListView().setOutputStreamWriter(out)
                    .write(1, this.medications);

            output.close();
            out.close();
            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}