package com.example.challenges.ocp.strings;

public class StringExamples  {
    public static void main(String[] args) {
        String string = "John Wayne";
        System.out.println(string.length()); // 10
        System.out.println(string.charAt(2)); // 'h'
        System.out.println(string.charAt(15)); // StringIndexOutOfBoundsException
        System.out.println(string.indexOf('W')); // 5
        System.out.println(string.indexOf("yne")); // 7
        System.out.println(string.indexOf("R")); // -1
        System.out.println(string.substring(3)); // n Wayne
        System.out.println(string.substring(3, 8)); // n Way
        System.out.println(string.substring(3, 15)); // StringIndexOutOfBoundsException
        System.out.println(string.toLowerCase()); // john wayne
        // equals(str), equalsIgnoreCase(str)
        // startsWith(str), endsWith(str)
        // contains()
        // replace("abc", "xyz")
        String abc = "     abc  ";
        System.out.println("|" + abc.strip() + "|"); // |abc|
        System.out.println("|" + abc.trim() + "|"); // |abc|
        System.out.println("|" + abc.stripLeading() + "|"); // |abc  |
        System.out.println("|" + abc.stripLeading() + "|"); // |     abc|
        String name = """
                     John
                    D.
                   Wayne""";
        System.out.println(name);
        /*
             John
                    D.
                   Wayne
         */
        // indent(positiveInteger), indent(negativeNumber), stripIndent()
        // translateEscape()
        // isEmpty(), isBlank()
        // String.format() => String.format("%s has %d marbles.", name, numberOfMarbles);
        // "".formatted() => "%s has %d marbles.".formatted(name, numberOfMarbles);
    }
}
