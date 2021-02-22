import { createApp } from "vue";
import axios from "axios";
import router from "./router.js";
import store from "./store/index.js";
import App from "./App.vue";

import "./assets/styles/index.css";

import BaseCard from "./components/ui/BaseCard.vue";
import BaseButton from "./components/ui/BaseButton.vue";
import BaseModal from "./components/ui/BaseModal.vue";
import BaseDropdown from "./components/ui/BaseDropdown.vue";
import BaseSpinner from "./components/ui/BaseSpinner.vue";
import BaseDialog from "./components/ui/BaseDialog.vue";
import BaseTab from "./components/ui/BaseTab.vue";

const app = createApp(App);

app.config.globalProperties.axios = axios;

app.use(router);
app.use(store);

app.component("base-card", BaseCard);
app.component("base-button", BaseButton);
app.component("base-modal", BaseModal);
app.component("base-dropdown", BaseDropdown);
app.component("base-spinner", BaseSpinner);
app.component("base-dialog", BaseDialog);
app.component("base-tab", BaseTab);

app.mount("#app");
