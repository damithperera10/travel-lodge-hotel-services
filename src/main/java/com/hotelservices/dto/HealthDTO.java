package com.hotelservices.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class HealthDTO.
 */
public class HealthDTO {

    /**
     * Business name.
     */
    @Getter
    @Setter
    private String name;

    /**
     * Application name.
     */
    @Getter
    @Setter
    private String application;

    /**
     * Application status.
     */
    @Getter
    @Setter
    private String status;

    public HealthDTO(String name, String application, String status){
        this.name = name;
        this.application = application;
        this.status = status;
    }


}

