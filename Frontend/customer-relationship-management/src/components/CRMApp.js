import React, { Component } from 'react'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import withNavigation from './withNavigation';
import LoginComponent from './LoginComponent';
import HeaderComponent from './HeaderComponent';
import WelcomeComponent from './WelcomeComponent';
import ErrorComponent from './ErrorComponent';
import LogoutComponent from './LogoutComponent';
import AddCustomerComponent from './AddCustomerComponent'
import CustomerComponent from './CustomerComponent'
import AuthenticatedRoute from './AuthenticatedRoute';
import withParams from './withParams'
import EditCustomerComponent from './EditCustomerComponent';
import RegisterManagerComponent from './RegisterManagerComponent'
import ProfileComponent from './ProfileComponent';
export default class CRMApp extends Component {
    render() {
        const Login = withNavigation(LoginComponent);
        const Logout = withNavigation(LogoutComponent);
        const Header = withNavigation(HeaderComponent);
        const Welcome = withNavigation(WelcomeComponent);
        const Error = withNavigation(ErrorComponent);
        const AddCustomer = withNavigation(AddCustomerComponent);
        const Customer = withParams(CustomerComponent);
        const EditCustomer = withParams(EditCustomerComponent);
        const RegisterManager = withNavigation(RegisterManagerComponent);
        const Profile = withNavigation(ProfileComponent)
        return (
            <div className='CRMApp'>
                <Router>
                    <Header />

                    <Routes>
                        <Route path="/welcome" element={
                            <AuthenticatedRoute>
                                <Welcome />
                            </AuthenticatedRoute>
                        } />

                        <Route path="/add" element={
                            <AuthenticatedRoute>
                                <AddCustomer />
                            </AuthenticatedRoute>
                        } />


                        <Route path="/profile" element={
                            <AuthenticatedRoute>
                                <Profile />
                            </AuthenticatedRoute>
                        } />


                        <Route path="/view/:id" element={
                            <AuthenticatedRoute>
                                <Customer />
                            </AuthenticatedRoute>
                        } />


                        <Route path="/edit/:id" element={
                            <AuthenticatedRoute>
                                <EditCustomer />
                            </AuthenticatedRoute>
                        } />

                        <Route path="/" element={<Login />}></Route>
                        <Route path="/login"
                            element={<Login />}>
                        </Route>

                        <Route path="/register"
                            element={<RegisterManager />}>
                        </Route>


                        <Route path="/logout" element={
                            <AuthenticatedRoute>
                                <Logout />
                            </AuthenticatedRoute>
                        } />



                        <Route path='*' element={<Error />} > </Route>

                    </Routes>
                </Router>

            </div>
        )
    }
}
