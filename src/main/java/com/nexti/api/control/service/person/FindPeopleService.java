package com.nexti.api.control.service.person;

import com.nexti.api.control.domain.Person;
import com.nexti.api.control.dto.PersonResponseDto;
import com.nexti.api.control.repository.person.impl.PersonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class FindPeopleService {

    @Autowired
    private MountQueryParameterPersonService mountQueryParameterPersonService;
    @Autowired
    private PersonRepositoryImpl personRepository;

    public Page<PersonResponseDto> find(Pageable pageable) {
        var queryParameterDto = mountQueryParameterPersonService.findAll();
        var people = personRepository.find(queryParameterDto).orElse(new ArrayList<>());
        var content = mapResponseDtos(people);
        return new PageImpl<>(content, pageable, content.size());
    }

    private List<PersonResponseDto> mapResponseDtos(List<Person> people) {
        return people.stream()
                .map(FindPeopleService::apply)
                .collect(toList());
    }

    private static PersonResponseDto apply(Person person) {
        return PersonResponseDto.builder()
                .name(person.getName())
                .enrolment(person.getEnrolment())
                .email(person.getEmail())
                .admissionDate(person.getAdmissionDate())
                .externalId(person.getExternalId())
                .registerDate(person.getRegisterDate())
                .lastUpdateDate(person.getLastUpdateDate())
                .uuid(person.getUuid())
                .build();
    }
}
