package com.nexti.api.control.controller;

import com.nexti.api.control.dto.*;
import com.nexti.api.control.service.person.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{personId}")
    public PersonResponseDto findPerson(@PathVariable Long personId) {
        return findPersonResponseService.find(personId);
    }

    @PostMapping("/")
    public ResponseEntity<Void> create(@Valid @RequestBody PersonDto personDto) {
        var personId = insertPersonService.insert(personDto);
        return responseCreated(personId);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<Void> update(@PathVariable Long personId,
                                       @Valid @RequestBody PersonUpdateDto personUpdateDto) {
        updatePersonService.update(personUpdateDto, personId);
        return responseNoContent();
    }

    @PatchMapping("/{personId}")
    public ResponseEntity<Void> patch(@PathVariable Long personId,
                                      @Valid @RequestBody PersonPatchDto personPatchDto) {
        patchPersonService.patch(personPatchDto, personId);
        return responseNoContent();
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> delete(@PathVariable Long personId) {
        deletePersonService.delete(personId);
        return responseNoContent();
    }
}