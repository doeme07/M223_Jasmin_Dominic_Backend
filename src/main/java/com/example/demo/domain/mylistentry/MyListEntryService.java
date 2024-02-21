package com.example.demo.domain.mylistentry;

import com.example.demo.core.exception.NoMyListEntryByIdFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public MyListEntry getMyListEntryById(Long id) throws NoMyListEntryByIdFoundException {
        return myListEntryRepository.findById(id).orElseThrow(() -> new NoMyListEntryByIdFoundException("No MyListEntry by Id found"));
    }

    public void deleteMyListEntry(Long myListEntryId) {
        myListEntryRepository.deleteById(myListEntryId);
    }

    public MyListEntry updateMyListEntry(Long myListEntryId, MyListEntry myListEntryDetails) throws NoMyListEntryByIdFoundException {
        MyListEntry myListEntry1 = myListEntryRepository.findById(myListEntryId).orElseThrow(() -> new NoMyListEntryByIdFoundException("No MyListEntry by Id found on update"));
        myListEntry1.setId(myListEntryDetails.getId());
        myListEntry1.setTitle(myListEntryDetails.getTitle());
        myListEntry1.setImportance(myListEntryDetails.getImportance());
        myListEntry1.setText(myListEntryDetails.getText());
        myListEntry1.setCreationDate(myListEntryDetails.getCreationDate());
        return myListEntryRepository.save(myListEntry1);
    }
}