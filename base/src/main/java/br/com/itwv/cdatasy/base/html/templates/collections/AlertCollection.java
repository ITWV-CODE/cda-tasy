package br.com.itwv.cdatasy.base.html.templates.collections;

import br.com.itwv.cdatasy.base.encoding.streams.GenericOutputStream;
import br.com.itwv.cdatasy.base.html.templates.binders.AlertBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.Alert;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class AlertCollection implements Iterable<Alert> {

    private ArrayList<Alert> alerts;

    public AlertCollection() {
        this.alerts = new ArrayList<Alert>();
    }

    public Iterator<Alert> iterator() {
        Iterator<Alert> iprof = alerts.iterator();
        return iprof;
    }

    public void add(Alert m) {
        this.alerts.add(m);
    }

    public Alert get(int index) {
        return this.alerts.get(index);
    }

    public void clear() {
        this.alerts.clear();
    }

    public int size() {
        return this.alerts.size();
    }

    public String toString() {
        try {
            GenericOutputStream output = new GenericOutputStream();
            OutputStreamWriter out = new OutputStreamWriter(output);
            AlertBinder.taskListView().setOutputStreamWriter(out)
                    .write(1, this.alerts);

            output.close();
            out.close();
            return output.toString();
        } catch (Exception e) {
            return null;
        }
    }
}