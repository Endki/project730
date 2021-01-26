import { createApp } from 'vue'

import router from './router.js';
import store from './store/index.js';
import App from './App.vue'
import BaseCard from  './components/ui/BaseCard.vue';
import BaseButton from  './components/ui/BaseButton.vue';
import BaseTag from './components/ui/BaseTag.vue';
import BaseModal from './components/ui/BaseModal.vue';

const app = createApp(App);

app.use(router);
app.use(store);

app.component('base-card', BaseCard);
app.component('base-button', BaseButton);
app.component('base-tag', BaseTag);
app.component('base-modal', BaseModal);

app.mount('#app')