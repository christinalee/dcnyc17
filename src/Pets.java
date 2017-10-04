class JavaPet<T> {
    public T newPet() {
        return null;
    }
}

class Example {
    void example() {
        JavaPet<? extends Dog> doggo; // Use site variance
        JavaPet<SmallDog> smallDog = new JavaPet<>();

        doggo = smallDog;
    }
}


