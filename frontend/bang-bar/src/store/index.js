import { createStore } from 'vuex';

import authModule from './modules/auth/index.js';
import recipeModule from './modules/recipes/index.js';
import boardModule from './modules/board/index.js';
import likeModule from './modules/like/index.js';
import followModule from './modules/follow/index.js';
import userModule from './modules/users/index.js';
import adminModule from './modules/admin/index.js';
import boardLikesModule from './modules/board_like/index.js';
import tagModule from './modules/tag/index.js';
import recommendationModule from './modules/recommendationes/index.js';

// import acountModule from './modules/account/index.js';
import createPersistedState from "vuex-persistedstate";

const store = createStore ({
    modules: {
        auth: authModule,
        recipes: recipeModule,
        boards: boardModule,
        likes : likeModule,
        follows : followModule,
        users : userModule,
        main: adminModule,
        boardlikes: boardLikesModule,
        tags : tagModule,
        recommendationes: recommendationModule
    },
    plugins: [createPersistedState()],
});

export default store;
