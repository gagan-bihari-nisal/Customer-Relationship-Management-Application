import axios from "axios"
export default new class AuthenticationService {
    registerSuccessfulLogin(username, token) {
        console.log(token)
        sessionStorage.setItem('username', username)
        sessionStorage.setItem('token', token)

        // this.setupAxiosInterceptors('Bearer ' + token,username)
    }

    // setupAxiosInterceptors(token,username) {
    //     axios.interceptors.request.use(
    //         (config) => {
    //             if (this.isUserLoggedIn()) {
    //                 config.headers.authorization = token
    //             }
    //             return config
    //         },(error)=> {

    //             return Promise.reject(error);
    //         }
    //     )
    // }


    logout() {

        sessionStorage.removeItem('username')
        sessionStorage.removeItem('token')
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem('username')
        if (user === null) return false
        return true
    }
}