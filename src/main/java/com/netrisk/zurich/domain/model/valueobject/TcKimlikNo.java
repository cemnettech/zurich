package com.netrisk.zurich.domain.model.valueobject;

public final class TcKimlikNo {

    private final String value;

    private TcKimlikNo(String value) {
        this.value = value;
    }

    public static TcKimlikNo of(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Tc Kimlik numarası boş olamaz");
        }

        String trimmed = value.trim();

        if (!trimmed.matches("\\d{11}")) {
            throw new IllegalArgumentException("Tc kimlik numarası 11 rakamdan oluşmalıdır");
        }

        if (trimmed.charAt(0) == '0') {
            throw new IllegalArgumentException("Tc kimlik numarası 0 ile başlayamaz");
        }

        if (!isChecksumValid(trimmed)) {
            throw new IllegalArgumentException("Geçersiz Tc kimlik numarası: " + trimmed);
        }

            return new TcKimlikNo(trimmed);
    }

    private static boolean isChecksumValid(String tc) {
        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = tc.charAt(i) - '0';
        }

        int oddSum = digits[0] + digits[2] + digits[4] + digits[6] + digits[8];
        int evenSum = digits[1] + digits[3] + digits[5] + digits[7];
        int tenth = ((oddSum * 7) - evenSum) % 10;
        if (tenth < 0) tenth += 10;
        if (tenth != digits[9]) return false;

        int totalSum =  0;
        for (int i = 0; i < 10; i++) totalSum += digits[i];
        int eleventh = totalSum % 10;
        return eleventh == digits[10];
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TcKimlikNo)) return false;
        return value.equals(((TcKimlikNo) o).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
