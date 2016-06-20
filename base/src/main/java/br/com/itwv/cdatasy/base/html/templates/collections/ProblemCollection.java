package br.com.itwv.cdatasy.base.html.templates.collections;


import br.com.itwv.cdatasy.base.encoding.streams.GenericOutputStream;
import br.com.itwv.cdatasy.base.html.templates.binders.ProblemBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.Problem;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class ProblemCollection implements Iterable<Problem> {

    private ArrayList<Problem> problems;

    public ProblemCollection() {
        this.problems = new ArrayList<Problem>();
    }

    public Iterator<Problem> iterator() {
        Iterator<Problem> iprof = problems.iterator();
        return iprof;
    }

    public void add(Problem p) {
        this.problems.add(p);
    }

    public Problem get(int index) {
        return this.problems.get(index);
    }

    public void clear() {
        this.problems.clear();
    }

    public int size() {
        return this.problems.size();
    }

    public String toString() {
        try {
            GenericOutputStream output = new GenericOutputStream();
            OutputStreamWriter out = new OutputStreamWriter(output);
            ProblemBinder.taskListView().setOutputStreamWriter(out)
                    .write(1, this.problems);

            output.close();

            out.close();
            return output.toString();
        } catch (Exception e) {
            return null;
        }
    }
}