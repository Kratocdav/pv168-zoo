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
public interface ZooManager {

    void addAnimalToCaptivity(Animal animal, Captivity captivity);

    void removeAnimalFromCaptivity(Animal animal, Captivity captivity);

    List<Animal> listAnimalInCaptivity(Captivity captivity);

    List<Captivity> listCaptivitiesWithAnimal(String animalName);

    List<Captivity> listEmptyCaptivities();
}
