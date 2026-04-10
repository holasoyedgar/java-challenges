package com.example.challenges.ocp.strings;

public class StringBuilderExamples {
    public static void main(String[] args) {
        // append(String)
        StringBuilder sb1 = new StringBuilder("John");
        sb1.append("Wa").append("yne");
        System.out.println(sb1); // JohnWayne

        // insert(String)
        StringBuilder sb2 = new StringBuilder("John Wayne");
        sb2.insert(5, "D. ") // John D. Wayne
                .insert(6, "A"); // John DA. Wayne
        System.out.println(sb2); // John DA. Wayne

        // delete(int, int)
        StringBuilder sb3 = new StringBuilder("abcdef");
        sb3.delete(1, 4);
        System.out.println(sb3); // aef

        // deleteChatAt(int)
        StringBuilder sb4 = new StringBuilder("abcdef");
        sb4.deleteCharAt(1);
        System.out.println(sb4); // acdef

        // replace(int, int, String)
        StringBuilder sb5 = new StringBuilder("abcdef");
        sb5.replace(1, 3, "JOHN");
        System.out.println(sb5); // aJOHNdef

        // reverse()
        StringBuilder name = new StringBuilder("Edgar");
        name.reverse();
        System.out.println(name); // radgE

        StringBuilder sb6 = new StringBuilder("AAAA");
        sb6.append("WWW");
        String string = sb6.toString();
        System.out.println(string); // AAAAWWW
    }
}
