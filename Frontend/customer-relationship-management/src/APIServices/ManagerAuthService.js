import axios from "axios"
import { MANAGER_SERVICE } from "../Constants/Constants"

export default new class ManagerAuthService {

    

    getManager() {
        const token=sessionStorage.getItem('token')
        const headers = {
            'Authorization': `Bearer ${token}`
        }
        return axios.get(`${MANAGER_SERVICE}/manager`, {
            headers: headers
        })
    }

    // userExists(username){
    //     return axios.get(`http://localhost:1000/auth/${username}/exists`)
    // }

    // updateUser(firstName, lastName, fatherName, gender, dob, contact, token) {
    //     const headers = {
    //         'Authorization': `Bearer ${token}`
    //     }
    //     // var username=null
    //     const body = {
    //         role: 'ROLE_USER',
    //         firstName: firstName,
    //         lastName: lastName,
    //         fatherName: fatherName,
    //         gender: gender,
    //         dob: dob,
    //         contact: contact
    //     }
    //     return axios.put('http://localhost:1000/auth/updateUser', body, {
    //         headers: headers
    //     })
    // }

    registerManager(firstName, lastName, gender, phone, username, password) {
        const body = {
            firstName: firstName,
            lastName: lastName,
            gender: gender,
            phone: phone,
            username: username,
            password: password
        }
       
        return axios.post(`${MANAGER_SERVICE}/register`, body,)
    }

    getToken(username, password) {
        const credentials = {
            'username': username,
            'password': password
        }
        return axios.post(`${MANAGER_SERVICE}/login`, credentials, )
    }
}