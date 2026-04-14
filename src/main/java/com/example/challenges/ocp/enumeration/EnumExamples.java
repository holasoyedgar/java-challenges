package com.example.challenges.ocp.enumeration;

enum Compass {
    NORTH("Move up"),
    SOUTH("Move down"),
    EAST("Move right"),
    WEST("Move left");
    private final String instruction;

    Compass(String instruction) {
        System.out.println(instruction);
        this.instruction = instruction;
    }

    public void printInstruction() {
        System.out.println(instruction);
    }
}

enum CompassAbstractMethods {
    NORTH {
        public String getInstruction() {
            return "Up";
        }
    },
    SOUTH {
        public String getInstruction() {
            return "Down";
        }
    },
    EAST {
        public String getInstruction() {
            return "Right";
        }
    },
    WEST {
        public String getInstruction() {
            return "Down";
        }
    };
    public abstract String getInstruction();
}

enum CompassDefinedMethod {
    NORTH {
        public String getInstruction() {
            return "Up";
        }
    },
    SOUTH {
        public String getInstruction() {
            return "Down";
        }
    },
    EAST,
    WEST;
    public String getInstruction() {
        return "Sideways";
    }
}

interface Planet {
    String getPlanet();
}

enum CompassInterface implements Planet {
    NORTH {
        @Override
        public String getPlanet() {
            return "Earth";
        }
    },
    SOUTH {
        @Override
        public String getPlanet() {
            return "Saturn";
        }
    },
    EAST {
        @Override
        public String getPlanet() {
            return "Jupiter";
        }
    },
    WEST {
        @Override
        public String getPlanet() {
            return "Uranus";
        }
    }, UNKNOWN;

    @Override
    public String getPlanet() {
        return "Mars";
    }
}

public class EnumExamples {
    public static void main(String[] args) {
        Compass N = Compass.NORTH;
        Compass NValueOf = Compass.valueOf("NORTH");
        System.out.println(N);

        for (var value : Compass.values()) {
            System.out.println("Ordinal: " + value.ordinal() + " name: " + value.name());
        }

        switch (NValueOf) {
            case NORTH -> System.out.println("You are headed north!");
            case SOUTH -> System.out.println("You are header south");
            default -> System.out.println("Get back!");
        }

        N.printInstruction();

        System.out.println(CompassAbstractMethods.NORTH.getInstruction());

        System.out.println(CompassDefinedMethod.NORTH.getInstruction());
        System.out.println(CompassDefinedMethod.EAST.getInstruction());
        System.out.println(CompassDefinedMethod.WEST.getInstruction());
        System.out.println(CompassDefinedMethod.WEST);

        System.out.println(CompassInterface.NORTH.getPlanet());
        System.out.println(CompassInterface.SOUTH.getPlanet());
        System.out.println(CompassInterface.EAST.getPlanet());
        System.out.println(CompassInterface.WEST.getPlanet());
        System.out.println(CompassInterface.UNKNOWN.getPlanet());

    }
}
