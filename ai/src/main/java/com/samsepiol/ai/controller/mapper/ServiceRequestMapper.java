package com.samsepiol.ai.controller.mapper;

import com.samsepiol.ai.controller.request.AIApiRequest;
import com.samsepiol.ai.service.request.AIServiceRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ServiceRequestMapper {

    ServiceRequestMapper MAPPER = Mappers.getMapper(ServiceRequestMapper.class);

    AIServiceRequest to(AIApiRequest request, Integer count);

}
