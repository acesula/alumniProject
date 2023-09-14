package com.alumni.project.search.service;

import com.alumni.project.dal.entity.User;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.user.UserDto;
import com.alumni.project.search.dto.SearchCriteria;
import com.alumni.project.search.dto.SearchRequestDto;
import com.alumni.project.service.mapping.MappingService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FilterServiceImpl implements FilterService {

    private final UserRepository userRepository;
    private final MappingService mappingService;

    public Specification<User> getSearchSpecification(SearchCriteria searchCriteria) {
        return (root, query, criteriaBuilder) -> {
            if (Objects.equals(searchCriteria.getOperation(), SearchCriteria.Operation.EQUAL)) {
                return criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
            } else if (Objects.equals(searchCriteria.getOperation(), SearchCriteria.Operation.LIKE)) {
                return criteriaBuilder.like(root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%");
            } else if (Objects.equals(searchCriteria.getOperation(), SearchCriteria.Operation.JOIN)) {
                return criteriaBuilder.equal(root.get(searchCriteria.getJoinTable()).get(searchCriteria.getKey()), searchCriteria.getValue());
            } else {
                throw new IllegalArgumentException("Unexpected operation: " + searchCriteria.getOperation());
            }
        };
    }

    public Specification<User> getSearchSpecification(List<SearchCriteria> searchCriteria, SearchRequestDto.GlobalOperator globalOperator) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (SearchCriteria searchCriteria1 : searchCriteria) {
                Predicate predicate = getSearchSpecification(searchCriteria1).toPredicate(root, query, criteriaBuilder);
                predicates.add(predicate);
            }

            if (globalOperator.equals(SearchRequestDto.GlobalOperator.AND)) {
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            } else {
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }
        };
    }

    @Override
    public List<UserDto> getUsers(SearchRequestDto searchRequestDto) {
        Specification<User> userSpecification = getSearchSpecification(searchRequestDto.getSearchCriteria(), searchRequestDto.getGlobalOperator());
        List<User> users = userRepository.findAll(userSpecification);

        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            var userDto = mappingService.convertToUserDto(user);
            userDtos.add(userDto);
        }

        return userDtos;
    }


}
