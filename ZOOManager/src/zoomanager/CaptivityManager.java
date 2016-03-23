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
public interface CaptivityManager {

    void createCaptivity(Captivity captivity);

    Captivity getCaptivity(Long id);

    void updateCaptivity(Captivity captivity);

    void deleteCaptivity(Long id);

    List<Captivity> findAllCaptivities();

}
