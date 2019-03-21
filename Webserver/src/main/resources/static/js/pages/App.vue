<template>

    <div v-bind:title="'This is my Vue'">
        <h1>{{ message + ' ' + id}}</h1>
        <div :title="message">
            Error message {{message}}
        </div>
        <div>
            <template v-if="status">
                <h1>Истина</h1>
                <p>Правда правда истина</p>
            </template>
            <template v-else>
                <h1>Ложь</h1>
                <p>Правда правда ложь</p>
            </template>
        </div>
        <div>
            <h1 v-show="status" :style="{color:'red', width:width + 'px'}">
                Element with V-SHOW
            </h1>
        </div>
        <ul>
            <li v-for="(item, index) in list">index {{index + ' ' + item}}</li>
        </ul>
        <div>
            <table border="1">
                <thead>
                <th>ID</th>
                <th>name</th>
                </thead>

                <tbody>
                <tr v-for="(user, index) in users">
                    <td @click="users[index].id = users[index].id + 1">{{user.id }}</td>
                    <td >{{user.name}}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div>
            <button type="button" @click="getCity">
                Get
            </button>
            <ul>
                <li v-for="city in cities">{{ city.region }}</li>
            </ul>

        </div>
        <div>
            <input type="text" size="50" placeholder="Write something" v-model="textSearch"
                   @keyup.enter="revers (textSearch)"/>
        </div>
        <div>
            <h5>
                {{ textSearch }}
            </h5>
        </div>
    </div>

</template>

<script>
    export default {

        data() {
            return {
                list: ['one', 'two', 'three'],
                users: [{
                    id: 1,
                    name: 'John'
                },
                    {
                        id: 2,
                        name: 'Mike'
                    }
                ],
                message: 'Hello Gennadi',
                textSearch: '',
                status: true,
                id: 10,
                isActive: true,
                isBtn: true,
                width: 1000,
                cities: [],
                url: 'https://dka-develop.ru/api?type=city'
            }
        },
        watch: {//Отслеживание изменений через v-model
            textSearch: function () {
                if (this.textSearch != 0)
                    this.textSearch = textSearch
            }
        },
        created: function () {//Действия выполняемые при создании app
            // alert(this.message)//Всплывающее окн
        },
        methods: {//Содержит именованные функции
            revers(param) {//Принимает на вход параметры
                this.textSearch = param.split("").reverse().join("");

            },
            nameFunction2: function () {
                alert('Alert function');
            },
            getCity() {
                axios.get(this.url).then((response) => {
                    this.cities = response.data;
                })
            }

        }

    }
</script>

<style scoped>

</style>