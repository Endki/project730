<template>
    <div class="card" :class="size">
        <slot></slot>
    </div>
</template>

<script>
export default {
    props: {
        size: {
            type: String,
            required: false,
            default: null,
        }
    }
}
</script>

<style scoped>
.card {
  border-radius: 30px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
  padding: 1rem;
  margin: 20px;
  background-color: white;
}

.box-290 {
    width: 290px;
}

.box-340 {
    width: 340px;
}

.box-400 {
    width: 400px;
}

.box-620 {
    width: 620px;
}

</style>