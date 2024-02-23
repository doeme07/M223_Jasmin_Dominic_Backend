package com.example.demo.domain.mylistentry.dto;

import com.example.demo.core.generic.AbstractMapper;
import com.example.demo.domain.mylistentry.MyListEntry;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.dto.UserDTO;
import com.example.demo.domain.user.dto.UserMinimalMapper;
import com.example.demo.domain.user.dto.UserRegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMinimalMapper.class})
public interface MyListEntryMapper extends  AbstractMapper<MyListEntry, MyListEntryDTO> {

}
