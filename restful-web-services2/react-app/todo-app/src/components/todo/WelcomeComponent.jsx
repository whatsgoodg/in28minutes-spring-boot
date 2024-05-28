import {useParams, Link} from 'react-router-dom'
import { useState } from 'react'
import { retrieveHelloWorldPathVariable } from './api/HelloWorldApiService'
import { useAuth } from './security/AuthContext'

function WelcomeComponent(){

    const {username} = useParams() // URN의 parameter를 받아옴

    const authContext = useAuth()
    const [message, setMessage] = useState(null)
    function callHelloWorldRestApi(){

        retrieveHelloWorldPathVariable('Ranaga', authContext.token)
            .then(  // REST API가 성공적으로 호출됐다면
                (response) => successfulResponse(response)
            )
            .catch((error) => errorResponse(error))
            .finally(() => console.log('cleanup'))
    }
    function successfulResponse(response){
        console.log(response)
        setMessage(response.data.message)
    }
    function errorResponse(error){
        console.log(error)
    }
    return(
        <div className="WelcomeComponent">
            <h1>Welcome to {username}</h1>
            <div>
                Manage You todos. - <Link to="/todos">Go here</Link>
            </div>
            <div>
                <button className="btn btn-success m-5" onClick={callHelloWorldRestApi}>
                    Call Hello World
                </button>
            </div>
            <div className="text-info">{message}</div>
        </div>
    )
}

export default WelcomeComponent