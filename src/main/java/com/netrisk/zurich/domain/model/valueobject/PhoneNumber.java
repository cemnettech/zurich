package com.netrisk.zurich.domain.model.valueobject;

public final class PhoneNumber {

    private final String value; // normalize edilmiş hali: 05XXXXXXXXX

    private PhoneNumber(String value) {
        this.value = value;
    }

    public static PhoneNumber of(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Telefon numarası boş olamaz");
        }

        // Sadece rakamları al
        String digitsOnly = value.trim().replaceAll("[^0-9]", "");

        // +90 veya 90 ile başlıyorsa ülke kodunu at
        if (digitsOnly.startsWith("90") && digitsOnly.length() == 12) {
            digitsOnly = "0" + digitsOnly.substring(2);
        }

        if (!digitsOnly.matches("0[5][0-9]{9}")) {
            throw new IllegalArgumentException("Geçersiz telefon numarası: " + value);
        }

        return new PhoneNumber(digitsOnly);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumber)) return false;
        return value.equals(((PhoneNumber) o).value);
    }

    @Override
    public int hashCode() { return value.hashCode(); }

    @Override
    public String toString() { return value; }
}
