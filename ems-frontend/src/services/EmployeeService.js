import axios from "axios";

const REST_API_BASE_URL='http://localhost:8080/api/employees';
export const listEmployee=()=>axios.get(REST_API_BASE_URL);

export const createEmployees=(employee)=>axios.post(REST_API_BASE_URL+'/create',employee)
export const updateEmployees=(id,employee)=>axios.put(REST_API_BASE_URL+`/${id}`,employee)

export const getEmployee=(id)=>axios.get(REST_API_BASE_URL+`/${id}`)
export const removeEmployee=(id)=>axios.delete(REST_API_BASE_URL+`/${id}`)