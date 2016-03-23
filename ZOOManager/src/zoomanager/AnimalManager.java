/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoomanager;

import java.util.List;

/**
 *
 * @author Matus Sedlak
 */
public interface AnimalManager {

    Animal getAnimal(Long id);
    
    void createAnimal(Animal animal);

    void updateAnimal(Animal animal);

    void deleteAnimal(Animal animal);

    List<Animal> findAllAnimals();

    List<Animal> findAnimalsByanimalClass(AnimalClass animalClass);

}
