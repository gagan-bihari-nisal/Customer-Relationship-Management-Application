import React, { Component } from 'react'
import ManagerAuthService from '../APIServices/ManagerAuthService';
import female from "../images/avatar3.png";
import male from '../images/avatar7.png'
import AuthenticationService from '../APIServices/AuthenticationService';
export default class ProfileComponent extends Component {
  constructor(props) {
    super(props)
    this.state = {
      // firstName: '',
      // lastName: '',
      // gender: '',
      // phone: '',
      manager: {}
    }
    this.refresh = this.refresh.bind(this)
  }

  refresh() {
    ManagerAuthService.getManager()
      .then(response => {
        this.setState({
          manager: response.data
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
      <>


        <div className="ProfileComponent">
          <div className="container-fluid min-vh-100 bg-transparent">
            <div className="row px-5 py-4">
              <div className="card border-0 shadow rounded-3 my-1">
                <div className="card-body p-0">
                  <form onSubmit={this.updateManager}>
                    <div className="row border-style-none m-0 p-0">
                      <div className="col-md-3 border-right">
                        <div className="d-flex flex-column align-items-center text-center p-3 py-5">
                          <img className="rounded-circle mt-5" src={this.state.manager.gender === "FEMALE" ? female : this.state.manager.gender === "MALE" ? male : ""} width="90px" alt="Unspecified" />
                          <span className="font-weight-bold">{this.state.manager.firstName + " " + this.state.manager.lastName}</span>
                          <span className="username text-dark fw-bold">{this.state.manager.username} </span>

                        </div>
                      </div>
                      <div className="col-md-5 border-right">
                        <div className="p-3 py-5">

                          <div className='bg-dark text-light text-center p-3 text-uppercase mb-3' style={{ border: "1px solid #000000", padding: "5px", margin: "0px" }}>
                            Manager Profile
                          </div>

                          <div className="row">
                            <div className="col">
                              <div className="form-floating mb-3">
                                <input type="text" name="firstName" onChange={this.handleChange} value={this.state.manager.firstName} placeholder="Enter full name" required className="form-control" />
                                <label htmlFor="floatingInput">First Name</label>
                              </div>
                            </div>




                          </div>
                          <div className="row">
                            <div className="col">
                              <div className="form-floating mb-3">
                                <input type="text" name="lastName" onChange={this.handleChange} value={this.state.manager.lastName} placeholder="Enter last name" required className="form-control" />
                                <label htmlFor="floatingInput">Last Name</label>
                              </div>
                            </div>
                          </div>
                          <div className="col">
                            <div className="form-floating mb-3">
                              <input type="text" name="gender" value={this.state.manager.gender} placeholder="Enter gender" required className="form-control" />
                              <label htmlFor="floatingInput">Gender</label>
                            </div>
                          </div>

                          <div className="row">

                            <div className="col">
                              <div className="form-floating mb-3">
                                <input type="tel" value={this.state.manager.phone} onChange={this.handleChange} name="phone" pattern="[123456789][0-9]{9}" required placeholder="Enter phone number" className="form-control" />
                                <label htmlFor="floatingInput">Phone</label>
                              </div>
                            </div>
                          </div>



                          {/* <div className=" text-center">
                          <div className="col">
                            <button name='update' type='submit' className="btn btn-success  text-uppercase fw-bold" >
                              Edit Profile
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

      </>
    )
  }
}
