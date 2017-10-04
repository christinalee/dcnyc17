

// The Basics

// A - B - C - D - E - F - G, T is a type param with upper bound An-y?
fun <T> defaultUpper() { }
fun <T: Any?> explicitUpper() { }


fun <T> ruhRho(t: T) { // Default generic is nullable
    t.hashCode()
}
fun <T: Any> ohhKay(t: T) { // Non nullable generic
    t.hashCode()
}




























// Multiple constraints
interface Animal
interface Fuzzy
fun <T> getFuzzyAnimal(): T where T : Animal, T: Fuzzy {
    TODO()
}





























/*********************************************************************

.___________.____    ____ .______    _______
|           |\   \  /   / |   _  \  |   ____|
`---|  |----` \   \/   /  |  |_)  | |  |__
    |  |       \_    _/   |   ___/  |   __|
    |  |         |  |     |  |      |  |____
    |__|         |__|     | _|      |_______|

_______ .______          ___           _______. __    __  .______       _______
|   ____||   _  \        /   \         /       ||  |  |  | |   _  \     |   ____|
|  |__   |  |_)  |      /  ^  \       |   (----`|  |  |  | |  |_)  |    |  |__
|   __|  |      /      /  /_\  \       \   \    |  |  |  | |      /     |   __|
|  |____ |  |\  \----./  _____  \  .----)   |   |  `--'  | |  |\  \----.|  |____
|_______|| _| `._____/__/     \__\ |_______/     \______/  | _| `._____||_______|

 **********************************************************************/







// Just like Java, Kotlin has type erasure, which means at run
// time you can't query types
fun <T> genericCollectionFun(list: Collection<T>) {
    if (list is List<Int>) {
        // ????
    }

    // Star projections!
    // star projection: no information about a generic argument


    if (list is List<*>) { // if (this is a list of any type)
        // WOOHOO!

        // But also, caution! Abuse of * can lead to runtime exceptions
        val unsafe = list as List<Int> // Will succeed at runtime
        val mightCrash: Int = unsafe.first() // Can crash at runtime
    }
}























// Hashtag Relatable:

interface MyInterface<T> {
    fun gimmeSomethingAwesome(): T
}

class WorksAnyways: MyInterface<String> {
    override fun gimmeSomethingAwesome(): String {
        return "This works anyways, why you so bossy?"
    }
}


// Da fudge is dis?
interface InOutExample<T> {
    fun consumer(t: T)

    fun producer(): T

    fun both(t: T): T
}

// We did it!
// Except... like, what does that mean?
























/***********************************************************************

____    ____  ___      .______       __       ___      .__   __.   ______  _______
\   \  /   / /   \     |   _  \     |  |     /   \     |  \ |  |  /      ||   ____|
 \   \/   / /  ^  \    |  |_)  |    |  |    /  ^  \    |   \|  | |  ,----'|  |__
  \      / /  /_\  \   |      /     |  |   /  /_\  \   |  . `  | |  |     |   __|
   \    / /  _____  \  |  |\  \----.|  |  /  _____  \  |  |\   | |  `----.|  |____
    \__/ /__/     \__\ | _| `._____||__| /__/     \__\ |__| \__|  \______||_______|

 ***********************************************************************/




// `in` and `out` are actually telling us something about
// the variance of the generic
// What is variance?
// Glad you asked.

















































// THE CONCEPT OF VARIANCE DESCRIBES HOW TYPES WITH THE SAME BASE TYPE AND
// DIFFERENT TYPE ARGUMENTS RELATE TO EACH OTHER
























// BREATH





// Slowly.





// Ok, we good?




// 3
// 2
// 1
// ... Let's go!


























// Invariant: if for any two different type parameters A and B,
// Foo<A> is neither a subtype nor a supertype of Foo<B>
val invariant1: Both<Two> = Both<One>()
val invariant2: Both<Two> = Both<Two>() // Invariant
val invariant3: Both<Two> = Both<Three>()

// Covariant: subtyping is preserved
// A is a subtype of B, Foo<A> is a subtype of Foo<B>
val covariant1: Producer<Two> = Producer<One>()
val covariant2: Producer<Two> = Producer<Two>() // Covariant
val covariant3: Producer<Two> = Producer<Three>()

// Contravariant: subtyping is inverted
// B is a subtype of A, so Foo<A> is a subtype of Foo<B> <-- notice the reversal
val contra1: Consumer<Two> = Consumer<One>()
val contra2: Consumer<Two> = Consumer<Two>() // Contravariant
val contra3: Consumer<Two> = Consumer<Three>()





















// So to answer the original question:

// `Out` means a generic is covariant and that subtyping is preserved
// We can only make this guarantee if T is used in producing positions
// Ergo `out` constrains T to producing positions





// Similarly...


// `in` means a generic is contravariant and that subtyping is inverted
// We can only make this guarantee if T is used in consuming positions
// Ergo `in` constrains T to consuming positions



















// Why do we care?

// Without variance, types would need to be an exact match

// MutableList is invariant because read + write, so types must match exactly
val modified: MutableList<Any> = mutableListOf<String>()




// But then this wouldn't work, even though it's safe to do:
val list: List<Any> = listOf<String>()


// TL;DR Variance allows us additional flexibility while maintaining safety
























// Listen up folks:
// VARIANCE RULES PROTECT A CLASS FROM MISUSE BY _EXTERNAL_ CLIENTS

// Constructors are neither `in` nor `out`
class GenericClass<in T, out R, S>(contravariant: T, covariant: R, invariant: S) {

    val field1: T = contravariant // Nope
    val field2: R = covariant
    val field3: S = invariant

    var field4: T = contravariant // Nope
    var field5: R = covariant  // Nope
    var field6: S = invariant

    // Everything works if it's private -- variance is to protect from external users
    private val field7: T = contravariant
    private val field8: R = covariant
    private val field9: S = invariant

    private var field10: T = contravariant
    private var field11: R = covariant
    private var field12: S = invariant


    // Same goes for functions: Everything works for private
    private fun producer1(): T = TODO()
    private fun producer2(): R = TODO()
    private fun producer3(): S = TODO()

    private fun consumer1(t: T): Nothing = TODO()
    private fun consumer2(r: R): Nothing = TODO()
    private fun consumer3(s: S): Nothing = TODO()
}




























// Use site vs declaration site variance

class Pet<out T> {  // <-- declaration of T, also variance info
    fun newInstance(): T = TODO()
}

val doggo: Pet<Dog> = Pet<SmallDog>() // <-- use of T













// You can still use use-site variance in Kotlin too:
class InvariantClass<T>
val projectedType: InvariantClass<out Number> = InvariantClass<Int>()
val projectedType2: InvariantClass<in Int> = InvariantClass<Number>()






















//  Use site variance declarations in Kotlin correspond
//  directly to Java bounded wild cards:

//  MutableList<out T> -> MutableList<? extends T>
//  MutableList<in T> —> MutableList<? super T>

//  This is useful because not all classes have invariant
//  and covariant versions the way MutableList and List do





















// Fun Snapple fact: Remember * projections?

// MutableList<*> is an out projected type MutableList<out Any?>

// For contravariant, the projection is nothing:
//      MutableList<*> —> MutableList<in Nothing>



























