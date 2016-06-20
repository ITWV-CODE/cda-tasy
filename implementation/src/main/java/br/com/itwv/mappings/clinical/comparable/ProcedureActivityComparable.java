package br.com.itwv.mappings.clinical.comparable;

import org.openhealthtools.mdht.uml.cda.ccd.ProcedureActivityProcedure;
import org.openhealthtools.mdht.uml.hl7.datatypes.IVL_TS;

public class ProcedureActivityComparable implements Comparable<ProcedureActivityProcedure> {

	private ProcedureActivityProcedure procedureActivity;

	public ProcedureActivityComparable(ProcedureActivityProcedure activity) {
		this.procedureActivity = activity;
	}

	public int compareTo(ProcedureActivityProcedure procedureActivity) {

		try {
			if (this.procedureActivity.getObservations().get(0).getCode().getCode()
					.equals(procedureActivity.getObservations().get(0).getCode().getCode())
					&& this.procedureActivity.getObservations().get(0).getCode().getCodeSystemName()
							.equals(procedureActivity.getObservations().get(0).getCode().getCodeSystemName())
					&& ((IVL_TS) this.procedureActivity.getEffectiveTime()).getLow().getValue()
							.equals(((IVL_TS) procedureActivity.getEffectiveTime()).getLow().getValue()))
				return 1;
		} catch (Exception e) {
			return 0;
		}
		return 0;
	}

	@Override
	public boolean equals(Object e) {
		return this.compareTo((ProcedureActivityProcedure) e) == 1 ? true : false;
	}
}
