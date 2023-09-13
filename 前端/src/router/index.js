import { createRouter,createWebHistory } from "vue-router"
import ContainerC from '../components/ContainerC'
import StudentC from '../components/StudentC'
import CourseC from '../components/CourseC'
import Student1C from '../components/Student1C'

const routes=[{
    path:"/",
    name:"main",
    component:ContainerC,
    children:[
        {
            path:"student",
            name:"student",
            component:StudentC,
        },
        {
            path:"course",
            name:"course",
            component:CourseC,
        },
        {
            path:"student1",
            name:"student1",
            component:Student1C,
        },
    ]
}]

const routers=createRouter({
    history:createWebHistory(),
    routes:routes
})

export default routers;