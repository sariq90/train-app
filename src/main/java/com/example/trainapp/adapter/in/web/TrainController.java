package com.example.trainapp.adapter.in.web;

import com.example.trainapp.application.port.in.ManageTrainUseCase;
import com.example.trainapp.application.port.in.QueryTrainUseCase;
import com.example.trainapp.domain.model.Train;
import com.example.trainapp.domain.model.WagonType;
import jakarta.validation.constraints.*;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposing endpoints for CRUD, validation, and sort.
 */
@RestController
@RequestMapping("/api/train")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TrainController {

    private final ManageTrainUseCase manage;
    private final QueryTrainUseCase query;

    public TrainController(ManageTrainUseCase manage, QueryTrainUseCase query) {
        this.manage = manage;
        this.query = query;
    }

    // DTOs
    public record TrainDto(String wagons, boolean valid) {}
    public record ReplaceDto(@Min(0) int index, @Pattern(regexp = "[0ABab]") String type) {}
    public record AddDto(@Pattern(regexp = "[0ABab]") String type) {}

    private TrainDto toDto(Train train) {
        return new TrainDto(train.toCompactString(), train.isValid());
    }

    @GetMapping
    public TrainDto get() {
        return toDto(query.get());
    }

    @GetMapping("/validate")
    public boolean validate() {
        return query.validate();
    }

    @PostMapping("/reset/{compact}")
    public TrainDto reset(@PathVariable String compact) {
        return toDto(manage.reset(compact));
    }

    @PostMapping("/left/add")
    public TrainDto addLeft(@RequestBody AddDto dto) {
        return toDto(manage.addLeft(parse(dto.type())));
    }

    @PostMapping("/right/add")
    public TrainDto addRight(@RequestBody AddDto dto) {
        return toDto(manage.addRight(parse(dto.type())));
    }

    @DeleteMapping("/left")
    public TrainDto removeLeft() {
        return toDto(manage.removeLeft());
    }

    @DeleteMapping("/right")
    public TrainDto removeRight() {
        return toDto(manage.removeRight());
    }

    @PutMapping
    public TrainDto replace(@RequestBody ReplaceDto dto) {
        return toDto(manage.replaceAt(dto.index(), parse(dto.type())));
    }

    @PostMapping("/sort")
    public TrainDto sort() {
        return toDto(manage.sortOrThrow());
    }

    private WagonType parse(String s) {
        return WagonType.fromChar(s.charAt(0));
    }

}