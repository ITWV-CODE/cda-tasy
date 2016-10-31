package br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2;

import br.com.itwv.cdatasy.base.encoding.streams.FastByteArrayOutputStream;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.helpers.ClinicalMappingEntryRelationships;
import br.com.itwv.cdatasy.common.business.interop.mappings.types.CDADataTypesFactory;
import br.com.itwv.cdatasy.common.business.resources.Resources;
import org.eclipse.emf.common.util.Diagnostic;
import org.openhealthtools.mdht.uml.cda.*;
import org.openhealthtools.mdht.uml.cda.ccd.*;
import org.openhealthtools.mdht.uml.cda.ccd.Product;
import org.openhealthtools.mdht.uml.cda.util.BasicValidationHandler;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.vocab.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.UUID;

import static br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping.x_DocEntryStatusCode;

public class ContinuityOfCareDocumentFactory implements IClinicalDocumentFactory {

    private static ClinicalDocument clinicalDocumentInstance;
    private static ContinuityOfCareDocumentFactory instance = new ContinuityOfCareDocumentFactory();

    protected ContinuityOfCareDocumentFactory() {
    }

    public static ContinuityOfCareDocumentFactory getInstance() {
        return instance;
    }

    private static void loadClinicalDocumentByFile(boolean validateClinicalDocument) {
        try {
            ContinuityOfCareDocumentFactory.clinicalDocumentInstance = CDAUtil.load((InputStream) Resources.class
                    .getResourceAsStream("/samples/SampleCCDDocument.xml"));
            if (validateClinicalDocument)
                ContinuityOfCareDocumentFactory.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadClinicalDocumentDefault(boolean instanceSections) {
        try {
            ContinuityOfCareDocumentFactory.clinicalDocumentInstance = CDAFactory.eINSTANCE.createClinicalDocument();
            ContinuityOfCareDocumentFactory.clinicalDocumentInstance.setTypeId(CDADataTypesFactory.getInstance().createInfrastructureRootTypeId("POCD_HD000040"));

            if (instanceSections) {
                ContinuityOfCareDocumentFactory.clinicalDocumentInstance.addSection(ContinuityOfCareDocumentFactory
                        .instanceSection(x_EObjectTypes.ALLERGIES));
                ContinuityOfCareDocumentFactory.clinicalDocumentInstance.addSection(ContinuityOfCareDocumentFactory
                        .instanceSection(x_EObjectTypes.MEDICATIONS));
                ContinuityOfCareDocumentFactory.clinicalDocumentInstance.addSection(ContinuityOfCareDocumentFactory
                        .instanceSection(x_EObjectTypes.PROBLEMS));
                ContinuityOfCareDocumentFactory.clinicalDocumentInstance.addSection(ContinuityOfCareDocumentFactory
                        .instanceSection(x_EObjectTypes.ENCOUNTERS));
                ContinuityOfCareDocumentFactory.clinicalDocumentInstance.addSection(ContinuityOfCareDocumentFactory
                        .instanceSection(x_EObjectTypes.FAMILY_HISTORY));
                ContinuityOfCareDocumentFactory.clinicalDocumentInstance.addSection(ContinuityOfCareDocumentFactory
                        .instanceSection(x_EObjectTypes.PLAN_OF_CARE));
                //ContinuityOfCareDocumentFactory.clinicalDocumentInstance.addSection(ContinuityOfCareDocumentFactory.instanceSection(x_EObjectTypes.LAB_RESULTS));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadClinicalDocumentByInputStream(InputStream clinicalDocument, boolean validateClinicalDocument) {
        try {

            CCDPackage.eINSTANCE.eClass();
            ContinuityOfCareDocumentFactory.clinicalDocumentInstance = CDAUtil.load(clinicalDocument);
            if (validateClinicalDocument)
                ContinuityOfCareDocumentFactory.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ClinicalDocument getClinicalDocumentInstance() {
        return ContinuityOfCareDocumentFactory.clinicalDocumentInstance;
    }

    public static void setClinicalDocumentInstance(ClinicalDocument clinicalDocumentInstance) {
        ContinuityOfCareDocumentFactory.clinicalDocumentInstance = clinicalDocumentInstance;
    }

    public static boolean validate() throws Exception {
        return CDAUtil.validate(ContinuityOfCareDocumentFactory.clinicalDocumentInstance, new BasicValidationHandler() {
            @Override
            public void handleError(Diagnostic diagnostic) {
                System.out.println("ERROR: " + diagnostic.getMessage());
            }

            @Override
            public void handleWarning(Diagnostic diagnostic) {
                System.out.println("WARNING: " + diagnostic.getMessage());
            }

            @Override
            public void handleInfo(Diagnostic diagnostic) {
                System.out.println("INFO: " + diagnostic.getMessage());
            }
        });
    }

    public static String getString() {
        try {
            FastByteArrayOutputStream writer = new FastByteArrayOutputStream();
            CDAUtil.save(ContinuityOfCareDocumentFactory.clinicalDocumentInstance, writer);
            String ret = writer.toString();
            writer.close();
            return ret;
        } catch (Exception e) {
            return null;
        }
    }


    public static void toFile(final String path) {
        try {
            final File fout = new File(path);
            final FileOutputStream fos = new FileOutputStream(fout);
            final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, Charset.forName("UTF-8")));
            CDAUtil.save(ContinuityOfCareDocumentFactory.clinicalDocumentInstance, writer);
            byte[] ret = writer.toString().getBytes();
            writer.close();
        } catch (Exception e) {
        }
    }

    public static byte[] getBytes() {
        try {
            FastByteArrayOutputStream writer = new FastByteArrayOutputStream();
            CDAUtil.save(ContinuityOfCareDocumentFactory.clinicalDocumentInstance, writer);
            byte[] ret = writer.toString().getBytes();
            writer.close();
            return ret;
        } catch (Exception e) {
            return null;
        }
    }

    public void createClinicalDocumentFactory(x_FactoryLoadTypes factoryLoadType, Object obj) {
        this.createClinicalDocumentFactory(factoryLoadType, obj, false, true);
    }

    public void createClinicalDocumentFactory(x_FactoryLoadTypes factoryLoadType, Object obj, boolean validateClinicalDocument,
                                              boolean instanceSections) {

        ContinuityOfCareDocumentFactory.clinicalDocumentInstance = null;
        switch (factoryLoadType) {
            case FILE:
                ContinuityOfCareDocumentFactory.loadClinicalDocumentByFile(validateClinicalDocument);
                break;
            case INPUTSTREAM:
                ContinuityOfCareDocumentFactory.loadClinicalDocumentByInputStream((InputStream) obj, validateClinicalDocument);
                break;
            case DEFAULT:
                ContinuityOfCareDocumentFactory.loadClinicalDocumentDefault(instanceSections);
            default:
                break;
        }
    }

    private static Section instanceSection(x_EObjectTypes x) throws Exception {
        switch (x) {
            case ALLERGIES:
                return ContinuityOfCareDocumentFactory.instanceAlertsSection();
            case IMMUNIZATIONS:
                return ContinuityOfCareDocumentFactory.instanceImmunizationsSection();
            case MEDICATIONS:
                return ContinuityOfCareDocumentFactory.instanceMedicationsSection();
            case PROBLEMS:
                return ContinuityOfCareDocumentFactory.instanceProblemsSection();
            case PROCEDURES:
                return ContinuityOfCareDocumentFactory.instanceProceduresSection();
            case MEDICALEQUIPMENT:
                return ContinuityOfCareDocumentFactory.instanceMedicalEquipmentSection();
            case ENCOUNTERS:
                return ContinuityOfCareDocumentFactory.instanceEncountersSection();
            case FAMILY_HISTORY:
                return ContinuityOfCareDocumentFactory.instanceFamilyHistorySection();
            case PLAN_OF_CARE:
                return ContinuityOfCareDocumentFactory.instancePlanOfCareSection();
            case LAB_RESULTS:
                return ContinuityOfCareDocumentFactory.instanceResultsSection();
            default:
                return null;
        }
    }

    private static MedicationsSection instanceMedicationsSection() throws Exception {

        MedicationsSection medicationsSection = CCDFactory.eINSTANCE.createMedicationsSection().init();
        medicationsSection.setTitle(DatatypesFactory.eINSTANCE.createST("Medicamentos Usados"));
        medicationsSection.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.12559.11.10.1.3.1.2.3", null));
        medicationsSection.setMoodCode(ActMood.EVN);
        medicationsSection.createStrucDocText("<content ID=\"nomedicationdescription\">Não existem medicamentos.</content>");
        medicationsSection.addSubstanceAdministration(ContinuityOfCareDocumentFactory.createMedicationsSectionEntry(null, true));

        return medicationsSection;
    }

    private static ImmunizationsSection instanceImmunizationsSection() throws Exception {

        ImmunizationsSection immunizationsSection = CCDFactory.eINSTANCE.createImmunizationsSection().init();
        immunizationsSection.setTitle(DatatypesFactory.eINSTANCE.createST("Historial de Vacinação"));
        immunizationsSection.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.3.23", null));
        immunizationsSection.setMoodCode(ActMood.EVN);
        immunizationsSection.createStrucDocText("<content ID=\"noimmunizationsdescription\">Não existem vacinas.</content>");
        immunizationsSection.addSubstanceAdministration(ContinuityOfCareDocumentFactory.createImmunizationsSectionEntry(null, true));

        return immunizationsSection;
    }

    private static EncountersSection instanceEncountersSection() throws Exception {

        EncountersSection encountersSection = CCDFactory.eINSTANCE.createEncountersSection().init();
        encountersSection.setTitle(DatatypesFactory.eINSTANCE.createST("Atendimentos"));
        encountersSection.setMoodCode(ActMood.EVN);
        encountersSection.createStrucDocText("<content ID=\"noencounters\">Não existem atendimentos.</content>");
        encountersSection.addEncounter(ContinuityOfCareDocumentFactory.createEncountersSectionEntry(null, true));

        return encountersSection;
    }

    private static ResultsSection instanceResultsSection() throws Exception {

        ResultsSection resultsSection = CCDFactory.eINSTANCE.createResultsSection().init();
        resultsSection.setTitle(DatatypesFactory.eINSTANCE.createST("Resultados de Laboratório"));
        resultsSection.setMoodCode(ActMood.EVN);
        resultsSection.createStrucDocText("<content ID=\"nolabresults\">Não existem resultados de laboratório.</content>");
        resultsSection.addAct(ContinuityOfCareDocumentFactory.createAlertsSectionEntry(null, true));
        resultsSection.addObservation(ContinuityOfCareDocumentFactory.createResultObservationEntry(null, true));
        return resultsSection;
    }

    private static AlertsSection instanceAlertsSection() throws Exception {

        AlertsSection alertsSection = CCDFactory.eINSTANCE.createAlertsSection().init();
        alertsSection.setTitle(DatatypesFactory.eINSTANCE.createST("Alergias, reacções adversas, alertas"));
        alertsSection.setMoodCode(ActMood.EVN);
        alertsSection.createStrucDocText("<content ID=\"noallergy\">Não existem reacções adversas.</content>");
        //alertsSection.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.3.13", null));
        alertsSection.addAct(ContinuityOfCareDocumentFactory.createAlertsSectionEntry(null, true));
        return alertsSection;
    }

    public static FamilyHistorySection instanceFamilyHistorySection() throws Exception {

        final FamilyHistorySection familyHistorySection = CCDFactory.eINSTANCE.createFamilyHistorySection().init();
        familyHistorySection.setTitle(DatatypesFactory.eINSTANCE.createST("Antecedentes Familiares"));
        familyHistorySection.setMoodCode(ActMood.EVN);
        familyHistorySection.createStrucDocText("<content ID=\"nofamilyhistory\">Não existem antecedentes familiares.</content>");
        familyHistorySection.addObservation(ContinuityOfCareDocumentFactory.createFamilyHistorySectionEntry(null, true));
        return familyHistorySection;
    }

    public static PlanOfCareSection instancePlanOfCareSection() throws Exception {

        final PlanOfCareSection planOfCareSection = CCDFactory.eINSTANCE.createPlanOfCareSection().init();
        planOfCareSection.setTitle(DatatypesFactory.eINSTANCE.createST("Plano de cuidados"));
        planOfCareSection.setMoodCode(ActMood.EVN);
        planOfCareSection.createStrucDocText("<content ID=\"noplanofcare\">Não existe plano de cuidados.</content>");
        planOfCareSection.addProcedure(ContinuityOfCareDocumentFactory.createPlanOfCareSectionEntry(null, true));
        return planOfCareSection;
    }

    private static ProblemSection instanceProblemsSection() throws Exception {

        ProblemSection problemSection = CCDFactory.eINSTANCE.createProblemSection().init();
        problemSection.setTitle(DatatypesFactory.eINSTANCE.createST("Historial de Doenças"));
        //problemSection.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.3.6", null));
        problemSection.setMoodCode(ActMood.EVN);
        problemSection.createStrucDocText("<content ID=\"actnoproblem\">Não existem problemas activos.</content>");
        problemSection.addAct(ContinuityOfCareDocumentFactory.createProblemsSectionEntry(null, true));

        return problemSection;
    }

    private static ProceduresSection instanceProceduresSection() throws Exception {

        ProceduresSection proceduresSection = CCDFactory.eINSTANCE.createProceduresSection().init();
        proceduresSection.setTitle(DatatypesFactory.eINSTANCE.createST("Intervenções Cirúrgicas"));
        proceduresSection.setMoodCode(ActMood.EVN);
        //proceduresSection.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.3.11", null));
        //proceduresSection.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.3.12", null));
        proceduresSection.createStrucDocText("<content ID=\"nosurgeries\">Sem cirurgias conhecidas.</content>");
        proceduresSection.addProcedure(ContinuityOfCareDocumentFactory.createProceduresSectionEntry(null, true));

        return proceduresSection;
    }

    private static MedicalEquipmentSection instanceMedicalEquipmentSection() throws Exception {

        MedicalEquipmentSection medicalEquipmentSection = CCDFactory.eINSTANCE.createMedicalEquipmentSection().init();
        medicalEquipmentSection.setTitle(DatatypesFactory.eINSTANCE.createST("Dispositivos Médicos e Implantes"));
        medicalEquipmentSection.getTemplateIds().add(
                CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.1.5.3.5", null));
        medicalEquipmentSection.getTemplateIds().add(
                CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.12559.11.10.1.3.1.2.4", "epSOS"));
        medicalEquipmentSection.setMoodCode(ActMood.EVN);
        medicalEquipmentSection.createStrucDocText("<content ID=\"nomedicalequipment\">Não existem dispositivos médicos.</content>");
        medicalEquipmentSection.addSupply(ContinuityOfCareDocumentFactory.createMedicalEquipmentSectionEntry(null, true));

        return medicalEquipmentSection;
    }

    public static String clinicalDocumentToString(ClinicalDocument doc) {

        try {
            FastByteArrayOutputStream writer = new FastByteArrayOutputStream();
            CDAUtil.save(doc, writer);
            String docString = writer.toString();
            writer.close();
            return docString;
        } catch (Exception e) {
            return null;
        }
    }

    public static ProblemAct createProblemsSectionEntry(ProblemAct problemAct, boolean emptyEntry) throws Exception {

        if (problemAct == null) {
            problemAct = CCDFactory.eINSTANCE.createProblemAct().init();
            //problemAct.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.4.5.1", null));
            //problemAct.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.4.5.2", null));
            problemAct.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
            problemAct.setCode(CDADataTypesFactory.getInstance().createBaseCodeCD("NA", NullFlavor.NA));
            problemAct.setClassCode(x_ActClassDocumentEntryAct.ACT);
            problemAct.setMoodCode(x_DocumentActMood.EVN);
        }

        ProblemObservation problemObservation = CCDFactory.eINSTANCE.createProblemObservation().init();
        problemAct.addObservation(problemObservation);
        problemObservation.setNegationInd(false);
        problemObservation.setText(CDADataTypesFactory.getInstance().createBaseED(null, "#problem"));
        problemObservation.setEffectiveTime(CDADataTypesFactory.getInstance().createBaseEffectiveTimeIVL_TS(null, null));
        // problemObservation.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.4.5", null));
        problemObservation.setCode(CDADataTypesFactory.getInstance().createBaseCodeCD("57041-6", "2.16.840.1.113883.6.1", "LOINC",
                "Histórico do utente e diagnósticos", null));
        problemObservation.getIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, null, null));
        problemObservation.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));

        if (emptyEntry) {
            problemAct.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
            problemAct.getIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, UUID.randomUUID().toString(), null));
            problemAct.setEffectiveTime(CDADataTypesFactory.getInstance().createBaseEffectiveTimeIVL_TS(null, null));
            problemObservation.setCode(CDADataTypesFactory.getInstance().createBaseCodeCD(null));
            problemObservation.setText(CDADataTypesFactory.getInstance().createBaseED(null, "#actnoproblem"));
            // TODO: SNOMED
            problemObservation.getValues().add(
                    CDADataTypesFactory.getInstance().createBaseCodeCD("160245001", "2.16.840.1.113883.6.96", "SNOMED-CT",
                            "Não existem problemas activos", CDADataTypesFactory.getInstance().createBaseED(null, "#actnoproblem")));
            ClinicalMappingEntryRelationships.getInstance().defineProblemActEntryRelationships(
                    ContinuityOfCareDocumentFactory.clinicalDocumentInstance, problemAct);
        }

        return problemAct;
    }

