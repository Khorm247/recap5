package de.neuefische.recap5.controller;

import de.neuefische.recap5.service.SuperKanbanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/todo")
public class SuperKanbanController {
    private final SuperKanbanService service;

    public SuperKanbanController(){

    }
}
