













// The Kotlin type system is the basis
// for many really cool things we can do.















// A NOTE ABOUT SUBTYPES


// Subtype: A type A is a subtype of type B if A can be used anywhere B can
// E.g. Cat is a subtype of animal because anywhere you need an animal
// a cat will suffice

// In simple cases, the type can be used interchangeably with the class name
val str: String = "string" // <-- String is the class and the type

// But this quickly falls apart with nullables
val str2: String? = "string" // <-- String is the class, String? is the type


















// In the simplest cases, classes can create two types.
// With Collections, there can be near infinite

val coll1: List<Int> = TODO()
val coll2: List<String>? = TODO() // <-- List is the class
val coll3: List<List<Pair<Int, List<String>>>> = TODO()

























// Why is this cool? Well we have null safety, because you can
// only assign a value to something that is a super type of it

val num: Number = Int.MAX_VALUE // Number is a super type of Int
val nullableStr: String? = null
val nonNullableStr: String = nullableStr // Won't compile, because
                                         // String? is not a subtype
                                         // of String





























// Fast blast Nothing type rundown
fun notImplemented(): List<List<List<Dog>>> {
    // Why does this work?
    TODO()
}


fun unreachable(someNullable: String?) {
    // Won't ever assign if null
    val nonNull: String = someNullable ?: throw NotImplementedError()

    val thing: Nothing = TODO() // Nothing type indicates
                                // the function never completes.
                                // That's why the assignment
                                // is unreachable
}













// Any type vs object

// Any is the default super type of all classes
// (in the same way unit is the default return type)
class Something
class Something2: Any()

fun primitiveNiceties() {
    val psuedo_primitive: Int = 1
    val anyField: Any = psuedo_primitive // Kotlin "primitives" are all
                                         // considered subtypes of Any
}










// Any corresponds to java.lang.Object (check the bytecode)
// but it's not the same as Object because it exposes only
// hashCode, equals, and toString methods.
fun any() {
    val value: Any = Any()
    value.hashCode()
    value.equals("other")
    value.toString()

    // Still have access to the object, but it's a warning
    (value as Object).notify()

    // As expected, Any is a subtype of Any?
    val any3: Any? = 2
    val any4: Any = any3 // But the reverse doesn't work
}
















// Unit

// "Void and unit are essentially the same concept.
// They're used to indicate that a function is called
// only for its side effects. The difference is that
// unit actually has a value. That means that unit functions
// can be used generically wherever a generic first
// class function is required. The C derived languages
// miss out by treating void functions as functions that
// don't return anything instead of as functions that
// don't return anything meaningful."
// - James Iry

fun defaultReturn(): Unit { }

val thing: Unit = Unit

// Why is this useful?
interface ResultOrError<out R: Any, out E: Exception> {
    // some apis return boolean == success
    // some api are side effects who's proper result type would be Unit
}
class ExampleResult : ResultOrError<Unit, UninitializedPropertyAccessException>


















// NOT COVERED: Platform types




















