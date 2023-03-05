import React, { Component } from 'react'

import AuthenticationService from '../APIServices/AuthenticationService';
import CustomerService from '../APIServices/CustomerService';
export default class EditCustomerComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            errorOccured: false,
            errorMsg: '',
            isSuccess: false,
            successMsg: '',
            id: this.props.params.id,
            customer: {},
            permanentAddress: {},
            currentAddress: {},
            firstName: '',
            lastName: '',
            gender: '',
            email: '',
            designation: '',
            phone: '',


            state: '',
            city: '',
            district: '',
            pincode: '',
            addressLine: '',
            current_state: '',
            current_city: '',
            current_district: '',
            current_pincode: '',
            current_addressLine: ''
        }
        this.refresh = this.refresh.bind(this)
        this.updateCustomer = this.updateCustomer.bind(this)
        this.handleChange = this.handleChange.bind(this)
    }

    handleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })

    }

    updateCustomer = (e) => {
        e.preventDefault();
        const firstName = this.state.firstName
        const lastName = this.state.lastName
        const designation = this.state.designation
        const gender = this.state.gender
        const email = this.state.email
        const phone = this.state.phone

        const permanentAddress = {
            state: this.state.state,
            city: this.state.city,
            district: this.state.district,
            pincode: this.state.pincode,
            addressLine: this.state.addressLine
        }

        const currentAddress = {
            current_state: this.state.current_state,
            current_city: this.state.current_city,
            current_district: this.state.current_district,
            current_pincode: this.state.current_pincode,
            current_addressLine: this.state.current_addressLine
        }

        CustomerService.updateById(this.state.id, firstName, lastName, gender, phone, email, designation, permanentAddress, currentAddress)
            .then(response => {
                this.setState({
                    isSuccess: true,
                    errorOccured: false,
                    successMsg: 'Customer Updated'
                })
            }).catch(error => {
                if (error.response.status === 403 || error.response.status === 401) {
                    AuthenticationService.logout()
                    this.props.navigate(`/login`)
                }
                this.setState({
                    errorOccured: true,
                    isSuccess: false,
                    errorMsg: error.response.data.message
                })
            })
        console.log(this.state)
    }

    refresh() {
        CustomerService.getById(this.state.id)
            .then(response => {
                // console.log(response.data)
                this.setState({
                    customer: response.data,
                    permanentAddress: response.data.permanentAddress,
                    currentAddress: response.data.currentAddress,
                    firstName: response.data.firstName,
                    lastName: response.data.lastName,
                    gender: response.data.gender,
                    email: response.data.email,
                    designation: response.data.designation,
                    phone: response.data.phone,

                    state: response.data.permanentAddress.state,
                    city: response.data.permanentAddress.city,
                    district: response.data.permanentAddress.district,
                    pincode: response.data.permanentAddress.pincode,
                    addressLine: response.data.permanentAddress.addressLine,

                    current_state: response.data.currentAddress.current_state,
                    current_city: response.data.currentAddress.current_city,
                    current_district: response.data.currentAddress.current_district,
                    current_pincode: response.data.currentAddress.current_pincode,
                    current_addressLine: response.data.currentAddress.current_addressLine,



                })
            }).catch(error => {
                if (error.response.status === 403 || error.response.status === 401) {
                    AuthenticationService.logout()
                    this.props.navigate(`/login`)
                }
            })
    }

    componentDidMount() {
        this.refresh()
    }
    render() {
        return (
            <div className="EditCustomerComponent">
                <div className="container-fluid min-vh-100 bg-transparent">
                    <div className="row px-5 py-4">
                        <div className="card border-0 shadow rounded-3 my-1">
                            <div className="card-body p-0">

                                <form onSubmit={this.updateCustomer}>

                                    <div className="row border-style-none m-0 p-0">

                                        <div className="col-md border-right">
                                            <div className="p-3 py-5">
                                                {this.state.isSuccess && <div className="alert alert-success text-center">
                                                    {this.state.successMsg}
                                                </div>}

                                                {this.state.errorOccured && <div className="alert alert-warning text-center">
                                                    {this.state.errorMsg}
                                                </div>}
                                                <div className='bg-dark text-light text-center p-3 text-uppercase mb-3' style={{ border: "1px solid #000000", padding: "5px", margin: "0px" }}>
                                                    Edit Customer
                                                </div>

                                                <div className="row">
                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" name="firstName" onChange={this.handleChange} value={this.state.firstName} placeholder="Enter full name" required className="form-control" />
                                                            <label htmlFor="floatingInput">First Name</label>
                                                        </div>
                                                    </div>


                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" name="lastName" onChange={this.handleChange} value={this.state.lastName} placeholder="Enter last name" required className="form-control" />
                                                            <label htmlFor="floatingInput">Last Name</label>
                                                        </div>
                                                    </div>

                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <select className="form-select" onChange={this.handleChange} value={this.state.gender} aria-label="Gender" name="gender">
                                                                <option value="MALE">MALE</option>
                                                                <option value="FEMALE">FEMALE</option>
                                                                <option value="OTHERS">OTHERS</option>
                                                            </select>
                                                            <label htmlFor="floatingInput">Gender</label>
                                                        </div>
                                                    </div>

                                                </div>



                                                <div className="row">
                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input id='email' type="text" name="email" onChange={this.handleChange} value={this.state.email} placeholder="Enter email" required className="form-control" />
                                                            <label htmlFor="floatingInput">E-mail</label>
                                                        </div>
                                                    </div>
                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="tel" onChange={this.handleChange} value={this.state.phone} name="phone" pattern="[123456789][0-9]{9}" required placeholder="Enter phone number" className="form-control" />
                                                            <label htmlFor="floatingInput">Phone</label>
                                                        </div>
                                                    </div>
                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" name="designation" onChange={this.handleChange} value={this.state.designation} placeholder="Enter designation" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">Designation</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className=' text-dark fw-bold fs-5 text-left  text-uppercase mb-3' >
                                                    Permanent Address
                                                </div>
                                                <div className="row">
                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" onChange={this.handleChange} value={this.state.state} name="state" placeholder="Enter state" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">State</label>
                                                        </div>
                                                    </div>
                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" onChange={this.handleChange} value={this.state.city} name="city" placeholder="Enter city" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">City</label>
                                                        </div>
                                                    </div>
                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" onChange={this.handleChange} value={this.state.district} name="district" placeholder="Enter district" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">District</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="row">

                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" onChange={this.handleChange} value={this.state.pincode} name="pincode" placeholder="Enter pincode" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">Pincode</label>
                                                        </div>
                                                    </div>

                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" onChange={this.handleChange} value={this.state.addressLine} name="addressLine" placeholder="Enter addressLine" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">Address Line</label>
                                                        </div>
                                                    </div>
                                                </div>



                                                <div className=' text-dark fw-bold fs-5 text-left  text-uppercase mb-3' >
                                                    Current Address
                                                </div>
                                                <div className="row">
                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" onChange={this.handleChange} value={this.state.current_state} name="current_state" placeholder="Enter state" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">State</label>
                                                        </div>
                                                    </div>
                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" onChange={this.handleChange} value={this.state.current_city} name="current_city" placeholder="Enter city" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">City</label>
                                                        </div>
                                                    </div>
                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" onChange={this.handleChange} value={this.state.current_district} name="current_district" placeholder="Enter district" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">District</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="row">

                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" onChange={this.handleChange} value={this.state.current_pincode} name="current_pincode" placeholder="Enter pincode" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">Pincode</label>
                                                        </div>
                                                    </div>

                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" onChange={this.handleChange} value={this.state.current_addressLine} name="current_addressLine" placeholder="Enter addressLine" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">Address Line</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className=" text-center">
                                                    <div className="col">
                                                        <button name='update' type='submit' className="btn btn-success  text-uppercase fw-bold" >
                                                            Update Profile
                                                        </button>

                                                    </div>
                                                </div>
                                                {/* <div className=" text-center">
                                                    <div className="col">
                                                        <button onClick={this.backClicked} className="btn btn-primary  text-uppercase fw-bold" >
                                                           Go Back
                                                        </button>
                                                    </div>
                                                </div> */}
                                            </div>
                                        </div>



                                    </div>
                                </form>



                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}
