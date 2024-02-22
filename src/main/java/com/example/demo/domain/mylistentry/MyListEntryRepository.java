package com.example.demo.domain.mylistentry;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MyListEntryRepository extends JpaRepository<MyListEntry, UUID> {

}
