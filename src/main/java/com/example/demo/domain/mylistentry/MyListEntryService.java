package com.example.demo.domain.mylistentry;

import com.example.demo.core.exception.NoMyListEntryByIdFoundException;
import com.example.demo.domain.mylistentry.dto.MyListEntryMinimalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyListEntryService {

    @Autowired
    MyListEntryRepository myListEntryRepository;

    // Method to create a new MyListEntry
    public MyListEntry createMyListEntry(MyListEntry myListEntry1) {
        return myListEntryRepository.save(myListEntry1);
    }

    // Method to get all MyListEntries
    public List<MyListEntry> getMyListEntries() {
        return myListEntryRepository.findAll();
    }

    // Method to get a MyListEntry by its ID
    public MyListEntry getMyListEntryById(UUID id) throws NoMyListEntryByIdFoundException {
        return myListEntryRepository.findById(id).orElseThrow(() -> new NoMyListEntryByIdFoundException("No MyListEntry by Id found: " + id));
    }

    // Method to delete a MyListEntry by its ID
    public void deleteMyListEntry(UUID myListEntryId) {
        myListEntryRepository.deleteById(myListEntryId);
    }

    // Method to update a MyListEntry by its ID
    public MyListEntry updateMyListEntry(UUID myListEntryId, MyListEntryMinimalDTO myListEntryDetails) throws NoMyListEntryByIdFoundException {
        MyListEntry myListEntry1 = myListEntryRepository.findById(myListEntryId).orElseThrow(() -> new NoMyListEntryByIdFoundException("No MyListEntry by Id found on update: " + myListEntryId));
        myListEntry1.setTitle(myListEntryDetails.getTitle());
        myListEntry1.setImportance(myListEntryDetails.getImportance());
        myListEntry1.setText(myListEntryDetails.getText());
        myListEntry1.setCreationDate(myListEntryDetails.getCreationDate());
        return myListEntryRepository.save(myListEntry1);
    }

    // Method to retrieve a sorted list of MyListEntries of a specific user by importance
    public List<MyListEntry> sortedMyListEntryListOfSpecificUserByImportance(UUID userId){
        Sort sortByImportanceDescending = Sort.by("importance").descending();
        return myListEntryRepository.findByUserId(userId, sortByImportanceDescending);
    }
}
