package org.mycode.myminiprogram.impl;

import ch.qos.logback.core.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.micrometer.common.util.StringUtils;
import org.mycode.myminiprogram.dto.TaskDto;
import org.mycode.myminiprogram.entity.Task;
import org.mycode.myminiprogram.exception.ResourceNotFoundException;
import org.mycode.myminiprogram.mapper.TaskMapper;
import org.mycode.myminiprogram.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.w3c.dom.ls.LSException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskServiceImpl implements TaskService {
    private final List<TaskDto> tasks = new ArrayList<>();

    private final TaskMapper taskMapper;
    //创建日志对象
    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    public TaskServiceImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override  //处理请求，返回数据
    public List<Task> getAllTasks() {
        //if (true) throw new NullPointerException("模拟的空指针");
        //return tasks;

        return taskMapper.selectList(null);



    }

    @Override
    public Page<Task> getPageTask(long current,long size){
        log.info("开始查询");
        log.debug("当前页码：{}，每页条数：{}", current, size);
        //创建分页对象
        Page<Task> page = new Page<>(current,size);

        taskMapper.selectPage(page,null);

        log.info("任务查询完成");

        //执行完后，MP会将查询结果和分页信息自动封装到我们传入的page对象中
        return page;
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
//       if (id <= 0 || id > tasks.size()){
//           throw  new ResourceNotFoundException("任务不存在");
//       }
//
//        for (TaskDto task : tasks) {
//            if (id.equals(task.getId())) {
//                return Optional.of(task);
//            }
//        }
//
//        return Optional.empty();
        //return ResponseEntity.status(404).body("找不到id为" + id +"的商品");

        Task existTask = taskMapper.selectById(id);
        if (existTask == null){
            return Optional.empty();
        }
        return Optional.of(existTask);
    }


    @Override
    public List<Task> getTaskByKey(String keyword) {
//        //还要判断是否输入为空
//        if (keyword == null || keyword.trim().isEmpty()) {
//            return tasks;
//        }
//
//        List<TaskDto> result = new ArrayList<>();
//
//        String findKeyWord = keyword.toLowerCase();
//
//
//
//        for (TaskDto task : tasks) {
//            if (task.getTitle() != null && task.getTitle().toLowerCase().contains(findKeyWord)) {
//                result.add(task);
//            }
//        }
//        return result;

//       return tasks.stream()     1. 把 List 集合变成一个流水线
//               .filter(task -> task.getTitle().toLowerCase().contains(lowerCaseKeyword))  2. 过滤器：留下符合条件的
//               .collect(Collectors.toList());   3. 把过滤后剩下的元素重新收集成一个新的 List 返回

//        方法1
//        if (keyword == null || keyword.trim().isEmpty()){
//            return taskMapper.selectList(null);
//        }
//
//        String findKeyword = keyword.toLowerCase();
//
//        QueryWrapper<Task> wrapper = new QueryWrapper<>();
//
//        wrapper.like("title",findKeyword);
//
//        return taskMapper.selectList(wrapper);

//        方法2  LambdaQueryWrapper.like()
//        LambdaQueryWrapper<Task> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//
//        //数据库字段使用不区分大小写的排序规则 就可以直接把keyword传进去
//        if (StringUtils.isNotBlank(keyword)){
//            lambdaQueryWrapper.like(Task::getTitle,keyword);
//        }
//
//        return taskMapper.selectList(lambdaQueryWrapper);

//        方法3  apply() + LOWER() 在数据比较多的时候性能会下降
        LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<>();
        //把数据库里面的title也转换成小写了
        if (StringUtils.isNotBlank(keyword)){
            wrapper.apply("LOWER(titlt) LIKE {0}" + "%" + keyword.toLowerCase()+"%");
        }

        return taskMapper.selectList(wrapper);
    }

    //新建任务
    @Override
    public Task createTask(Task task) {
        //创建这个新对象
        Task newTask = new Task();
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setCompleted(task.isCompleted());

        //执行完insert后，MP会自动将数据库生成的主键ID回填到我们传入的task对象中！
        taskMapper.insert(newTask);

        //返回结果 body + headers + status
        return  newTask;
    }

    @Override
    public Optional<Task> updateTask(Long id,Task newTask) {
//        //判断修改的任务是否存在 存在的话直接修改这个对象
//        for(TaskDto task :tasks){
//            if (task.getId().equals(id)){
//                task.setTitle(newTaskDto.getTitle());
//                task.setDescription(newTaskDto.getDescription());
//                task.setCompleted(newTaskDto.isCompleted());
//                return Optional.of(task);
//            }
//        }
//
//        return Optional.empty();

//        方法一
//        //1.找到要更新的对象
//        Task existTask = taskMapper.selectById(id);
//        if (existTask == null){
//            return Optional.empty();
//        }
//
//        //2.把id设置到新的任务对象上 前端请求体传过来的时候是不带id的
//        //id在路径上，所以要先设置一个id才知道要修改哪个
//        newTask.setId(id);
//
//        //3.修改时间
//        newTask.setUpdateTime(LocalDateTime.now());
//
//        //4.修改任务
//        taskMapper.updateById(newTask);
//
//        //5.返回对象
//        return Optional.of(newTask);


//        方法2
        LambdaUpdateWrapper<Task> wrapper = new LambdaUpdateWrapper<>();

        wrapper.eq(Task::getId,id);

        //newTask 里面可能没有 id，因为 id 是从 URL 中获取的
        newTask.setId(id);

        return taskMapper.update(newTask,wrapper) > 0 ? Optional.of(newTask) : Optional.empty();


    }

    @Override
    public boolean  delTask(Long id){
//        for (int i = 0;i < tasks.size();i++){
//            TaskDto task = tasks.get(i);
//            if (task.getId().equals(id)){
//                tasks.remove(i);
//                //没有内容 204
//                return true;
//            }
//        }
//        //找不到 404
//        return false;

        //deleteById返回的是受影响的行数
        int rowEffected = taskMapper.deleteById(id);
        return rowEffected > 0;

    }
}
