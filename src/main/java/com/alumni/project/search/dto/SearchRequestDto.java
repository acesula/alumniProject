package com.alumni.project.search.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class SearchRequestDto {
    private List<SearchCriteria> searchCriteria;

    private GlobalOperator globalOperator;


    public enum GlobalOperator {
        AND, OR;
    }
}
