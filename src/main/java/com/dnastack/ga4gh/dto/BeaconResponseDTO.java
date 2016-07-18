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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jim
 */
@XmlRootElement(name = "BEACONResponse")
public class BeaconResponseDTO {

    private String name;
    private Boolean exists;
    private Long alleleFreq;

    public BeaconResponseDTO() {

    }

    public BeaconResponseDTO(String name, Boolean exists) {
        this.exists = exists;
        this.alleleFreq = null;
    }

    public BeaconResponseDTO(String name, Boolean exists, Long alleleFreq) {
        this.exists = exists;
        this.alleleFreq = alleleFreq;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "exists")
    public Boolean getExists() {
        return exists;
    }

    public void setExists(Boolean exists) {
        this.exists = exists;
    }

    @XmlElement(name = "frequency")
    public Long getAlleleFreq() {
        return alleleFreq;
    }

    public void setAlleleFreq(Long alleleFreq) {
        this.alleleFreq = alleleFreq;
    }
}
