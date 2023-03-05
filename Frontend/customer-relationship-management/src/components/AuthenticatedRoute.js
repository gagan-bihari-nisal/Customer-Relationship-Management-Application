import React, { Component } from 'react'
import AuthenticationService from '../APIServices/AuthenticationService'
import { Navigate } from 'react-router-dom'
export default class AuthenticatedRoute extends Component {
    render() {
      if (AuthenticationService.isUserLoggedIn()) {
        return {...this.props.children}
      } else {
        return <Navigate to="/login" />
      }
    }
  }