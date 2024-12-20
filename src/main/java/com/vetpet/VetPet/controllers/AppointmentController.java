package com.vetpet.VetPet.controllers;

import com.vetpet.VetPet.dto.RequestAppointmentDto;
import com.vetpet.VetPet.dto.ResponseAppointmentDto;
import com.vetpet.VetPet.services.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<ResponseAppointmentDto> createAppointment(@Valid @RequestBody RequestAppointmentDto dto) {
        ResponseAppointmentDto createdAppointment = appointmentService.createAppointment(dto);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }
//GET//
    @GetMapping("/{id}")
    public ResponseEntity<ResponseAppointmentDto> getAppointmentById(@PathVariable Long id) {
        ResponseAppointmentDto appointment = appointmentService.getAppointmentById(id);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResponseAppointmentDto>> getAllAppointments() {
        List<ResponseAppointmentDto> appointments = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/next")
    public ResponseEntity<List<ResponseAppointmentDto>> getNextAppointmentsByPetId(@RequestParam Long petId) {
        List<ResponseAppointmentDto> nextAppointments = appointmentService.getNextAppointmentsByPetId(petId);
        return new ResponseEntity<>(nextAppointments, HttpStatus.OK);
    }

    @GetMapping("/past")
    public ResponseEntity<List<ResponseAppointmentDto>> getPastAppointmentsByPetId(@RequestParam Long petId) {
        List<ResponseAppointmentDto> pastAppointments = appointmentService.getPastAppointmentsByPetId(petId);
        return new ResponseEntity<>(pastAppointments, HttpStatus.OK);
    }
//UPDATE//
    @PutMapping("/{id}")
    public ResponseEntity<ResponseAppointmentDto> updateAppointment(@PathVariable Long id, @Valid @RequestBody RequestAppointmentDto dto) {
        ResponseAppointmentDto updatedAppointment = appointmentService.updateAppointment(id, dto);
        return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return new ResponseEntity<>("Appointment has been deleted.",HttpStatus.NO_CONTENT);
    }
}
