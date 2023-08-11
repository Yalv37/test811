<template>
    <div>
    <h1>课程列表</h1>
    <el-button type="primary" @click="getCourses">获取课程信息</el-button>
    <el-button type="primary" @click="addCourse">新增</el-button>
    <el-table :data="courses" stripe style="width: 100%">
        <el-table-column prop="id" label="课程号码">
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
        <el-table-column prop="name" label="课程名称">
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
        <el-table-column prop="credit" label="课程学分">
            <template #default="scope">
            <el-input
                v-if="scope.row.isEdit"
                v-model="scope.row.credit"
                type="number"
                placeholder="请填写"
            />
            <span v-else>{{ scope.row.credit }}</span>
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
    </div>  
</template>

<script>
import axios from "axios"
import { set } from 'lodash-es'
export default{
    data(){
        return{
            courses:[]
        }
    },
    methods:{
        getCourses(){
            axios({
                url:"http://localhost:8888/course",
                method:"GET"
            }).then(
                res=>{
                    this.courses=res.data;
                }
            )
        },

        handleRowDelete(index)  {      
            let item = this.courses[index]
            axios({
                url: "http://localhost:8888/delcourse",
                method : "DELETE",
                data : {
                    id: item.id,
                    name : "",
                    credit: 0,
                }
            }).then(res => {
                console.log(res.data)
                this.courses.splice(index, 1)
            }) 
        },

        addCourse() {
            this.students.push({
                id : '',
                name: '',
                credit: '',
                isEdit: true,
                isNew: true
            })
        },
        handleRowUpdate(row)  {
            console.log(row)
            axios({
                url: "http://localhost:8888/updatecourse",
                method : "PUT",
                data : {
                        id: row.id,
                        name : row.name,
                        credit: row.credit,
                }
            }).then(res => {
                    console.log(res.data)
                    row.isEdit = false
            }) 
        },

        handleRowInsert(row)  {
            console.log(row)
            axios({
                url: "http://localhost:8888/addcourse",
                method : "POST",
                data : {
                        id: row.id,
                        name : row.name,
                        credit: row.credit
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
            console.log(row)
            if (row.isNew) {
                this.handleRowInsert(row)
            } else {
                this.handleRowUpdate(row);
            }
        },
        handleRowCancel(row, index)  {
            if (row.isNew)
                this.courses.splice(index, 1)
            else {
                set(row, 'isEdit', false)
            }
            
        }
    }
}
</script>