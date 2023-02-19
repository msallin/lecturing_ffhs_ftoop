package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MailpieceSlideDeterminatorTest {

    @org.junit.jupiter.api.Test
    void determine_shouldReturnCorrectSlide_WhenParcelAndDefaultCase() {
        // Arrange
        Mailpiece mailpiece = new MailpieceBuilder()
                .build();

        MailpieceSlideDeterminator objectUnderTest = new MailpieceSlideDeterminator();

        // Act
        int result = objectUnderTest.determine(mailpiece);

        // Assert
        assertEquals(2, result);
    }

    @org.junit.jupiter.api.Test
    void determine_shouldReturnCorrectSlide_WhenLetterAndDefaultCase() {
        // Arrange
        Mailpiece mailpiece = new MailpieceBuilder()
                .setIsLetter(true)
                .build();

        MailpieceSlideDeterminator objectUnderTest = new MailpieceSlideDeterminator();

        // Act
        int result = objectUnderTest.determine(mailpiece);

        // Assert
        assertEquals(0, result);
    }

    @org.junit.jupiter.api.Test
    void determine_shouldReturnCorrectSlide_WhenLParcelAndSpecialCase() {
        // Arrange
        Mailpiece mailpiece = new MailpieceBuilder()
                .setBarcodes(Arrays.asList(new Barcode("1001511", "Matrix")))
                .build();

        MailpieceSlideDeterminator objectUnderTest = new MailpieceSlideDeterminator();

        // Act
        int result = objectUnderTest.determine(mailpiece);

        // Assert
        assertEquals(1, result);
    }
}

class MailpieceBuilder {
    private String identifier = "11111";
    private List<Barcode> barcodes = new ArrayList<Barcode>() {{
        new Barcode("QR", "222344822");
        new Barcode("Matrix", "114848");
    }};
    private boolean isLetter = false;

    public MailpieceBuilder setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public MailpieceBuilder setBarcodes(List<Barcode> barcodes) {
        this.barcodes = barcodes;
        return this;
    }

    public MailpieceBuilder setIsLetter(boolean isLetter) {
        this.isLetter = isLetter;
        return this;
    }

    public Mailpiece build() {
        return new Mailpiece(identifier, barcodes, isLetter);
    }
}


class MailpieceSlideDeterminator {
    public int determine(Mailpiece mailpiece) {
        if(mailpiece.isLetter()) {
            return 0;
        } else {

            boolean needsSpecialTreatment = mailpiece.getBarcodes().stream()
                    .anyMatch(barcode -> barcode.getValue().startsWith("10015") && barcode.getType() == "Matrix");
            if(needsSpecialTreatment) {
                return 1;
            } else {
                return 2;
            }
        }
    }
}

class Mailpiece {
    private String Identifier;

    private List<Barcode> barcodes;

    private boolean isLetter;

    Mailpiece(String identifier, List<Barcode> barcodes, boolean isLetter) {
        Identifier = identifier;
        this.barcodes = barcodes;
        this.isLetter = isLetter;
    }

    public String getIdentifier() {
        return Identifier;
    }

    public List<Barcode> getBarcodes() {
        return barcodes;
    }

    public boolean isLetter() { return isLetter; }
}

class Barcode {
    private String value;
    private String type;

    Barcode(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() { return value; }

    public String getType() { return type; }
}
