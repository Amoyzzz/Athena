package backstage.pgn;


import java.util.regex.Pattern;

import backstage.util.StringUtil;

/**
 * The definition of a Portable Game Notation (PGN) property, also known as <i>tag</i>.
 */
public class PgnProperty {

    /**
     * The Byte Order Mark (BOM) that defines a big-endian representation of bytes.
     */
    public final static String UTF8_BOM = "\uFEFF";

    private final static Pattern PROPERTY_PATTERN = Pattern.compile("\\[.* \".*\"\\]");
    /**
     * The name of the PGN property.
     */
    public String name;
    /**
     * The value of the PGN property.
     */
    public String value;

    /**
     * Constructs an empty PGN property.
     */
    public PgnProperty() {
    }

    /**
     * Constructs an PGN property formed by a tag pair.
     *
     * @param name  the name of the property
     * @param value the value of the property
     */
    public PgnProperty(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Checks if the line of text contains a PGN property.
     *
     * @param line the line of text to check
     * @return {@code true} if the line is a PGN property
     */
    public static boolean isProperty(String line) {
        return PROPERTY_PATTERN.matcher(line).matches();
    }

    /**
     * Parses a line of text that contains a PGN property.
     *
     * @param line the line of text that includes the PGN property
     * @return the PGN property extracted from the line of text
     */
    public static PgnProperty parsePgnProperty(String line) {
        try {

            String l = line.replace("[", "");
            l = l.replace("]", "");
            l = l.replace("\"", "");

            return new PgnProperty(StringUtil.beforeSequence(l, " "),
                    StringUtil.afterSequence(l, " "));
        } catch (Exception e) {
            // do nothing
        }

        return null;
    }
}