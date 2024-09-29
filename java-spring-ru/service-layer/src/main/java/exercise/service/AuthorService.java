package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> getAll() {
        var authors = authorRepository.findAll();
        var result = authors.stream()
                .map(authorMapper::map)
                .toList();
        return result;
    }

    public AuthorDTO findById(long id) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Author with id %s not found", id)
                ));
        var authorDTO = authorMapper.map(author);

        return authorDTO;
    }

    public AuthorDTO create(AuthorCreateDTO data) {
        var author = authorMapper.map(data);
        authorRepository.save(author);
        var authorDTO = authorMapper.map(author);

        return authorDTO;
    }

    public AuthorDTO update(long id, AuthorUpdateDTO data) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Author with id %s not found", id)
                ));
        authorMapper.update(data, author);
        authorRepository.save(author);
        var authorDTO = authorMapper.map(author);

        return authorDTO;
    }

    public void delete(long id) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Author with id %s not found", id)
                ));
        authorRepository.delete(author);
    }
    // END
}
