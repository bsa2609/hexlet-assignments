package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN
    private final String MESSAGE_TASK_NOT_FOUND_BY_ID = "Task with id %s not found";

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping("")
    public List<TaskDTO> index() {
        return taskRepository.findAll().stream()
                .map(taskMapper::map)
                .toList();
    }

    @GetMapping("/{id}")
    public TaskDTO show(@PathVariable long id) {
        var maybeTask = taskRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                        String.format(MESSAGE_TASK_NOT_FOUND_BY_ID, id)
                ));
        return taskMapper.map(maybeTask);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@Valid @RequestBody TaskCreateDTO data) {
        var task = taskMapper.map(data);
        taskRepository.save(task);

        return taskMapper.map(task);
    }

    @PutMapping("/{id}")
    public TaskDTO update(@PathVariable long id, @Valid @RequestBody TaskUpdateDTO data){
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(MESSAGE_TASK_NOT_FOUND_BY_ID, id)
                ));

        long dataAssigneeId = data.getAssigneeId();
        boolean assigneeWasChanged = task.getAssignee().getId() != dataAssigneeId;

        taskMapper.update(data, task);

        if (assigneeWasChanged) {
            var newAssignee = userRepository.findById(dataAssigneeId).
                    orElseThrow(() -> new ResourceNotFoundException(
                            String.format("User with id %s not found", dataAssigneeId)
                    ));

            task.setAssignee(newAssignee);
        }
        
        taskRepository.save(task);

        return taskMapper.map(task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable long id) {
        taskRepository.deleteById(id);
    }
    // END
}
