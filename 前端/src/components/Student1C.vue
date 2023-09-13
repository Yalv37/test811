<template>
    <h1>优秀学生列表</h1>
    <el-button type="primary" @click="getStudents1">获取优秀学生信息</el-button>
    <el-table :data="students" stripe style="width: 100%">
        <el-table-column prop="id" label="学号">
    
        </el-table-column>
        <el-table-column prop="name" label="姓名">

        </el-table-column>
        <el-table-column prop="gender" label="性别">

        </el-table-column>
        <el-table-column prop="gpa" label="绩点">

        </el-table-column>
    </el-table>
        

</template>

<script>
import axios from "axios"

export default{
    data(){
        return{
            students:[]
        }
    },
    methods:{
        getStudents1(){
            axios({
                url:"http://localhost:8888/student1",
                method:"GET"
            }).then(
                res=>{
                    this.students=res.data;
                }
            )
        },
    }
}
</script>

<style>

</style>
