package com.alumni.project.search.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {
    String key;
    String value;
    Operation operation;
    String joinTable;

    public enum Operation {
        EQUAL, LIKE, IN, GREATER_THAN, LESS_THAN, BETWEEN, JOIN;
    }
}
