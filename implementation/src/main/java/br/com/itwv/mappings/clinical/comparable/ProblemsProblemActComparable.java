package br.com.itwv.mappings.clinical.comparable;

import org.openhealthtools.mdht.uml.cda.ccd.ProblemAct;
import org.openhealthtools.mdht.uml.hl7.datatypes.IVL_TS;

public class ProblemsProblemActComparable implements Comparable<ProblemAct> {

	private ProblemAct problemAct;

	public ProblemsProblemActComparable(ProblemAct problemAct) {
		this.problemAct = problemAct;
	}

	public int compareTo(ProblemAct problemAct) {

		try {
			if (this.problemAct.getObservations().get(0).getCode().getCode().equals(problemAct.getObservations().get(0).getCode().getCode())
					&& this.problemAct.getObservations().get(0).getCode().getCodeSystemName()
							.equals(problemAct.getObservations().get(0).getCode().getCodeSystemName())
					&& ((IVL_TS) this.problemAct.getEffectiveTime()).getLow().getValue()
							.equals(((IVL_TS) problemAct.getEffectiveTime()).getLow().getValue()))
				return 1;
		} catch (Exception e) {
			return 0;
		}
		return 0;
	}

	@Override
	public boolean equals(Object e) {
		return this.compareTo((ProblemAct) e) == 1 ? true : false;
	}
}
