import React, { Component } from 'react'
import { LinearProgress } from '@mui/material'
import ManagerAuthService from '../APIServices/ManagerAuthService'
export default class RegisterManagerComponent extends Component {

  constructor(props) {
    super(props)
    this.state = {
      errorOccured: false,
      errorMsg: '',
      isSuccess: false,
      successMsg: '',
      firstName: '',
      lastName: '',
      gender: 'MALE',
      phone: '',
      username: '',
      password: '',
      cpassword: ''
    }
    this.handleChange = this.handleChange.bind(this)
    this.registerManager = this.registerManager.bind(this)
  }
  handleChange = (event) => {
    this.setState({
      [event.target.name]: event.target.value
    })
  }

  registerManager = (e) => {
    this.setState({ showProgress: true })
    e.preventDefault()
    console.log("register ")
    //  console.log(this.state)
    if (this.state.password !== this.state.cpassword) {
      this.setState({
        errorOccured: true,
        isSuccess: false,
        errorMsg: 'Password did not match.'
      })
      this.setState({ showProgress: false })

    } else {
      const firstName = this.state.firstName
      const lastName = this.state.lastName
      const gender = this.state.gender
      const phone = this.state.phone
      const username = this.state.username
      const password = this.state.password
     // const cpassword = this.state.cpassword
      ManagerAuthService.registerManager(firstName, lastName, gender, phone, username, password)
        .then(response => {
          this.setState({
            isSuccess: true,
            errorOccured: false
          })
          this.setState({ showProgress: false })
        }).catch(error => {
          this.setState({
            errorOccured: true,
            isSuccess: false,
            errorMsg: error.response.data.message
          })
          this.setState({ showProgress: false })
        })
    }

  }

  render() {
    return <>
      <div className="RegisterManagerComponent">
        {
          this.state.showProgress && <LinearProgress color='success' />
        }

        <div className="container-fluid min-vh-100 py-3 px-5">

          <div className="row">
            <div className="col-12  ">
              <div className="card border-0 shadow rounded-3 my-5 ">

                <div className="card-body p-4 p-sm-5">
                  <div className='bg-dark text-light text-center p-3 text-uppercase mb-3' style={{ border: "1px solid #000000", padding: "5px", margin: "0px" }}>
                    Manager Registration
                  </div>
                  {/* <h3 className="card-title text-center mb-4 fw-bold text-uppercase">Register</h3> */}
                  <form onSubmit={this.registerManager}>
                    {this.state.isSuccess && <div className="alert alert-success text-center">
                      Registration Successful
                    </div>}

                    {this.state.errorOccured && <div className="alert alert-warning text-center">
                      {this.state.errorMsg}
                    </div>}
                    <div className="row">
                      <div className="col-sm-12 col-md-6 col-lg-3">
                        <div className="form-floating mb-3">
                          <input name='firstName' required type="text" onChange={this.handleChange} value={this.state.firstName} className="form-control" placeholder="Enter First Name" />
                          <label for="floatingInput">First Name</label>
                        </div>
                      </div>
                      <div className="col-sm-12 col-md-6 col-lg-3">
                        <div className="form-floating mb-3">
                          <input name='lastName' required type="text" onChange={this.handleChange} value={this.state.lastName} className="form-control" placeholder="Enter Last Name" />
                          <label for="floatingInput">Last Name</label>
                        </div>
                      </div>
                      <div className="col-sm-12 col-md-6 col-lg-3">
                        <div className="form-floating mb-3">
                          <select className="form-select" aria-label="Gender" onChange={this.handleChange} value={this.state.gender} name="gender" >
                            <option value="MALE">MALE</option>
                            <option value="FEMALE">FEMALE</option>
                            <option value="OTHERS">OTHERS</option>
                          </select>
                          <label for="floatingInput">Gender</label>
                        </div>

                      </div>
                      <div className="col-sm-12 col-md-6 col-lg-3">
                        <div className="form-floating mb-3">
                          <input type="tel" pattern="[123456789][0-9]{9}" onChange={this.handleChange} value={this.state.phone} name="phone" required placeholder="Enter phone number" className="form-control" />
                          <label for="floatingInput">Phone</label>
                        </div>
                      </div>

                    </div>


                    <div className="row">

                      <div className="col-sm-12 col-md-6 col-lg-3">
                        <div className="form-floating mb-3">
                          <input id='username' name='username' required type="text" onChange={this.handleChange} value={this.state.username} className="form-control" placeholder="Enter your username" />
                          <label for="floatingInput">Username</label>
                        </div>
                      </div>
                      <div className="col-sm-12 col-md-6 col-lg-3">
                        <div className="form-floating mb-3">
                          <input name='password' required type="password" onChange={this.handleChange} value={this.state.password} className="form-control" placeholder="Enter your password" />
                          <label for="floatingInput">Password</label>
                        </div>
                      </div>
                      <div className="col-sm-12 col-md-6 col-lg-3">
                        <div className="form-floating mb-3">
                          <input name='cpassword' required type="password" onChange={this.handleChange} value={this.state.cpassword} className="form-control" placeholder="Confirm your password" />
                          <label for="floatingInput">Confirm Password</label>
                        </div>
                      </div>
                    </div>



                    <hr className="my-4" />
                    <div className="row">
                      <div className="col">
                        <center>
                          <button id='submit' className="btn btn-success btn-login text-uppercase fw-bold px-5" type="submit">Register Manager</button>
                        </center>
                      </div>
                    </div>

                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div >
    </>
  }
}
