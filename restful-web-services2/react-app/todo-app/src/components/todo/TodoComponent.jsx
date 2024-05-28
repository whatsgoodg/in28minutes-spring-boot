import {useNavigate, useParams} from 'react-router-dom'
import { createTodoApi, retrieveTodoApi, updateTodoApi } from './api/TodoApiService'
import { useAuth } from './security/AuthContext'
import { useEffect, useState } from 'react'
import {Formik, Form, Field, ErrorMessage} from 'formik'
import {moment} from 'moment'

export default function TodoComponent(){
    const {id} = useParams()

    const [description, setDescription] = useState('')
    const [targetDate, setTargetDate] = useState('')

    const authContext = useAuth()
    const navigate = useNavigate()

    const username = authContext.username
    useEffect(
        () => retrieveTodos(),
        [id] // id가 변경될 때마다 새로고침함
    )
    function retrieveTodos(){
        if(id != -1){
            retrieveTodoApi(username, id)
            .then(response => {
                setDescription(response.data.description)
                setTargetDate(response.data.targetDate)
            })
            .catch(error => console.log(error))   
        }
    }

    function onSubmit(values){
        console.log(values)
        const todo = {
            id: id,
            username: username,
            description: values.description,
            targetDate: values.targetDate,
            done: false,
        }
        //console.log(values.targetDate)
        if(id == -1) {
            createTodoApi(username, todo)
            .then(response => {
                console.log(response)
                navigate('/todos')
            })
            .catch(error => console.log(error))
        }
        else {
            updateTodoApi(username, id, todo)
            .then(response => {
                console.log(response)
                navigate('/todos')
            })
            .catch(error => console.log(error))
        }
    }

    function validate(values){
        let errors = {
            // description: 'Enter a valid description',
            // targetDate: 'Enter a valid targetDate'
        }
        if(values.description.length < 5){
            errors.description = 'Enter at least 5 characters'
        }
        if(values.targetDate == null || values.targetDate=='' /*|| !moment(values.targetDate).isValid()*/ ){
            errors.targetDate = 'Enter a target date'
        }
        console.log(values)
        return errors
    }
    return (
        <div className="container">
            <h1>Enter Todo Details</h1>
            <div>
            {/*jsx를 반환하는 함수를 정의 */}
            <Formik initialValues={{description, targetDate}}
                enableReinitialize={true}
                onSubmit = {onSubmit}
                validate = {validate}
                validateOnChange = {false}
                validateOnBlur = {false}
            > 
                {
                    (props) => (
                        <Form>
                            <ErrorMessage
                                name="description"
                                component="div"
                                className="alert alert-warning" 
                            />

                            <ErrorMessage
                                name="targetDate"
                                component="div"
                                className="alert alert-warning" 
                            />
                            <fieldset className="form-group">
                                <label htmlFor="">Description</label>
                                <Field type="text" className="form-control" name="description"/>
                            </fieldset>
                            <fieldset className="form-group">
                                <label htmlFor="">Target Date</label>
                                <Field type="date" dateFormat="dd-MM-yyyy" className="form-control" name="targetDate"/>
                            </fieldset>
                            <div>
                                <button className="btn btn-success m-5" type="submit">
                                    Save
                                </button>
                            </div>
                        </Form>
                    )
                }
                </Formik>
            </div>
        </div>
    )
}