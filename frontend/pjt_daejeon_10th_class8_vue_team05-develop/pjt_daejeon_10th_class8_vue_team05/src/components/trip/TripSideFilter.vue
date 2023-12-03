<script setup>
import { watch } from "vue";
import { useTripStore } from "../../stores/trip"
import { useTripListStore } from "../../stores/tripList"

import { storeToRefs } from "pinia";
const { category, _with } = storeToRefs(useTripListStore());


// console.log(_with)
// console.log(category)
// const emit = defineEmits(['changeResult'])

// const { result } = useTripStore();

// _with = ""
// category = []

// watch(
//     category,
//     () => {
//         // emit('changeResult', category, _with)
//         console.log(1)
//     }
// )
// category.value = [1,2,3]
// category.push(2)

</script>

<template>
    <!-- {{ category }}
    <hr>
    {{ _with }}
    <hr> -->
    <div id="main">
        <div class="option">
            <h3>동반자</h3>
            <input type="radio"  v-model="_with" class="btn-check" name="options-base" id="option" autocomplete="off" value="" checked>
            <label class="btn btn-outline-primary m-2" for="option">모두</label>
            
            <input type="radio"  v-model="_with" class="btn-check" name="options-base" id="option1" autocomplete="off" value="혼자" >
            <label class="btn btn-outline-primary m-2" for="option1">혼자</label>

            <input type="radio"  v-model="_with" class="btn-check" name="options-base" id="option2" autocomplete="off" value="친구와" >
            <label class="btn btn-outline-primary m-2" for="option2">친구와</label>

            <input type="radio"  v-model="_with" class="btn-check" name="options-base" id="option3" autocomplete="off" value="연인과">
            <label class="btn btn-outline-primary m-2" for="option3">연인과</label>

            <input type="radio"  v-model="_with" class="btn-check" name="options-base" id="option4" autocomplete="off" value="배우자와">
            <label class="btn btn-outline-primary m-2" for="option4">배우자와</label>

            <input type="radio"  v-model="_with" class="btn-check" name="options-base" id="option5" autocomplete="off" value="아이와">
            <label class="btn btn-outline-primary m-2" for="option5">아이와</label>

            <input type="radio"  v-model="_with" class="btn-check" name="options-base" id="option6" autocomplete="off" value="부모님과">
            <label class="btn btn-outline-primary m-2" for="option6">부모님과</label>

            <input type="radio" v-model="_with"  class="btn-check" name="options-base" id="option7" autocomplete="off" value="반려동물과">
            <label class="btn btn-outline-primary m-2" for="option7">반려동물과</label>

            <input type="radio" v-model="_with"  class="btn-check" name="options-base" id="option8" autocomplete="off" value="기타">
            <label class="btn btn-outline-primary m-2" for="option8">기타</label>
        </div>

        <hr>

        <div class="option">
            <h3>카테고리</h3>
            <input type="checkbox" class="btn-check" id="btn-check-outlined" autocomplete="off" v-model="category" value="체험-액티비티">
            <label class="btn btn-outline-primary m-2" for="btn-check-outlined">체험, 액티비티</label>

            <input type="checkbox" class="btn-check" id="btn-check-outlined2" autocomplete="off" v-model="category" value="SNS-핫플레이스">
            <label class="btn btn-outline-primary m-2" for="btn-check-outlined2">SNS 핫플레이스</label>
            
            <input type="checkbox" class="btn-check" id="btn-check-outlined3" autocomplete="off" v-model="category" value="자연">
            <label class="btn btn-outline-primary m-2" for="btn-check-outlined3">자연</label>
            
            <input type="checkbox" class="btn-check" id="btn-check-outlined4" autocomplete="off" v-model="category" value="유명-관광지">
            <label class="btn btn-outline-primary m-2" for="btn-check-outlined4">유명 관광지</label>
            
            <input type="checkbox" class="btn-check" id="btn-check-outlined5" autocomplete="off" v-model="category" value="힐링">
            <label class="btn btn-outline-primary m-2" for="btn-check-outlined5">힐링</label>
            
            <input type="checkbox" class="btn-check" id="btn-check-outlined6" autocomplete="off" v-model="category" value="문화-예술-역사">
            <label class="btn btn-outline-primary m-2" for="btn-check-outlined6">문화, 예술, 역사</label>
            
            <input type="checkbox" class="btn-check" id="btn-check-outlined7" autocomplete="off" v-model="category" value="체험">
            <label class="btn btn-outline-primary m-2" for="btn-check-outlined7">체험</label>
            
            <input type="checkbox" class="btn-check" id="btn-check-outlined8" autocomplete="off" v-model="category" value="쇼핑">
            <label class="btn btn-outline-primary m-2" for="btn-check-outlined8">쇼핑</label>
            
            <input type="checkbox" class="btn-check" id="btn-check-outlined9" autocomplete="off" v-model="category" value="음식">
            <label class="btn btn-outline-primary m-2" for="btn-check-outlined9">음식</label>

        </div>

    </div>
</template>

<style scoped>
#main{
    text-align: center;
    position: fixed;
    width: 20%;
}

.option{
    width: 100%;
    margin: 50px auto;
}

hr{
    width: 60%;
    display: block;
    margin: auto;
}
</style>