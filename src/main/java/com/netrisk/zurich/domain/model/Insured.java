package com.netrisk.zurich.domain.model;

import com.netrisk.zurich.domain.model.valueobject.Email;
import com.netrisk.zurich.domain.model.valueobject.PhoneNumber;
import com.netrisk.zurich.domain.model.valueobject.TcKimlikNo;

import java.time.LocalDate;

public class Insured {

    private final TcKimlikNo tcKimlikNo;
    private final String policyNumber;
    private final String insuredNumber;
    private final String projectName;
    private final String coverageName;
    private final LocalDate enrollmentDate;
    private final String fullName;
    private final String address;
    private final String province;
    private final String district;
    private final Email email;
    private final PhoneNumber phoneNumber;

    private Insured(Builder builder) {
        this.tcKimlikNo     = builder.tcKimlikNo;
        this.policyNumber   = builder.policyNumber;
        this.insuredNumber  = builder.insuredNumber;
        this.projectName    = builder.projectName;
        this.coverageName   = builder.coverageName;
        this.enrollmentDate = builder.enrollmentDate;
        this.fullName       = builder.fullName;
        this.address        = builder.address;
        this.province       = builder.province;
        this.district       = builder.district;
        this.email          = builder.email;
        this.phoneNumber    = builder.phoneNumber;
    }

    // --- İş Kuralı Methodları ---

    // Örnek: bu sigortalı aktif poliçeye sahip mi?
    public boolean hasValidEnrollmentDate() {
        return enrollmentDate != null && !enrollmentDate.isAfter(LocalDate.now());
    }

    // İleride buraya domain'e özgü başka iş kuralları eklenecek
    // Örn: belirli bir projeye ait mi, teminat adı geçerli mi vs.

    // --- Getter'lar ---
    public TcKimlikNo getTcKimlikNo()       { return tcKimlikNo; }
    public String getPolicyNumber()         { return policyNumber; }
    public String getInsuredNumber()        { return insuredNumber; }
    public String getProjectName()          { return projectName; }
    public String getCoverageName()         { return coverageName; }
    public LocalDate getEnrollmentDate()    { return enrollmentDate; }
    public String getFullName()             { return fullName; }
    public String getAddress()              { return address; }
    public String getProvince()             { return province; }
    public String getDistrict()             { return district; }
    public Email getEmail()                 { return email; }
    public PhoneNumber getPhoneNumber()     { return phoneNumber; }

    // --- Builder ---
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private TcKimlikNo tcKimlikNo;
        private String policyNumber;
        private String insuredNumber;
        private String projectName;
        private String coverageName;
        private LocalDate enrollmentDate;
        private String fullName;
        private String address;
        private String province;
        private String district;
        private Email email;
        private PhoneNumber phoneNumber;

        public Builder tcKimlikNo(String tc)            { this.tcKimlikNo = TcKimlikNo.of(tc); return this; }
        public Builder policyNumber(String v)           { this.policyNumber = v; return this; }
        public Builder insuredNumber(String v)          { this.insuredNumber = v; return this; }
        public Builder projectName(String v)            { this.projectName = v; return this; }
        public Builder coverageName(String v)           { this.coverageName = v; return this; }
        public Builder enrollmentDate(LocalDate v)      { this.enrollmentDate = v; return this; }
        public Builder fullName(String v)               { this.fullName = v; return this; }
        public Builder address(String v)                { this.address = v; return this; }
        public Builder province(String v)               { this.province = v; return this; }
        public Builder district(String v)               { this.district = v; return this; }
        public Builder email(String v)                  { this.email = Email.of(v); return this; }
        public Builder phoneNumber(String v)            { this.phoneNumber = PhoneNumber.of(v); return this; }

        public Insured build() {
            validateRequiredFields();
            return new Insured(this);
        }

        private void validateRequiredFields() {
            if (tcKimlikNo == null)    throw new IllegalStateException("TC kimlik no zorunludur");
            if (policyNumber == null)  throw new IllegalStateException("Poliçe numarası zorunludur");
            if (fullName == null)      throw new IllegalStateException("İsim soyisim zorunludur");
            if (email == null)         throw new IllegalStateException("Email zorunludur");
        }
    }
}