    public static ProcedureActivityProcedure createProceduresSectionEntry(ProcedureActivityProcedure procedureActivityProcedure, boolean emptyEntry)
            throws Exception {

        if (procedureActivityProcedure == null) {
            procedureActivityProcedure = CCDFactory.eINSTANCE.createProcedureActivityProcedure().init();
            procedureActivityProcedure.setClassCode(ActClass.PROC);
            procedureActivityProcedure.setMoodCode(x_DocumentProcedureMood.EVN);
            procedureActivityProcedure.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
            procedureActivityProcedure.getTemplateIds().add(
                    CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.4.19", null));
            procedureActivityProcedure.setCode(CDADataTypesFactory.getInstance().createBaseCodeCD("NA", NullFlavor.NA));
            procedureActivityProcedure.setText(CDADataTypesFactory.getInstance().createBaseED(null, "#procedure"));
        }

        ProcedureActivityObservation procedureActivityObservation = CCDFactory.eINSTANCE.createProcedureActivityObservation().init();
        procedureActivityProcedure.addObservation(procedureActivityObservation);
        procedureActivityObservation.getIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, UUID.randomUUID().toString(), null));
        procedureActivityObservation.setClassCode(ActClassObservation.OBS);
        procedureActivityObservation.setCode(CDADataTypesFactory.getInstance().createBaseCodeCD("NA", NullFlavor.NA));

