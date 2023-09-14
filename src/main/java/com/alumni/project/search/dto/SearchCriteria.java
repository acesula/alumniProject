package com.alumni.project.search.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {
    String key;
    Object value;
    Operation operation;
    String joinTable;

    public enum Operation {
        EQUAL, LIKE, JOIN;
    }
}
