package com.cloudsystemhq.service.util.mapping;

import com.cloudsystemhq.model.domain.Role;
import com.cloudsystemhq.model.dto.request.RoleRequestDto;
import com.cloudsystemhq.model.dto.response.RoleResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<Role, RoleRequestDto, RoleResponseDto> {

}
