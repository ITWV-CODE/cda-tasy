package br.com.itwv.mappings.document;

import br.com.itwv.br.com.itwv.dto.AuthorDto;
import br.com.itwv.cdatasy.base.encoding.streams.OIDUtil;
import br.com.itwv.cdatasy.base.util.ExtendedDateUtils;
import br.com.itwv.cdatasy.common.business.interop.entities.Dto;
import br.com.itwv.cdatasy.common.business.interop.mappings.types.CDADataTypesFactory;
import org.openhealthtools.mdht.uml.cda.*;
import org.openhealthtools.mdht.uml.hl7.datatypes.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public abstract class DocumentMappingBase {

    public DocumentMappingBase() {
    }

    protected static ClinicalDocument mapDocumentProperties(ClinicalDocument doc)
            throws Exception {
        try {

            TS effectiveTime = DatatypesFactory.eINSTANCE.createTS(new SimpleDateFormat("yyyyMMddHHmmssZ").format(new Timestamp(new java.util.Date().getTime())));
            doc.setEffectiveTime(effectiveTime);

            II id = DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.19.5", new OIDUtil().getUUID().toString());
            doc.setId(id);

            CE confidentialityCode = DatatypesFactory.eINSTANCE.createCE("R", "2.16.840.1.113883.5.25");
            doc.setConfidentialityCode(confidentialityCode);

            ST title = DatatypesFactory.eINSTANCE.createST("Sumário Clínico");
            doc.setTitle(title);

            CE code = DatatypesFactory.eINSTANCE.createCE("60591-5", "2.16.840.1.113883.6.1", "LOINC", "Patient Summary");
            doc.setCode(code);

            InfrastructureRootTypeId typeId = CDAFactory.eINSTANCE.createInfrastructureRootTypeId();
            typeId.setRoot("2.16.840.1.113883.1.3");
            typeId.setExtension("POCD_HD000040");
            doc.setTypeId(typeId);

            doc.getTemplateIds().clear();
            doc.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "2.16.840.1.113883.10.20.1", null));

            INT versionNumber = DatatypesFactory.eINSTANCE.createINT();
            versionNumber.setValue(1);
            doc.setVersionNumber(versionNumber);

            CS languageCode = DatatypesFactory.eINSTANCE.createCS("pt-BR");
            doc.setLanguageCode(languageCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    protected static ClinicalDocument mapDocumentAuthor(ClinicalDocument doc, final List<Dto> dto) throws Exception {
        try {

            final AuthorDto author = (AuthorDto) dto.get(0);

            doc.getAuthors().clear();
            doc.getAuthors().add(
                    CDADataTypesFactory.getInstance().createBaseAuthor(
                            //new SimpleDateFormat("yyyyMMddHHmmssZ").format(new Timestamp(new java.util.Date().getTime())),
                            ExtendedDateUtils.getUTCDateFormatted(ExtendedDateUtils.CDA_DATE_TIME_FORMAT),
                            author.getId(),
                            "2.16.840.1.113883.19.5", null,
                            author.getFamilyName(),
                            author.getGivenName(),
                            author.getPrefix(), null, null, "BR",
                            null, null, null, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    protected static ClinicalDocument mapDocumentCustodian(final ClinicalDocument doc, final List<Dto> dto)
            throws Exception {
        try {
            final AuthorDto author = (AuthorDto) dto.get(0);
            Custodian custodian = CDAFactory.eINSTANCE.createCustodian();
            AssignedCustodian assignedCustodian = CDAFactory.eINSTANCE.createAssignedCustodian();
            CustodianOrganization custodianOrganization = CDAFactory.eINSTANCE.createCustodianOrganization();
            assignedCustodian.setRepresentedCustodianOrganization(custodianOrganization);
            custodian.setAssignedCustodian(assignedCustodian);
            doc.setCustodian(custodian);
            custodianOrganization.getIds().add(DatatypesFactory.eINSTANCE.createII("2.16.840.1.113883.19.5", author.getInstitution().getCode()));
            ON custodianOrganizationName = DatatypesFactory.eINSTANCE.createON();
            custodianOrganizationName.addText(author.getInstitution().getDescription());
            custodianOrganization.setName(custodianOrganizationName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

/*
    protected static ClinicalDocument mapDocumentParticipant(PD1 pd1, ORC orc, ClinicalDocument doc) throws Exception {
        try {
            doc.getParticipants().clear();
            if (pd1 != null && pd1.getPd14_PatientPrimaryCareProviderNameIDNo().length > 0)
                doc.getParticipants().add(
                        CDADataTypesFactory.getInstance().createBaseParticipant(
                                HL7DataTypesFactory.getInstance().XCNtoFamilyName(pd1.getPd14_PatientPrimaryCareProviderNameIDNo(0)),
                                HL7DataTypesFactory.getInstance().XCNtoGivenName(pd1.getPd14_PatientPrimaryCareProviderNameIDNo(0)),
                                HL7DataTypesFactory.getInstance().XCNtoDegree(pd1.getPd14_PatientPrimaryCareProviderNameIDNo(0)), "PT", null, null,
                                null, null));
            else
                doc.getParticipants().add(CDADataTypesFactory.getInstance().createBaseParticipant(null, null, null, "PT", null, null, null, null));
        } catch (Exception e) {
            throw MapDocumentAuthorsException.getInstance().definesException(TraceHelper.getMethodName(0), e.getMessage(), false,
                    LoggingSeverity.ERROR);
        }
        return doc;
    }


    protected static ClinicalDocument mapDocumentDocumentOf(ORC orc, ClinicalDocument doc) throws Exception {

        try {
            doc.getDocumentationOfs().clear();
            doc.getDocumentationOfs().add(
                    CDADataTypesFactory.getInstance().createBaseDocumentationOf(orc.getOrc9_DateTimeOfTransaction().getTs1_Time().getValue(),
                            orc.getOrc9_DateTimeOfTransaction().getTs1_Time().getValue(),
                            orc.getOrc9_DateTimeOfTransaction().getTs1_Time().getValue(),
                            orc.getOrc9_DateTimeOfTransaction().getTs1_Time().getValue(), null,
                            HL7DataTypesFactory.getInstance().XCNtoDegree(orc.getOrc10_EnteredBy()[0]),
                            HL7DataTypesFactory.getInstance().XCNtoId(orc.getOrc10_EnteredBy()[0]), "2.16.840.1.113883.19.5", null,
                            HL7DataTypesFactory.getInstance().XCNtoFamilyName(orc.getOrc10_EnteredBy()[0]),
                            HL7DataTypesFactory.getInstance().XCNtoGivenName(orc.getOrc10_EnteredBy()[0]),
                            HL7DataTypesFactory.getInstance().XCNtoDegree(orc.getOrc10_EnteredBy()[0]),
                            orc.getOrc17_EnteringOrganization().getCe2_Text().getValue(),
                            orc.getOrc17_EnteringOrganization().getCe1_Identifier().getValue(),
                            orc.getOrc2_PlacerOrderNumber().getEi1_EntityIdentifier().getValue(), "PT", null, null, null, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    protected static ClinicalDocument mapDocumentDocumentOf(MSH msh, IAM iam, ClinicalDocument doc) throws Exception {

        try {
            doc.getDocumentationOfs().clear();
            doc.getDocumentationOfs().add(
                    CDADataTypesFactory.getInstance().createBaseDocumentationOf(iam.getIam13_ReportedDateTime().getTs1_Time().getValue(),
                            iam.getIam13_ReportedDateTime().getTs1_Time().getValue(), iam.getIam13_ReportedDateTime().getTs1_Time().getValue(),
                            iam.getIam13_ReportedDateTime().getTs1_Time().getValue(), null,
                            HL7DataTypesFactory.getInstance().XPNtoDegree(iam.getIam14_ReportedBy()),
                            HL7DataTypesFactory.getInstance().XPNtoId(iam.getIam14_ReportedBy()), "2.16.840.1.113883.19.5", null,
                            HL7DataTypesFactory.getInstance().XPNtoFamilyName(iam.getIam14_ReportedBy()),
                            HL7DataTypesFactory.getInstance().XPNtoGivenName(iam.getIam14_ReportedBy()),
                            HL7DataTypesFactory.getInstance().XPNtoDegree(iam.getIam14_ReportedBy()),
                            iam.getIam7_AllergyUniqueIdentifier().getEi3_UniversalID().getValue(),
                            iam.getIam7_AllergyUniqueIdentifier().getEi2_NamespaceID().getValue(),
                            iam.getIam7_AllergyUniqueIdentifier().getEi1_EntityIdentifier().getValue(), "PT", null, null, null, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    protected static ClinicalDocument mapDocumentDocumentOf(MSH msh, PRB prb, ROL rol, ClinicalDocument doc) throws Exception {

        try {
            doc.getDocumentationOfs().clear();
            doc.getDocumentationOfs().add(
                    CDADataTypesFactory.getInstance().createBaseDocumentationOf(prb.getPrb2_ActionDateTime().getTs1_Time().getValue(),
                            prb.getPrb2_ActionDateTime().getTs1_Time().getValue(), prb.getPrb2_ActionDateTime().getTs1_Time().getValue(),
                            prb.getPrb2_ActionDateTime().getTs1_Time().getValue(), null,
                            HL7DataTypesFactory.getInstance().XCNtoDegree(rol.getRol4_RolePerson(0)),
                            HL7DataTypesFactory.getInstance().XCNtoId(rol.getRol4_RolePerson(0)), "2.16.840.1.113883.19.5", null,
                            HL7DataTypesFactory.getInstance().XCNtoFamilyName(rol.getRol4_RolePerson(0)),
                            HL7DataTypesFactory.getInstance().XCNtoGivenName(rol.getRol4_RolePerson(0)),
                            HL7DataTypesFactory.getInstance().XCNtoDegree(rol.getRol4_RolePerson(0)),
                            rol.getRol4_RolePerson(0).getXcn14_AssigningFacility().getHd2_UniversalID().getValue(),
                            rol.getRol4_RolePerson(0).getXcn14_AssigningFacility().getHd1_NamespaceID().getValue(),
                            prb.getPrb4_ProblemInstanceID().getEi1_EntityIdentifier().getValue(), "PT", null, null, null, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }*/
}
