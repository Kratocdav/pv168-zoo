/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoomanager;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Matus Sedlak
 */
public class CaptivityManagerImpl implements CaptivityManager {

    private final DataSource dataSource;

    public CaptivityManagerImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createCaptivity(Captivity captivity) throws ServiceFailureException {

        validate(captivity);
        if (captivity.getId() != null) {
            throw new IllegalArgumentException("animal ID is already set");
        }

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "INSERT INTO CAPTIVITY (pavilion, capacity, info) VALUES (?,?,?)",
                        Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, captivity.getPavilion());
            st.setInt(2, captivity.getCapacity());
            st.setString(3, captivity.getInfo());
            int addedRows = st.executeUpdate();
            if (addedRows != 1) {
                throw new ServiceFailureException("Error : More rows ("
                        + addedRows + ") inserted when trying to insert captivity" + captivity);
            }

            ResultSet keyRS = st.getGeneratedKeys();
            captivity.setId(getKey(keyRS, captivity));

        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when inserting captivity " + captivity, ex);
        }

    }

    private void validate(Captivity captivity) throws IllegalArgumentException {

        if (captivity == null) {
            throw new IllegalArgumentException();
        }

        if (captivity.getCapacity() < 0) {
            throw new IllegalArgumentException();
        }
        
        if(captivity.getInfo() == null) {
            throw new IllegalArgumentException();
        }
        
        if(captivity.getPavilion() == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Captivity getCaptivity(Long id) {

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "SELECT id,pavilion,capacity,info FROM captivity WHERE id = ?")) {

            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Captivity captivity = resultSetToCaptivity(rs);

                if (rs.next()) {
                    throw new ServiceFailureException(
                            "Error: More entities with the same id found"
                            + "(source id: " + id + ", found " + captivity + " and " + resultSetToCaptivity(rs));
                }

                return captivity;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when retrieving captivity with id " + id, ex);
        }
    }

    @Override
    public void updateCaptivity(Captivity captivity) {
        validate(captivity);
        if(captivity.getId() == null) throw new IllegalArgumentException("captivity id is null");

        
        try(Connection conn = dataSource.getConnection();
            PreparedStatement st = conn.prepareStatement(
            "UPDATE captivity SET pavilion=? capacity=? info=? WHERE id=?")) {
            
                st.setString(1, captivity.getPavilion());
                st.setInt(2, captivity.getCapacity());
                st.setString(3, captivity.getInfo());
                st.setLong(4, captivity.getId());
                
                if(st.executeUpdate() != 1) {
                    throw new ServiceFailureException("could not update captivity"+captivity);
                }
            
        } catch(SQLException ex) {
            throw new ServiceFailureException(
                    "Error when updating captivity " + captivity, ex);
        }
    }

    @Override
    public void deleteCaptivity(Long id) {
        if(id == null) throw new IllegalArgumentException("captivity id is null");
        if(id < 0) throw new IllegalArgumentException("captivity id is negative");
        
        try(Connection conn = dataSource.getConnection();
            PreparedStatement st = conn.prepareStatement(
            "DELETE FROM captivity WHERE id=?")){

                st.setLong(1,id);
                
                if(st.executeUpdate()!=1) {
                    throw new ServiceFailureException("could not delete captivity with id ="+id);
                
            }
        } catch(SQLException ex) {
            throw new ServiceFailureException(
                    "Error when deleting captivity " + id, ex);
        }
    }

    @Override
    public List<Captivity> findAllCaptivities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Long getKey(ResultSet keyRS, Captivity captivity) throws ServiceFailureException, SQLException {
        if (keyRS.next()) {
            if (keyRS.getMetaData().getColumnCount() != 1) {
                throw new ServiceFailureException("Error : Generated Key"
                        + "retrieving failed when trying to insert captivity " + captivity
                        + " - wrong key fields count: " + keyRS.getMetaData().getColumnCount());
            }
            Long result = keyRS.getLong(1);
            if (keyRS.next()) {
                throw new ServiceFailureException("Error : Generated key"
                        + "retrieving failed when trying to insert captivity " + captivity
                        + " - more keys found");
            }
            return result;
        } else {
            throw new ServiceFailureException("Error : Generated key"
                    + "retrieving failed when tyring to insert captivity " + captivity
                    + " - no key found");
        }
    }

    private Captivity resultSetToCaptivity(ResultSet rs) throws SQLException {
        Captivity captivity = new Captivity();
        captivity.setId(rs.getLong("id"));
        captivity.setCapacity(rs.getInt("capacity"));
        captivity.setPavilion(rs.getString("pavilion"));
        captivity.setInfo(rs.getString("info"));
        return captivity;
    }

}