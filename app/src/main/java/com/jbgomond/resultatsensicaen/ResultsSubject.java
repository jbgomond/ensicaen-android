package com.jbgomond.resultatsensicaen;

public class ResultsSubject {
    private String name;
    private String code;
    private Float nbHoursCours;
    private Float nbHoursTD;
    private Float nbHoursTP;
    private Float coefficientPartiel;
    private Float coefficientCours;
    private Float coefficientTP;
    private Float markPartiel;
    private Float markCours;
    private Float markTP;

    public ResultsSubject() {}

    public ResultsSubject(String name, String code, Float nbHoursCours, Float nbHoursTD, Float nbHoursTP, Float coefficientPartiel, Float coefficientCours, Float coefficientTP, Float markPartiel, Float markCours, Float markTP) {
        this.name = name;
        this.code = code;
        this.nbHoursCours = nbHoursCours;
        this.nbHoursTD = nbHoursTD;
        this.nbHoursTP = nbHoursTP;
        this.coefficientPartiel = coefficientPartiel;
        this.coefficientCours = coefficientCours;
        this.coefficientTP = coefficientTP;
        this.markPartiel = markPartiel;
        this.markCours = markCours;
        this.markTP = markTP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getNbHoursCours() {
        return nbHoursCours;
    }

    public void setNbHoursCours(String nbHoursCours) {
        this.nbHoursCours = convertEntValuesToFloat(nbHoursCours);
    }

    public Float getNbHoursTD() {
        return nbHoursTD;
    }

    public void setNbHoursTD(String nbHoursTD) {
        this.nbHoursTD = convertEntValuesToFloat(nbHoursTD);
    }

    public Float getNbHoursTP() {
        return nbHoursTP;
    }

    public void setNbHoursTP(String nbHoursTP) {
        this.nbHoursTP = convertEntValuesToFloat(nbHoursTP);
    }

    public Float getCoefficientPartiel() {
        return coefficientPartiel;
    }

    public void setCoefficientPartiel(String coefficientPartiel) {
        this.coefficientPartiel = convertEntValuesToFloat(coefficientPartiel);
    }

    public Float getCoefficientCours() {
        return coefficientCours;
    }

    public void setCoefficientCours(String coefficientCours) {
        this.coefficientCours = convertEntValuesToFloat(coefficientCours);
    }

    public Float getCoefficientTP() {
        return coefficientTP;
    }

    public void setCoefficientTP(String coefficientTP) {
        this.coefficientTP = convertEntValuesToFloat(coefficientTP);
    }

    public Float getMarkPartiel() {
        return markPartiel;
    }

    public void setMarkPartiel(Float markPartiel) {
        this.markPartiel = markPartiel;
    }

    public Float getMarkCours() {
        return markCours;
    }

    public void setMarkCours(Float markCours) {
        this.markCours = markCours;
    }

    public Float getMarkTP() {
        return markTP;
    }

    public void setMarkTP(Float markTP) {
        this.markTP = markTP;
    }

    @Override
    public String toString() {
        return "ResultsSubject{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", nbHoursCours=" + nbHoursCours +
                ", nbHoursTD=" + nbHoursTD +
                ", nbHoursTP=" + nbHoursTP +
                ", coefficientPartiel=" + coefficientPartiel +
                ", coefficientCours=" + coefficientCours +
                ", coefficientTP=" + coefficientTP +
                ", markPartiel=" + markPartiel +
                ", markCours=" + markCours +
                ", markTP=" + markTP +
                '}';
    }

    /**
     * Converts a text value from ENT to a float
     *
     * @param value value to convert
     * @return converted value
     */
    private Float convertEntValuesToFloat(String value) {
        return (value.equals("-") ? 0 : Float.parseFloat(value));
    }
}
