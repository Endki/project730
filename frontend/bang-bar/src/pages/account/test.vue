<template>
    <div>
        <button @click="getMembers()">클릭</button>

    </div>
</template>
<script>
export default {
    data() {
        return {
            result:[]
        }
    },
    
    methods: {
          getMembers () {
            this.axios.get('http://localhost:8081/admin/members', {
                headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json; charset = utf-8'
                }
            })
            .then((result)=>{
                console.log(result)
            })
            .catch(e=>{
                console.log('error:',e)
            })
        },
    },
}
</script>
<style scoped>
</style>