import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import AuthenticationService from '../APIServices/AuthenticationService';
import { NavLink, Navbar, Nav } from 'react-bootstrap';

export default class HeaderComponent extends Component {
  render() {
    const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
    const active = window.location.pathname;
    return (
      <>
        <div className="HeaderComponent">
          <Navbar collapseOnSelect className='px-2' expand="sm" bg="dark" variant="dark">
            <Navbar.Toggle aria-controls="navbarScroll" data-bs-toggle="collapse" data-bs-target="#navbarScroll" />
            <Navbar.Brand  >CRM APPLICATION</Navbar.Brand>
            <Navbar.Collapse id="navbarScroll">
              <Nav>
                {isUserLoggedIn && <NavLink eventKey="1" active={active === '/welcome' ? true : false} as={Link} to="/welcome">Home</NavLink>}
                {/* <NavLink  eventKey="2" as={Link} to="/about">About</NavLink> */}
                {isUserLoggedIn && <NavLink eventKey="2" active={active === '/add' ? true : false} as={Link} to="/add">Add New Customer</NavLink>}
                {isUserLoggedIn && <NavLink eventKey="3" active={active === '/profile' ? true : false} as={Link} to="/profile">My Profile</NavLink>}

              </Nav>

              {/* <Nav>
                     <NavDropdown
              id="nav-dropdown-dark-example"
              title="My Profile"
              menuVariant="dark"
            >
              <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.2">
                Another action
              </NavDropdown.Item>
              <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="#action/3.4">
                Separated link
              </NavDropdown.Item>
            </NavDropdown>

                     </Nav> */}

              <Nav className="ms-auto" >
                {!isUserLoggedIn && <NavLink eventKey="3" active={active === '/login' ? true : false} as={Link} to='/login'>Login</NavLink>}
                {isUserLoggedIn && <NavLink eventKey="4" active={active === '/logout' ? true : false} onClick={AuthenticationService.logout} as={Link} to="/logout">Logout</NavLink>}

              </Nav>

            </Navbar.Collapse>
          </Navbar>
        </div>

      </>
    )
  }
}
