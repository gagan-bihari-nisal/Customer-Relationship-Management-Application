import React, { Component } from 'react'

export default class ErrorComponent extends Component {
  render() {
    return  <>
    <div className="error m-5 p-5 text-center fw-bold  d-flex align-items-center justify-content-center">
        Error Occured! Check the URL and try again later.
    </div>
    </>
  }
}
