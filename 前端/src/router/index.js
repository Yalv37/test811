import { createRouter,createWebHistory } from "vue-router"
import ContainerC from '../components/ContainerC'
import StudentC from '../components/StudentC'
import CourseC from '../components/CourseC'

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
    ]
}]

const routers=createRouter({
    history:createWebHistory(),
    routes:routes
})

export default routers;