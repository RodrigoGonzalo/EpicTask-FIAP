package br.com.fiap.epictaskapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService service;
    
    @GetMapping
    public List<Task> index(){
        return service.listAll();
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody @Valid Task task){
        service.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }


    @GetMapping("{id}")
    public ResponseEntity<Task> show(@PathVariable Long id){
       return ResponseEntity.of(service.getById(id));
    } 
    
    //seeding

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){

        Optional<Task> optional = service.getById(id);

        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("{id}")
    public ResponseEntity<Task> update(@PathVariable long id, @RequestBody @Valid Task newTask){
        //buscar a tarefa bo banco
        Optional<Task> optional = service.getById(id);

        //verifiar se existe tarefa com esse id
        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        // atualizar dados no objeto
        var task = optional.get();
        BeanUtils.copyProperties(newTask, task);
        task.setId(id);

        //salvar no bd
        service.save(task);

        return ResponseEntity.ok(task);

    }


}
