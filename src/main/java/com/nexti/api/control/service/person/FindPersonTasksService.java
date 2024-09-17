package com.nexti.api.control.service.person;

import com.nexti.api.control.domain.Person;
import com.nexti.api.control.dto.PersonTasksResponseDto;
import com.nexti.api.control.service.task.FindTaskResponseDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindPersonTasksService {

    @Autowired
    private FindPersonService findPersonService;
    @Autowired
    private FindTaskResponseDtoService findTaskResponseDtoService;

    public PersonTasksResponseDto find(UUID personUuid) {
        var person = findPersonService.find(personUuid);
        return mapResponseDtos(person);
    }

    private PersonTasksResponseDto mapResponseDtos(Person person) {
        var tasks = findTaskResponseDtoService.find(person.getId());
        return PersonTasksResponseDto.builder()
                .name(person.getName())
                .enrolment(person.getEnrolment())
                .tasks(tasks)
                .build();
    }
}
