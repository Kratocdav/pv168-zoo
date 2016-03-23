/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoomanager;

import java.util.List;
import java.sql.*;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Matus Sedlak
 */
public class AnimalManagerImpl implements AnimalManager {

    private static final Logger LOG = Logger.getLogger(
            AnimalManagerImpl.class.getName());
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    private void checkDataSource() {
        if(dataSource == null) {
            throw new IllegalStateException("DataSource is not set2");
        }
    }
    @Override
    public void createAnimal(Animal animal) throws ServiceFailureException {
        checkDataSource();
        
    }

    @Override
    public Animal getAnimal(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAnimal(Animal animal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAnimal(Animal animal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Animal> findAllAnimals() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Animal> findAnimalsByanimalClass(AnimalClass animalClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void validate(Animal animal) {
        
    
    }
}
