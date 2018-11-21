/*
 * Copyright 2016 DNAstack
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dnastack.ga4gh.dto;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author jim
 */
@XmlRootElement(name = "BEACONResponses")
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