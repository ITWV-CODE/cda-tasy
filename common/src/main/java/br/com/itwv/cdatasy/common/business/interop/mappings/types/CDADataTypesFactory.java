package br.com.itwv.cdatasy.common.business.interop.mappings.types;

import br.com.itwv.cdatasy.base.encoding.streams.OIDUtil;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import org.apache.commons.lang3.StringUtils;
import org.openhealthtools.mdht.uml.cda.*;
import org.openhealthtools.mdht.uml.cda.ccd.*;
import org.openhealthtools.mdht.uml.hl7.datatypes.*;
import org.openhealthtools.mdht.uml.hl7.vocab.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CDADataTypesFactory {

    private static CDADataTypesFactory instance = null;

    protected CDADataTypesFactory() {
    }

    public static CDADataTypesFactory getInstance() {
        if (instance == null) {
            instance = new CDADataTypesFactory();
        }
        return instance;
    }

    public InfrastructureRootTypeId createInfrastructureRootTypeId(final String extension) {
        final InfrastructureRootTypeId infrastructureRootTypeId = CDAFactory.eINSTANCE.createInfrastructureRootTypeId();
        infrastructureRootTypeId.setExtension(extension);
        return infrastructureRootTypeId;
    }

    public CS createBaseStatusCodeCS(String status) {

        CS statusCode = DatatypesFactory.eINSTANCE.createCS();
        statusCode.setCode(status);
        return statusCode;
    }

   /* public CS createBaseStatusCodeCS(String status, IClinicalMapping.x_DocEntryStatusCode x_DocEntryStatusCode) {

        CS statusCode = DatatypesFactory.eINSTANCE.createCS();
        statusCode.setCode(status);
        statusCode.setCodeSystem("2.16.840.1.113883.5.14");
        statusCode.setCodeSystemName("ActStatus");
        statusCode.setOriginalText(DatatypesFactory.eINSTANCE.createED(x_DocEntryStatusCode.toString()));
        return statusCode;
    }*/

    public PIVL_TS createBaseEffectiveTimePIVL_TS(boolean institutionSpecified, SetOperator operator, NullFlavor... nullFlavor) {
        return this.createBaseEffectiveTimePIVL_TS(null, null, institutionSpecified, operator, nullFlavor);
    }

    public PIVL_TS createBaseEffectiveTimePIVL_TS(Long periodValue, String periodValueUnit, boolean institutionSpecified, SetOperator operator,
                                                  NullFlavor... nullFlavor) {

        PIVL_TS effectiveTime = DatatypesFactory.eINSTANCE.createPIVL_TS();
        effectiveTime.setInstitutionSpecified(institutionSpecified);
        effectiveTime.setOperator(operator);
        if (nullFlavor.length > 0)
            effectiveTime.setNullFlavor(nullFlavor[0]);
        if (periodValue != null) {
            PQ period = DatatypesFactory.eINSTANCE.createPQ();
            effectiveTime.setPeriod(period);
            period.setValue(new BigDecimal(periodValue));
            if (periodValueUnit != null)
                period.setUnit(periodValueUnit);
        }
        return effectiveTime;
    }

    public TS createBaseTime(String value) {

        TS ts = DatatypesFactory.eINSTANCE.createTS();
        ts.setValue(value);
        return ts;
    }

    public SXCM_TS createBaseSXCM_TS(String value, NullFlavor... nullFlavor) {

        SXCM_TS sxcm_ts = DatatypesFactory.eINSTANCE.createSXCM_TS();

        if (value != null)
            sxcm_ts.setValue(value);
        if (nullFlavor.length > 0)
            sxcm_ts.setNullFlavor(nullFlavor[0]);
        return sxcm_ts;
    }

    public IVL_TS createBaseEffectiveTimeIVL_TS(String lowValue, String highValue) {

        IVL_TS effectiveTime = DatatypesFactory.eINSTANCE.createIVL_TS();
        IVXB_TS lowIVXB_TS = this.createBaseEffectiveTimeIVXB_TS(lowValue);
        IVXB_TS highIVXB_TS = this.createBaseEffectiveTimeIVXB_TS(highValue);

        effectiveTime.setLow(lowIVXB_TS);
        effectiveTime.setHigh(highIVXB_TS);

        return effectiveTime;
    }

    public IVXB_TS createBaseEffectiveTimeIVXB_TS(String value) {

        IVXB_TS ts = DatatypesFactory.eINSTANCE.createIVXB_TS();

        if (value != null)
            ts.setValue(value);
        else
            ts.setNullFlavor(NullFlavor.NA);

        return ts;
    }

    public ON createBaseOrganizationName(String text) {

        ON on = DatatypesFactory.eINSTANCE.createON();

        if (text == null) {
            on.setNullFlavor(NullFlavor.NI);
            return on;
        }

        on.addText(text);
        return on;

    }

    public PN createBasePN(String familyName, String givenName, String suffixName, String prefixName) {

        PN name = DatatypesFactory.eINSTANCE.createPN();

        try {
            if (familyName == null) {
                name.setNullFlavor(NullFlavor.NI);
                return name;
            }

            if (familyName != null)
                name.addFamily(familyName);

            if (givenName != null)
                name.addGiven(givenName);

            if (suffixName != null)
                name.addSuffix(suffixName);

            if (prefixName != null)
                name.addPrefix(prefixName);

        } catch (Exception e) {
            name.setNullFlavor(NullFlavor.NI);
        }
        return name;
    }

    private Person createBasePerson(String familyName, String givenName, String suffixName, String prefixName) {

        Person person = CDAFactory.eINSTANCE.createPerson();
        try {
            PN name = this.createBasePN(familyName, givenName, suffixName, prefixName);
            person.getNames().add(name);
        } catch (Exception e) {
            person.setNullFlavor(NullFlavor.NI);
        }
        return person;
    }

    private AssignedAuthor createBaseAssignedAuthor(String extension, String root, String assigningAuthorityName, String personFamilyName,
                                                    String personGivenName, String personSuffixName, String personPrefixName, String organizationName, String organizationExtension, String country, String city,
                                                    String postalCode, String streetAddressLine, String state) {

        AssignedAuthor assignedAuthor = CDAFactory.eINSTANCE.createAssignedAuthor();
        try {
            assignedAuthor.setAssignedPerson(this.createBasePerson(personFamilyName, personGivenName, personSuffixName, personPrefixName));
            assignedAuthor.getIds().add(this.createBaseRootII(extension, root, assigningAuthorityName));
            assignedAuthor.setRepresentedOrganization(this.createBaseOrganization(organizationName, organizationExtension, country, city, postalCode,
                    streetAddressLine, state));
        } catch (Exception e) {
            assignedAuthor.setNullFlavor(NullFlavor.NI);
        }
        return assignedAuthor;
    }

    public Author createBaseAuthor(String time, String extension, String root, String assigningAuthorityName, String personFamilyName,
                                   String personGivenName, String personSuffixName, String organizationName, String organizationExtension, String country, String city,
                                   String postalCode, String streetAddressLine, String state) {

        Author author = CDAFactory.eINSTANCE.createAuthor();
        try {
            author.setTime(this.createBaseTime(time));
            if (personSuffixName != null)
                author.setFunctionCode(this.createBaseCodeCE(null, null, null, personSuffixName, null));
            author.setAssignedAuthor(this.createBaseAssignedAuthor(extension, root, assigningAuthorityName, personFamilyName, personGivenName, null, null,
                    organizationName, organizationExtension, country, city, postalCode, streetAddressLine, state));
        } catch (Exception e) {
            author.setNullFlavor(NullFlavor.NI);
        }
        return author;
    }

    public Supply createBaseSupply(ClinicalStatement statement, String time, String extension, String root, String assigningAuthorityName,
                                   String personFamilyName, String personGivenName, String personSuffixName, String organizationName, String organizationExtension,
                                   String relatedSubjectCode, String relatedSubjectName, String country, String city, String postalCode, String streetAddressLine,
                                   String state) {

        Supply supply = CCDFactory.eINSTANCE.createSupplyActivity().init();
        statement.addSupply(supply);
        supply.setMoodCode(x_DocumentSubstanceMood.INT);
        supply.setSubject(this.createBaseSubject(relatedSubjectCode, relatedSubjectName));
        supply.getAuthors().add(
                this.createBaseAuthor(time, extension, root, assigningAuthorityName, personFamilyName, personGivenName, personSuffixName,
                        organizationName, organizationExtension, country, city, postalCode, streetAddressLine, state));

        return supply;
    }

    private Subject createBaseSubject(String relatedSubjectCode, String relatedSubjectName) {

        Subject subject = CDAFactory.eINSTANCE.createSubject();
        subject.setTypeCode(ParticipationTargetSubject.SBJ);
        subject.setRelatedSubject(this.createBaseRelatedSubject(relatedSubjectCode, relatedSubjectName));
        return subject;
    }

    private RelatedSubject createBaseRelatedSubject(String relatedSubjectCode, String relatedSubjectName) {

        RelatedSubject relatedSubject = CDAFactory.eINSTANCE.createRelatedSubject();
        if (relatedSubjectCode == null)
            relatedSubject.setNullFlavor(NullFlavor.NI);
        else
            relatedSubject.setCode(this.createBaseCodeCE(relatedSubjectCode, null, null, relatedSubjectName, null));
        return relatedSubject;
    }

    public ReactionObservation createBaseReactionObservation(ClinicalStatement statement, String code, String codeSystem, String codeSystemName,
                                                             String displayName) {

        ReactionObservation reactionObservation = CCDFactory.eINSTANCE.createReactionObservation().init();
        statement.addObservation(reactionObservation);
        // TODO: SNOMED
        reactionObservation.setCode(this.createBaseCodeCD("418799008", "2.16.840.1.113883.6.96", "SNOMED-CT",
                "Finding reported 1950 by subject or history provider", null));
        reactionObservation.setStatusCode(this.createBaseStatusCodeCS(IClinicalMapping.x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
        reactionObservation.getValues().add(this.createBaseCodeCD(code, codeSystem, codeSystemName, displayName, null));
        return reactionObservation;
    }

    public AlertStatusObservation createBaseAlertStatusObservation(ClinicalStatement statement, String code, String codeSystem,
                                                                   String codeSystemName, String displayName, String originalText) {

        AlertStatusObservation alertStatusObservation = CCDFactory.eINSTANCE.createAlertStatusObservation().init();
        statement.addObservation(alertStatusObservation);
        alertStatusObservation.getValues().add(
                CDADataTypesFactory.getInstance().createBaseCodeCE(code, codeSystem, codeSystemName, displayName,
                        this.createBaseED(originalText, null)));

        return alertStatusObservation;
    }

    public SeverityObservation createBaseSeverityObservation(ClinicalStatement statement, String code, String codeSystem, String codeSystemName,
                                                             String displayName) {

        SeverityObservation severityObservation = CCDFactory.eINSTANCE.createSeverityObservation().init();
        statement.addObservation(severityObservation);
        severityObservation.setCode(CDADataTypesFactory.getInstance()
                .createBaseCodeCD("SEV", "2.16.840.1.113883.5.4", "HL7ActCode", "Severity", null));
        severityObservation.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(
                IClinicalMapping.x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
        severityObservation.getValues().add(CDADataTypesFactory.getInstance().createBaseCodeCD(code, codeSystem, codeSystemName, displayName, null));
        return severityObservation;
    }

    public EntryRelationship defineEntryRelationship(EntryRelationship e, x_ActRelationshipEntryRelationship x_ActRelationshipEntryRelationship,
                                                     boolean inversionInd) {

        e.setTypeCode(x_ActRelationshipEntryRelationship);
        e.setInversionInd(inversionInd);
        return e;
    }

    public ClinicalStatement createBasePatientInstruction(ClinicalStatement statement, String instructions) {

        PatientInstruction patientInstruction = CCDFactory.eINSTANCE.createPatientInstruction().init();
        statement.addAct(patientInstruction);
        patientInstruction.setClassCode(x_ActClassDocumentEntryAct.ACT);
        patientInstruction.setMoodCode(x_DocumentActMood.EVN);
        patientInstruction.setCode(this.createBaseCodeCD("PINSTRUCT", "1.3.6.1.4.1.19376.1.5.3.2", "IHEActCode", null, null));
        patientInstruction.setText(this.createBaseED(instructions, null));
        patientInstruction.setStatusCode(this.createBaseStatusCodeCS(IClinicalMapping.x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
        return statement;
    }

    public ClinicalStatement createBaseFulfillmentInstruction(ClinicalStatement statement, CD codedElement, String commentId, String comment,
                                                              List<CD> translations) {

        FulfillmentInstruction fulfillmentInstruction = CCDFactory.eINSTANCE.createFulfillmentInstruction().init();
        statement.getSupplies().get(0).addAct(fulfillmentInstruction);
        fulfillmentInstruction.setMoodCode(x_DocumentActMood.EVN);
        fulfillmentInstruction.setStatusCode(this.createBaseStatusCodeCS(IClinicalMapping.x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
        fulfillmentInstruction.setCode(codedElement);
        if (comment != null)
            this.createBaseComment(fulfillmentInstruction, commentId, comment);

        for (CD t : translations)
            codedElement.getTranslations().add(t);

        return statement;
    }

    public ClinicalStatement createBaseComment(ClinicalStatement statement, String commentId, String commentText) {

        Comment comment = CCDFactory.eINSTANCE.createComment().init();
        statement.addAct(comment);
        comment.getIds().add(this.createBaseRootII(commentId, null, null));
        comment.setClassCode(x_ActClassDocumentEntryAct.ACT);
        comment.setMoodCode(x_DocumentActMood.EVN);
        comment.setStatusCode(this.createBaseStatusCodeCS(IClinicalMapping.x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
        comment.setCode(this.createBaseCodeCD("48767-8", "2.16.840.1.113883.6.1", "LOINC", "Annotation Comment", null));
        comment.setText(this.createBaseED(commentText, null));
        return statement;
    }

    public IVL_PQ createBaseDoseQuantityIVL_PQ() {
        IVL_PQ doseQuantity = DatatypesFactory.eINSTANCE.createIVL_PQ();
        doseQuantity.setNullFlavor(NullFlavor.NI);
        return doseQuantity;
    }

    public IVXB_PQ createBaseIVXB_PQ(String value, String units) {

        IVXB_PQ i = DatatypesFactory.eINSTANCE.createIVXB_PQ();
        if (value != null)
            i.setValue(new BigDecimal(value));
        if (units != null)
            i.setUnit(units);
        return i;
    }

    public IVL_PQ createBaseDoseQuantityIVL_PQ(String value, String units) {

        IVL_PQ doseQuantity = DatatypesFactory.eINSTANCE.createIVL_PQ();
        try {
            doseQuantity.setLow(this.createBaseIVXB_PQ(value, units));
            doseQuantity.setHigh(this.createBaseIVXB_PQ(value, units));
        } catch (Exception e) {
            doseQuantity.setNullFlavor(NullFlavor.NA);
        }
        return doseQuantity;
    }

    public IVL_PQ createBaseIVL_PQ() {

        return DatatypesFactory.eINSTANCE.createIVL_PQ();
    }

    public CD createBaseCodeCD(String code, NullFlavor... nullFlavor) {
        CD cd = this.createBaseCodeCD(code, null, null, null, null);
        if (nullFlavor.length > 0)
            cd.setNullFlavor(NullFlavor.NA);
        return cd;
    }

    public CD createBaseCodeCD(String code, String codeSystem, String codeSystemName, String displayName, ED originalText) {

        CD codedDefinition = DatatypesFactory.eINSTANCE.createCD();

        if (code == null) {
            codedDefinition.setNullFlavor(NullFlavor.NA);
            return codedDefinition;
        }

        codedDefinition.setCode(code);
        codedDefinition.setCodeSystem(codeSystem);
        codedDefinition.setCodeSystemName(codeSystemName);
        codedDefinition.setDisplayName(displayName);
        if (originalText != null)
            codedDefinition.setOriginalText(originalText);
        return codedDefinition;
    }

    public CE createMaterialCE(CE codedElement, List<CD> translations) {

        for (CD t : translations)
            codedElement.getTranslations().add(t);
        return codedElement;
    }

    public CE createBaseCodeCE(String code, NullFlavor... nullFlavor) {
        CE ce = this.createBaseCodeCE(code, null, null, null, null);
        if (nullFlavor.length > 0)
            ce.setNullFlavor(NullFlavor.NA);
        return ce;
    }

    public CE createBaseCodeCE(String code, String codeSystem, String codeSystemName, String displayName, ED originalText) {

        CE codedElement = DatatypesFactory.eINSTANCE.createCE();

        if (code == null)
            codedElement.setNullFlavor(NullFlavor.NA);
        else
            codedElement.setCode(code);

        if (codeSystem != null)
            codedElement.setCodeSystem(codeSystem);
        if (codeSystemName != null)
            codedElement.setCodeSystemName(codeSystemName);
        if (displayName != null)
            codedElement.setDisplayName(displayName);
        if (originalText != null)
            codedElement.setOriginalText(originalText);
        return codedElement;
    }

    public II createBaseRootII(String extension, String root, String assigningAuthorityName) {

        II id = DatatypesFactory.eINSTANCE.createII();

        if (extension != null)
            id.setExtension(extension);

        if (assigningAuthorityName != null)
            id.setAssigningAuthorityName(assigningAuthorityName);

        if (root != null)
            id.setRoot(root);
        else
            id.setRoot(new OIDUtil().getOID(false));

        return id;
    }

    public ED createBaseED(String value, String reference) {
        return this.createBaseED(value, null, null, reference);
    }

    private ED createBaseED(String value, String mediaType, BinaryDataEncoding representation, String reference) {

        ED ed = DatatypesFactory.eINSTANCE.createED();

        if (value != null)
            ed.addText(value);
        else if (reference == null) {
            ed.setNullFlavor(NullFlavor.NA);
            return ed;
        }

        if (mediaType != null)
            ed.setMediaType(mediaType);

        if (representation != null)
            ed.setRepresentation(BinaryDataEncoding.B64);

        if (reference != null)
            ed.setReference(this.createBaseTEL(reference));
        return ed;
    }

    public ST createBaseST(String value) {

        ST st = DatatypesFactory.eINSTANCE.createST();
        if (value != null)
            st.addText(value);
        else
            st.setNullFlavor(NullFlavor.NI);
        return st;
    }

    private ADXP createBaseADXP(String text) {

        ADXP adxp = DatatypesFactory.eINSTANCE.createADXP();
        if (text == null)
            adxp.setNullFlavor(NullFlavor.NI);
        else
            adxp.addText(text);
        return adxp;
    }

    private AD createBaseAddress(String country, String city, String postalCode, String streetAddressLine, String state) {

        AD addr = DatatypesFactory.eINSTANCE.createAD();
        try {
            addr.getCountries().add(this.createBaseADXP(country));
            addr.getCities().add(this.createBaseADXP(city));
            addr.getPostalCodes().add(this.createBaseADXP(postalCode));
            addr.getStreetAddressLines().add(this.createBaseADXP(streetAddressLine));
            addr.getStates().add(this.createBaseADXP(state));
        } catch (Exception e) {
            return addr;
        }
        return addr;
    }

    private Organization createBaseOrganization(String organizationName, String organizationExtension, String country, String city,
                                                String postalCode, String streetAddressLine, String state) {

        Organization organization = CDAFactory.eINSTANCE.createOrganization();
        try {
            organization.getIds().add(this.createBaseRootII(organizationExtension, null, null));
            organization.getNames().add(this.createBaseOrganizationName(organizationName));
            organization.getAddrs().add(this.createBaseAddress(country, city, postalCode, streetAddressLine, state));
        } catch (Exception e) {
            organization.setNullFlavor(NullFlavor.NI);
        }
        return organization;
    }

    private SDTCPatient createBaseSDTCPatient(String extension, String root, String assigningAuthorityName) {

        SDTCPatient sdtcPatient = CDAFactory.eINSTANCE.createSDTCPatient();
        sdtcPatient.setId(this.createBaseRootII(extension, root, assigningAuthorityName));
        return sdtcPatient;
    }

    public AssignedEntity createBaseAssignedEntity(String extension, String root, String assigningAuthorityName, String personFamilyName,
                                                   String personGivenName, String personSuffixName, String personPrefixName, String organizationName, String organizationExtension, String clinicalRecordIdentifier,
                                                   String country, String city, String postalCode, String streetAddressLine, String state, boolean ignoreSDTCPatient) {

        AssignedEntity assignedEntity = CDAFactory.eINSTANCE.createAssignedEntity();
        try {
            assignedEntity.setClassCode(RoleClassAssignedEntity.ASSIGNED);
            assignedEntity.getIds().add(this.createBaseRootII(extension, root, assigningAuthorityName));
            assignedEntity.getRepresentedOrganizations().add(
                    this.createBaseOrganization(organizationName, organizationExtension, country, city, postalCode, streetAddressLine, state));
            assignedEntity.setAssignedPerson(this.createBasePerson(personFamilyName, personGivenName, personSuffixName, personPrefixName));
            if (!ignoreSDTCPatient) {
                assignedEntity.setSDTCPatient(this.createBaseSDTCPatient(clinicalRecordIdentifier, null, null));
            }
        } catch (Exception e) {
            assignedEntity.setNullFlavor(NullFlavor.NI);
        }
        return assignedEntity;
    }

    private Performer1 createBasePerformer1(String performerFunctionCode, String performerFunctionDisplayName, String performerLowValue,
                                            String performerHighValue, String extension, String root, String assigningAuthorityName, String personFamilyName, String personGivenName,
                                            String personSuffixName, String personPrefixName, String organizationName, String organizationExtension, String clinicalRecordIdentifier, String country,
                                            String city, String postalCode, String streetAddressLine, String state) {

        Performer1 performer = CDAFactory.eINSTANCE.createPerformer1();
        try {
            performer.setTypeCode(x_ServiceEventPerformer.PRF);
            if (performerFunctionCode != null)
                performer.setFunctionCode(this.createBaseCodeCE(performerFunctionCode, null, null, performerFunctionDisplayName, null));
            performer.setTime(this.createBaseEffectiveTimeIVL_TS(performerLowValue, performerHighValue));
            performer.setAssignedEntity(this.createBaseAssignedEntity(extension, root, assigningAuthorityName, personFamilyName, personGivenName,
                    personSuffixName, personPrefixName, organizationName, organizationExtension, clinicalRecordIdentifier, country, city, postalCode,
                    streetAddressLine, state, false));
        } catch (Exception e) {
            performer.setNullFlavor(NullFlavor.NI);
        }
        return performer;
    }

    private ServiceEvent createBaseServiceEvent(String serviceEventLowValue, String serviceEventHighValue, String performerLowValue,
                                                String performerHighValue, String performerFunctionCode, String performerFunctionDisplayName, String extension, String root,
                                                String assigningAuthorityName, String personFamilyName, String personGivenName, String personSuffixName, String personPrefixName, String organizationName,
                                                String organizationExtension, String clinicalRecordIdentifier, String country, String city, String postalCode, String streetAddressLine,
                                                String state) {

        ServiceEvent serviceEvent = CDAFactory.eINSTANCE.createServiceEvent();
        try {
            serviceEvent.setClassCode(ActClassRoot.PCPR);
            serviceEvent.setEffectiveTime(this.createBaseEffectiveTimeIVL_TS(serviceEventLowValue, serviceEventHighValue));
            serviceEvent.getPerformers().add(
                    this.createBasePerformer1(performerFunctionCode, performerFunctionDisplayName, performerLowValue, performerHighValue, extension,
                            root, assigningAuthorityName, personFamilyName, personGivenName, personSuffixName, personPrefixName, organizationName,
                            organizationExtension, clinicalRecordIdentifier, country, city, postalCode, streetAddressLine, state));
        } catch (Exception e) {
            serviceEvent.setNullFlavor(NullFlavor.NI);
        }
        return serviceEvent;
    }

    public DocumentationOf createBaseDocumentationOf(String serviceEventLowValue, String serviceEventHighValue, String performerLowValue,
                                                     String performerHighValue, String performerFunctionCode, String performerFunctionDisplayName, String extension, String root,
                                                     String assigningAuthorityName, String personFamilyName, String personGivenName, String personSuffixName, String personPrefixName, String organizationName,
                                                     String organizationExtension, String clinicalRecordIdentifier, String country, String city, String postalCode, String streetAddressLine,
                                                     String state) {

        DocumentationOf documentationOf = CDAFactory.eINSTANCE.createDocumentationOf();
        documentationOf.setServiceEvent(this.createBaseServiceEvent(serviceEventLowValue, serviceEventHighValue, performerLowValue,
                performerHighValue, performerFunctionCode, performerFunctionDisplayName, extension, root, assigningAuthorityName, personFamilyName,
                personGivenName, personSuffixName, personPrefixName, organizationName, organizationExtension, clinicalRecordIdentifier, country, city, postalCode,
                streetAddressLine, state));
        return documentationOf;
    }

    public RelatedDocument createBaseRelatedDocument(String parentId, long parentVersion) {

        RelatedDocument relatedDocument = null;
        if (StringUtils.isNotEmpty(parentId)) {
            relatedDocument = CDAFactory.eINSTANCE.createRelatedDocument();
            relatedDocument.setTypeCode(x_ActRelationshipDocument.APND);
            ParentDocument parentDocument = CDAFactory.eINSTANCE.createParentDocument();
            parentDocument.getIds().add(DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.19.5", parentId));
            INT parentVersionNumber = DatatypesFactory.eINSTANCE.createINT();
            parentVersionNumber.setValue(BigInteger.valueOf(parentVersion));
            parentDocument.setVersionNumber(parentVersionNumber);
            relatedDocument.setParentDocument(parentDocument);
        }
        return relatedDocument;
    }

    public CE createPlayingEntityCodeCE(String code, String codeSystem, String codeSystemName, String displayName, ED originalText,
                                        CD... translations) {

        CE ce = this.createBaseCodeCE(code, codeSystem, codeSystemName, displayName, originalText);

        for (CD cd : translations)
            ce.getTranslations().add(cd);

        return ce;

    }

    public Component2 createBaseNonXMLComponent(String text) {

        Component2 component2 = CDAFactory.eINSTANCE.createComponent2();
        NonXMLBody body = CDAFactory.eINSTANCE.createNonXMLBody();
        body.setClassCode(ActClass.DOCBODY);
        body.setMoodCode(ActMood.EVN);
        body.setText(this.createBaseED(text, "application/pdf", BinaryDataEncoding.B64, null));
        component2.setNonXMLBody(body);

        return component2;
    }

    public CE createBaseAdministrativeGenderCode(String gender) {

        CE administrativeGenderCode = DatatypesFactory.eINSTANCE.createCE();
        administrativeGenderCode.setCode(gender);
        administrativeGenderCode.setCodeSystem("2.16.840.1.113883.5.1");
        administrativeGenderCode.setCodeSystemName("HL7:AdministrativeGender");
        administrativeGenderCode.setDisplayName((gender.equals("M")) ? "Masculino" : (gender.equals("F") ? "Feminino" : "Desconhecido"));

        return administrativeGenderCode;
    }

    public CD createProblemCodeCD(String code, String codeSystemName, String displayName) {

        CD codedDefinition = DatatypesFactory.eINSTANCE.createCD();

        if (code == null) {
            codedDefinition.setNullFlavor(NullFlavor.NA);
            return codedDefinition;
        }

        codedDefinition.setCode(code);
        codedDefinition.setCodeSystem((codeSystemName.equals("I9C")) ? "2.16.840.1.113883.6.2"
                : (codeSystemName.equals("ICNP")) ? "2.16.840.1.113883.6.97" : (codeSystemName.equals("ICPC")) ? "2.16.840.1.113883.6.139" : "");
        codedDefinition.setCodeSystemName((codeSystemName.equals("I9C")) ? "ICD-9CM" : (codeSystemName.equals("ICNP")) ? "ICNP" : (codeSystemName
                .equals("ICPC")) ? "ICPC2E" : "");
        codedDefinition.setDisplayName(displayName);

        return codedDefinition;
    }

    public Organization createBaseProviderOrganization(String extension, String organizationName) {
        Organization providerOrganization = CDAFactory.eINSTANCE.createOrganization();
        providerOrganization.getIds().add(this.createBaseRootII(extension, "2.16.840.1.113883.19.5", null));
        providerOrganization.getNames().add(this.createBaseOrganizationName(organizationName));

        return providerOrganization;
    }

    private AssociatedEntity createBaseAssociatedEntity(RoleClassAssociative role, String personFamilyName, String personGivenName,
                                                        String personSuffixName, String personPrefixName, String country, String city, String postalCode, String streetAddressLine, String state) {
        AssociatedEntity associatedEntity = CDAFactory.eINSTANCE.createAssociatedEntity();
        try {
            associatedEntity.setClassCode(role);
            associatedEntity.getAddrs().add(this.createBaseAddress(country, city, postalCode, streetAddressLine, state));
            associatedEntity.getTelecoms().add(this.createBaseTEL(null));
            associatedEntity.setAssociatedPerson(this.createBasePerson(personFamilyName, personGivenName, personSuffixName, personPrefixName));
        } catch (Exception e) {
            associatedEntity.setNullFlavor(NullFlavor.NI);
        }
        return associatedEntity;
    }

    private TEL createBaseTEL(String value) {

        TEL tel = DatatypesFactory.eINSTANCE.createTEL();

        if (value == null)
            tel.setNullFlavor(NullFlavor.NI);
        else
            tel.setValue(value);
        return tel;
    }

    public Participant1 createBaseParticipant(String personFamilyName, String personGivenName, String personSuffixName, String personPrefixName, String country, String city,
                                              String postalCode, String streetAddressLine, String state) {

        Participant1 participant = CDAFactory.eINSTANCE.createParticipant1();
        try {
            participant.setTypeCode(ParticipationType.IND);
            participant.setFunctionCode(this
                    .createBaseCodeCE("PCP", "2.16.840.1.113883.5.88", "HL7 ParticipationFunction", "Médico de Família", null));
            participant.setAssociatedEntity(this.createBaseAssociatedEntity(RoleClassAssociative.PRS, personFamilyName, personGivenName,
                    personSuffixName, personPrefixName, country, city, postalCode, streetAddressLine, state));
        } catch (Exception e) {
            participant.setNullFlavor(NullFlavor.NI);
        }
        return participant;
    }

    public String getPrettyPrintDate(Object dateTime, String oldFormat, String newFormat) {
        try {
            if (dateTime == null)
                return null;

            if (dateTime instanceof IVL_TS) {
                if (((IVL_TS) dateTime).getValue() == null)
                    return null;
            } else if (dateTime instanceof IVXB_TS) {
                if (((IVXB_TS) dateTime).getValue() == null)
                    return null;
            } else if (dateTime instanceof SXCM_TS) {
                if (((SXCM_TS) dateTime).getValue() == null)
                    return null;
            } else
                return null;

            if (dateTime instanceof IVL_TS)
                return new SimpleDateFormat(newFormat).format(new SimpleDateFormat(oldFormat).parse(((IVL_TS) dateTime).getValue()));
            if (dateTime instanceof IVXB_TS)
                return new SimpleDateFormat(newFormat).format(new SimpleDateFormat(oldFormat).parse(((IVXB_TS) dateTime).getValue()));

            if (dateTime instanceof SXCM_TS)
                return new SimpleDateFormat(newFormat).format(new SimpleDateFormat(oldFormat).parse(((SXCM_TS) dateTime).getValue()));

        } catch (ParseException e) {
            return null;
        }
        return null;
    }

    public EN createBaseEN(String value) {

        EN en = DatatypesFactory.eINSTANCE.createEN();
        if (value == null)
            en.setNullFlavor(NullFlavor.NA);
        else
            en.addText(value);
        return en;
    }

    public Author createBaseEmptyAuthor() {
        Author author = CDAFactory.eINSTANCE.createAuthor();
        AssignedAuthor assignedAuthor = CDAFactory.eINSTANCE.createAssignedAuthor();
        author.setAssignedAuthor(assignedAuthor);
        assignedAuthor.setNullFlavor(NullFlavor.NI);
        return author;
    }

    public CE createBaseCodeCE(String code, String codeSystem, String codeSystemName, String displayName, ED originalText, List<CD> translations) {

        CE codedElement = DatatypesFactory.eINSTANCE.createCE();

        if (code == null)
            codedElement.setNullFlavor(NullFlavor.NA);
        else
            codedElement.setCode(code);

        if (codeSystem != null)
            codedElement.setCodeSystem(codeSystem);
        if (codeSystemName != null)
            codedElement.setCodeSystemName(codeSystemName);
        if (displayName != null)
            codedElement.setDisplayName(displayName);
        if (originalText != null)
            codedElement.setOriginalText(originalText);

        for (CD t : translations)
            codedElement.getTranslations().add(t);

        return codedElement;
    }
}