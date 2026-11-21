package org.mycode.myminiprogram.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import org.mycode.myminiprogram.dto.TaskDto;
import org.mycode.myminiprogram.entity.Task;
import org.mycode.myminiprogram.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    // 1. 声明对Service接口的依赖，使用final确保不变性
    private final TaskService taskservice;

    // 2. 使用构造器注入（推荐方式）
    // Spring会自动寻找一个TaskService类型的Bean（即TaskServiceImpl实例）并传入
    public TaskController(TaskService taskservice) {
        this.taskservice = taskservice;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskservice.getAllTasks();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Task>> getPageTask(@RequestParam(required = false,defaultValue = "1") long current,
                                  @RequestParam(required = false,defaultValue = "2") long size){
        //返回查询的对象
        Page<Task>task =  taskservice.getPageTask(current,size);

        return ResponseEntity.ok(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskservice.getTaskById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/search")
    public List<Task> getTaskByKey(@RequestParam(required = false) String keyword) {
        return taskservice.getTaskByKey(keyword);
    }

    //新建任务
    @PostMapping()
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task createTask = taskservice.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task newTask) {
        return taskservice.updateTask(id, newTask).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDto> delTask(@PathVariable Long id) {
        return taskservice.delTask(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}