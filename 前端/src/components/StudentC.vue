<template>
    <h1>学生列表</h1>
    <el-button type="primary" @click="getStudents">获取学生信息</el-button>
    <el-button type="primary" @click="addStudent">新增</el-button>
    <el-table :data="students" stripe style="width: 100%">
        <el-table-column prop="id" label="学号">
            <template #default="scope">
            <el-input
                v-if="scope.row.isEdit"
                v-model="scope.row.id"
                type="text"
                placeholder="请填写"
            />
            <span v-else>{{ scope.row.id }}</span>
            </template>
        </el-table-column>
        <el-table-column prop="name" label="姓名">
            <template #default="scope">
            <el-input
                v-if="scope.row.isEdit"
                v-model="scope.row.name"
                type="text"
                placeholder="请填写"
            />
            <span v-else>{{ scope.row.name }}</span>
            </template>
        </el-table-column>
        <el-table-column prop="gender" label="性别">
            <template #default="scope">
            <el-input
                v-if="scope.row.isEdit"
                v-model="scope.row.gender"
                type="text"
                placeholder="请填写"
            />
            <span v-else>{{ scope.row.gender }}</span>
            </template>
        </el-table-column>
        <el-table-column prop="gpa" label="绩点">
            <template #default="scope">
            <el-input
                v-if="scope.row.isEdit"
                v-model="scope.row.gpa"
                type="number"
                placeholder="请填写"
            />
            <span v-else>{{ scope.row.gpa }}</span>
            </template>
        </el-table-column>
        <el-table-column label="操作" fixed="right">
            <template #default="scope">
            <div v-if="scope.row.isEdit">
                <el-button type="primary" @click="handleRowSave(scope.row)">保存</el-button>
                <el-button type="danger" @click="handleRowCancel(scope.row, scope.$index)
                ">取消</el-button>
            </div>
            <div v-else>
                <el-button type="primary" @click="handleRowEdit(scope.row)"> 编辑</el-button>
                <el-button type="danger" @click="handleRowDelete(scope.$index)">删除</el-button>
            </div>
            </template>
        </el-table-column>
    </el-table>
        

</template>

<script>
import axios from "axios"
import { set } from 'lodash-es'
export default{
    data(){
        return{
            students:[]
        }
    },
    methods:{
        getStudents(){
            axios({
                url:"http://localhost:8888/student",
                method:"GET"
            }).then(
                res=>{
                    this.students=res.data;
                }
            )
        },

        handleRowDelete(index)  {      
            let item = this.students[index]
            axios({
                url: "http://localhost:8888/delstudent",
                method : "DELETE",
                data : {
                    id: item.id,
                    name : "",
                    gender: "",
                    gpa : 0
                }
            }).then(res => {
                    // 如删除成功，在表格中删除该行
                console.log(res.data)
                this.students.splice(index, 1)
            }) 
        },
        // 新增联络人
        addStudent() {
            this.students.push({
                id : '',
                name: '',
                gender: '',
                gpa: '',
                isEdit: true, // 当这个值为true时表示可以编辑
                isNew: true
            })
        },
        handleRowUpdate(row)  {
            console.log(row)
            axios({
                url: "http://localhost:8888/updatestudent",
                method : "PUT",
                data : {
                        id: row.id,
                        name : row.name,
                        gender: row.gender,
                        gpa : row.gpa
                }
            }).then(res => {
                    console.log(res.data)
                    row.isEdit = false
            }) 
        },

        handleRowInsert(row)  {
            //this.students.splice(index, 1)
            // 如何区分是新数据还是旧数据 ?
            console.log(row)
            axios({
                url: "http://localhost:8888/addstudent",
                method : "POST",
                data : {
                        id: row.id,
                        name : row.name,
                        gender: row.gender,
                        gpa : row.gpa
                }
            }).then(res => {
                    console.log(res.data)
                    row.isEdit = false
                    row.isNew = false
            }) 
        },
        handleRowEdit(row){
            console.log(row)
            if(!row.isEdit){
                row.isEdit=true
            }
        },
        handleRowSave(row)  {
            //this.students.splice(index, 1)
            // 如何区分是新数据还是旧数据 ?
            console.log(row)
            if (row.isNew) {
                this.handleRowInsert(row)
            } else {
                this.handleRowUpdate(row);
            }
        },
        handleRowCancel(row, index)  {
            if (row.isNew)
                this.students.splice(index, 1)
            else {
                // TODO: 恢复单个学生数据
                set(row, 'isEdit', false)
            }
            
        }
    }
}
</script>

<style>

</style>
