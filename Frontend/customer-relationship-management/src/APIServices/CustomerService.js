import { CUSTOMER_SERVICE } from "../Constants/Constants"
import axios from "axios"
export default new class CustomerService{
    getAll(){
        const token=sessionStorage.getItem('token')
        const headers = {
            'Authorization': `Bearer ${token}`
        }
        return axios.get(`${CUSTOMER_SERVICE}/getAll`,{
            headers:headers
        })
    }

    getById(id){
        const token=sessionStorage.getItem('token')
        const headers = {
            'Authorization': `Bearer ${token}`
        }
        return axios.get(`${CUSTOMER_SERVICE}/getById/${id}`,{
            headers:headers
        })
    }

    deleteById(id){
        const token=sessionStorage.getItem('token')
        const headers = {
            'Authorization': `Bearer ${token}`
        }
        return axios.delete(`${CUSTOMER_SERVICE}/delete/${id}`,{
            headers:headers
        })
    }

    addCustomer(firstName,lastName,gender,phone,email,designation,permanentAddress,currentAddress){
        const token=sessionStorage.getItem('token')
        const headers = {
            'Authorization': `Bearer ${token}`
        }
        const body = {
            firstName: firstName,
            lastName: lastName,
            designation: designation,
            gender: gender,
            email: email,
            phone: phone,
            permanentAddress:permanentAddress,
            currentAddress:currentAddress
        }
        return axios.post(`${CUSTOMER_SERVICE}/create`,body,{
            headers:headers
        })
    }

    updateById(id,firstName,lastName,gender,phone,email,designation,permanentAddress,currentAddress){
        const token=sessionStorage.getItem('token')
        const headers = {
            'Authorization': `Bearer ${token}`
        }
        const body = {
            firstName: firstName,
            lastName: lastName,
            designation: designation,
            gender: gender,
            email: email,
            phone: phone,
            permanentAddress:permanentAddress,
            currentAddress:currentAddress
        }
        return axios.put(`${CUSTOMER_SERVICE}/update/${id}`,body,{
            headers:headers
        })
    }
}