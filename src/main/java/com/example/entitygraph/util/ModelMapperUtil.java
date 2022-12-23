package com.example.entitygraph.util;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ModelMapperUtil {

    private final ModelMapper modelMapper;

    public <T, R> R dtoBuilder(T entity, Class<R> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

}
