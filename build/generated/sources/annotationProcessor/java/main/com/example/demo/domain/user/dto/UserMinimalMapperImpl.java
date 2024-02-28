package com.example.demo.domain.user.dto;

import com.example.demo.domain.user.User;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-28T15:33:56+0100",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.0.jar, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class UserMinimalMapperImpl implements UserMinimalMapper {

    @Override
    public User fromDTO(UserMinimalDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );

        return user;
    }

    @Override
    public List<User> fromDTOs(List<UserMinimalDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtos.size() );
        for ( UserMinimalDTO userMinimalDTO : dtos ) {
            list.add( fromDTO( userMinimalDTO ) );
        }

        return list;
    }

    @Override
    public Set<User> fromDTOs(Set<UserMinimalDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        Set<User> set = new LinkedHashSet<User>( Math.max( (int) ( dtos.size() / .75f ) + 1, 16 ) );
        for ( UserMinimalDTO userMinimalDTO : dtos ) {
            set.add( fromDTO( userMinimalDTO ) );
        }

        return set;
    }

    @Override
    public UserMinimalDTO toDTO(User BO) {
        if ( BO == null ) {
            return null;
        }

        UserMinimalDTO userMinimalDTO = new UserMinimalDTO();

        userMinimalDTO.setId( BO.getId() );

        return userMinimalDTO;
    }

    @Override
    public List<UserMinimalDTO> toDTOs(List<User> BOs) {
        if ( BOs == null ) {
            return null;
        }

        List<UserMinimalDTO> list = new ArrayList<UserMinimalDTO>( BOs.size() );
        for ( User user : BOs ) {
            list.add( toDTO( user ) );
        }

        return list;
    }

    @Override
    public Set<UserMinimalDTO> toDTOs(Set<User> BOs) {
        if ( BOs == null ) {
            return null;
        }

        Set<UserMinimalDTO> set = new LinkedHashSet<UserMinimalDTO>( Math.max( (int) ( BOs.size() / .75f ) + 1, 16 ) );
        for ( User user : BOs ) {
            set.add( toDTO( user ) );
        }

        return set;
    }
}
