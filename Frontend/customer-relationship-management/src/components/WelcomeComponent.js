import React, { Component } from 'react'
import CustomerService from '../APIServices/CustomerService'
import AuthenticationService from '../APIServices/AuthenticationService'

import { MDBTable, MDBTableHead, MDBTableBody } from 'mdbreact';
export default class WelcomeComponent extends Component {

  constructor(props) {
    super(props)
    this.state = {
      errorOccured: false,
      errorMsg: '',
      isSuccess: false,
      successMsg: '',
      customers: [],
      filter: 'firstName',
      filterVal: '',
    }
    this.refresh = this.refresh.bind(this)
    this.viewClicked = this.viewClicked.bind(this)
    this.handleDelete = this.handleDelete.bind(this)
    this.editClicked = this.editClicked.bind(this)
    this.handleChange = this.handleChange.bind(this)
  }

  handleChange = (event) => {
    this.setState({
      [event.target.name]: event.target.value
    })

  }




  editClicked = (id) => {
    // console.log(id);
    this.props.navigate(`/edit/${id}`)
  }

  handleDelete = (id) => {
    CustomerService.deleteById(id)
      .then(response => {
        this.setState({
          isSuccess: true,
          errorOccured: false,
          successMsg: 'Customer Deleted'
        })
        this.refresh()
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
  }

  viewClicked(id) {
    this.props.navigate(`/view/${id}`)
  }



  componentDidMount() {
    this.refresh()
  }



  refresh() {
    CustomerService.getAll()
      .then(response => {
        this.setState({
          customers: response.data
        })
      }).catch(error => {
        if (error.response.status === 403 || error.response.status === 401) {
          AuthenticationService.logout()
          this.props.navigate(`/login`)
        }
      })
  }


  render() {
    return (
      <>
        <div className="WelcomeComponent">
          <div className="container-fluid min-vh-100 py-3 px-5">
            <div className="card border-0 shadow rounded-3 my-5">

              <div className="card-title p-3 " style={{ border: "0px solid #000000" }}>
                {this.state.isSuccess && <div className="alert alert-success text-center">
                  {this.state.successMsg}
                </div>}

                {this.state.errorOccured && <div className="alert alert-warning text-center">
                  {this.state.errorMsg}
                </div>}



                <form onSubmit={this.handleFilter}>

                  <div className="row py-3">
                    <div className="col-lg-6 col-md-6 col-sm-12 mt-3">
                      <select className="form-select" onChange={this.handleChange} value={this.state.filter} name="filter" >
                        <option value="firstName">First Name</option>
                        <option value="lastName">Last Name</option>
                      </select>

                    </div>
                    <div className="col-lg-6 col-md-6 col-sm-12 mt-3">
                      <input type="text" name="filterVal" onChange={this.handleChange} value={this.state.filterVal} placeholder="Type something to filter" required className="form-control" />
                    </div>
                    {/* <div className="col">
                      <button type='submit' className='btn btn-success' >Filter</button>
                    </div> */}
                  </div>
                </form>





                <div >
                  <div>

                  </div>
                </div>
                <div className='bg-dark text-light text-center p-3 text-uppercase ' style={{ border: "1px solid #000000", padding: "5px", margin: "0px" }}>
                  Customers
                </div>


              </div>
              <div className="card-body">

                <MDBTable hover responsive  >
                  <MDBTableHead >
                    <tr className='table-dark'>
                      <th scope='col'>Id</th>
                      <th scope='col'>First Name</th>
                      <th scope="col">Last Name</th>
                      <th scope="col">Gender</th>
                      <th scope="col">Designation</th>
                      <th scope="col">Email</th>
                      <th scope="col">Phone</th>
                      <th scope='col' colSpan={3} className='text-center'>Actions</th>
                    </tr>
                  </MDBTableHead>
                  <MDBTableBody>
                    {this.state.customers.length === 0 && <td colSpan={8} className='text-center fw-bold'>No customers found to display.</td>}

                    {this.state.customers
                      .filter(f => {
                        if (this.state.filterVal.length === 0)
                          return true

                        if ((this.state.filter === 'lastName' && f.lastName.toLowerCase() === this.state.filterVal.toLowerCase().trim()))
                          return true;
                        if (this.state.filter === 'firstName' && f.firstName.toLowerCase() === this.state.filterVal.toLowerCase().trim())
                          return true;
                        
                        return false;

                      }
                      )
                      .map((item, i) => {
                        return <tr key={i}>
                          <td>{item.customerId}</td>
                          <td>{item.firstName}</td>
                          <td>{(item.lastName)}</td>
                          <td>{(item.gender)}</td>
                          <td>{(item.designation)}</td>
                          <td id='email'>{item.email}</td>
                          <td>{(item.phone)}</td>
                          <td>
                            <div className="btn btn-sm btn-success" onClick={(e) => this.viewClicked(item.customerId)}>
                              View
                            </div>
                          </td>
                          <td>
                            <div className="btn btn-sm btn-primary" onClick={(e) => {
                              this.editClicked(item.customerId)
                            }}>
                              Edit
                            </div>
                          </td>
                          <td>
                            <div className="btn btn-sm btn-danger" onClick={(e) => {
                              this.handleDelete(item.customerId)
                            }}>
                              Delete
                            </div>
                          </td>
                        </tr>
                      })}
                  </MDBTableBody>
                </MDBTable>






              </div>
            </div>

          </div>
        </div>
      </>
    )
  }
}
