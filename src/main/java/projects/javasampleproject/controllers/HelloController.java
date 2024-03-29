package projects.javasampleproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/say/{name}/{id}")
    public String SayHello(@PathVariable("name") String name,
                           @PathVariable("id") int id){
        return "Hello there "+name+"!!!. You have entered Id= ." +id  ;
    }
}
