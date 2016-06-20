package br.com.itwv.cdatasy.base.html.templates.binders;

import br.com.itwv.cdatasy.base.html.elements.HtmlTable;
import br.com.itwv.cdatasy.base.html.elements.HtmlTr;
import br.com.itwv.cdatasy.base.html.flows.HtmlView;
import br.com.itwv.cdatasy.base.html.flows.ModelBinder;
import br.com.itwv.cdatasy.base.html.templates.objects.Alert;

import java.io.OutputStreamWriter;

public class AlertBinder {

	private static ModelBinder<Alert> binderGetAllergenCode() {
		return new ModelBinder<Alert>() {
			public void bind(OutputStreamWriter out, Alert model)
					throws Exception {
				out.write(model.getAllergenCode());
			}
		};
	}

	private static ModelBinder<Alert> binderGetAllergenDescription() {
		return new ModelBinder<Alert>() {
			public void bind(OutputStreamWriter out, Alert model)
					throws Exception {
				out.write(model.getAllergenDescription());
			}
		};
	}

	private static ModelBinder<Alert> binderGetDiagnosisDate() {
		return new ModelBinder<Alert>() {
			public void bind(OutputStreamWriter out, Alert model)
					throws Exception {
				out.write(model.getDiagnosisDate());
			}
		};
	}

	private static ModelBinder<Alert> binderGetReactions() {
		return new ModelBinder<Alert>() {
			public void bind(OutputStreamWriter out, Alert model)
					throws Exception {
				out.write(model.getReactions());
			}
		};
	}

	private static ModelBinder<Alert> binderGetStatus() {
		return new ModelBinder<Alert>() {
			public void bind(OutputStreamWriter out, Alert model)
					throws Exception {
				out.write(model.getStatus());
			}
		};
	}

	private static ModelBinder<Alert> binderGetCategory() {
		return new ModelBinder<Alert>() {
			public void bind(OutputStreamWriter out, Alert model)
					throws Exception {
				out.write(model.getCategory());
			}
		};
	}

	private static ModelBinder<Alert> binderGetSeverity() {
		return new ModelBinder<Alert>() {
			public void bind(OutputStreamWriter out, Alert model)
					throws Exception {
				out.write(model.getSeverity());
			}
		};
	}

	private static ModelBinder<Alert> binderGetSource() {
		return new ModelBinder<Alert>() {
			public void bind(OutputStreamWriter out, Alert model)
					throws Exception {
				out.write(model.getSource());
			}
		};
	}

	@SuppressWarnings("unchecked")
	public static HtmlView<Iterable<Alert>> taskListView() {
		HtmlView<Iterable<Alert>> taskView = new HtmlView<Iterable<Alert>>();
		taskView.head();

		HtmlTable<Iterable<Alert>> t = taskView.body().table();
		HtmlTr<Iterable<Alert>> headerRow = t.tr();
		headerRow.th().text("Código Alergénio CPARA");
		headerRow.th().text("Descrição Alergénio");
		headerRow.th().text("Data Diagnóstico");
		headerRow.th().text("Reacção");
		headerRow.th().text("Estado");
		headerRow.th().text("Categoria");
		headerRow.th().text("Severidade");
		headerRow.th().text("Origem");

		t.trFromIterable(AlertBinder.binderGetAllergenCode(),
				AlertBinder.binderGetAllergenDescription(),
				AlertBinder.binderGetDiagnosisDate(),
				AlertBinder.binderGetReactions(),
				AlertBinder.binderGetStatus(), AlertBinder.binderGetCategory(),
				AlertBinder.binderGetSeverity(), AlertBinder.binderGetSource());
		return taskView;
	}
}
