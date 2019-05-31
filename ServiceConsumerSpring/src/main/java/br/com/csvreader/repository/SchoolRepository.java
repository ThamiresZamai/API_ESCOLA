package br.com.csvreader.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.csvreader.model.School;

public interface SchoolRepository extends CrudRepository<School, Long> {

}