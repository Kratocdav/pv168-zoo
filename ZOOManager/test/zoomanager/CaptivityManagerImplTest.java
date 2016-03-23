package zoomanager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;


import static org.hamcrest.CoreMatchers.*;
import org.junit.After;

/**
 *
 * @author David
 */
public class CaptivityManagerImplTest {

    private CaptivityManagerImpl manager;
    private Captivity captivity;
    private DataSource dataSource;
    
    @Before
    public void setUp() throws SQLException {
        captivity = new Captivity("Felines", 8, "Closed");
        
        dataSource = prepareDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement("CREATE TABLE CAPTIVITY ("
                    + "id bigint primary key generated always as identity,"
                    + "pavilion varchar(255),"
                    + "capacity int not null,"
                    + "info varchar(255))").executeUpdate();
        }
        
        manager = new CaptivityManagerImpl(dataSource);
    }

    @After
    public void tearDown() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement("DROP TABLE CAPTIVITY").executeUpdate();
        }
    }

    private static DataSource prepareDataSource() throws SQLException {
        EmbeddedDataSource ds = new EmbeddedDataSource();
        //we will use in memory database
        ds.setDatabaseName("memory:gravemgr-test");
        ds.setCreateDatabase("create");
        return ds;
    }
    
    @Test
    public void createCaptivity() {
        manager.createCaptivity(captivity);
        Long id = captivity.getId();
        
        assertThat(id).isNotNull();
        
        Captivity result = manager.getCaptivity(id);
        assertEquals(captivity, result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createCaptivityWithChangedId() throws Exception {
        captivity.setId(1L);
        manager.createCaptivity(captivity);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createCaptivityWithNullPavilion() throws Exception {
        Captivity cap = new Captivity(null, 8, "Closed");
        manager.createCaptivity(cap);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createCaptivityWithNullInfo() throws Exception { 
        Captivity cap = new Captivity("Felines", 8, null);
        manager.createCaptivity(cap);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createCaptivityWithNegativeCapacity() throws Exception {
        Captivity cap = new Captivity("Felines", -1, "Closed");
        manager.createCaptivity(cap);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createCaptivityWithZeroCapacity() throws Exception {
        Captivity cap = new Captivity("Felines", 0, "Closed");
        manager.createCaptivity(cap);
    }
    
    @Test
    public void updateCaptivityPavilion() {
        manager.createCaptivity(captivity);
        
        Long captivityId = captivity.getId();
        
        captivity.setPavilion("Birds");
        manager.updateCaptivity(captivity);
        captivity = manager.getCaptivity(captivityId);
        
        assertThat(manager.getCaptivity(captivityId).getPavilion()).isEqualTo("Birds");
        assertThat(manager.getCaptivity(captivityId).getCapacity()).isEqualTo(8);
        assertThat(manager.getCaptivity(captivityId).getPavilion()).isEqualTo("Closed");
    }
    
    @Test
    public void updateCaptivityCapacity() {
        manager.createCaptivity(captivity);
        
        Long captivityId = captivity.getId();
        
        captivity.setCapacity(3);
        manager.updateCaptivity(captivity);
        captivity = manager.getCaptivity(captivityId);
        
        assertThat(manager.getCaptivity(captivityId).getPavilion()).isEqualTo("Felines");
        assertThat(manager.getCaptivity(captivityId).getCapacity()).isEqualTo(3);
        assertThat(manager.getCaptivity(captivityId).getPavilion()).isEqualTo("Closed");
    }
    
    @Test
    public void updateCaptivityInfo() {
        manager.createCaptivity(captivity);
        
        Long captivityId = captivity.getId();
        
        captivity.setInfo("Opened");
        manager.updateCaptivity(captivity);
        captivity = manager.getCaptivity(captivityId);
        
        assertThat(manager.getCaptivity(captivityId).getPavilion()).isEqualTo("Felines");
        assertThat(manager.getCaptivity(captivityId).getCapacity()).isEqualTo(8);
        assertThat(manager.getCaptivity(captivityId).getPavilion()).isEqualTo("Opened");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void deleteNullCaptivity() throws Exception {
        manager.deleteCaptivity(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteCaptivityWithWrongId() throws Exception {      
        captivity.setId(-1L);
        manager.deleteCaptivity(captivity.getId());
    }
}
