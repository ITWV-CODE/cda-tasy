package br.com.itwv.cdatasy.base.html.templates.collections;

import br.com.itwv.cdatasy.base.encoding.streams.GenericOutputStream;
import br.com.itwv.cdatasy.base.html.templates.binders.ProcedureBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.Procedure;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class ProcedureCollection implements Iterable<Procedure> {

    private ArrayList<Procedure> procedures;

    public ProcedureCollection() {
        this.procedures = new ArrayList<Procedure>();
    }

    public Iterator<Procedure> iterator() {
        Iterator<Procedure> iprof = procedures.iterator();
        return iprof;
    }

    public void add(Procedure p) {
        this.procedures.add(p);
    }

    public Procedure get(int index) {
        return this.procedures.get(index);
    }

    public void clear() {
        this.procedures.clear();
    }

    public int size() {
        return this.procedures.size();
    }

    public String toString() {
        try {
            GenericOutputStream output = new GenericOutputStream();
            OutputStreamWriter out = new OutputStreamWriter(output);
            ProcedureBinder.taskListView().setOutputStreamWriter(out)
                    .write(1, this.procedures);

            output.close();

            out.close();
            return output.toString();
        } catch (Exception e) {
            return null;
        }
    }
}