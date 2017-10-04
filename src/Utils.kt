

// Empty class setup
// Used for explaining co/contra/invariance
open class One
open class Two: One()
class Three : Two()

class Both<T> {
    fun both(t: T): T {
        TODO()
    }
}

class Producer<out T> {
    fun produceT() : T {
        TODO()
    }
}

class Consumer<in T> {
    fun consumeT(t: T) {
        TODO()
    }
}


// Used for declaration site variance example
open class Dog
class SmallDog: Dog()


