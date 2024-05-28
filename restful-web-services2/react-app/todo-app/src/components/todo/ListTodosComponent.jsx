import { useEffect, useState } from "react"
import { deleteTodoApi, retrieveAllTodosForUsernameApi } from "./api/TodoApiService"
import { useAuth } from "./security/AuthContext"
import { useNavigate } from "react-router-dom"

function ListTodosComponent(){

    const authContext = useAuth()

    const username = authContext.username

    const navigate = useNavigate()

    const [todos, setTodos] = useState([])

    const [message, setMessage] = useState(null)

    useEffect( // 컴포넌트가 준비되었을 때 Todo가 렌더링됨. 더 공부해야함.
        () => refreshTodos(), []
    )
    // useEffect( () => {authContext.addingToken()}, [])

    function refreshTodos(){
        retrieveAllTodosForUsernameApi(username)
            .then(response => {
                console.log(response)
                setTodos(response.data)
                }
            )
            .catch(error => console.log(error))
    }
    function deleteTodo(id){
        deleteTodoApi(username, id)   
        .then(
            () =>{
                setMessage(`Delete of todo with id = ${id} successful`)
                refreshTodos()
            }
            //1: Display message
            //2: Update Todos list
        )
    }

    function updateTodo(id){
       navigate(`/todo/${id}`)
    }

    function addNewTodo(){
        navigate(`/todo/-1`)
     }
    return(
        <div className="container">
            <h1>Things You Want To Do!</h1>
            {message && <div className="alert alert-warning">{message}</div>}
            <div>
                <table className="table">
                    <thead>
                        <tr>
                            <th>description</th>
                            <th>is Done?</th>
                            <th>Target Date</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                    {
                        todos.map(
                            todo => (                        
                            <tr key={todo.id}>
                                <td>{todo.description}</td>
                                <td>{todo.done.toString()}</td>
                                <td>{todo.targetDate.toString()}</td>
                                <td><button className="btn btn-warning" 
                                                onClick={() => deleteTodo(todo.id)}>Delete</button></td>
                                <td><button className="btn btn-success" 
                                                onClick={() => updateTodo(todo.id)}>Update</button></td>
                            </tr>
                            )
                        )
                    }
                    </tbody>

                </table>
            </div>
            <div className="btn btn-success m-5" onClick={addNewTodo}>Add New Todo</div>
        </div>
    )
}

export default ListTodosComponent