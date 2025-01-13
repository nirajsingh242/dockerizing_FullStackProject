import React,{useEffect, useState} from 'react'
import { listEmployee, removeEmployee} from '../services/EmployeeService'
import { useNavigate } from 'react-router-dom';
//rafce ->react arrrow funtion component export
const ListEmployeeComponent = () => {
    const navigate=useNavigate();

    const [employees,setEmployees]=useState([]);

    useEffect(()=>{
        getAllEMployees()},[])

function getAllEMployees()
{
    listEmployee().then((response)=>{setEmployees(response.data)}).catch(error=>{console.error(error)})
}

function addNewEmployee()
{
    navigate('/add-employee')
}

function updateEmployee(id)
{
    navigate(`/edit-employee/${id}`)
}

function deleteEmployee(id)
{
    removeEmployee(id).then((response)=>{console.log("successFully deleted"+response);
        getAllEMployees();
    }).catch(error=>{console.error(error)});

}


  return (
    <div className='container'>
        <h2 className='text center' >List of Employee</h2>
        <button className='btn btn-primary mb-2' onClick={addNewEmployee}>Add Employee</button>
        <table className='table table-stripped table-bordered'>
            <thead>
                <tr>
                <th> Id</th>
                    <th> first name</th>
                    <th> last name</th>
                    <th> email id</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {
                    employees.map(employee=>
                    <tr key={employee.id}>
                        <td>{employee.id}</td>
                        <td>{employee.firstName}</td>
                        <td>{employee.lastName}</td>
                        <td>{employee.email }</td>
                        <td>
                            <button className='btn btn-info' onClick={()=>updateEmployee(employee.id)}>Update</button>
                            &nbsp;&nbsp;
                            <button className='btn btn-danger' onClick={()=>deleteEmployee(employee.id)}>delete</button>
                        </td>
                    </tr>)
                }
                
            </tbody>
        </table>
        
 </div>
  )
}

export default ListEmployeeComponent