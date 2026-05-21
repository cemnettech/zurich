package com.netrisk.zurich.domain.model.valueobject;

public final class Email {

    private final String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email of(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email boş olamaz");
        }

        String normalized = value.trim().toLowerCase();

        if (!normalized.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-z]{2,}$")) {
            throw new IllegalArgumentException("Geçersiz email formatı: " + value);
        }

        return new Email(normalized);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        return value.equals(((Email) o).value);
    }

    @Override
    public int hashCode() { return value.hashCode(); }

    @Override
    public String toString() { return value; }
}
