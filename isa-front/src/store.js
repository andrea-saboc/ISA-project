import Vue from 'vue';
import {createStore} from 'vuex';
import axios from "axios";

//Vue.use(Vuex);

const store = createStore({
    state: {
        title: 'vuex store',
        accessToken: null,
        expiresIn: null,
        userType: null,
        canChange: true
    },
    getters: {
        tokenString: state => {
            if (store.state.accessToken == null) {
                return '';
            } else {
                return "Bearer " + state.accessToken;
            }
        },


    },
    mutations: {
        setData: (state) => {
            let tokenData = loadFromSessionStorage();
            if (isTokenDataValid(tokenData)) {
                assignData(state, tokenData);
            }
        },

        logOut: state => {
            sessionStorage.clear();
            console.log("Logging out.")
            state.userType = null;
            state.accessToken = null;
            state.expiresIn = null;
            state.canChange = true;
        },


    },
    actions: {
        pushDataFromSession: ({commit, state}) => {
            commit('setData', {state});
        },
        setData: (context, tokenData) => {
            assignData(context.state, tokenData);
        },
        startSession: (context, tokenData) => {
            if (tokenData == null) {
                tokenData = loadFromSessionStorage();
            } else {
                saveToSessionStorage(tokenData);
            }
            if (!isTokenDataValid(tokenData)) {
                context.commit('logOut');
                return;
            }
            try {
                assignData(context.state, tokenData);
                setTimeout(function () {
                    if (context.state.userType != null &&
                        context.state.accessToken != null &&
                        Number.isInteger(context.state.expiresIn)) {
                        context.state.canChange = false;
                        axios.post(process.env.VUE_APP_BACKEND_URL + 'user/refresh ', {},
                            {
                                headers: {
                                    Authorization: 'Bearer ' + context.state.accessToken
                                }
                            }).then(
                            response => {
                                context.state.canChange = true;
                                console.log("Session is refreshed.")
                                context.dispatch('startSession', response.data).then();
                            }
                        ).catch(err => {
                            console.log(err);
                            context.commit('logOut');
                        });
                    }
                }, context.state.expiresIn / 2)
            } catch (err) {
                console.log("Error in session validation!");
                context.commit('logOut');
            }
        },

        logOut(context) {
            context.commit('logOut');
        }
    }
});

export default store;

function assignData(state, tokenData) {
    state.userType = tokenData.userType;
    state.accessToken = tokenData.accessToken;
    state.expiresIn = parseInt(tokenData.expiresIn, 10);
}

function loadFromSessionStorage() {
    let tokenData = {};
    tokenData.userType = sessionStorage.getItem('userType');
    tokenData.accessToken = sessionStorage.getItem('accessToken');
    tokenData.expiresIn = parseInt(sessionStorage.getItem('expiresIn'));
    return tokenData;
}

function isTokenDataValid(tokenData) {
    if (tokenData == null) {
        return false;
    } else if (!('userType' in tokenData &&
        'accessToken' in tokenData &&
        'expiresIn' in tokenData)) {
        return false;
    } else return !(tokenData.accessToken == null || tokenData.userType == null);
}

function saveToSessionStorage(tokenData) {
    sessionStorage.setItem('userType', tokenData.userType);
    sessionStorage.setItem('accessToken', tokenData.accessToken);
    sessionStorage.setItem('expiresIn', tokenData.expiresIn);
}