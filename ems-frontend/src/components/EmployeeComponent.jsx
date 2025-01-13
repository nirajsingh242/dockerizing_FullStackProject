import React, { useEffect, useState } from 'react';
import { createEmployees, getEmployee, updateEmployees} from '../services/EmployeeService'
import { useNavigate, useParams } from 'react-router-dom';
const EmployeeComponent = () => {

 const[firstName,setFirstName]=useState('')
 const[lastName,setlastName]=useState('')
 const[email,setEmail]=useState('')
const navigator=useNavigate()
const {id}=useParams();
const [errors,setErros] =useState(
    {
        firstName:'',
        lastName:'',
        email:''
    }
)

 const saveOrUpdateEmployee=(e)=>
 {
    debugger;
    e.preventDefault();
    if(validateForm())
    {
    const employee={firstName,lastName,email}
    console.log('Form Data Submitted:', employee);
    if(id)
    {//updatinf a employee
        updateEmployees(id,employee).then((response)=>{
            console.log(response.data)
            navigator('/employees')
        })

    }else{
        //creating a employee
        createEmployees(employee).then((response)=>{
            console.log(response.data)
            navigator('/employees')
        })
    }
 
}
}

useEffect(()=>{
    if(id)
    {
        debugger;
            getEmployee(id).then((response) =>{
                setFirstName(response.data.firstName);
                setlastName(response.data.lastName);
                setEmail(response.data.email);
            }).catch(error=>{
                console.error(error)
            })
    }
},[id])

 function validateForm()
 {
    debugger;
    let valid=true;
    const errorsCopy={...errors}
    if(firstName.trim())
    {
        errorsCopy.firstName='';
    }else
    {
        valid=false;
        errorsCopy.firstName='FirstName is required';
    }

    if(lastName.trim())
        {
            errorsCopy.lastName='';
        }else
        {
            valid=false;
            errorsCopy.lastName='LastName is required';
        }

        
    if(email.trim())
        {
            errorsCopy.email='';
        }else
        {
            valid=false;
            errorsCopy.email='Email is required';
        }

        setErros(errorsCopy);
        return valid;
 }

 function pageTitle()
 {
    debugger;
    if(id)
    {
        return  <h2 className='text-center'>Edit Employee</h2>
    }else{
        return  <h2 className='text-center'>Add Employee</h2>
    }
 }

  return (
    <div className='container'>
        <div className='row'>
        <div className='card'>
           {
            pageTitle()
           }
            <div className='card-body'>
            <form >
      <div className="form-group mb-2">
        <label htmlFor="firstName" className="form-label">First Name</label>
        <input
          type="text"
          className={`form-control ${errors.firstName?'is-invalid':''}`}
          id="firstName"
          name="firstName"
          value={firstName}
          onChange={(e)=>setFirstName(e.target.value)}
        />
        {errors.firstName && <div className='invalid-feedback'>{errors.firstName}</div>}
      </div>

      <div className="form-group mb-2">
        <label htmlFor="lastName" className="form-label">Last Name</label>
        <input
          type="text"
          className={`form-control  ${errors.lastName?'is-invalid':''}`}
          id="lastName"
          name="lastName"
          value={lastName}
          onChange={(e)=>setlastName(e.target.value)}
        />
        {errors.lastName && <div className='invalid-feedback'>{errors.lastName}</div>}
      </div>

      <div className="form-group mb-2">
        <label htmlFor="email" className="form-label">Email</label>
        <input
          type="email"
          className={`form-control  ${errors.email?'is-invalid':''}`}
          id="email"
          name="email"
          value={email}
          onChange={(e)=>setEmail(e.target.value)}
        />
          {errors.email && <div className='invalid-feedback'>{errors.email}</div>}
      </div>

      <button type="submit" className="btn btn-primary" onClick={saveOrUpdateEmployee}>Submit</button>
    </form>
            </div>
        </div>
    </div>
    </div>
  );
  
}

export default EmployeeComponent