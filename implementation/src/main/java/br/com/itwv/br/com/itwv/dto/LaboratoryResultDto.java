package br.com.itwv.br.com.itwv.dto;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by itwv_2 on 03/10/2016.
 */
public class LaboratoryResultDto extends Dto implements Serializable {

    private TermCodedValueDto batteryTest;
    private List<LaboratoryResultTestDto> tests;

    public LaboratoryResultDto(String id) {
        super(id);
    }

    public TermCodedValueDto getBatteryTest() {
        return batteryTest;
    }

    public void setBatteryTest(TermCodedValueDto batteryTest) {
        this.batteryTest = batteryTest;
    }

    public List<LaboratoryResultTestDto> getTests() {
        if (tests == null) {
            tests = new ArrayList<LaboratoryResultTestDto>();
        }
        return tests;
    }

    public void setTests(List<LaboratoryResultTestDto> tests) {
        this.tests = tests;
    }

    public static class LaboratoryResultTestDto extends Dto implements Serializable {

        private TermCodedValueDto test;
        private String date;
        private String value;
        private TermCodedValueDto units;
        private TermCodedValueDto interpretationValue;
        private String lowerReferenceValue;
        private String higherReferenceValue;

        public LaboratoryResultTestDto(String id) {
            super(id);
        }

        public TermCodedValueDto getTest() {
            return test;
        }

        public void setTest(TermCodedValueDto test) {
            this.test = test;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public TermCodedValueDto getUnits() {
            return units;
        }

        public void setUnits(TermCodedValueDto units) {
            this.units = units;
        }

        public TermCodedValueDto getInterpretationValue() {
            return interpretationValue;
        }

        public void setInterpretationValue(TermCodedValueDto interpretationValue) {
            this.interpretationValue = interpretationValue;
        }

        public String getLowerReferenceValue() {
            return lowerReferenceValue;
        }

        public void setLowerReferenceValue(String lowerReferenceValue) {
            this.lowerReferenceValue = lowerReferenceValue;
        }

        public String getHigherReferenceValue() {
            return higherReferenceValue;
        }

        public void setHigherReferenceValue(String higherReferenceValue) {
            this.higherReferenceValue = higherReferenceValue;
        }
    }
}
