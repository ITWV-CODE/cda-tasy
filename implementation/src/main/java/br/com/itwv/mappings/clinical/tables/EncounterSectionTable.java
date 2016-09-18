package br.com.itwv.mappings.clinical.tables;

import br.com.itwv.cdatasy.base.html.templates.collections.EncounterCollection;
import br.com.itwv.cdatasy.base.html.templates.objects.Encounter;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.types.CDADataTypesFactory;
import org.openhealthtools.mdht.uml.cda.ccd.EncountersSection;

public class EncounterSectionTable {
    private static EncounterSectionTable instance = null;

    protected EncounterSectionTable() {
    }

    public static EncounterSectionTable getInstance() {
        if (instance == null) {
            instance = new EncounterSectionTable();
        }
        return instance;
    }

    public String getTable(EncountersSection encountersSection) {

        EncounterCollection encounterCollection = new EncounterCollection();

        for (org.openhealthtools.mdht.uml.cda.Encounter enc : encountersSection.getEncounters()) {

            switch (IClinicalMapping.x_DocEntryStatusCode.valueOf(enc.getStatusCode().getCode().toUpperCase())) {
                case ACTIVE:
                case NEW:
                case COMPLETED: {

                    Encounter encounter = new Encounter();
                    encounter.setDate(CDADataTypesFactory.getInstance().getPrettyPrintDate(enc.getEffectiveTime().getLow(),
                            "yyyyMMdd", "yyyy/MM/dd"));
                    encounter.setDescription(enc.getCode().getDisplayName());
                    encounter.setLocation(enc.getParticipants().get(0).getParticipantRole().getPlayingEntity().getNames().get(0).getText());
                    encounterCollection.add(encounter);
                    break;
                }
                default:
                    break;
            }
        }
        if (encounterCollection.size() == 0)
            return "<content ID=\"surgeries\">Sem atendimentos.</content>";
        return encounterCollection.toString();
    }
}
