package org.mycode.myminiprogram.dto;

// 如果添加了Lombok依赖并配置了IDEA插件，可以使用@Data简化代码
 import lombok.Data;
 import lombok.AllArgsConstructor;
 import lombok.NoArgsConstructor;

 @Data //生成getter setter方法
 @NoArgsConstructor
 @AllArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private boolean completed;

    // 如果没有使用Lombok，需要手动添加构造函数、getter和setter方法
//    public TaskDto() {
//    }
//
//    public TaskDto(Long id, String title, String description, boolean completed) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
     ////        this.completed = completed;
     ////    }
     //
     ////    public Long getId() { return id; }
     ////    public void setId(Long id) { this.id = id; }
     ////    public String getTitle() { return title; }
     ////    public void setTitle(String title) { this.title = title; }
     ////    public String getDescription() { return description; }
     ////    public void setDescription(String description) { this.description = description; }
     ////    public boolean isCompleted() { return completed; } // 注意boolean类型的getter是isXXX
     ////    public void setCompleted(boolean completed) { this.completed = completed; }
     //
     //    // (可选) 重写toString, equals, hashCode方法
 }