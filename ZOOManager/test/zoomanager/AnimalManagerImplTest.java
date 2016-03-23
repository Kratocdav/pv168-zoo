/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoomanager;

import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matúš
 */
public class AnimalManagerImplTest {

    private AnimalManager manager;

    @Before
    public void setUp() throws SQLException {
        manager = new AnimalManagerImpl();
    }

    @Test
    public void createAndGetAnimal() {
        Animal animal = newAnimal("Mys", AnimalClass.MAMMAL, 1, Gender.MAN);
        manager.createAnimal(animal);

        Long animalId = animal.getId();
        assertNotNull(animalId);

        Animal result = manager.getAnimal(animalId);
        assertEquals(animal, result);
        assertDeepEquals(animal, result);
    }

    //create animal with wrong values - tests
    @Test(expected = IllegalArgumentException.class)
    public void createAnimalWithWrongAnimalName() {
        Animal animal = newAnimal("56789", AnimalClass.MAMMAL, 1, Gender.MAN);
        manager.createAnimal(animal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createAnimalWithNullAnimalClass() {
        Animal animal = newAnimal("Mys", null, 1, Gender.MAN);
        manager.createAnimal(animal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createAnimalWithNegativeAge() {
        Animal animal = newAnimal("Pavuk", AnimalClass.OTHER, -1, Gender.MAN);
        manager.createAnimal(animal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNullAnimal() {
        manager.createAnimal(null);
    }

    @Test
    public void deleteAnimal() {
        Animal a1 = newAnimal("Lev", AnimalClass.MAMMAL, 1, Gender.MAN);
        manager.createAnimal(a1);

        assertNotNull(manager.getAnimal(a1.getId()));

        manager.deleteAnimal(a1);

        assertNull(manager.getAnimal(a1.getId()));
    }

    private void assertDeepEquals(Animal expected, Animal actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getAnimalClass(), actual.getAnimalClass());
        assertEquals(expected.getAge(), actual.getAge());
        assertEquals(expected.getGender(), actual.getGender());
    }

    private static Animal newAnimal(String animalName, AnimalClass animalClass, int age, Gender gender) {
        Animal animal = new Animal();
        animal.setAnimalClass(animalClass);
        animal.setAnimalName(animalName);
        animal.setAge(age);
        animal.setGender(gender);
        return animal;
    }
}
