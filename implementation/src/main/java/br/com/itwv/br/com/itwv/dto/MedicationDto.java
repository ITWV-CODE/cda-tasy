package br.com.itwv.br.com.itwv.dto;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;

/**
 * Created by itwv_2 on 20/06/2016.
 */
public class MedicationDto extends Dto {

    private String date;
    private String dosage;
    private TermCodedValueDto route;
    private String dose;
    private TermCodedValueDto unit;
    private TermCodedValueDto medicine;

    public MedicationDto(String id) {
        super(id);
    }

    public TermCodedValueDto getMedicine() {
        return medicine;
    }

    public void setMedicine(TermCodedValueDto medicine) {
        this.medicine = medicine;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public TermCodedValueDto getRoute() {
        return route;
    }

    public void setRoute(TermCodedValueDto route) {
        this.route = route;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public TermCodedValueDto getUnit() {
        return unit;
    }

    public void setUnit(TermCodedValueDto unit) {
        this.unit = unit;
    }
}