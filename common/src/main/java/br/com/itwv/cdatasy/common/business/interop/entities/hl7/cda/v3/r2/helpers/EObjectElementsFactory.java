package br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.helpers;

import br.com.itwv.cdatasy.base.exceptions.helpers.TraceHelper;
import br.com.itwv.cdatasy.base.logging.LoggingSeverity;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.exceptions.DuplicateEntryException;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.exceptions.SectionNotFoundException;
import org.openhealthtools.mdht.uml.cda.*;
import org.openhealthtools.mdht.uml.cda.ccd.*;
import org.openhealthtools.mdht.uml.cda.ccd.impl.*;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil.Filter;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil.Query;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.IClinicalDocumentFactory.x_EObjectTypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EObjectElementsFactory {

    public static class EObjectElementCode implements Serializable {

        private static final long serialVersionUID = -4762731160096441743L;
        private String code;
        private String codeSystem;
        private String codeSystemName;
        private String displayName;

        public EObjectElementCode() {
            super();
        }

        public EObjectElementCode(String code, String codeSystem, String codeSystemName, String displayName) {
            super();
            this.code = code;
            this.codeSystem = codeSystem;
            this.codeSystemName = codeSystemName;
            this.displayName = displayName;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCodeSystem() {
            return codeSystem;
        }

        public void setCodeSystem(String codeSystem) {
            this.codeSystem = codeSystem;
        }

        public String getCodeSystemName() {
            return codeSystemName;
        }

        public void setCodeSystemName(String codeSystemName) {
            this.codeSystemName = codeSystemName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
    }

    @SuppressWarnings({"unchecked"})
    private static int getAlertsElementsSize(ClinicalDocument doc, x_EObjectTypes x) throws Exception {

        AlertsSection alertsSection = (AlertsSection) EObjectElementsFactory.getDocumentSection(doc, x);

        if (alertsSection != null && alertsSection.getClass().getSimpleName().equals(AlertsSectionImpl.class.getSimpleName()))
            return new ArrayList<ProblemAct>((Collection<? extends ProblemAct>) CDAUtil.query(alertsSection,
                    "self.getProblemActs()->select(prb : ccd::ProblemAct | prb.oclIsKindOf(ccd::ProblemAct))->collect(oclAsType(ccd::ProblemAct))"))
                    .size();
        return 0;
    }

    @SuppressWarnings("rawtypes")
    public static Section getDocumentSection(ClinicalDocument doc, final x_EObjectTypes x) throws Exception {

        Query query = new Query(doc);

        switch (x) {
            case MEDICATIONS:
                return query.getSection(MedicationsSectionImpl.class, new Filter<MedicationsSectionImpl>() {
                    public boolean accept(MedicationsSectionImpl item) {
                        for (Class c : item.getClass().getInterfaces())
                            if (c.equals(MedicationsSection.class))
                                return true;
                        return false;
                    }
                });
            case ALLERGIES:
                return query.getSection(AlertsSectionImpl.class, new Filter<AlertsSectionImpl>() {
                    public boolean accept(AlertsSectionImpl item) {
                        for (Class c : item.getClass().getInterfaces())
                            if (c.equals(AlertsSection.class))
                                return true;
                        return false;
                    }
                });
            case IMMUNIZATIONS:
                return query.getSection(ImmunizationsSectionImpl.class, new Filter<ImmunizationsSectionImpl>() {
                    public boolean accept(ImmunizationsSectionImpl item) {
                        for (Class c : item.getClass().getInterfaces())
                            if (c.equals(ImmunizationsSection.class))
                                return true;
                        return false;
                    }
                });
            case PROBLEMS:
                return query.getSection(ProblemSectionImpl.class, new Filter<ProblemSectionImpl>() {
                    public boolean accept(ProblemSectionImpl item) {
                        for (Class c : item.getClass().getInterfaces())
                            if (c.equals(ProblemSection.class))
                                return true;
                        return false;
                    }
                });
            case PROCEDURES:
                return query.getSection(ProceduresSectionImpl.class, new Filter<ProceduresSectionImpl>() {
                    public boolean accept(ProceduresSectionImpl item) {
                        for (Class c : item.getClass().getInterfaces())
                            if (c.equals(ProceduresSection.class))
                                return true;
                        return false;
                    }
                });
            default:
                return null;
        }
    }

    public static ClinicalStatement getSectionEntry(ClinicalDocument doc, Section section, String id, String source, final x_EObjectTypes x)
            throws Exception {

        switch (x) {
            case ALLERGIES: {
                for (ProblemAct p : (((AlertsSection) section).getProblemActs()))
                    for (II ii : p.getIds())
                        for (Observation o : p.getObservations())
                            for (Supply supply : o.getSupplies())
                                for (Author author : supply.getAuthors())
                                    for (II organizationId : author.getAssignedAuthor().getRepresentedOrganization().getIds())
                                        if (ii.getExtension().equals(id) && organizationId.getExtension().equals(source))
                                            return p;
            }
            return null;
            case MEDICATIONS: {
                for (MedicationActivity m : ((MedicationsSection) section).getMedicationActivities())
                    for (II ii : m.getIds())
                        for (Supply supply : m.getSupplies())
                            for (Author author : supply.getAuthors())
                                for (II organizationId : author.getAssignedAuthor().getRepresentedOrganization().getIds())
                                    if (ii.getExtension().equals(id) && organizationId.getExtension().equals(source))
                                        return m;
            }
            return null;
            case IMMUNIZATIONS: {
                for (MedicationActivity m : ((ImmunizationsSection) section).getMedicationActivities())
                    for (II ii : m.getIds())
                        for (Supply supply : m.getSupplies())
                            for (Author author : supply.getAuthors())
                                for (II organizationId : author.getAssignedAuthor().getRepresentedOrganization().getIds())
                                    if (ii.getExtension().equals(id) && organizationId.getExtension().equals(source))
                                        return m;
            }
            return null;
            case PROBLEMS: {
                for (ProblemAct p : (((ProblemSection) section).getProblemActs()))
                    for (II ii : p.getIds())
                        for (Observation o : p.getObservations())
                            for (Supply supply : o.getSupplies())
                                for (Author author : supply.getAuthors())
                                    for (II organizationId : author.getAssignedAuthor().getRepresentedOrganization().getIds())
                                        if (ii.getExtension().equals(id) && organizationId.getExtension().equals(source))
                                            return p;
            }
            return null;
            case PROCEDURES: {
                for (Procedure p : (((ProceduresSection) section).getProcedures()))
                    for (II ii : p.getIds())
                        for (Observation o : p.getObservations())
                            for (Supply supply : o.getSupplies())
                                for (Author author : supply.getAuthors())
                                    for (II organizationId : author.getAssignedAuthor().getRepresentedOrganization().getIds())
                                        if (ii.getExtension().equals(id) && organizationId.getExtension().equals(source))
                                            return p;
            }
            return null;
            default:
                return null;
        }
    }

    @SuppressWarnings({"unchecked"})
    private static int getMedicationsElementsSize(ClinicalDocument doc, x_EObjectTypes x) throws Exception {

        MedicationsSection medicationsSection = (MedicationsSection) EObjectElementsFactory.getDocumentSection(doc, x);

        if (medicationsSection != null && medicationsSection.getClass().getSimpleName().equals(MedicationsSectionImpl.class.getSimpleName()))
            return new ArrayList<MedicationActivity>(
                    (Collection<? extends MedicationActivity>) CDAUtil
                            .query(medicationsSection,
                                    "self.getMedicationActivities()->select(act : ccd::MedicationActivity | act.oclIsKindOf(ccd::MedicationActivity))->collect(oclAsType(ccd::MedicationActivity))"))
                    .size();
        return 0;
    }

    @SuppressWarnings({"unchecked"})
    private static int getImmunizationsElementsSize(ClinicalDocument doc, x_EObjectTypes x) throws Exception {

        ImmunizationsSection immunizationsSection = (ImmunizationsSection) EObjectElementsFactory.getDocumentSection(doc, x);

        if (immunizationsSection != null && immunizationsSection.getClass().getSimpleName().equals(ImmunizationsSectionImpl.class.getSimpleName()))
            return new ArrayList<MedicationActivity>(
                    (Collection<? extends MedicationActivity>) CDAUtil
                            .query(immunizationsSection,
                                    "self.getMedicationActivities()->select(act : ccd::MedicationActivity | act.oclIsKindOf(ccd::MedicationActivity))->collect(oclAsType(ccd::MedicationActivity))"))
                    .size();
        return 0;
    }

    @SuppressWarnings({"unchecked"})
    private static int getProblemsElementsSize(ClinicalDocument doc, x_EObjectTypes x) throws Exception {

        ProblemSection problemSection = (ProblemSection) EObjectElementsFactory.getDocumentSection(doc, x);

        if (problemSection != null && problemSection.getClass().getSimpleName().equals(ProblemSectionImpl.class.getSimpleName()))
            return new ArrayList<ProblemAct>((Collection<? extends ProblemAct>) CDAUtil.query(problemSection,
                    "self.getProblemActs()->select(prb : ccd::ProblemAct | prb.oclIsKindOf(ccd::ProblemAct))->collect(oclAsType(ccd::ProblemAct))"))
                    .size();
        return 0;
    }

    @SuppressWarnings({"unchecked"})
    private static int getProceduresElementsSize(ClinicalDocument doc, x_EObjectTypes x) throws Exception {

        ProceduresSection proceduresSection = (ProceduresSection) EObjectElementsFactory.getDocumentSection(doc, x);

        if (proceduresSection != null && proceduresSection.getClass().getSimpleName().equals(ProceduresSectionImpl.class.getSimpleName()))
            return new ArrayList<Procedure>((Collection<? extends Procedure>) CDAUtil.query(proceduresSection,
                    "self.getProcedures()->select(prc : cda::Procedure | prc.oclIsKindOf(cda::Procedure))->collect(oclAsType(cda::Procedure))"))
                    .size();
        return 0;
    }

    public static int getEObjectElementsSize(ClinicalDocument doc, x_EObjectTypes x) throws Exception {
        try {
            switch (x) {
                case ALLERGIES:
                    return EObjectElementsFactory.getAlertsElementsSize(doc, x);
                case MEDICATIONS:
                    return EObjectElementsFactory.getMedicationsElementsSize(doc, x);
                case IMMUNIZATIONS:
                    return EObjectElementsFactory.getImmunizationsElementsSize(doc, x);
                case PROBLEMS:
                    return EObjectElementsFactory.getProblemsElementsSize(doc, x);
                case PROCEDURES:
                    return EObjectElementsFactory.getProceduresElementsSize(doc, x);
                default:
                    return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static List<EObjectElementCode> getEObjectElementCodes(ClinicalDocument doc) throws Exception {

        List<EObjectElementCode> eObjectElementCodeList = new ArrayList<EObjectElementCode>();

        for (x_EObjectTypes x : x_EObjectTypes.values())
            if (EObjectElementsFactory.getEObjectElementsSize(doc, x) > 0 && !EObjectElementsFactory.defaultSectionInstance(doc, x))
                eObjectElementCodeList.add(EObjectElementsFactory.setEObjectElementCode(x));

        return eObjectElementCodeList;
    }

    private static EObjectElementCode setEObjectElementCode(x_EObjectTypes x) throws Exception {

        switch (x) {
            case ALLERGIES:
                return new EObjectElementCode("48765-2", "2.16.840.1.113883.6.1", "LOINC", "Alergias, reacções adversas, alertas");
            case IMMUNIZATIONS:
                return new EObjectElementCode("11369-6", "2.16.840.1.113883.6.1", "LOINC", "Historial de Vacinação");
            case MEDICATIONS:
                return new EObjectElementCode("10160-0", "2.16.840.1.113883.6.1", "LOINC", "Medicamentos Usados");
            case PROBLEMS:
                return new EObjectElementCode("11450-4", "2.16.840.1.113883.6.1", "LOINC", "Historial de doenças");
            case PROCEDURES:
                return new EObjectElementCode("47519-4", "2.16.840.1.113883.6.1", "LOINC", "Intervenções Cirúgicas");
            case MEDICALEQUIPMENT:
                return new EObjectElementCode("46264-8", "2.16.840.1.113883.6.1", "LOINC", "Dispositivos Médicos e Implantes");
            default:
                return null;
        }
    }

    public static void resetDocumentSection(Section section, x_EObjectTypes x) throws Exception {

        String id = null;
        switch (x) {
            case ALLERGIES:
                id = "noallergy";
                break;
            case MEDICATIONS:
                id = "nomedicationdescription";
                break;
            case IMMUNIZATIONS:
                id = "noimmunizationsdescription";
                break;
            case PROBLEMS:
                id = "actnoproblem";
                break;
            case PROCEDURES:
                id = "nosurgeries";
                break;
            default:
                break;
        }

        if (section.getText().getText(id) != null) {
            section.getEntries().clear();
            section.setText(null);
        }
    }

    public static boolean defaultSectionInstance(ClinicalDocument doc, x_EObjectTypes x) throws Exception {

        switch (x) {
            case ALLERGIES:
                return (EObjectElementsFactory.getDocumentSection(doc, x).getText().getText("noallergy") != null);
            case MEDICATIONS:
                return (EObjectElementsFactory.getDocumentSection(doc, x).getText().getText("nomedicationdescription") != null);
            case IMMUNIZATIONS:
                return (EObjectElementsFactory.getDocumentSection(doc, x).getText().getText("noimmunizationsdescription") != null);
            case PROBLEMS:
                return (EObjectElementsFactory.getDocumentSection(doc, x).getText().getText("actnoproblem") != null);
            case PROCEDURES:
                return (EObjectElementsFactory.getDocumentSection(doc, x).getText().getText("nosurgeries") != null);
            default:
                return false;
        }
    }

    public static Section getLogicalDocumentSection(ClinicalDocument doc, x_EObjectTypes x, String id, String source) throws Exception {

        Section section = EObjectElementsFactory.getDocumentSection(doc, x);
        if (section == null)
            throw SectionNotFoundException.getInstance().definesException(TraceHelper.getMethodName(0), "Section [" + x.name() + "] not found.",
                    false, LoggingSeverity.ERROR);
        EObjectElementsFactory.resetDocumentSection(section, x);
        if (EObjectElementsFactory.getSectionEntry(doc, section, id, source, x) != null)
            throw DuplicateEntryException.getInstance().definesException(TraceHelper.getMethodName(0),
                    "Duplicate entry with id " + id + " and source " + source, false, LoggingSeverity.ERROR);

        return section;
    }
}