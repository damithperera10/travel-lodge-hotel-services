package com.hotelservices.service;

import com.hotelservices.dto.HealthDTO;
import com.hotelservices.constats.HealthServiceConstants;
import org.springframework.stereotype.Component;

/**
 * The Class HealthServiceImpl.
 */
@Component
public class HealthServiceImpl implements HealthService {

    @Override
    public HealthDTO getHealth() {
        return new HealthDTO(HealthServiceConstants.NAME, HealthServiceConstants.APPLICATION, HealthServiceConstants.STATUS);
    }
}
