<template>
    <div class="common=layout">
        <el-container>
            
            <el-container>
                <el-aside width="200px"><NavC/></el-aside>
                <el-main><router-view/></el-main>
            </el-container>
        </el-container>

    </div>
</template>

<script>
import NavC from './NavC.vue'
export default{
    name:"ContainerC",
    components:{
        NavC
    }
}
</script>