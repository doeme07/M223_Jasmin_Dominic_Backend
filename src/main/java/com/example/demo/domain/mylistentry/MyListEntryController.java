package com.example.demo.domain.mylistentry;


import java.util.List;
import java.util.NoSuchElementException;

import com.example.demo.core.exception.NoMyListEntryByIdFoundException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Log4j2
public class MyListEntryController {
    @Autowired
    MyListEntryService myListEntryService;

    @RequestMapping(value= "/mylistentries", method=RequestMethod.POST)
    @PreAuthorize(value = "hasAuthority('CREATE')")
    @Operation(summary = "Creates a new myListEntry", description = "Creates a new myListEntry with status code 201 when successful")
    public ResponseEntity<MyListEntry> createListEntry(@Valid @RequestBody MyListEntry myListEntry1) {
        log.info("Endpoint of creating new MyListEntry was called");
        return ResponseEntity.status(HttpStatus.CREATED).body(myListEntryService.createMyListEntry(myListEntry1));
    }

    @RequestMapping(value= "/mylistentries", method=RequestMethod.GET)
    @PreAuthorize(value = "hasAuthority('READ')")
    @Operation(summary = "Fetches all MyListEntries", description = "Fetches all MyListEntries with status code 200 when successful")
    public ResponseEntity<List<MyListEntry>> readMyListEntries() {
        log.info("Endpoint of getting all MyListEntries was called");
        return ResponseEntity.status(HttpStatus.OK).body(myListEntryService.getMyListEntries());
    }

    @RequestMapping(value= "/mylistentries/{myListEntryId}", method=RequestMethod.GET)
    @PreAuthorize(value = "hasAuthority('READ')")
    @Operation(summary = "Fetches MyListEntry by Id", description = "Fetches MyListEntry by Id with status code 200 when successful")
    public ResponseEntity<MyListEntry> readMyListEntryById(@Valid @PathVariable(value = "myListEntryId") Long id) throws NoMyListEntryByIdFoundException {
        log.info("Endpoint of getting MyListEntry by Id was called");
        return ResponseEntity.status(HttpStatus.OK).body(myListEntryService.getMyListEntryById(id));
    }

    @RequestMapping(value= "/mylistentries/{myListEntryId}", method=RequestMethod.PUT)
    @PreAuthorize(value = "hasAuthority('UPDATE')")
    @Operation(summary = "Updates a MyListEntry", description = "Updates a MyListEntry with status code 201 when successful")
    public ResponseEntity<MyListEntry> updateReturn(@Valid @PathVariable(value = "myListEntryId") Long id, @RequestBody MyListEntry myListEntryDetails) throws NoMyListEntryByIdFoundException {
        log.info("Endpoint of updating a MyListEntry was called");
        return ResponseEntity.status(HttpStatus.CREATED).body(myListEntryService.updateMyListEntry(id, myListEntryDetails));
    }

    @RequestMapping(value= "/mylistentries/{myListEntryId}", method=RequestMethod.DELETE)
    @PreAuthorize(value = "hasAuthority('DELETE')")
    @Operation(summary = "Deletes a MyListEntry", description = "Deletes a MyListEntry with status code 200 when successful")
    public void deleteMyListEntry(@Valid @PathVariable(value = "myListEntryId") Long id) {
        log.info("Endpoint of deleting a MyListEntry was called");
        myListEntryService.deleteMyListEntry(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e){
        log.error("No such element");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such Element");
    }

    @ExceptionHandler(NoMyListEntryByIdFoundException.class)
    public ResponseEntity<String> handleNoReturnFoundException(NoMyListEntryByIdFoundException e){
        log.error("No MyListEntry by Id found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("Argument not Valid");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Argument not Valid!");
    }
}