package ffhs.lecturing.jpl.designpatterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class MailpieceSlideDeterminatorTest {

    @org.junit.jupiter.api.Test
    void determine_shouldReturnCorrectSlide_WhenParcelAndDefaultCase() {
        // Arrange
        Mailpiece mailpiece = getTestMailpiece();

        MailpieceSlideDeterminator objectUnderTest = new MailpieceSlideDeterminator();

        // Act
        int result = objectUnderTest.determine(mailpiece);

        // Assert
        assertEquals(2, result);
    }

    // This test is red because the getTestMailpiece method returns a wrong mailpiece.
    // We now encounter a problem. We could pass the boolean flag to the getTestMailpiece method.
    // If we do this we force every test to be changed and we do not have the default value anymore.
    // It gets worse if we need special cases for barcodes as well.
    // We can solve this problem with a builder pattern!
    // Task: Create a mailpieceBuilder.
    // When we call "new MailpieceBuilder().build();" this must return what getTestMailpiece now returns.
    // We can overwrite certain values from the default case using the builder methods.
    // Make this test green by replace the getTestMailpiece call with the following
    // new MailpieceBuilder().setIsLetter().build();
    // Then, create a new test to cover the remaining branch in the MailpieceSlideDeterminator class.
    @org.junit.jupiter.api.Test
    void determine_shouldReturnCorrectSlide_WhenLetterAndDefaultCase() {
        // Arrange
        Mailpiece mailpiece = getTestMailpiece();

        MailpieceSlideDeterminator objectUnderTest = new MailpieceSlideDeterminator();

        // Act
        int result = objectUnderTest.determine(mailpiece);

        // Assert
        assertEquals(0, result);
    }

    private static Mailpiece getTestMailpiece() {
        return new Mailpiece("11111", new ArrayList<>() {{
            new Barcode("QR", "222344822");
            new Barcode("Matrix", "114848");
        }}, false);
    }
}

class MailpieceSlideDeterminator {
    public int determine(Mailpiece mailpiece) {
        if(mailpiece.isLetter()) {
            return 0;
        } else {

            boolean needsSpecialTreatment = mailpiece.getBarcodes().stream()
                    .anyMatch(barcode -> barcode.value().startsWith("10015") && Objects.equals(barcode.type(), "Matrix"));
            if(needsSpecialTreatment) {
                return 1;
            } else {
                return 2;
            }
        }
    }
}


class Mailpiece {
    private final String Identifier;

    private final List<Barcode> barcodes;

    private final boolean isLetter;

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

record Barcode(String value, String type) {
}
