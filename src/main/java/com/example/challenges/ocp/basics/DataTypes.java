package com.example.challenges.ocp.basics;

public class DataTypes {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        boolean bool = false;
        byte myByte = 12;
        short myShort = 12_001;
        int myInt = 331_123_312;
        long myLong = 1234444L;
        float myFloat = 12.3f;
        double myDouble = 1534.23;
        char myChar = 'd';

        Boolean boolWrapper = Boolean.valueOf(true);
        Byte byteWrapper = Byte.valueOf((byte) 21);
        Short shortWrapper = Short.valueOf((short) 4567);
        Integer integerWrapper = Integer.valueOf(123455);
        Long longWrapper = Long.valueOf(12334566546L);
        Float floatWrapper = Float.valueOf(1433.56f);
        Double doubleWrapper = Double.valueOf(23.5678887);
        Character character = Character.valueOf('d');

        Integer integer = Integer.valueOf("1234");
        int parsedInt = Integer.parseInt("1244");
    }
}
