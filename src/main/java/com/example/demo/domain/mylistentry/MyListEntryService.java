package com.example.demo.domain.mylistentry;

import com.example.demo.core.exception.NoMyListEntryByIdFoundException;
import com.example.demo.domain.mylistentry.dto.MyListEntryMinimalDTO;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserDetailsImpl;
import jakarta.persistence.SecondaryTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyListEntryService {

    @Autowired
    MyListEntryRepository myListEntryRepository;

    public MyListEntry createMyListEntry(MyListEntry myListEntry1) {
        return myListEntryRepository.save(myListEntry1);
    }

    public List<MyListEntry> getMyListEntries() {
        return myListEntryRepository.findAll();
    }

    public MyListEntry getMyListEntryById(UUID id) throws NoMyListEntryByIdFoundException {
        return myListEntryRepository.findById(id).orElseThrow(() -> new NoMyListEntryByIdFoundException("No MyListEntry by Id found"));
    }

    public void deleteMyListEntry(UUID myListEntryId) {
        myListEntryRepository.deleteById(myListEntryId);
    }

    public MyListEntry updateMyListEntry(UUID myListEntryId, MyListEntryMinimalDTO myListEntryDetails) throws NoMyListEntryByIdFoundException {
        MyListEntry myListEntry1 = myListEntryRepository.findById(myListEntryId).orElseThrow(() -> new NoMyListEntryByIdFoundException("No MyListEntry by Id found on update"));
        myListEntry1.setTitle(myListEntryDetails.getTitle());
        myListEntry1.setImportance(myListEntryDetails.getImportance());
        myListEntry1.setText(myListEntryDetails.getText());
        myListEntry1.setCreationDate(myListEntryDetails.getCreationDate());
        return myListEntryRepository.save(myListEntry1);
    }

    public List<MyListEntry> sortedMyListEntryListByImportance(Set<MyListEntry> list){
        return myListEntryRepository.findAll(Sort.by("importance").descending());
    }

    public void canEditOrDeleteMyListEntry(MyListEntry entry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl currentUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = currentUser.user();
        System.out.println(user.getId());

        if (user.getId() == entry.getUser().getId()) System.out.println("can be deleted!");
    }

    private boolean isAdmin(Authentication authentication) {
        // Überprüfe, ob der Benutzer die Rolle "ADMIN" hat
        return authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));
    }
}