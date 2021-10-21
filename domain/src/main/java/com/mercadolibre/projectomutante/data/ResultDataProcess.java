package com.mercadolibre.projectomutante.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ResultDataProcess implements Serializable {


    private int code;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> fieldsValid;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ResultStats resultStats;

}