        if (emptyEntry) {
            procedureActivityProcedure.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
            procedureActivityProcedure.setEffectiveTime(CDADataTypesFactory.getInstance().createBaseEffectiveTimeIVL_TS(null, null));
            procedureActivityProcedure.setText(CDADataTypesFactory.getInstance().createBaseED(null, "#noprocedure"));
            procedureActivityProcedure.getIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, UUID.randomUUID().toString(), null));
        }
        ClinicalMappingEntryRelationships.getInstance().defineProcedureActivityProcedureEntryRelationships(ContinuityOfCareDocumentFactory.clinicalDocumentInstance, procedureActivityProcedure);
        return procedureActivityProcedure;
    }

    public static Supply createMedicalEquipmentSectionEntry(Supply supply, boolean emptyEntry) throws Exception {

        if (supply == null) {
            supply = CDAFactory.eINSTANCE.createSupply();
        }
        Participant2 participant = CDAFactory.eINSTANCE.createParticipant2();
        ParticipantRole participantRole = CDAFactory.eINSTANCE.createParticipantRole();
        Device playingDevice = CDAFactory.eINSTANCE.createDevice();
        supply.setClassCode(ActClassSupply.SPLY);
        supply.setMoodCode(x_DocumentSubstanceMood.EVN);

        if (emptyEntry) {
            supply.setNullFlavor(NullFlavor.NA);
            supply.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.12559.11.10.1.3.1.3.5", null));
            supply.getEffectiveTimes().add(CDADataTypesFactory.getInstance().createBaseSXCM_TS(null, NullFlavor.NA));
            supply.getParticipants().add(participant);
            participant.setParticipantRole(participantRole);
            participantRole.setPlayingDevice(playingDevice);
            participant.setTypeCode(ParticipationType.DEV);
            participantRole.setClassCode(RoleClassRoot.MANU);
            playingDevice.setClassCode(EntityClassDevice.DEV);
            playingDevice.setDeterminerCode(EntityDeterminer.INSTANCE);
            playingDevice.setCode(CDADataTypesFactory.getInstance().createBaseCodeCE(null));
        }
        return supply;
    }

    public static MedicationActivity createMedicationsSectionEntry(MedicationActivity medicationActivity, boolean emptyEntry) throws Exception {

        Material material = CDAFactory.eINSTANCE.createMaterial();

        if (medicationActivity == null) {
            medicationActivity = CCDFactory.eINSTANCE.createMedicationActivity().init();
            Consumable consumable = CDAFactory.eINSTANCE.createConsumable();
            Product product = CCDFactory.eINSTANCE.createProduct().init();
            medicationActivity.setMoodCode(x_DocumentSubstanceMood.INT);
            //medicationActivity.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.4.7", null));
            //medicationActivity.getTemplateIds()                    .add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.4.7.1", null));
            //medicationActivity.getTemplateIds().add(                    CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.12559.11.10.1.3.1.3.4", null));
            medicationActivity.setClassCode(ActClass.SBADM);
            medicationActivity.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
            medicationActivity.setConsumable(consumable);
            consumable.setManufacturedProduct(product);
            product.setManufacturedMaterial(material);
            //product.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.4.7.2", null));
            //material.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.12559.11.10.1.3.1.3.1", null));
            //material.setName(CDADataTypesFactory.getInstance().createBaseEN(null));
            material.setCode(CDADataTypesFactory.getInstance().createBaseCodeCE(null, null, null, null,
                    CDADataTypesFactory.getInstance().createBaseED(null, "#medicationdescription")));
        }

        if (emptyEntry) {
            // TODO: SNOMED
            medicationActivity.setCode(CDADataTypesFactory.getInstance().createBaseCodeCD("182904002", "2.16.840.1.113883.6.96", "SNOMED-CT",
                    "Tratamento desconhecido", CDADataTypesFactory.getInstance().createBaseED(null, "#medicationdescription")));
            medicationActivity.getIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, UUID.randomUUID().toString(), null));
            medicationActivity.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
            medicationActivity.setDoseQuantity(CDADataTypesFactory.getInstance().createBaseDoseQuantityIVL_PQ());
            medicationActivity.setRouteCode(null);
            material.setCode(CDADataTypesFactory.getInstance().createBaseCodeCE(null, null, null, null,
                    CDADataTypesFactory.getInstance().createBaseED(null, "#nomedicationdescription")));
            ClinicalMappingEntryRelationships.getInstance().defineMedicationActivityEntryRelationships(
                    ContinuityOfCareDocumentFactory.clinicalDocumentInstance, medicationActivity);
        }
        return medicationActivity;
    }

    public static ProblemAct createAlertsSectionEntry(ProblemAct problemAct, boolean emptyEntry) throws Exception {

        if (problemAct == null) {
            problemAct = CCDFactory.eINSTANCE.createProblemAct().init();
            problemAct.setClassCode(x_ActClassDocumentEntryAct.ACT);
            problemAct.setMoodCode(x_DocumentActMood.EVN);
            // problemAct.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.4.5.1", null));
            //problemAct.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.4.5.3", null));
            problemAct.setCode(CDADataTypesFactory.getInstance().createBaseCodeCD("NA", NullFlavor.NA));
            problemAct.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
        }
        AlertObservation alertObservation = CCDFactory.eINSTANCE.createAlertObservation().init();
        problemAct.addObservation(alertObservation);
        problemAct.setEffectiveTime(CDADataTypesFactory.getInstance().createBaseEffectiveTimeIVL_TS(null, null));
        alertObservation.setClassCode(ActClassObservation.OBS);
        alertObservation.setNegationInd(false);
        alertObservation.setMoodCode(x_ActMoodDocumentObservation.EVN);
        //  alertObservation.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.4.6", null));
        //  alertObservation.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.4.5", null));
        // alertObservation.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "2.16.840.1.113883.10.20.1.28", null));
        alertObservation.getIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, UUID.randomUUID().toString(), null));
        alertObservation.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
        alertObservation.setEffectiveTime(CDADataTypesFactory.getInstance().createBaseEffectiveTimeIVL_TS(null, null));
        alertObservation.setText(CDADataTypesFactory.getInstance().createBaseED(null, "#allergy"));
        alertObservation.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));

        if (emptyEntry) {
            problemAct.getIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, UUID.randomUUID().toString(), null));
            problemAct.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
            alertObservation.setCode(CDADataTypesFactory.getInstance().createBaseCodeCD("NA", NullFlavor.NA));
            alertObservation.setEffectiveTime(CDADataTypesFactory.getInstance().createBaseEffectiveTimeIVL_TS(null, null));
            alertObservation.getValues().add(
                    CDADataTypesFactory.getInstance().createBaseCodeCD("52473-6", "2.16.840.1.113883.6.1", "LOINC", "Observação da Alergia",
                            CDADataTypesFactory.getInstance().createBaseED(null, "#noallergy")));
            ClinicalMappingEntryRelationships.getInstance().defineProblemActEntryRelationships(
                    ContinuityOfCareDocumentFactory.clinicalDocumentInstance, problemAct);
        }
        return problemAct;
    }

    public static EncountersActivity createEncountersSectionEntry(EncountersActivity encountersActivity, boolean emptyEntry) throws Exception {

        if (encountersActivity == null) {
            encountersActivity = CCDFactory.eINSTANCE.createEncountersActivity().init();
            encountersActivity.setClassCode(ActClass.ENC);
            encountersActivity.setMoodCode(x_DocumentEncounterMood.EVN);
            encountersActivity.setCode(CDADataTypesFactory.getInstance().createBaseCodeCD("NA", NullFlavor.NA));
            encountersActivity.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
        }
        return encountersActivity;
    }

    public static ResultObservation createResultObservationEntry(ResultObservation resultObservation, boolean emptyEntry) throws Exception {

        if (resultObservation == null) {
            resultObservation = CCDFactory.eINSTANCE.createResultObservation().init();
            resultObservation.setClassCode(ActClassObservation.OBS);
            resultObservation.setMoodCode(x_ActMoodDocumentObservation.EVN);
            resultObservation.setCode(CDADataTypesFactory.getInstance().createBaseCodeCD("NA", NullFlavor.NA));
            resultObservation.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
        }
        return resultObservation;
    }

    public static PlanOfCareActivityProcedure createPlanOfCareSectionEntry(PlanOfCareActivityProcedure planOfCareActivityProcedure, boolean emptyEntry) throws Exception {

        if (planOfCareActivityProcedure == null) {
            planOfCareActivityProcedure = CCDFactory.eINSTANCE.createPlanOfCareActivityProcedure().init();
            planOfCareActivityProcedure.setClassCode(ActClass.PROC);
            planOfCareActivityProcedure.setMoodCode(x_DocumentProcedureMood.RQO);
            planOfCareActivityProcedure.setCode(CDADataTypesFactory.getInstance().createBaseCodeCD("NA", NullFlavor.NA));
            planOfCareActivityProcedure.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
        }
        return planOfCareActivityProcedure;
    }

    public static FamilyHistoryObservation createFamilyHistorySectionEntry(FamilyHistoryObservation familyHistoryObservation, boolean emptyEntry) throws Exception {

        if (familyHistoryObservation == null) {
            familyHistoryObservation = CCDFactory.eINSTANCE.createFamilyHistoryObservation().init();
            familyHistoryObservation.setClassCode(ActClassObservation.OBS);
            familyHistoryObservation.setMoodCode(x_ActMoodDocumentObservation.EVN);
            familyHistoryObservation.setCode(CDADataTypesFactory.getInstance().createBaseCodeCD("NA", NullFlavor.NA));
            familyHistoryObservation.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
        }
        return familyHistoryObservation;
    }

    public static MedicationActivity createImmunizationsSectionEntry(MedicationActivity medicationActivity, boolean emptyEntry) throws Exception {

        Material material = CDAFactory.eINSTANCE.createMaterial();
        if (medicationActivity == null) {
            medicationActivity = CCDFactory.eINSTANCE.createMedicationActivity().init();
            Consumable consumable = CDAFactory.eINSTANCE.createConsumable();
            Product product = CCDFactory.eINSTANCE.createProduct().init();
            medicationActivity.setMoodCode(x_DocumentSubstanceMood.EVN);
            medicationActivity.setClassCode(ActClass.SBADM);
            medicationActivity.setNegationInd(false);
            medicationActivity.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
            medicationActivity.setCode(CDADataTypesFactory.getInstance().createBaseCodeCD("IMMUNIZ", "2.16.840.1.113883.5.4", "ActCode", null, null));
            medicationActivity.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.4.12", null));
            medicationActivity.setConsumable(consumable);
            consumable.setManufacturedProduct(product);
            product.setManufacturedMaterial(material);
            consumable.setTypeCode(ParticipationType.CSM);
            product.setClassCode(RoleClassManufacturedProduct.MANU);
            product.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.19376.1.5.3.1.4.7.2", null));
            material.setClassCode(EntityClassManufacturedMaterial.MMAT);
            material.setDeterminerCode(EntityDeterminerDetermined.KIND);
            material.setCode(CDADataTypesFactory.getInstance().createBaseCodeCE(null, null, null, null,
                    CDADataTypesFactory.getInstance().createBaseED(null, "#immunizationsdescription")));
        }

        if (emptyEntry) {
            medicationActivity.getIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, UUID.randomUUID().toString(), null));
            medicationActivity.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
            medicationActivity.setDoseQuantity(CDADataTypesFactory.getInstance().createBaseDoseQuantityIVL_PQ());
            medicationActivity.setRouteCode(null);
            material.setCode(CDADataTypesFactory.getInstance().createBaseCodeCE(null, null, null, null,
                    CDADataTypesFactory.getInstance().createBaseED(null, "#noimmunizationsdescription")));
            ClinicalMappingEntryRelationships.getInstance().defineMedicationActivityEntryRelationships(
                    ContinuityOfCareDocumentFactory.clinicalDocumentInstance, medicationActivity);
        }
        return medicationActivity;
    }
}