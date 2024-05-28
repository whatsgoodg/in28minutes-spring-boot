import './Counter.css'
import {PropTypes} from 'prop-types'

export default function CounterButton({by, incrementMethod, decrementMethod}){
    
    //const [count, setCount] = useState(0) // 기본 값을 0으로 설정함.
    //[0, f] 이 반환됨. 두 개의 요소를 가진 배열임.
    // f는 현재 상태를 갱신할 때 사용하는 함수이다.
    
    function incrementCounterFunction(){
        // setCount(count + by) // 상태값을 변경하는 함수를 호출
        incrementMethod(by);
    } 

    function decrementCounterFunction(){
        // setCount(count - by) // 상태값을 변경하는 함수를 호출
        decrementMethod(by);
    } 
    return (
        <div className="Counter">
            <button className="counterButton" 
                onClick={() => incrementMethod(by)}
                
                >+{by}</button> {/*()을 붙이면 onClick에 함수 호출 결과가 포함되게 됨.*/}
            <button className="counterButton" 
                onClick={() => decrementMethod(by)}
                
                >-{by}</button> {/*()을 붙이면 onClick에 함수 호출 결과가 포함되게 됨.*/}
        </div>
    )
}

CounterButton.propTypes = { // by property에 숫자만 넣어야함.
    by : PropTypes.number
}

CounterButton.propTypes = { // 기본값 설정
    by : 1
}