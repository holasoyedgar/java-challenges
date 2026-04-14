package com.example.challenges.ocp.nestedclasses;

public class A {
    private final int t = 1;
    class B {
        private final int t = 2;
        class C {
            private final int t = 3;
            public void printT() {
                System.out.println(t);
                System.out.println(this.t);
                System.out.println(B.this.t);
                System.out.println(A.this.t);
            }
        }
    }

    public static void main(String[] args) {
        A a = new A();
        B b = a.new B();
        B.C c = b.new C();

        c.printT();
    }
}
