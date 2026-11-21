package org.mycode.myminiprogram.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mycode.myminiprogram.dto.TaskDto;
import org.mycode.myminiprogram.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    //获取所有任务
    public List<Task> getAllTasks();
    //分页查询数据
    public Page<Task> getPageTask(long current,long size);
    //获取特定的任务
    public Optional<Task> getTaskById(Long id);
    //通过关键词获取任务
    public List<Task> getTaskByKey(String keyword);
    //新建任务
    public Task createTask(Task task);
    //修改任务
    public Optional<Task> updateTask(Long id,Task newTask);
    //删除任务
    public  boolean delTask(Long id);
}
