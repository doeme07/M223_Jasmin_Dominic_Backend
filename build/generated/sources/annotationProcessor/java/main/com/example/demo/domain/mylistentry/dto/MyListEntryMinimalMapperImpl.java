package com.example.demo.domain.mylistentry.dto;

import com.example.demo.domain.mylistentry.MyListEntry;
import com.example.demo.domain.user.dto.UserMinimalMapper;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-28T16:15:57+0100",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.0.jar, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class MyListEntryMinimalMapperImpl implements MyListEntryMinimalMapper {

    @Autowired
    private UserMinimalMapper userMinimalMapper;

    @Override
    public MyListEntry fromDTO(MyListEntryMinimalDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MyListEntry myListEntry = new MyListEntry();

        myListEntry.setId( dto.getId() );
        myListEntry.setTitle( dto.getTitle() );
        myListEntry.setText( dto.getText() );
        myListEntry.setImportance( dto.getImportance() );
        myListEntry.setUser( userMinimalMapper.fromDTO( dto.getUser() ) );

        return myListEntry;
    }

    @Override
    public List<MyListEntry> fromDTOs(List<MyListEntryMinimalDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<MyListEntry> list = new ArrayList<MyListEntry>( dtos.size() );
        for ( MyListEntryMinimalDTO myListEntryMinimalDTO : dtos ) {
            list.add( fromDTO( myListEntryMinimalDTO ) );
        }

        return list;
    }

    @Override
    public Set<MyListEntry> fromDTOs(Set<MyListEntryMinimalDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        Set<MyListEntry> set = new LinkedHashSet<MyListEntry>( Math.max( (int) ( dtos.size() / .75f ) + 1, 16 ) );
        for ( MyListEntryMinimalDTO myListEntryMinimalDTO : dtos ) {
            set.add( fromDTO( myListEntryMinimalDTO ) );
        }

        return set;
    }

    @Override
    public MyListEntryMinimalDTO toDTO(MyListEntry BO) {
        if ( BO == null ) {
            return null;
        }

        MyListEntryMinimalDTO myListEntryMinimalDTO = new MyListEntryMinimalDTO();

        myListEntryMinimalDTO.setId( BO.getId() );
        myListEntryMinimalDTO.setTitle( BO.getTitle() );
        myListEntryMinimalDTO.setText( BO.getText() );
        myListEntryMinimalDTO.setImportance( BO.getImportance() );
        myListEntryMinimalDTO.setUser( userMinimalMapper.toDTO( BO.getUser() ) );

        return myListEntryMinimalDTO;
    }

    @Override
    public List<MyListEntryMinimalDTO> toDTOs(List<MyListEntry> BOs) {
        if ( BOs == null ) {
            return null;
        }

        List<MyListEntryMinimalDTO> list = new ArrayList<MyListEntryMinimalDTO>( BOs.size() );
        for ( MyListEntry myListEntry : BOs ) {
            list.add( toDTO( myListEntry ) );
        }

        return list;
    }

    @Override
    public Set<MyListEntryMinimalDTO> toDTOs(Set<MyListEntry> BOs) {
        if ( BOs == null ) {
            return null;
        }

        Set<MyListEntryMinimalDTO> set = new LinkedHashSet<MyListEntryMinimalDTO>( Math.max( (int) ( BOs.size() / .75f ) + 1, 16 ) );
        for ( MyListEntry myListEntry : BOs ) {
            set.add( toDTO( myListEntry ) );
        }

        return set;
    }
}
