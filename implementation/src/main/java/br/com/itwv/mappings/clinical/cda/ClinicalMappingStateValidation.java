package br.com.itwv.mappings.clinical.cda;

import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.types.CDADataTypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.CS;

import static br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping.x_DocEntryStatusCode;

public class ClinicalMappingStateValidation {

    private static ClinicalMappingStateValidation instance = null;

    protected ClinicalMappingStateValidation() {
    }

    public static ClinicalMappingStateValidation getInstance() {
        if (instance == null) {
            instance = new ClinicalMappingStateValidation();
        }
        return instance;
    }

    public boolean validateEntryStatusCode(x_DocEntryStatusCode x_DocEntryStatusCode, String statusCode) {

        switch (x_DocEntryStatusCode) {
            case CANCELLED:
                return ClinicalMappingStateValidation.inStatusCode(IClinicalMapping.x_DocEntryStatusCode.valueOf(statusCode),
                        IClinicalMapping.x_DocEntryStatusCode.COMPLETED, IClinicalMapping.x_DocEntryStatusCode.ACTIVE,
                        IClinicalMapping.x_DocEntryStatusCode.HELD);
            case ABORTED:
                return ClinicalMappingStateValidation.inStatusCode(IClinicalMapping.x_DocEntryStatusCode.valueOf(statusCode),
                        IClinicalMapping.x_DocEntryStatusCode.HELD);
            case ACTIVE:
                return ClinicalMappingStateValidation.inStatusCode(IClinicalMapping.x_DocEntryStatusCode.valueOf(statusCode),
                        IClinicalMapping.x_DocEntryStatusCode.CANCELLED, IClinicalMapping.x_DocEntryStatusCode.ABORTED,
                        IClinicalMapping.x_DocEntryStatusCode.HELD);
            case HELD:
                return ClinicalMappingStateValidation.inStatusCode(IClinicalMapping.x_DocEntryStatusCode.valueOf(statusCode),
                        IClinicalMapping.x_DocEntryStatusCode.COMPLETED, IClinicalMapping.x_DocEntryStatusCode.CANCELLED,
                        IClinicalMapping.x_DocEntryStatusCode.ABORTED, IClinicalMapping.x_DocEntryStatusCode.ACTIVE);
            case SUSPENDED:
                return ClinicalMappingStateValidation.inStatusCode(IClinicalMapping.x_DocEntryStatusCode.valueOf(statusCode),
                        IClinicalMapping.x_DocEntryStatusCode.COMPLETED, IClinicalMapping.x_DocEntryStatusCode.ACTIVE);
            case OBSOLETE:
                return ClinicalMappingStateValidation.inStatusCode(IClinicalMapping.x_DocEntryStatusCode.valueOf(statusCode),
                        IClinicalMapping.x_DocEntryStatusCode.COMPLETED, IClinicalMapping.x_DocEntryStatusCode.ACTIVE,
                        IClinicalMapping.x_DocEntryStatusCode.HELD);
            default:
                return false;
        }
    }

    protected static boolean inStatusCode(x_DocEntryStatusCode x_DocEntryStatusCode, x_DocEntryStatusCode... l) {

        for (x_DocEntryStatusCode i : l)
            if (i.equals(x_DocEntryStatusCode))
                return true;
        return false;
    }

    public IClinicalMapping.x_DocEntryMoodCode computeEntryMoodCode(x_DocEntryStatusCode x_DocEntryStatusCode) {

        switch (x_DocEntryStatusCode) {
            case SUSPENDED:
                return IClinicalMapping.x_DocEntryMoodCode.INT;
            default:
                return IClinicalMapping.x_DocEntryMoodCode.EVN;
        }
    }

    public CS computeEntryStatusCode(x_DocEntryStatusCode x_DocEntryStatusCode, CS statusCode) {

        IClinicalMapping.x_DocEntryStatusCode x = null;

        switch (x_DocEntryStatusCode) {
            case CANCELLED:
            case ABORTED:
                switch (IClinicalMapping.x_DocEntryStatusCode.valueOf(statusCode.getOriginalText().getText())) {
                    case ACTIVE:
                    case COMPLETED:
                        x = IClinicalMapping.x_DocEntryStatusCode.CANCELLED;
                        break;
                    case HELD:
                        x = IClinicalMapping.x_DocEntryStatusCode.ABORTED;
                        break;
                    default:
                        x = x_DocEntryStatusCode;
                        break;
                }
                break;
            case OBSOLETE:
                x = IClinicalMapping.x_DocEntryStatusCode.valueOf(statusCode.getOriginalText().getText());
                break;
            default:
                x = x_DocEntryStatusCode;
                break;
        }
        return CDADataTypesFactory.getInstance().createBaseStatusCodeCS(x.name().toLowerCase());
    }
}
