package br.com.itwv.mappings.clinical.comparable;

import org.openhealthtools.mdht.uml.cda.Observation;
import org.openhealthtools.mdht.uml.cda.ccd.AlertObservation;
import org.openhealthtools.mdht.uml.cda.ccd.ProblemAct;

public class AlertsProblemActComparable implements Comparable<ProblemAct> {
	private ProblemAct problemAct;

	public AlertsProblemActComparable(ProblemAct problemAct) {
		this.problemAct = problemAct;
	}

	private AlertObservation getAlertObservation(ProblemAct problemAct) {
		for (Observation observation : problemAct.getObservations())
			if (observation instanceof AlertObservation)
				return (AlertObservation) observation;
		return null;
	}

	public int compareTo(ProblemAct problemAct) {

		try {
			AlertObservation inAlertObservation = this.getAlertObservation(this.problemAct);
			AlertObservation outAlertObservation = this.getAlertObservation(problemAct);

			if (outAlertObservation.getCode().getCode().equals(inAlertObservation.getCode().getCode())
					&& outAlertObservation.getParticipants().get(0).getParticipantRole().getPlayingEntity().getCode().getCode()
							.equals(inAlertObservation.getParticipants().get(0).getParticipantRole().getPlayingEntity().getCode().getCode()))
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
