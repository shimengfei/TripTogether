package com.hebut.triptogether.Model;


import java.util.Date;

public class route {

        private Long id;
        private String name;
        private String time;
        public Long getId(){
            return id;
        }
        public void setId(Long id){
            this.id=id;
        }
        public String getName(){
            return name ;
        }
        public void setName(String name){
            this.name=name;
        }
        public String getTime(){
            return time;
        }
        public void setTime(String time){
            this.time=time;
        }
        public route(Long id,String name,String time) {
            super();
            this.id=id;
            this.name=name;
            this.time=time;
        }
        public route(String name,String time){
            super();
            this.name=name;
            this.time=time;
        }
        public route(){
            super();
        }
        public String toString(){
            return "[序号："+id+",行程名称："+name+",时间："+time+"]";
        }

}
