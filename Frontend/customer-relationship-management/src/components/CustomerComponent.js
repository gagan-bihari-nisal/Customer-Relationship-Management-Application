import React, { Component } from 'react'
import CustomerService from '../APIServices/CustomerService'
import female from "../images/avatar3.png";
import male from '../images/avatar7.png'
import AuthenticationService from '../APIServices/AuthenticationService';
export default class CustomerComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            id: this.props.params.id,
            customer: {},
            permanentAddress:{},
            currentAddress:{}
        }
        this.refresh = this.refresh.bind(this)
     //   this.backClicked=this.backClicked.bind(this)
     
    }


    refresh() {
        CustomerService.getById(this.state.id)
            .then(response => {
                // console.log(response.data)
                this.setState({
                    customer: response.data,
                    permanentAddress:response.data.permanentAddress,
                    currentAddress:response.data.currentAddress
            
                })
            }).catch(error => {
                if (error.response.status === 403 || error.response.status===401) {
                    AuthenticationService.logout()
                    this.props.navigate(`/login`)
                  }
            })
    }

    componentDidMount() {
        this.refresh()
    }
    render() {
        return <>
            <div className="CustomerComponent">
                <div className="container-fluid min-vh-100 bg-transparent">
                    <div className="row px-5 py-4">
                        <div className="card border-0 shadow rounded-3 my-1">
                            <div className="card-body p-0">
                                    <div className="row border-style-none m-0 p-0">
                                        <div className="col-md-3 border-right">
                                            <div className="d-flex flex-column align-items-center text-center p-3 py-5">
                                                <img className="rounded-circle mt-5" src={this.state.customer.gender === "FEMALE" ? female : this.state.customer.gender === "MALE" ? male : ""} width="90px" alt="Unspecified" />
                                                {/* <span className="font-weight-bold">{this.state.customer.firstName + " " + this.state.customer.lastName}</span> */}
                                                <span className='font-weight-bold text-dark  fw-bold'>
                                                    Designation : &nbsp;
                                                    <span className='text-uppercase ml-3'>
                                                 {this.state.customer.designation}
                                                 
                                                </span>
                                                </span>
                                               
                                            </div>
                                        </div>
                                        <div className="col-md-6 border-right">
                                            <div className="p-3 py-5">
                                                {/* <div className="d-flex justify-content-between align-items-center mb-3">
                            <h3 className="card-title text-center  fw-bold fs-25">Edit Profile</h3>
                          </div> */}
                                                <div className='bg-dark text-light text-center p-3 text-uppercase mb-3' style={{ border: "1px solid #000000", padding: "5px", margin: "0px" }}>
                                                    Customer
                                                </div>

                                                <div className="row">
                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" name="firstName" readOnly value={this.state.customer.firstName} placeholder="Enter full name" required className="form-control" />
                                                            <label htmlFor="floatingInput">First Name</label>
                                                        </div>
                                                    </div>


                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" name="lastName" readOnly value={this.state.customer.lastName} placeholder="Enter last name" required className="form-control" />
                                                            <label htmlFor="floatingInput">Last Name</label>
                                                        </div>
                                                    </div>

                                                    <div className="col">
                                                    <div className="form-floating mb-3">
                                                            <input type="text" name="gender" readOnly value={this.state.customer.gender} placeholder="Enter gender" required className="form-control" />
                                                            <label htmlFor="floatingInput">Gender</label>
                                                        </div>
                                                    </div>

                                                </div>



                                                <div className="row">
                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input id='email' type="email" name="email" value={this.state.customer.email}readOnly className="form-control" />
                                                            <label htmlFor="floatingInput">Email</label>
                                                        </div>
                                                    </div>
                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="tel" value={this.state.customer.phone} readOnly name="phone" pattern="[123456789][0-9]{9}" required placeholder="Enter phone number" className="form-control" />
                                                            <label htmlFor="floatingInput">Phone</label>
                                                        </div>
                                                    </div>
                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" name="designation" value={this.state.customer.designation} readOnly placeholder="Enter designation" required className="form-control" id="floatingInputUsername" />
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
                                                            <input type="text" value={this.state.permanentAddress.state} readOnly name="state" placeholder="Enter state" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">State</label>
                                                        </div>
                                                    </div>
                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" value={this.state.permanentAddress.city} readOnly name="city" placeholder="Enter city" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">City</label>
                                                        </div>
                                                    </div>
                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" value={this.state.permanentAddress.district} readOnly name="district" placeholder="Enter district" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">District</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="row">

                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" value={this.state.permanentAddress.pincode}readOnly name="pincode" placeholder="Enter pincode" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">Pincode</label>
                                                        </div>
                                                    </div>

                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" value={this.state.permanentAddress.addressLine} readOnly name="addressLine" placeholder="Enter addressLine" required className="form-control" id="floatingInputUsername" />
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
                                                            <input type="text" value={this.state.currentAddress.current_state} readOnly name="state" placeholder="Enter state" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">State</label>
                                                        </div>
                                                    </div>
                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" value={this.state.currentAddress.current_city} readOnly name="city" placeholder="Enter city" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">City</label>
                                                        </div>
                                                    </div>
                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" value={this.state.currentAddress.current_district} readOnly name="district" placeholder="Enter district" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">District</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="row">

                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" value={this.state.currentAddress.current_pincode} readOnly name="pincode" placeholder="Enter pincode" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">Pincode</label>
                                                        </div>
                                                    </div>

                                                    <div className="col">
                                                        <div className="form-floating mb-3">
                                                            <input type="text" value={this.state.currentAddress.current_addressLine} readOnly name="addressLine" placeholder="Enter addressLine" required className="form-control" id="floatingInputUsername" />
                                                            <label htmlFor="floatingInput">Address Line</label>
                                                        </div>
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
                              
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </>
    }
}