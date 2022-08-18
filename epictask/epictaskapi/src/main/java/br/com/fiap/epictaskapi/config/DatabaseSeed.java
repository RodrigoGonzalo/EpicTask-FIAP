package br.com.fiap.epictaskapi.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.repository.TaskRepository;

public class DatabaseSeed  implements CommandLineRunner{

    @Autowired
    TaskRepository repository;

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        Task t1 = new Task( "Modelar BD", "Modelar tabelas do banco", 150);

        repository.saveAll(List.of(
            new Task( "Modelar BD", "Modelar tabelas do banco", 150),
            new Task( "Prototipo", "prototipar as telas", 250),
            new Task( "Bug", "corrgir o erro da API", 50)
            ));
    }
    
}
