package br.com.itwv.mappings.clinical.tables;

import br.com.itwv.cdatasy.base.html.templates.collections.ProcedureCollection;
import br.com.itwv.cdatasy.base.html.templates.objects.Procedure;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.types.CDADataTypesFactory;
import org.openhealthtools.mdht.uml.cda.Observation;
import org.openhealthtools.mdht.uml.cda.ccd.ProcedureActivityObservation;
import org.openhealthtools.mdht.uml.cda.ccd.ProceduresSection;

public class ProcedureSectionTable {
	private static ProcedureSectionTable instance = null;

	protected ProcedureSectionTable() {
	}

	public static ProcedureSectionTable getInstance() {
		if (instance == null) {
			instance = new ProcedureSectionTable();
		}
		return instance;
	}

	public String getTable(ProceduresSection procedureSection) {

		ProcedureCollection procedureCollection = new ProcedureCollection();

		for (org.openhealthtools.mdht.uml.cda.Procedure procedureActivity : procedureSection.getProcedures()) {

			switch (IClinicalMapping.x_DocEntryStatusCode.valueOf(procedureActivity.getStatusCode().getCode().toUpperCase())) {
			case ACTIVE:
			case NEW:
			case COMPLETED: {

				Procedure procedure = new Procedure();
				procedure.setInitialDate(CDADataTypesFactory.getInstance().getPrettyPrintDate(procedureActivity.getEffectiveTime().getLow(),
						"yyyyMMdd", "yyyy/MM/dd"));

				for (Observation observation : procedureActivity.getObservations()) {
					if (observation instanceof ProcedureActivityObservation) {
						procedure.setCode(observation.getCode().getCode());
						procedure.setDescription(observation.getCode().getDisplayName());
						procedure.setClassificationCode(observation.getCode().getCodeSystemName());
					}
				}
				procedureCollection.add(procedure);

				break;
			}
			default:
				break;
			}
		}
		if (procedureCollection.size() == 0)
			return "<content ID=\"surgeries\">Sem cirurgias conhecidas.</content>";
		return procedureCollection.toString();
	}
}
