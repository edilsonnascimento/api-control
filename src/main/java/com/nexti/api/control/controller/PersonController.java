package com.nexti.api.control.controller;

import com.nexti.api.control.dto.*;
import com.nexti.api.control.service.person.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/people")
@RequiredArgsConstructor
public class PersonController extends BaseRestController {

    private final InsertPersonService insertPersonService;
    private final FindPersonResponseService findPersonResponseService;
    private final FindPeopleService findPeopleService;
    private final UpdatePersonService updatePersonService;
    private final PatchPersonService patchPersonService;
    private final DeletePersonService deletePersonService;

    @GetMapping("/")
    public Page<PersonResponseDto> findPeople(@PageableDefault(page = 0,
                                                               size = 10,
                                                               sort = "enrolment",
                                                               direction = Sort.Direction.DESC) Pageable pageable) {
        return findPeopleService.find(pageable);
    }

    @GetMapping("/{uuid}")
    public PersonResponseDto findPerson(@PathVariable(value = "uuid") UUID personUuid) {
        return findPersonResponseService.find(personUuid);
    }

    @PostMapping("/")
    public ResponseEntity<Void> create(@Valid @RequestBody PersonDto personDto) {
        UUID uuidCreated = insertPersonService.insert(personDto);
        return responseCreated(uuidCreated);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Void> update(@PathVariable(value = "uuid") UUID personUuid,
                                       @Valid @RequestBody PersonUpdateDto personUpdateDto) {
        updatePersonService.update(personUpdateDto, personUuid);
        return responseNoContent();
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<Void> patch(@PathVariable(value = "uuid") UUID personUuid,
                                      @Valid @RequestBody PersonPatchDto personPatchDto) {
        patchPersonService.patch(personPatchDto, personUuid);
        return responseNoContent();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable(value = "uuid") UUID personUuid) {
        deletePersonService.delete(personUuid);
        return responseNoContent();
    }
}