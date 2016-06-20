package br.com.itwv.mappings.clinical.tables;

import br.com.itwv.cdatasy.base.html.templates.collections.ProblemCollection;
import br.com.itwv.cdatasy.base.html.templates.objects.Problem;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.types.CDADataTypesFactory;
import org.openhealthtools.mdht.uml.cda.Observation;
import org.openhealthtools.mdht.uml.cda.ccd.ProblemAct;
import org.openhealthtools.mdht.uml.cda.ccd.ProblemObservation;
import org.openhealthtools.mdht.uml.cda.ccd.ProblemSection;

public class ProblemSectionTable {
	private static ProblemSectionTable instance = null;

	protected ProblemSectionTable() {
	}

	public static ProblemSectionTable getInstance() {
		if (instance == null) {
			instance = new ProblemSectionTable();
		}
		return instance;
	}

	public String getTable(ProblemSection problemSection) {

		ProblemCollection problemCollection = new ProblemCollection();

		for (ProblemAct problemAct : problemSection.getProblemActs()) {

			switch (IClinicalMapping.x_DocEntryStatusCode.valueOf(problemAct.getStatusCode().getOriginalText().getText())) {
			case ACTIVE:
			case NEW:
			case COMPLETED: {

				Problem problem = new Problem();
				problem.setInitialDate(CDADataTypesFactory.getInstance().getPrettyPrintDate(problemAct.getEffectiveTime().getLow(), "yyyyMMdd",
						"yyyy/MM/dd"));
				for (Observation observation : problemAct.getObservations()) {
					if (observation instanceof ProblemObservation) {
						problem.setCode(observation.getCode().getCode());
						problem.setDescription(observation.getCode().getDisplayName());
						problem.setClassificationCode(observation.getCode().getCodeSystemName());
					}
				}
				problemCollection.add(problem);

				break;
			}
			default:
				break;
			}
		}
		if (problemCollection.size() == 0)
			return "<content ID=\"actproblem\">Não existem problemas activos.</content>";
		return problemCollection.toString();
	}
}
