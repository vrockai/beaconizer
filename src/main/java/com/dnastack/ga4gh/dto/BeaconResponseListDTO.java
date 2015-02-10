/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnastack.ga4gh.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jim
 */
@XmlRootElement(name="BEACONResponses")
public class BeaconResponseListDTO {
    private List<BeaconResponseDTO> beaconResponses;

    @XmlElementRef
    public List<BeaconResponseDTO> getBeaconResponses() {
        return beaconResponses;
    }

    public void setBeaconResponses(List<BeaconResponseDTO> beaconResponses) {
        this.beaconResponses = beaconResponses;
    }
    
    
}
