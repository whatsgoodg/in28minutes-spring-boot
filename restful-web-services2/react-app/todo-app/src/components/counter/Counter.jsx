import { useState } from 'react'
import CounterButton from './CounteButton'
import './Counter.css'

export default function Counter({by}){
    const [count, setCount] = useState(0) 

    function incrementCounterFunction(by){
        setCount(count + by)
    }

    function decrementCounterFunction(by){
        setCount(count - by)
    }
    function resetCounter(){
        setCount(0)
    }
    return (
        <>
            <span className="totalCount">{count}</span>
            <CounterButton by={1} incrementMethod={incrementCounterFunction} 
                decrementMethod = {decrementCounterFunction}/>
            <CounterButton by={2} incrementMethod={incrementCounterFunction} 
                decrementMethod = {decrementCounterFunction}/>
            <CounterButton by={5} incrementMethod={incrementCounterFunction} 
                decrementMethod = {decrementCounterFunction}/>
             <button className="resetButton" 
                onClick={resetCounter}
                
                >Reset</button> {/*()을 붙이면 onClick에 함수 호출 결과가 포함되게 됨.*/}

        </>
    )
}
