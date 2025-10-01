package com.tendwa.zobbackend.Monica.services;

import com.tendwa.zobbackend.generic.enums.AppServiceStatus;
import com.tendwa.zobbackend.Monica.dtos.crud.create.CanaryCheckReq;

import java.util.UUID;

public interface MoniCaService {
    //* Register Canary
        //? Auto
    UUID registerCanary();

    //* Register Canary - Manual
        //? Manual
    UUID registerCanary(CanaryCheckReq canaryCheckReq);

    //* Run Probe
    UUID runProbe(UUID canaryId);

    //* Run all Probes
        //? Service
    UUID runAllProbes(UUID serviceId);

    //* Get the health of a service
    AppServiceStatus getServiceHealth(UUID serviceId);

    //* Auto Resolve a service
    // We had handled this, but functionality may be moved to Monica if it makes sense
    // Does it make sense for Monica to resolve incidents?
    // I do not think so... I believe Ima should be in charge of resolving incidents because she creates the incident
    // Consensus: We won't implement auto resolve here. Review and rework Ima's auto resolve
}
