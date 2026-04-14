package com.example.challenges.ocp.sealedclasses;

sealed class Car permits Ford, Fiat, Chevrolet { }

final class Ford extends Car { }

non-sealed class Fiat extends Car { }

final class Uno extends Fiat { }

sealed class Chevrolet extends Car permits Silverado, C3 { }

final class Silverado extends Chevrolet { }

final class C3 extends Chevrolet { }

sealed interface Mammal permits Cat, Dog, Eats { }

non-sealed class Dog implements Mammal { }

non-sealed interface Eats extends Mammal { }

final class Cat implements Mammal { }
